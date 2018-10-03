package com.bootdo.system.service;

import com.bootdo.system.domain.RechargeDO;
import org.springframework.stereotype.Service;

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
