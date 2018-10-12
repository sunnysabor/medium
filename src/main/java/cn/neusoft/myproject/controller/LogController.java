package cn.neusoft.myproject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.neusoft.myproject.domain.LogDO;
import cn.neusoft.myproject.domain.PageDO;
import cn.neusoft.myproject.service.LogService;
import cn.neusoft.myproject.utils.Query;
import cn.neusoft.myproject.utils.R;

@RequestMapping("/common/log")
@Controller
public class LogController {
    @Autowired
    LogService logService;
    String prefix = "common/log";

    @GetMapping()
    String log() {
        return prefix + "/log";
    }

    @ResponseBody
    @GetMapping("/list")
    PageDO<LogDO> list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        PageDO<LogDO> page = logService.queryList(query);
        return page;
    }

    @ResponseBody
    @PostMapping("/remove")
    R remove(Long id) {
        if (logService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    R batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }
}
