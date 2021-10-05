package top.renhongchen.community.dto;


import lombok.Data;
import top.renhongchen.community.model.User;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;   //新加
}
