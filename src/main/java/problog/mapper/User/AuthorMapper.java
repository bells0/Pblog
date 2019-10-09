package problog.mapper.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import problog.domain.User.Author;

@Mapper
public interface AuthorMapper {

    // 根据用户名查询
    //@Select("select * from author where user_name= #{username}")
    Author SelectByName(String username);

    // 根据id查询
    //@Select("select * from author where id = #{id}")
    Author SelectById(Integer id);
}
