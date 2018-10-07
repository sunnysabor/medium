package com.bootdo.system.service;

import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.Tree;
import org.springframework.stereotype.Service;

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
