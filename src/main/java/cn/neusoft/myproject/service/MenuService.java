package cn.neusoft.myproject.service;

import org.springframework.stereotype.Service;

import cn.neusoft.myproject.domain.MenuDO;
import cn.neusoft.myproject.domain.Tree;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface MenuService {

    List<Tree<MenuDO>> listMenuTree(Long id);

    Tree<MenuDO> getTree();

    List<MenuDO> list(Map<String, Object> params);

    int remove(Long id);

    int save(MenuDO menu);

    int update(MenuDO menu);

    MenuDO get(Long id);

    Set<String> listPerms();
}
