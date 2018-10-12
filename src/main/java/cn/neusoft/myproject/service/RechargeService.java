package cn.neusoft.myproject.service;

import org.springframework.stereotype.Service;

import cn.neusoft.myproject.domain.RechargeDO;

import java.util.List;
import java.util.Map;
//充值的service
@Service
public interface RechargeService {
    //通过id查询充值信息
    RechargeDO get(Long id);

    //通过参数（比如当前页码，页面大小，用户名等）来查询充值信息列表
    List<RechargeDO> list(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小等）来查询充值列表大小
    int count(Map<String, Object> map);

    //将充值信息保存到数据库中
    int save(RechargeDO user);

    //将充值信息从数据库中删除，按userId
    int remove(Long userId);

    //将充值信息从数据库中批量删除，userIds
    int batchremove(Long[] userIds);
}
