package cn.neusoft.myproject.service;



import java.util.List;
import java.util.Map;

import cn.neusoft.myproject.domain.FileDO;
import cn.neusoft.myproject.domain.FileRelation;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
public interface FileService {
    //通过用户id查询文件信息
    FileDO get(Long id);

    FileRelation getRelation(Long id);
    //通过参数（比如当前页码，页面大小，用户名等）来查询文件信息列表
    List<FileDO> list(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小，用户名等）来查询文件信息列表，包括用户名
    List<FileRelation> listRelation(Map<String, Object> map);

    //通过参数（比如当前页码，页面大小，用户名等）来查询文件列表大小
    int count(Map<String, Object> map);

    //将文件信息保存到数据库中
    int save(FileDO sysFile);

    //将文件信息更新到数据库中
    int update(FileDO sysFile);

    //修改文件的状态
    int updateStatus(Long id, String status);

    //将文件信息从数据库中删除，按id
    int remove(Long id);

    //将文件信息从数据库中批量删除，ids
    int batchRemove(Long[] ids);

    /**
     * 判断一个文件是否存在
     *
     * @param url FileDO中存的路径
     * @return
     */
    Boolean isExist(String url);
}
