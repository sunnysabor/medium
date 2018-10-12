package cn.neusoft.myproject.service;


import org.springframework.stereotype.Service;

import cn.neusoft.myproject.domain.ConsultDO;
import cn.neusoft.myproject.domain.ConsultRelation;

import java.util.List;
import java.util.Map;

//征集的service
@Service
public interface ConsultService {
    //通过用户id查询征集信息
    ConsultDO get(Long id);

    //通过参数（比如当前页码，页面大小，用户名等）来查询征集信息列表
    List<ConsultDO> list(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小，用户名等）来查询征集信息列表，包括用征集信息
    List<ConsultRelation> listConsultRelation(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小，用户名等）来查询征集列表大小
    int count(Map<String, Object> map);

    //将征集信息保存到数据库中
    int save(ConsultDO sysFile);

    //将征集信息更新到数据库中
    int update(ConsultDO sysFile);

    //将征集信息从数据库中删除，id
    int remove(Long id);

    //将征集信息从数据库中批量删除，ids
    int batchRemove(Long[] ids);
}
