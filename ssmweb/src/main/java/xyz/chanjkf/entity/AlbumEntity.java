package xyz.chanjkf.entity;

import xyz.chanjkf.entity.common.BaseEntity;

import javax.persistence.*;

@Entity(name = "album")
public class AlbumEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "img_name")
    private String imgName;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private PhotoType type;

    @Column(name = "qi_niu_key")
    private String qiNiuKey;

    @Column(name = "url")
    private String url;

    @Column(name = "view_count")
    private int viewCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public PhotoType getType() {
        return type;
    }

    public void setType(PhotoType type) {
        this.type = type;
    }

    public String getQiNiuKey() {
        return qiNiuKey;
    }

    public void setQiNiuKey(String qiNiuKey) {
        this.qiNiuKey = qiNiuKey;
    }
}