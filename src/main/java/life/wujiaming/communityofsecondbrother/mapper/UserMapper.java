package life.wujiaming.communityofsecondbrother.mapper;

import life.wujiaming.communityofsecondbrother.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Wu JiaMing, head of National Aeronautics and Space Administration
 * @date 27/07/2023 15:12
 */

@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);
}
