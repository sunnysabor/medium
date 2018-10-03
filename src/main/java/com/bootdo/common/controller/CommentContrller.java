package com.bootdo.common.controller;

import com.bootdo.common.domain.CommentDO;
import com.bootdo.common.service.CommentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common/comment")
public class CommentContrller extends BaseController {

    @Autowired
    private CommentService commentService;


    @GetMapping()
    String comment(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "common/comment/comment";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<CommentDO> commentList = commentService.list(query);
        int total = commentService.count(query);
        PageUtils pageUtils = new PageUtils(commentList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "common/comment/add";
    }

    @GetMapping("/edit")
    String edit(Long id, Model model) {
        CommentDO comment = commentService.get(id);
        model.addAttribute("comment", comment);
        return "common/comment/edit";
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CommentDO comment = commentService.get(id);
        return R.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(CommentDO comment) throws Exception {
        if (StringUtils.isEmpty(comment.getUserId())) {
            if (StringUtils.isEmpty(getUserId()))
                throw new Exception("获取用户ID为空");
            comment.setUserId(getUserId());
        }
        if (commentService.save(comment) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CommentDO comment) {
        commentService.update(comment);

        return R.ok();
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (commentService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        commentService.batchRemove(ids);
        return R.ok();
    }
}
