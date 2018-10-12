package cn.neusoft.myproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.neusoft.myproject.domain.UserDO;
import cn.neusoft.myproject.utils.UserVO;

import java.util.List;
import java.util.Map;

//用户的service
@Service
public interface UserService {
  //通过用户id查询用户信息
  UserDO get(Long id);

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
  int batchremove(Long[] userIds);

  //通过参数（比如当前页码，页面大小，用户名等）来判断该用户名是否重名
  boolean exit(Map<String, Object> params);

  //重置用户密码
  int resetPwd(UserVO userVO, UserDO userDO) throws Exception;

  //重置管理员密码
  int adminResetPwd(UserVO userVO) throws Exception;

  /**
   * 更新个人信息
   *
   * @param userDO
   * @return
   */
  int updatePersonal(UserDO userDO);

  /**
   * 更新个人图片
   *
   * @param file        图片
   * @param avatar_data 裁剪信息
   * @param userId      用户ID
   * @throws Exception
   */
  Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId)
    throws Exception;
}
