package problog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import problog.security.service.CustomUserDetailsService;

/**
 * springSecurity核心配置类
 * @ClassName:WebSecurityConfig
 * @Author:Timelin
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/article").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登录页
               .formLogin().loginPage("/login")
                // 设置登录成功和失败
               .defaultSuccessUrl("/login/success").permitAll()
                .failureUrl("/login/fail")
                .and()
                .logout()
                   .logoutUrl("/loginOut")// 注销接口
                    .logoutSuccessUrl("/login").permitAll()  // 注销成功跳转接口
                   // .invalidateHttpSession(true)// 指定是否在注销时让HttpSession无效

                .and().rememberMe();// 自动登录

        // 关闭CSRF跨域
        http.csrf().disable();



    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置拦截器忽略文件夹，可以对静态资源放行

        web.ignoring().antMatchers("/bootstrap/**","/img/**","/fonts/**","/css/**","/js/**");
    }
}
