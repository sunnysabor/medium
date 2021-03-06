package cn.neusoft.myproject.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.neusoft.myproject.domain.CommentDO;
import cn.neusoft.myproject.domain.CommentRelation;
import cn.neusoft.myproject.service.CommentService;
import cn.neusoft.myproject.utils.PageUtils;
import cn.neusoft.myproject.utils.Query;
import cn.neusoft.myproject.utils.R;

@Controller
@RequestMapping("/common/comment")
public class CommentController extends BaseController {

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
        List<CommentRelation> commentList = commentService.listCommentRelation(query);
        int total = commentService.count(query);
        PageUtils pageUtils = new PageUtils(commentList, total);
        return pageUtils;
    }

    @GetMapping("/add/{fileId}")
    String add(Model model, @PathVariable("fileId") String fileId) {
        model.addAttribute("fileId", fileId);
        return "user/index/add_comment";
    }

    @GetMapping("/edit")
    String edit(Long id, Model model) {
        CommentDO comment = commentService.get(id);
        model.addAttribute("comment", comment);
        return "common/comment/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(String content,Long fileId) throws Exception {
        CommentDO comment=new CommentDO();
        comment.setUserId(getUserId());
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setFileId(fileId);
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
        if (commentService.update(comment) > 0) {
            return R.ok();
        }
        return R.error("修改失败");
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id, HttpServletRequest request) {
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
        commentService.batchRemove(ids);
        return R.ok();
    }

    @GetMapping("/userlist")
    String userlist() {
        return "user/index/comment";
    }

    @GetMapping("/minecomment")
    @ResponseBody
    PageUtils minecomment(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        params.put("userId",getUser().getUserId());
        Query query = new Query(params);
        List<CommentRelation> commentDOList = commentService.listCommentRelation(query);
        int total = commentService.count(query);
        PageUtils pageUtil = new PageUtils(commentDOList, total);
        return pageUtil;
    }
}
