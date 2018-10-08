package cn.neusoft.myproject.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.neusoft.myproject.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {
    //通过用户id查询用户信息
    UserDO get(Long userId);
    //通过参数（比如当前页码，页面大小，用户名等）来查询用户信息列表

    List<UserDO> list(Map<String, Object> map);
    //通过参数（比如当前页码，页面大小，用户名等）来查询用户列表大小

    int count(Map<String, Object> map);
    //将用户信息保存到数据库中

    int save(UserDO user);
    //将用户信息更新到数据库中

    int update(UserDO user);
    //将用户信息从数据库中删除，按userId

    int remove(Long userId);
    //将用户信息从数据库中批量删除，userIds

    int batchRemove(Long[] userIds);
}
