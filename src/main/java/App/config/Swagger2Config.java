package App.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
     * Swagger2配置类
     * 在与spring boot集成时，放在与Application.java同级的目录下。
     * 通过@Configuration注解，让Spring来加载该类配置。
     * 再通过@EnableSwagger2注解来启用Swagger2。
     */
    @Configuration
    @EnableSwagger2
    public class Swagger2Config {
    
        /**
         * 创建API应用
         * apiInfo() 增加API相关信息
         * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
         * 本例采用指定扫描的包路径来定义指定要建立API的目录。
         * 
         * @return
         */
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("App.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }
    
        /**
         * 创建该API的基本信息（这些基本信息会展现在文档页面中）
         * 访问地址：http://项目实际地址/swagger-ui.html
         * @return
         */
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("SpringBoot项目 API接口文档")
                    .description("SpringBoot项目 API接口文档")
                    .termsOfServiceUrl("http://localhost:8080/")
                    .contact(new Contact("GH",null,"2766905653@qq.com"))
                    .version("1.0")
                    .build();
        }
    }