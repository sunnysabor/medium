package com.bootdo.common.domain;

public class CollectionRelation extends CollectionDO {

    private static final long serialVersionUID = 5135880118154033740L;

    private String userName;
    private String fileUrl;

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
}

