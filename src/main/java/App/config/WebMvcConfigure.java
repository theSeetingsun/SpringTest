package App.config;

import App.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
    @Autowired
    LoginCheckInterceptor loginCheckInterceptor = new LoginCheckInterceptor();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //只要是/static目录下的静态资源都可以直接访问   放行
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")             //拦截所有请求
                .excludePathPatterns("/login", "/index", "/userList","/user/userListPage", "/projectList","/technicianList",
                        "/basicInformation","/project/getProinfosPage","/static/**");    //要放行的请求
    }*/
}