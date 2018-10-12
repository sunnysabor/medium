package cn.neusoft.myproject.service;

import org.springframework.stereotype.Service;

import cn.neusoft.myproject.domain.MenuDO;
import cn.neusoft.myproject.domain.Tree;

import java.util.List;
import java.util.Map;
import java.util.Set;
//菜单的service
@Service
public interface MenuService {
    //通过id查询菜单信息
    List<Tree<MenuDO>> listMenuTree(Long id);

    //查询所有菜单信息，封装数据
    Tree<MenuDO> getTree();

    //通过参数（比如当前页码，页面大小等）来查询菜单信息列表
    List<MenuDO> list(Map<String, Object> params);

    //将菜单信息从数据库中删除，按id
    int remove(Long id);

    //将菜单信息保存到数据库中
    int save(MenuDO menu);

    //将菜单信息更新到数据库中
    int update(MenuDO menu);

    //通过id查询菜单信息
    MenuDO get(Long id);

    //查询权限
    Set<String> listPerms();
}
