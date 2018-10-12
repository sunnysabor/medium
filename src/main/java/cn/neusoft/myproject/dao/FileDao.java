package cn.neusoft.myproject.dao;


import org.apache.ibatis.annotations.Mapper;

import cn.neusoft.myproject.domain.FileDO;
import cn.neusoft.myproject.domain.FileRelation;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface FileDao {
    FileDO get(Long id);

    FileRelation  getRelation(Long id);

    List<FileDO> list(Map<String, Object> map);

    List<FileRelation> listRelation(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(FileDO file);

    int update(FileDO file);

    int updateStatus(FileDO file);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
