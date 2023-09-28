package App.controller;

import App.dto.LoginDto;
import App.service.IUserservice;
import App.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 后台管理系统跳转页面使用的controller
 * 只能加@Controller注解，不能加@RestController注解
 * 原因：
 *
 * @RestController是一个组合注解，包含了@Controller和@ResponseBody两个注解， 而@ResponseBody注解的作用是当前方法或者当前类中的所有方法都采用响应消息体的方式传输json格式字符串给前端，而不走视图解析器【也就是不跳转页面】
 * 如果没有@ResponseBody注解的方法，它底层默认采用请求转发的方式跳转页面。
 * <p>
 * 转发与重定向的区别？？？
 */
@Controller
public class AdminController {
    @Autowired
    private IUserservice userservice;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody LoginDto loginDto) {
        try {
            System.out.println(loginDto);
            Map<String, Object> map = userservice.login(loginDto);
            return JsonResult.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("发生错误！" + e.getMessage());
        }
    }


    @RequestMapping("/index")
    public String toIndex() {
        //这里要求返回的是html文件路径，thymeleaf会自动拼接前缀和后缀，classpath:/templates/index.html
        return "index";
    }
    @RequestMapping("/userList")
    public String toUserList() {
        //这里要求返回的是html文件路径，thymeleaf会自动拼接前缀和后缀，classpath:/templates/index.html
        return "userList";
    }
    @RequestMapping("/projectList")
    public String toProjectList() {
        //这里要求返回的是html文件路径，thymeleaf会自动拼接前缀和后缀，classpath:/templates/index.html
        return "projectList";
    }
    @RequestMapping("/technicianList")
    public String totechnicianList() {
        //这里要求返回的是html文件路径，thymeleaf会自动拼接前缀和后缀，classpath:/templates/index.html
        return "technicianList";
    }
    @RequestMapping("/basicInformation")
    public String tobasicInformation() {
        //这里要求返回的是html文件路径，thymeleaf会自动拼接前缀和后缀，classpath:/templates/index.html
        return "basicInformation";
    }

}