package com.szq.securityanalysis.pojo.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/6/6 23:28
 * @Version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class Comment implements Serializable {
    @Id
    private String id;
    @Field("content")
    private String content;

    private Date publishTime;

    @Indexed
    private String userId;

    private String nickName;

    private LocalDateTime createDateTime;

    private Integer likeNum;

    private Integer replyNum;

    private String state;

    private String parentId;

    private String articleId;
}
