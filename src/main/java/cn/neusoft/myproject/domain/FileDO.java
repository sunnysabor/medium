package cn.neusoft.myproject.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
public class FileDO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  // 文件类型
  private Integer type;
  // URL地址
  private String url;
  // 创建时间
  private Date createDate;
  //文件状态(toAudit,Audited，Rejected)
  private String status;
  //用户ID
  private Long userId;
  //征集ID
  private String consultId;
  //是否需要会员(需要会员='member')
  private String fileType;

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getConsultId() {
    return consultId;
  }

  public void setConsultId(String consultId) {
    this.consultId = consultId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public FileDO() {
    super();
  }

  public FileDO(Integer type, String url, Date createDate) {
    super();
    this.type = type;
    this.url = url;
    this.createDate = createDate;
  }

  /**
   * 获取：
   */
  public Long getId() {
    return id;
  }

  /**
   * 设置：
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 获取：文件类型
   */
  public Integer getType() {
    return type;
  }

  /**
   * 设置：文件类型
   */
  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * 获取：URL地址
   */
  public String getUrl() {
    return url;
  }

  /**
   * 设置：URL地址
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * 获取：创建时间
   */
  public Date getCreateDate() {
    return createDate;
  }

  /**
   * 设置：创建时间
   */
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "FileDO{" +
      "id=" + id +
      ", type=" + type +
      ", url='" + url + '\'' +
      ", createDate=" + createDate +
      '}';
  }
}
