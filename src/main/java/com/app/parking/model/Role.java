package com.app.parking.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by gatomulesei on 3/28/2017.
 */
@Entity
public class Role extends BaseEntity implements GrantedAuthority{

    private static final long serialVersionUID = 1L;

    private String authority;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<User> users;

    @Override
    public String getAuthority() {
        return null;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
