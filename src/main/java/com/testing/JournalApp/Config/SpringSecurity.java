package com.testing.JournalApp.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.testing.JournalApp.Service.UserDetailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http .csrf(csrf -> csrf.disable()) // Disable CSRF for API if not
	 * needed .cors(Customizer.withDefaults()) // Enable CORS
	 * .authorizeHttpRequests(authorize -> authorize
	 * .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permit all OPTIONS
	 * requests .requestMatchers("/public/**").permitAll() // Your permitAll
	 * endpoints .anyRequest().authenticated() ); return http.build(); }
	 */

	/*
	 * public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
	 * Exception{ return http.authorizeHttpRequests(request -> request
	 * .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permit all OPTIONS
	 * requests .requestMatchers("/public/**").permitAll()
	 * .requestMatchers("/journal/**").authenticated()
	 * //.requestMatchers("/admin/**").hasRole("ADMIN") .anyRequest().permitAll())
	 * .httpBasic(Customizer.withDefaults()) .csrf(csrf->csrf.disable()) .build();
	 * 
	 * //http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.
	 * STATELESS)).csrf(csrf->csrf.disable());
	 * 
	 * }
	 */

	/*
	 * @Bean
	 * 
	 * @Order(0) // Process this chain first public SecurityFilterChain
	 * publicEndpoints(HttpSecurity http) throws Exception { http
	 * .securityMatchers(matchers -> matchers.requestMatchers("/public/**"))
	 * .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
	 * .csrf(csrf -> csrf.disable()); // Disable CSRF for public endpoints if
	 * applicable return http.build(); }
	 * 
	 * @Bean
	 * 
	 * @Order(1) // Your main security chain public SecurityFilterChain
	 * mainSecurityChain(HttpSecurity http) throws Exception { http
	 * 
	 * .authorizeHttpRequests(authorize ->
	 * authorize.requestMatchers("/JournalEntry3/**","/user/**"). authenticated()
	 * .requestMatchers("/JournalEntry3/**").hasAnyRole("USER").anyRequest().
	 * permitAll()).csrf(csrf -> csrf.disable()); return http.build(); }
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.authorizeHttpRequests(auth -> auth.requestMatchers("/public/**").permitAll() // give any of
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()// request
				.requestMatchers("/JournalEntry3/**").hasAnyRole("USER", "ADMIN") // Accessible by USER or ADMIN
				.requestMatchers("/User/**").hasAnyRole( "ADMIN") 																							// endpoint
				.anyRequest().authenticated()) // All other requests require authentication
		.csrf(csrfConfig -> csrfConfig.disable())// Disable CSRF for simplicity, not recommended for production
		
			.httpBasic(Customizer.withDefaults())	

				.sessionManagement(sessionConfig -> // Disable JSESSIONID for simplicity, not recommended for production
				sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				//.formLogin(Customizer.withDefaults()) // Enable form login here I use default login

				.logout(Customizer.withDefaults()); // Enable logout

		return httpSecurity.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

}
