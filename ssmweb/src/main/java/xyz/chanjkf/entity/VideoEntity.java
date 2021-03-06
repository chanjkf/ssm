package xyz.chanjkf.entity;

import xyz.chanjkf.entity.common.BaseEntity;

import javax.persistence.*;

/**
 *
 */
@Entity(name = "video")
public class VideoEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "qi_niu_key")
    private String qiNiuKey;

    @Column(name = "view_count")
    private int viewCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQiNiuKey() {
        return qiNiuKey;
    }

    public void setQiNiuKey(String qiNiuKey) {
        this.qiNiuKey = qiNiuKey;
    }
}
