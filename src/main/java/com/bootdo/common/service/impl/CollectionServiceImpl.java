/**
 * Copyright (C), 2018, Jerry
 * FileName: CollectionServiceImpl
 * Author:   jerry
 * Date:     2018/10/3 15:23
 * Description: 收藏表的具体实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bootdo.common.service.impl;

import com.bootdo.common.dao.CollectionDao;
import com.bootdo.common.domain.CollectionDO;
import com.bootdo.common.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈收藏表的具体实现〉
 *
 * @author jerry
 * @create 2018/10/3
 * @since 1.0.0
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionMapper;

    @Override
    public CollectionDO get(Long id) {
        return collectionMapper.get(id);
    }

    @Override
    public List<CollectionDO> list(Map<String, Object> map) {
        return collectionMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return collectionMapper.count(map);
    }

    @Override
    public int save(CollectionDO collection) {
        return collectionMapper.save(collection);
    }

    @Override
    public int remove(Long id) {
        return collectionMapper.remove(id);
    }

    @Override
    public int batchremove(Long[] ids) {
        return collectionMapper.batchRemove(ids);
    }
}