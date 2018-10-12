package cn.neusoft.myproject.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.neusoft.myproject.config.MediumConfig;
import cn.neusoft.myproject.dao.FileDao;
import cn.neusoft.myproject.domain.FileDO;
import cn.neusoft.myproject.domain.FileRelation;
import cn.neusoft.myproject.service.FileService;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao sysFileMapper;

    @Autowired
    private MediumConfig bootdoConfig;

    @Override
    public FileDO get(Long id) {
        return sysFileMapper.get(id);
    }

    @Override
    public FileRelation getRelation(Long id) {
        return sysFileMapper.getRelation(id);
    }

    @Override
    public List<FileDO> list(Map<String, Object> map) {
        return sysFileMapper.list(map);
    }

    @Override
    public List<FileRelation> listRelation(Map<String, Object> map) {
        return sysFileMapper.listRelation(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysFileMapper.count(map);
    }

    @Override
    public int save(FileDO sysFile) {
        return sysFileMapper.save(sysFile);
    }

    @Override
    public int update(FileDO sysFile) {
        return sysFileMapper.update(sysFile);
    }

    @Override
    public int updateStatus(Long id, String status) {
        FileDO fileDO = new FileDO();
        fileDO.setId(id);
        fileDO.setStatus(status);
        return sysFileMapper.updateStatus(fileDO);
    }

    @Override
    public int remove(Long id) {
        return sysFileMapper.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return sysFileMapper.batchRemove(ids);
    }

    @Override
    public Boolean isExist(String url) {
        Boolean isExist = false;
        if (!StringUtils.isEmpty(url)) {
            String filePath = url.replace("/files/", "");
            filePath = bootdoConfig.getUploadPath() + filePath;
            File file = new File(filePath);
            if (file.exists()) {
                isExist = true;
            }
        }
        return isExist;
    }
}
