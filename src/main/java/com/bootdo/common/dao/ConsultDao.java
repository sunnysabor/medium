package com.bootdo.common.dao;

import com.bootdo.common.domain.ConsultDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ConsultDao {

    ConsultDO get(Long id);

    List<ConsultDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ConsultDO file);

    int update(ConsultDO file);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
