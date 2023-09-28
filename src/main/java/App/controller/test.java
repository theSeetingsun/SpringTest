package App.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 是一个组合注解
 *  @Controller：表示当前类就是一个Controller类
 *  @ResponseBody：表示响应消息体采用json格式
 *
 * @RequestMapping 写在类上面，表示给当前类指定一个地址映射，写在方法上面，表示给当前方法再指定一个地址映射
 *  应该如何才能访问到这个方法？
 *      就是两个地址进行拼接：/App.test.App.test/App.test.App.test
 *
 */
@RestController
@RequestMapping("/test")
public class test {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        System.out.println("TestController的test方法被访问了。。。");
        return "哎哟 不错哟。";
    }

    @RequestMapping("/test2")
    public String test2(){
        System.out.println("TestController的test2方法被访问了。。。");
        return "哎哟 不错哟。222222";
    }
}