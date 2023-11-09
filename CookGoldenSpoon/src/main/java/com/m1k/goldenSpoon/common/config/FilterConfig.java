package com.m1k.goldenSpoon.common.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.m1k.goldenSpoon.common.filter.AdminFilter;
import com.m1k.goldenSpoon.common.filter.LoginFilter;


@Configuration
public class FilterConfig {

	@Bean // 해당 메서드에서 반환된 객체를 bean으로 등록
	public FilterRegistrationBean<LoginFilter> loginFilter(){
		//FilterRegistrationBean : 필터 등록 Bean
		
		FilterRegistrationBean<LoginFilter> filter
			= new FilterRegistrationBean<>();
		filter.setFilter(new LoginFilter() ); // 사용할 필터 객체 추가
		
		String[] filteringUrl = {"/my_page/*", "/editBoard/*"};
		
		// Arrays.asList(배열) : 배열 -> List로 변환
		filter.setUrlPatterns(Arrays.asList(filteringUrl));
		
		filter.setName("loginFilter");
		
		filter.setOrder(1);
		
		return filter;
	};
	
	

	@Bean // 해당 메서드에서 반환된 객체를 bean으로 등록
	public FilterRegistrationBean<AdminFilter> adminFilter(){
		//FilterRegistrationBean : 필터 등록 Bean
		
		FilterRegistrationBean<AdminFilter> filter
			= new FilterRegistrationBean<>();
		filter.setFilter(new AdminFilter() ); // 사용할 필터 객체 추가
		
		List<String> filteringUrl = new ArrayList<>();
		filteringUrl.add("/admin/*");
		
		filter.setUrlPatterns(filteringUrl); 
		
		filter.setName("AdminFilter");
		
		filter.setOrder(2);
		
		return filter;
	};
	
}
