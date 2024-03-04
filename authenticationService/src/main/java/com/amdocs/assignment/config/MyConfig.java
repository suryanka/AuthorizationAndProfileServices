package com.amdocs.assignment.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;


import com.amdocs.assignment.service.CustomUserDetailsServiceImpl;



@Configuration
public class MyConfig  {
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	
//	 @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeHttpRequests((authz) -> authz
//	                .anyRequest().authenticated()
//	            )
//	            .httpBasic(withDefaults());
//	        return http.build();
//	    }
//	 
//
//	    
//	    @Bean
//	    public InMemoryUserDetailsManager userDetailsService() {
//	        UserDetails user = Use
//	        return new InMemoryUserDetailsManager(user);
//	    }
	
//	public MyConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthServiceImpl authServiceImpl) {
//		super();
//		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//		this.authServiceImpl = authServiceImpl;
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
//		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
        
        return http.csrf(AbstractHttpConfigurer :: disable)
        		.authorizeHttpRequests(
        				req -> req
        				//.dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
        				.requestMatchers("/assignment/login").permitAll()
        				//.requestMatchers("/assignment/register").permitAll()
        				.anyRequest().authenticated())
        		
        		.sessionManagement(
        				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
        		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        		.build();
        
        //.userDetailsService(customUserDetailsServiceImpl)
        				
	}
	
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().requestMatchers("/assignment/register");
//	}
}


