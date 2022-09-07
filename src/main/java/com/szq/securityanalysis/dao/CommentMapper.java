package com.szq.securityanalysis.dao;

import com.szq.securityanalysis.pojo.article.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/6/6 23:42
 * @Version 1.0.0
 */
public interface CommentMapper extends MongoRepository<Comment, String> {

    /** 通过父节点分页查询
     * @param parentId 父节点
     * @param pageable 分页
     * @return
     */
    Page<Comment> findByParentId(String parentId, Pageable pageable);
}
