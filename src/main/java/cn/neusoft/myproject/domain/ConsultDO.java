package cn.neusoft.myproject.domain;

import java.io.Serializable;
import java.util.Date;

public class ConsultDO implements Serializable {

    private static final long serialVersionUID = -4275937768699497750L;

    private Long id;
    private Long userId;
    private String content;
    private Long readed;
    private Long gooded;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReaded() {
        return readed;
    }

    public void setReaded(Long readed) {
        this.readed = readed;
    }

    public Long getGooded() {
        return gooded;
    }

    public void setGooded(Long gooded) {
        this.gooded = gooded;
    }
}
