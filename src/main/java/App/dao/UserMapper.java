package App.dao;

import App.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过用户名查找对象
     * @param username
     * @return
     */
    User selectByUsernameOrPhone(String username);
}
