package com.szq.securityanalysis.service.impl;

import com.szq.securityanalysis.dao.CommentMapper;
import com.szq.securityanalysis.pojo.article.Comment;
import com.szq.securityanalysis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/6/6 23:45
 * @Version 1.0.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    // @Autowired
    // private MongoTemplate template;

    @Override
    public void saveComment(Comment comment) {
        commentMapper.save(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.save(comment);
    }

    @Override
    public List<Comment> findCommentList() {
        return commentMapper.findAll();
    }

    @Override
    public Page<Comment> findCommentListByParentId(String parentId, int page, int size) {
        return commentMapper.findByParentId(parentId, PageRequest.of(page -1, size));
    }
}
