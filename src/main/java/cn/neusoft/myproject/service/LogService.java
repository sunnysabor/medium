package cn.neusoft.myproject.service;


import org.springframework.stereotype.Service;

import cn.neusoft.myproject.domain.LogDO;
import cn.neusoft.myproject.domain.PageDO;
import cn.neusoft.myproject.utils.Query;
//日志的service
@Service
public interface LogService {
    //将日志信息保存到数据库中
    void save(LogDO logDO);

    //通过参数（比如当前页码，页面大小，用户名等）来查询日志信息列表
    PageDO<LogDO> queryList(Query query);

    //将日志信息从数据库中删除，按id
    int remove(Long id);

    //将日志信息从数据库中批量删除，ids
    int batchRemove(Long[] ids);
}
