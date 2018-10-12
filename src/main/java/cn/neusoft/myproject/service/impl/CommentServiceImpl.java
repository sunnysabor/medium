package cn.neusoft.myproject.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.neusoft.myproject.dao.CommentDao;
import cn.neusoft.myproject.domain.CommentDO;
import cn.neusoft.myproject.domain.CommentRelation;
import cn.neusoft.myproject.service.CommentService;

import java.util.List;
import java.util.Map;

@Service
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
    public List<CommentRelation> listCommentRelation(Map<String, Object> map) {
        return commentDao.listCommentRelation(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return commentDao.count(map);
    }

    @Override
    public int save(CommentDO comment) {
        return commentDao.save(comment);
    }

    @Override
    public int update(CommentDO comment) {
        return commentDao.update(comment);
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
