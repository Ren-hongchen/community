package top.renhongchen.community.model;


import lombok.Data;

@Data
public class Post {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Integer creator;
    private Integer view_count;
    private Integer comment_count;
    private Integer like_count;
    private Long gmt_create;
    private Long gmt_modified;
}
