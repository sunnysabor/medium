/**
 * Copyright (C), 2018, Jerry
 * FileName: CollectionDO
 * Author:   jerry
 * Date:     2018/10/3 15:18
 * Description: 收藏的实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.neusoft.myproject.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈收藏的实体〉
 *
 * @author jerry
 * @create 2018/10/3
 * @since 1.0.0
 */
public class CollectionDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    // 用户id
    private Long userId;
    // 多媒体id
    private Long fileId;
    //收藏时间
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}