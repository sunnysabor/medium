/**
 * Copyright (C), 2018, Jerry
 * FileName: RechargeServiceImpl
 * Author:   jerry
 * Date:     2018/10/2 17:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.neusoft.myproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.neusoft.myproject.dao.RechargeDao;
import cn.neusoft.myproject.domain.RechargeDO;
import cn.neusoft.myproject.service.RechargeService;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jerry
 * @create 2018/10/2
 * @since 1.0.0
 */
@Transactional
@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    RechargeDao rechargeMapper;

    @Override
    public RechargeDO get(Long id) {
        RechargeDO recharge = rechargeMapper.get(id);
        return recharge;
    }

    @Override
    public List<RechargeDO> list(Map<String, Object> map) {
        return rechargeMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return rechargeMapper.count(map);
    }

    @Override
    public int save(RechargeDO user) {
        int count = rechargeMapper.save(user);
        return count;
    }


    @Override
    public int remove(Long userId) {
        return rechargeMapper.remove(userId);
    }

    @Override
    public int batchremove(Long[] userIds) {
        int count = rechargeMapper.batchRemove(userIds);
        return count;
    }
}