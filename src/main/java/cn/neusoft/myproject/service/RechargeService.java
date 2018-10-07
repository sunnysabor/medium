package cn.neusoft.myproject.service;

import org.springframework.stereotype.Service;

import cn.neusoft.myproject.domain.RechargeDO;

import java.util.List;
import java.util.Map;

@Service
public interface RechargeService {
    RechargeDO get(Long id);

    List<RechargeDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RechargeDO user);

    int remove(Long userId);

    int batchremove(Long[] userIds);

}
