package com.bootdo.common.service.impl;

import com.bootdo.common.dao.CommentDao;
import com.bootdo.common.domain.CommentDO;
import com.bootdo.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public CommentDO get(Long id) {
        return commentDao.get(id);
    }

    @Override
    public List<CommentDO> list(Map<String, Object> map) {
        return commentDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return commentDao.count(map);
    }

    @Override
    public int save(CommentDO sysFile) {
        return commentDao.save(sysFile);
    }

    @Override
    public int update(CommentDO sysFile) {
        return commentDao.update(sysFile);
    }

    @Override
    public int remove(Long id) {
        return commentDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return commentDao.batchRemove(ids);
    }
}
