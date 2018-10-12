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
package cn.neusoft.myproject.service;


import org.springframework.stereotype.Service;

import cn.neusoft.myproject.domain.CollectionDO;
import cn.neusoft.myproject.domain.CollectionRelation;

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
//收藏的service
@Service
public interface CollectionService {
    //通过收藏id查询收藏信息
    CollectionDO get(Long id);

    //通过参数（比如当前页码，页面大小，用户名等）来查询收藏信息列表
    List<CollectionDO> list(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小，用户名等）来查询收藏信息列表，带出用户信息和文件信息
    List<CollectionRelation> listCollectionRelation(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小，用户名等）来查询收藏列表大小
    int count(Map<String, Object> map);

    //将收藏信息保存到数据库中
    int save(CollectionDO collection);

    //将收藏信息从数据库中删除，id
    int remove(Long id);

    //将收藏信息从数据库中批量删除，ids
    int batchremove(Long[] ids);
}