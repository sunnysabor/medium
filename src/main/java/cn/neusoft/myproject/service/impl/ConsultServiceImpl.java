package cn.neusoft.myproject.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.neusoft.myproject.dao.ConsultDao;
import cn.neusoft.myproject.domain.ConsultDO;
import cn.neusoft.myproject.domain.ConsultRelation;
import cn.neusoft.myproject.service.ConsultService;

import java.util.List;
import java.util.Map;

@Service
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    private cn.neusoft.myproject.dao.ConsultDao ConsultDao;

    @Override
    public ConsultDO get(Long id) {
        return ConsultDao.get(id);
    }

    @Override
    public List<ConsultDO> list(Map<String, Object> map) {
        return ConsultDao.list(map);
    }

    @Override
    public List<ConsultRelation> listConsultRelation(Map<String, Object> map) {
        return ConsultDao.listConsultRelation(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return ConsultDao.count(map);
    }

    @Override
    public int save(ConsultDO sysFile) {
        return ConsultDao.save(sysFile);
    }

    @Override
    public int update(ConsultDO sysFile) {
        return ConsultDao.update(sysFile);
    }

    @Override
    public int remove(Long id) {
        return ConsultDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return ConsultDao.batchRemove(ids);
    }
}
