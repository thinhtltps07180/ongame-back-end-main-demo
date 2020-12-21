package com.nhn.web.ongame.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nhn.web.ongame.security.services.UserDetailsServiceImpl;
import com.nhn.web.ongame.security.jwt.AuthEntryPointJwt;
import com.nhn.web.ongame.security.jwt.AuthTokenFilter;

/*@Configuration là một Annotation đánh dấu trên một Class cho phép Spring Boot biết được đây là
 nơi định nghĩa ra các Bean.*/
@Configuration
//@EnableWebSecurity để kích hoạt tính năng Spring Security trên ứng dụng
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true, Cho phép phân quyền sử dụng annotation @Secured - annotation của spring
        // jsr250Enabled = Cho phép phân quyền sử dụng @RoleAllow - annotation theo chuẩn JSR-250
        // standard của java Về cơ bản chức năng của 3 thằng là như nhau
        prePostEnabled = true
        //Cho phép phân quyền sử dụng annotation@PreAuthorize/@PostAuthorize - annotation của spring
        //securedEnabled: Cho phép phân quyền sử dụng annotation @Secured - annotation của spring
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }






    /*
     * CORS là một cơ chế cho phép nhiều tài nguyên khác nhau (fonts, Javascript, v.v…) của một trang web
     * có thể được truy vấn từ domain khác với domain của trang đó. CORS là viết tắt của từ Cross-origin resource sharing.
     * CSRF hay còn gọi là kỹ thuật tấn công “Cross-site Request Forgery“, nghĩa là kỹ thuật tấn công giả mạo chính chủ thể của nó
     * */

}
