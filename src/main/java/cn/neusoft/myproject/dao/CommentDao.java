package cn.neusoft.myproject.dao;


import org.apache.ibatis.annotations.Mapper;

import cn.neusoft.myproject.domain.CommentDO;
import cn.neusoft.myproject.domain.CommentRelation;

import java.util.List;
import java.util.Map;


@Mapper
public interface CommentDao {

    CommentDO get(Long id);

    List<CommentDO> list(Map<String, Object> map);

    List<CommentRelation> listCommentRelation(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CommentDO file);

    int update(CommentDO file);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
