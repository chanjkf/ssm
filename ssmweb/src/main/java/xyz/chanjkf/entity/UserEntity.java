package xyz.chanjkf.entity;

import xyz.chanjkf.entity.common.BaseEntity;

import javax.persistence.*;

@Entity(name = "user")
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String userPassword;

    @Column(name = "email")
    private String email;

    @Column(name = "use_flag")
    private Boolean useFlag;

    @Column(name = "validate_code")
    private String validateCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUseFlag(Boolean useFlag) {
        this.useFlag = useFlag;
    }

    public Boolean isUseFlag() {
        return useFlag;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}