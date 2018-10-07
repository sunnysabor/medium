/**
 * Copyright (C), 2018, Jerry
 * FileName: RechargeDao
 * Author:   jerry
 * Date:     2018/10/2 18:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.neusoft.myproject.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.neusoft.myproject.domain.RechargeDO;

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
@Mapper
public interface RechargeDao {
    RechargeDO get(Long id);

    List<RechargeDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RechargeDO recharge);

    int remove(Long id);

    int batchRemove(Long[] ids);
}