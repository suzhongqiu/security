package com.szq.securityanalysis.service;

import com.szq.securityanalysis.pojo.article.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/6/6 23:44
 * @Version 1.0.0
 */
public interface CommentService {

    /** 功能描述: <br>
     * <> 保存评论内容
     * @param comment: 评论内容
     * @return: void
     * @author: szq
     * @Date: 2022/6/6 23:54
     */
    void saveComment(Comment comment);

    /** 功能描述: <br>
     * <> 跟新评论内容
     * @param comment:
     * @return: void
     * @author: szq
     * @Date: 2022/6/6 23:55
     */
    void updateComment(Comment comment);

    /** 功能描述: <br>
     * <> 查找所有
     * @return: java.util.List<com.szq.securityanalysis.pojo.article.Comment>
     * @author: szq
     * @Date: 2022/6/6 23:56
     */
    List<Comment> findCommentList();

    /** 功能描述: <br>
     * <> 通过父id分页查询
     * @param parentId:
     * @param page:
     * @param size:
     * @return: org.springframework.data.domain.Page<com.szq.securityanalysis.pojo.article.Comment>
     * @author: szq
     * @Date: 2022/6/15 22:17
     */
    Page<Comment> findCommentListByParentId(String parentId, int page, int size);
}
