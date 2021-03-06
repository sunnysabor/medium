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
package cn.neusoft.myproject.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.neusoft.myproject.dao.CollectionDao;
import cn.neusoft.myproject.domain.CollectionDO;
import cn.neusoft.myproject.domain.CollectionRelation;
import cn.neusoft.myproject.service.CollectionService;

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
    public List<CollectionRelation> listCollectionRelation(Map<String, Object> map) {
        return collectionMapper.listCollectionRelation(map);
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