package problog.service;


import org.springframework.stereotype.Service;
import problog.domain.User.Author;

@Service
public interface AuthorService {
    // 根据用户名查询
    Author SelectByName(String username);

    // 根据id查询
    Author SelectById(Integer id);

}
