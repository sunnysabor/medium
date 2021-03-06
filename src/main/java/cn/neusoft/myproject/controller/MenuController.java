package cn.neusoft.myproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.neusoft.myproject.aspect.Log;
import cn.neusoft.myproject.domain.MenuDO;
import cn.neusoft.myproject.domain.Tree;
import cn.neusoft.myproject.service.MenuService;
import cn.neusoft.myproject.utils.R;

/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
    String prefix = "system/menu";
    @Autowired
    MenuService menuService;

    @GetMapping()
    String menu(Model model) {
        return prefix + "/menu";
    }

    @RequestMapping("/list")
    @ResponseBody
    List<MenuDO> list(@RequestParam Map<String, Object> params) {
        List<MenuDO> menus = menuService.list(params);
        return menus;
    }

    @Log("添加菜单")
    @GetMapping("/add/{pId}")
    String add(Model model, @PathVariable("pId") Long pId) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.get(pId).getName());
        }
        return prefix + "/add";
    }

    @Log("编辑菜单")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Long id) {
        MenuDO mdo = menuService.get(id);
        Long pId = mdo.getParentId();
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.get(pId).getName());
        }
        model.addAttribute("menu", mdo);
        return prefix + "/edit";
    }

    @Log("保存菜单")
    @PostMapping("/save")
    @ResponseBody
    R save(MenuDO menu) {
        if (menuService.save(menu) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("更新菜单")
    @PostMapping("/update")
    @ResponseBody
    R update(MenuDO menu) {
        if (menuService.update(menu) > 0) {
            return R.ok();
        } else {
            return R.error(1, "更新失败");
        }
    }

    @Log("删除菜单")
    @PostMapping("/remove")
    @ResponseBody
    R remove(Long id) {
        if (menuService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @GetMapping("/tree")
    @ResponseBody
    Tree<MenuDO> tree() {
        Tree<MenuDO> tree = menuService.getTree();
        return tree;
    }
}
