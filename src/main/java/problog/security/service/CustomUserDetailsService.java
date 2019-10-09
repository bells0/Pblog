package problog.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import problog.domain.User.Author;
import problog.service.AuthorService;

import java.util.ArrayList;

/**
 * 自定义封装用户名和密码、权限
 * @ClassName:CustomUserDetailsService
 * @Author:Timelin
 **/
@Service("userDetailsService")
public class CustomUserDetailsService  implements UserDetailsService{

    @Autowired
    AuthorService authorService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 定义一个权限集合
        ArrayList<GrantedAuthority>   authorities= new ArrayList<>();

        // 从数据库总取出用户信息
        Author author = authorService.SelectByName(username);

        // 判断用户是否存在
        if(author==null){
            throw new UsernameNotFoundException("用户名错误或不存在、密码错误！");

        }

        // 返回UserDetails实现类
        return new User(author.getUserName(),author.getPassword(),authorities);
    }
}
