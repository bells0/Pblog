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


    @Override
    public Author SelectByName(String username) {
        System.out.println(username);
        return authorMapper.SelectByName(username);
    }

    @Override
    public Author SelectById(Integer id) {
        return authorMapper.SelectById(id);
    }
}
