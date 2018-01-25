package xyz.chanjkf.entity;


import xyz.chanjkf.entity.common.BaseEntity;

import javax.persistence.*;

/**
 * Created by yi on 2017/7/31.
 */
@Entity(name = "user_role")
public class UserRoleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;


    @ManyToOne(fetch = FetchType.EAGER)
    private RoleEntity roleEntity;

    public UserRoleEntity() {
        super();
    }

    public UserRoleEntity(Long userId, String userName, RoleEntity roleEntity) {
        this.userId = userId;
        this.userName = userName;
        this.roleEntity = roleEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
