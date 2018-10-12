package cn.neusoft.myproject.dao;


import org.apache.ibatis.annotations.Mapper;

import cn.neusoft.myproject.domain.ConsultDO;
import cn.neusoft.myproject.domain.ConsultRelation;

import java.util.List;
import java.util.Map;


@Mapper
public interface ConsultDao {

    ConsultDO get(Long id);

    List<ConsultDO> list(Map<String, Object> map);

    List<ConsultRelation> listConsultRelation(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ConsultDO file);

    int update(ConsultDO file);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
