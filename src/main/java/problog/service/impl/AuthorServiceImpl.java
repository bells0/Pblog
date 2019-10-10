package problog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import problog.domain.User.Author;
import problog.mapper.User.AuthorMapper;
import problog.service.AuthorService;

/**
 * @ClassName:AuthorServiceImpl
 * @Author:Timelin
 **/
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;


    // 根据用户名称查询
    @Override
    public Author SelectByName(String username) {
        return authorMapper.SelectByName(username);
    }

    // 根据用户ID查询
    @Override
    public Author SelectById(Integer id) {
        return authorMapper.SelectById(id);
    }
}
