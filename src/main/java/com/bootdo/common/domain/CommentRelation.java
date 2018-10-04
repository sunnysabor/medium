package com.bootdo.common.domain;

import java.io.Serializable;
import java.util.Date;

public class CommentRelation extends CommentDO {
    private static final long serialVersionUID = 9035408037903858187L;

    private String userName;
    private String fileUrl;
    private Integer fileType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}
