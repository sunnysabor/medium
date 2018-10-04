package com.bootdo.common.service;

import com.bootdo.common.domain.ConsultDO;
import com.bootdo.common.domain.ConsultRelation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ConsultService {

    ConsultDO get(Long id);

    List<ConsultDO> list(Map<String, Object> map);

    List<ConsultRelation> listConsultRelation(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ConsultDO sysFile);

    int update(ConsultDO sysFile);

    int remove(Long id);

    int batchRemove(Long[] ids);


}
