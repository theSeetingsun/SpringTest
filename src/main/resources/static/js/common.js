//前端访问后端接口的统一地址前缀
var ctx = "http://127.0.0.1:8080/";
//项目中的图片地址前缀
var ctxImg = "http://127.0.0.1:8080/static/img/";

layui.use(['form','layer'],function () {
    var $ = layui.$;
    var form = layui.form;
    var layer = layui.layer;
    // 统一配置token请求头
    $(document).ajaxSend(function(event, jqxhr, settings) {
        let usertoken = localStorage.getItem("usertoken");
        if(usertoken){
            jqxhr.setRequestHeader('usertoken', usertoken);
        }
    })
});