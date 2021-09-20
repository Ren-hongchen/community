package top.renhongchen.community.model;


import lombok.Data;

@Data
public class Post {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;
}
