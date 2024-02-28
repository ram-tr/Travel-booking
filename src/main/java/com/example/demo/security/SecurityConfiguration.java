package com.example.demo.security;


import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.config.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final  JwtAuthenticationFilter jwtAuthFilter;
	
	private  final AuthenticationProvider authenticationProvider;
	
	@Bean
	public SecurityFilterChain customFilterChain(HttpSecurity httpSecurity) throws Exception
	{
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests().requestMatchers("/customers","/customers/l*","/admin/" )
                .permitAll().anyRequest().authenticated()
                .and().sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class));
		
		return httpSecurity.build();

		
	}
	
	
	

}
