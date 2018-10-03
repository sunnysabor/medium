package com.bootdo.common.service;

import com.bootdo.common.domain.CommentDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface CommentService {

    CommentDO get(Long id);

    List<CommentDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CommentDO comment);

    int update(CommentDO comment);

    int remove(Long id);

    int batchRemove(Long[] ids);


}
