/**
 * Copyright (C), 2018, Jerry
 * FileName: CollectionService
 * Author:   jerry
 * Date:     2018/10/3 15:17
 * Description: 收藏的service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bootdo.common.service;

import com.bootdo.common.domain.CollectionDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈收藏的service〉
 *
 * @author jerry
 * @create 2018/10/3
 * @since 1.0.0
 */
@Service
public interface CollectionService {
    CollectionDO get(Long id);

    List<CollectionDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CollectionDO collection);

    int remove(Long id);

    int batchremove(Long[] ids);
}