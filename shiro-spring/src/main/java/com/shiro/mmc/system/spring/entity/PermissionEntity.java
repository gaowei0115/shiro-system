package com.shiro.mmc.system.spring.entity;

import java.io.Serializable;

/**
 * @packageName：com.shiro.mmc.system.spring.entity
 * @desrciption: 权限Entity
 * @author: gaowei
 * @date： 2017-12-08 10:27
 * @history: (version) author date desc
 */
public class PermissionEntity implements Serializable{

    private static final long serialVersionUID = -5245325558762265872L;

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 是否生效可用，0：可用，1：不可用
     */
    private String isEffect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PermissionEntity that = (PermissionEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        return isEffect != null ? isEffect.equals(that.isEffect) : that.isEffect == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isEffect != null ? isEffect.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                ", isEffect='" + isEffect + '\'' +
                '}';
    }
}
