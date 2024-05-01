package ch.sbsoft.coverletter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import ch.sbsoft.coverletter.specification.MappingPath;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(MappingPath.ENDPOINTS_WHITELIST).permitAll()
				.anyRequest().authenticated()
			);

		return http.build();

	}
}
