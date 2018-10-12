/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * FileName: FileRelationComment
 * Author:   chenchen
 * Date:     2018/10/5 16:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.neusoft.myproject.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author chenchen
 * @create 2018/10/5
 * @since 1.0.0
 */
public class FileRelationComment implements Serializable {

  private FileRelation fileRelation;
  private List<CommentRelation> commentDOList;

  public FileRelation getFileRelation() {
    return fileRelation;
  }

  public void setFileRelation(FileRelation fileRelation) {
    this.fileRelation = fileRelation;
  }

  public List<CommentRelation> getCommentDOList() {
    return commentDOList;
  }

  public void setCommentDOList(List<CommentRelation> commentDOList) {
    this.commentDOList = commentDOList;
  }
}
