package cn.tedu.store.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store.interceptor.LoginInterceptor;


/**
 * 登录拦截器配置类
 * @author soft01
 *
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
	   //创建；拦截器
      HandlerInterceptor interceptor = new LoginInterceptor();
		
       //创建白名单
        List<String> excludePaths = new ArrayList<>();
       excludePaths.add("/js/**");
       excludePaths.add("/css/**");
       excludePaths.add("/images/**");
       excludePaths.add("/bootstrap3/**");
       excludePaths.add("/web/register.html");
       excludePaths.add("/web/login.html");
       excludePaths.add("/users/reg");
       excludePaths.add("/users/login");
       //注册拦截器 并设置黑白名单
      registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(excludePaths);
      
	}

	
	
	
	
}
