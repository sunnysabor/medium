package com.bootdo.common.service;

import com.bootdo.common.domain.CommentDO;

import java.util.List;
import java.util.Map;

public interface CommentService {

    CommentDO get(Long id);

    List<CommentDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CommentDO sysFile);

    int update(CommentDO sysFile);

    int remove(Long id);

    int batchRemove(Long[] ids);


}
