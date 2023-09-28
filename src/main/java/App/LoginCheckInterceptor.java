package App;

import App.utils.JsonResult;
import App.utils.jwt.LoginContext;
import App.utils.jwt.PayLoadData;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    /**
     * 前置拦截：在请求到达Controller目标接口方法之前执行，用于拦截校验，不满足要求的请求将被拦截
     * @param request
     * @param response
     * @param handler
     * @return true表示放行，false表示拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //1.获取请求头的值
            String usertoken = request.getHeader(LoginContext.JWT_TOKEN_HEADER_NAME);
            System.out.println(usertoken);
            if(!StringUtils.hasText(usertoken)){
                System.out.println("请求头中没有token，拒绝访问。。。。。");
                //返回json数据 （JsonResult）
                handleAccessDenied(response,request);
            }

            //2.解析jwttoken
            PayLoadData payLoadData = LoginContext.getCurrentUserInfo(usertoken);
            if(payLoadData == null || payLoadData.getUser() == null){
                System.out.println("token已失效，拒绝访问。。。。。");
                //返回json数据 （JsonResult）
                handleAccessDenied(response,request);
            }

            //放行 允许访问目标接口资源
            System.out.println("token正常，允许访问。。。。。");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("token已过期，拒绝访问。。。。。");
            //返回json数据 （JsonResult）
            handleAccessDenied(response,request);
        }
        return false;
    }

    /**
     * 返回json数据 （JsonResult）
     * 返回校验失败的消息
     * @param response
     */
    private void handleAccessDenied(HttpServletResponse response,HttpServletRequest request) {
        try {
            String xRequestedwith = request.getHeader("X-Requested-With");

            if (StringUtils.hasText(xRequestedwith) && "XMLHttprequest".equals(xRequestedwith)) {
                JsonResult jsonResult = JsonResult.error("noLogin");
                //将对象转化为json格式的字符串：下面三句代码就是@ResponseBody注解的底层代码
                String jsonString = JSON.toJSONString(jsonResult);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(jsonString);
            } else {
                response.sendRedirect("/login");
            }
            response.sendRedirect("http://127.0.0.1:8080/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
