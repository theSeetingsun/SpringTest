package App.utils.jwt;


import App.domain.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.ExpiredJwtException;

import java.awt.*;
import java.security.PublicKey;
import java.util.List;

/**
 * 登录上下文工具类：获取当前登录用户信息
 *  如果是后台管理系统用户，则还额外包含他所属的店铺id
 */
public class LoginContext {
    public static final String JWT_TOKEN_HEADER_NAME = "usertoken";

    /**
     * 获取当前登录用户信息的PayLoadData，里面包含了LoginInfo对象、用户的权限列表、用户拥有的菜单集合
     * @return
     */
    public static PayLoadData getCurrentUserInfo(String jwtToken){
        try {
            //公钥解密  解析token
            PublicKey publicKey = RsaUtils.getPublicKey(JwtUtils.class.getClassLoader()
                    .getResource("hrm_auth_rsa.pub").getFile());
            Payload<PayLoadData> infoFromToken = JwtUtils.getInfoFromToken(jwtToken, publicKey, PayLoadData.class);

            if(infoFromToken != null){
                return infoFromToken.getUserInfo();
            }
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw  new RuntimeException("Token已过期，请重新登录！");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前登录用户信息
     * @param jwtToken
     * @return
     */
    public static JSONObject getCurrentUser(String jwtToken){
        PayLoadData payLoadData = getCurrentUserInfo(jwtToken);
        if(payLoadData != null){
            return (JSONObject) payLoadData.getUser();
        }
        return null;
    }
    /**
     * 获取当前登录用户信息 user_id
     * @param jwtToken
     * @return
     */
    public static Long getCurrentUserId(String jwtToken){
        PayLoadData payLoadData = getCurrentUserInfo(jwtToken);
        if(payLoadData != null){
            JSONObject jsonObject = (JSONObject) payLoadData.getUser();
            return jsonObject.getLong("userId");
        }
        return null;
    }

    /**
     * 获取当前登录用户信息 后台用户所属的店铺id
     *  思想：要想获取到店铺id，登录成功的处理那里就必须先保存店铺id
     * @param jwtToken
     * @return
     */


    /**
     * 获取当前登录用户的权限列表
     * @param jwtToken
     * @return
     */
    public static List<String> getCurrentUserPermissions(String jwtToken){
        PayLoadData payLoadData = getCurrentUserInfo(jwtToken);
        if(payLoadData != null){
            return payLoadData.getPermissions();
        }
        return null;
    }

    /**
     * 获取当前登录用户的菜单
     * @param jwtToken
     * @return
     */
    public static List getCurrentUserMenus(String jwtToken){
        PayLoadData payLoadData = getCurrentUserInfo(jwtToken);
        if(payLoadData != null){
            return payLoadData.getMenus();
        }
        return null;
    }



}
