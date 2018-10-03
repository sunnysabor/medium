package com.bootdo.common.domain;

import java.io.Serializable;
import java.util.Date;

public class CommentAll implements Serializable {
    private static final long serialVersionUID = 9035408037903858187L;
    private Long id;
    private Long userId;
    private Long fileId;
    private String content;
    private Date createDate;


}
