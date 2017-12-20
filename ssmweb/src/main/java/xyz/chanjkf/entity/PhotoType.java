package xyz.chanjkf.entity;

import xyz.chanjkf.entity.common.DXPEntity;

import javax.persistence.*;

/**
 * Created by yi on 2017/12/18.
 */
@Entity(name = "photo_type")
public class PhotoType extends DXPEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "type_name")
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
