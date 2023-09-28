package App.service.impl;

import App.dto.LoginDto;
import App.dao.UserMapper;
import App.domain.User;
import App.service.IUserservice;
import App.utils.jwt.JwtUtils;
import App.utils.jwt.PayLoadData;
import App.utils.jwt.RsaUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserserviceImpl extends ServiceImpl<UserMapper, User> implements IUserservice {
    @Override
    public Map<String, Object> login(LoginDto loginDto) {
        //通过用户名查询用户对象
        User user = baseMapper.selectByUsernameOrPhone(loginDto.getUsername());

        Assert.isTrue(user != null, "用户名不存在！");

        //比较密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        Assert.isTrue(matches, "密码错误");
        return loginSuccess(user);
    }

    private Map<String, Object> loginSuccess(User user) {
        Map<String, Object> map = new HashMap<>();

        try {
            PayLoadData payLoadData = new PayLoadData();
            user.setPassword(null);
            payLoadData.setUser(user);
            //私钥加密
            PrivateKey privateKey = RsaUtils.getPrivateKey(JwtUtils.class.getClassLoader()
                    .getResource("hrm_auth_rsa").getFile());
            String usertoken = JwtUtils.generateTokenExpireInMinutes(payLoadData, privateKey, 720);
            map.put("loginuser", user);
            map.put("usertoken", usertoken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
