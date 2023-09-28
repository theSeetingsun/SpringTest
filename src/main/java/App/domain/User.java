package App.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private Long id;
    private String nickname;
    private String openid;
    private String imageurl;
    private String sessionkey;
    private String token;
    private String username;
    private String phone;
    private String password;

}
