package App.service;

import App.dto.LoginDto;
import App.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface IUserservice extends IService<User> {


    Map<String, Object> login(LoginDto loginDto);
}
