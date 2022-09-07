package com.szq.securityanalysis.service.impl;

import com.szq.securityanalysis.pojo.article.Comment;
import com.szq.securityanalysis.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/6/6 23:51
 * @Version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testFindComment() {
        List<Comment> commentList = commentService.findCommentList();
        System.out.println(commentList);
    }

    @Test
    public void testSaveComment(){
        Comment comment = new Comment();
        comment.setArticleId("100000");
        comment.setContent("测试添加的数据");
        comment.setCreateDateTime(LocalDateTime.now());
        comment.setUserId("1003");
        comment.setNickName("凯撒大帝");
        comment.setState("1");
        comment.setLikeNum(0);
        comment.setReplyNum(0);
        commentService.saveComment(comment);

    }

}