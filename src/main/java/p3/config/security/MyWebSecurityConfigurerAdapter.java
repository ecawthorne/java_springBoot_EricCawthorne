package p3.config.security;

import org.apache.catalina.security.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter
{
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	@Value("${mh.security.authserver.BCryptPasswordEncoder.usedToEncodePassword:true}")
	private boolean useBCryptPasswordEncoder2encodePassword;

	private String encode(String rawPassword)
	{
		String password = useBCryptPasswordEncoder2encodePassword ? passwordEncoder().encode(rawPassword) : rawPassword;
		logger.info("--Eric --> encode({}) with useBCryptPasswordEncoder2encodePassword:{} returning password as {}",
				rawPassword, useBCryptPasswordEncoder2encodePassword, password);
		return password;
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
				.antMatchers("/", "/home", "/public")
				.permitAll()
				.antMatchers("/anonymous")
				.anonymous()
				.antMatchers("/h2_console/**", "/admin/**")
				.hasRole("admin")
				.antMatchers("/cats/**", "/rest/v1/cats")
				.hasRole("catMaster")
				.antMatchers("/dogs/**", "/rest/v1/dogs")
				.hasRole("dogMaster")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.permitAll();

		http.exceptionHandling().accessDeniedPage("/403");
		http.csrf().disable();
		http.headers().frameOptions().disable();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password(encode("admin"))
				.roles("admin")
				.and()
				.withUser("developer")
				.password(encode("developer"))
				.authorities("haveDogs", "ROLE_admin", "ROLE_developer", "ROLE_catMaster", "ROLE_dogMaster")
				.and()
				.withUser("catsUser")
				.password(encode("catsUser"))
				.roles("catMaster")
				.and()
				.withUser("dogsUser")
				.password(encode("dogsUser"))
				.authorities("haveDogs", "ROLE_dogMaster");
	}
}
