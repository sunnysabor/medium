package cn.neusoft.myproject.service;


import org.springframework.stereotype.Service;

import cn.neusoft.myproject.domain.CommentDO;
import cn.neusoft.myproject.domain.CommentRelation;

import java.util.List;
import java.util.Map;

@Service
public interface CommentService {
    //通过id查询评论信息
    CommentDO get(Long id);

    //通过参数（比如当前页码，页面大小，用户名等）来查询评论信息列表
    List<CommentDO> list(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小，用户名等）来查询评论信息列表，带出用户信息，文件信息
    List<CommentRelation> listCommentRelation(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小，用户名等）来查询评论列表大小
    int count(Map<String, Object> map);

    //将评论信息保存到数据库中
    int save(CommentDO comment);

    //将评论信息更新到数据库中
    int update(CommentDO comment);

    //将评论信息从数据库中删除，id
    int remove(Long id);

    //将评论信息从数据库中批量删除，ids
    int batchRemove(Long[] ids);
}
