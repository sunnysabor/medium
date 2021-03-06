/**
 * Copyright (C), 2018, Jerry
 * FileName: CollectionDao
 * Author:   jerry
 * Date:     2018/10/3 15:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.neusoft.myproject.dao;


import org.apache.ibatis.annotations.Mapper;

import cn.neusoft.myproject.domain.CollectionDO;
import cn.neusoft.myproject.domain.CollectionRelation;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jerry
 * @create 2018/10/3
 * @since 1.0.0
 */
@Mapper
public interface CollectionDao {
    CollectionDO get(Long id);

    List<CollectionDO> list(Map<String, Object> map);

    List<CollectionRelation> listCollectionRelation(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CollectionDO collection);

    int update(CollectionDO collection);

    int remove(Long id);

    int batchRemove(Long[] ids);
}