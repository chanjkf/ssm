package xyz.chanjkf.entity.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -8742242989447584754L;

    @Column(name = "creator_id")
    private Long creator_id;

    @Column(name = "create_time")
    private Date create_time;

    @Column(name = "update_time")
    private Date update_time;

    @Column(name = "active_flag")
    private boolean active_flag;

    public BaseEntity() {
        setCreate_time(new Date());
        setUpdate_time(new Date());
        setActive_flag(true);
    }

    public BaseEntity(Long creator_id) {
        this.creator_id = creator_id;
        setCreate_time(new Date());
        setUpdate_time(new Date());
        setActive_flag(true);
    }

    public BaseEntity(Long creator_id, boolean active_flag){
        this.creator_id = creator_id;
        this.active_flag = active_flag;
        setCreate_time(new Date());
        setUpdate_time(new Date());
    }

    public BaseEntity(Long creator_id, Date create_time, Date update_time, boolean active_flag) {
        this.create_time = create_time;
        this.creator_id = creator_id;
        this.active_flag = active_flag;
        this.update_time = update_time;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public boolean isActive_flag() {
        return active_flag;
    }

    public void setActive_flag(boolean active_flag) {
        this.active_flag = active_flag;
    }
}
