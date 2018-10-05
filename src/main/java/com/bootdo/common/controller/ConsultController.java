package com.bootdo.common.controller;

import com.bootdo.common.domain.ConsultDO;
import com.bootdo.common.domain.ConsultRelation;
import com.bootdo.common.service.ConsultService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common/consult")
public class ConsultController extends BaseController {

    @Autowired
    private ConsultService consultService;

    @GetMapping()
    String consult(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "common/consult/consult";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<ConsultRelation> consultList = consultService
                .listConsultRelation(query);
        int total = consultService.count(query);
        PageUtils pageUtils = new PageUtils(consultList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "user/index/add_consult";
    }

    @GetMapping("/edit")
    String edit(Long id, Model model) {
        ConsultDO consult = consultService.get(id);
        model.addAttribute("consult", consult);
        return "common/consult/edit";
    }

    /**
     * 查看
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ConsultDO consult = consultService.get(id);
        if (consult != null) {
        }
        consult.setReaded(consult.getReaded() + 1);
        if (consultService.update(consult) > 1) {
            return R.ok().put("consult", consult);
        }
        return R.error();
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(String content) throws Exception {
        ConsultDO consult = new ConsultDO();
        consult.setGooded(0L);
        consult.setReaded(0L);
        consult.setUserId(getUserId());
        consult.setContent(content);
        consult.setCreateTime(new Date());
        if (consultService.save(consult) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ConsultDO consult) {
        if (consultService.update(consult) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 点赞
     */
    @PostMapping("/good")
    @ResponseBody
    public R good(Long id,HttpServletRequest request) {
        ConsultDO consultDO = consultService.get(id);
        if (consultDO != null) {
            consultDO.setGooded(consultDO.getGooded() + 1);
            if (consultService.update(consultDO) > 0) {
                return R.ok();
            }
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id, HttpServletRequest request) {
        if (consultService.remove(id) > 0) {
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
        consultService.batchRemove(ids);
        return R.ok();
    }

    @GetMapping("/userlist")
    String userlist() {
        return "user/index/consult";
    }

    @GetMapping("/mineconsult")
    @ResponseBody
    PageUtils mineconsult(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        params.put("userId", getUser().getUserId());
        Query query = new Query(params);
        List<ConsultRelation> consultDOList = consultService.listConsultRelation(query);
        int total = consultService.count(query);
        PageUtils pageUtil = new PageUtils(consultDOList, total);
        return pageUtil;
    }

    @GetMapping("/me")
    String me() {
        return "user/index/meconsult";
    }


}
