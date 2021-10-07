package top.renhongchen.community.dto;

import lombok.Data;
import top.renhongchen.community.model.User;

@Data
public class CommentDTO {

    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
