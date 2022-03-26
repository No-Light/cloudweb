package com.webcloud.config;

import com.webcloud.security.MemberDetailsSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 1.通过 @EnableWebSecurity注解开启Spring Security的功能。使用@EnableGlobalMethodSecurity(prePostEnabled = true)这个注解，
 * 可以开启security的注解，我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。
 *
 * 2.extends 继承 WebSecurityConfigurerAdapter 类，并重写它的方法来设置一些web安全的细节。
 * 我们结合@EnableWebSecurity注解和继承WebSecurityConfigurerAdapter，来给我们的系统加上基于web的安全机制。
 *
 * 3.在configure(HttpSecurity http)方法里面，默认的认证代码是
 * ((HttpSecurity)((HttpSecurity)((AuthorizedUrl)http
 *                                                  .authorizeRequests()
 *                                                  .anyRequest())
 *                                                  .authenticated()
 *                                                          .and())
 *                                                  .formLogin()
 *                                                          .and())
 *                                                  .httpBasic();
 *
 * 我们使用SpringBoot默认的配置super.configure(http)，它通过 authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护。
 * 默认配置是所有访问页面都需要认证，才可以访问。
 *
 * 4.通过 formLogin() 定义当需要用户登录时候，转到的登录页面。
 *
 * 5.configureGlobal(AuthenticationManagerBuilder auth) 方法，在内存中创建了一个用户，
 * 该用户的名称为root，密码为root，用户角色为USER。
 *
 * 在方法上添加@PreAuthorize这个注解，value="hasRole('ADMIN')")是Spring-EL expression，
 * 当表达式值为true，标识这个方法可以被调用。如果表达式值是false，标识此方法无权限访问。
 *
 *
 * 作者：禅与计算机程序设计艺术
 * 链接：https://www.jianshu.com/p/08cc28921fd0
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() { //覆盖写userDetailsService方法 (1)
        return new MemberDetailsSecurity();

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/amchart/**",
                        "/bootstrap/**",
                        "/build/**",
                        "/css/**",
                        "/dist/**",
                        "/documentation/**",
                        "/fonts/**",
                        "/js/**",
                        "/pages/**",
                        "/plugins/**"
                ).permitAll() //默认不拦截静态资源的url pattern （2）
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")// 登录url请求路径 (3)
                .defaultSuccessUrl("/main").permitAll().and() // 登录成功跳转路径url(4)
                .logout().permitAll();

        http.logout().logoutSuccessUrl("/"); // 退出默认跳转页面 (5)

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("root")
//                .password("root")
//                .roles("USER")
//                .and()
//                //多个用户用and隔开
//                .withUser("admin").password("admin")
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password("user")
//                .roles("USER");

        //AuthenticationManager使用我们的 MemberUserDetailService 来获取用户信息
        auth.userDetailsService(userDetailsService()); // （6）
    }

//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resourcesDir/**");
//    }
//

}

