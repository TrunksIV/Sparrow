package com.logistics.Organisations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logistics.Users.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "organisations")
@Table(name = "organisations")
public class Organisation {

    @Id
    @SequenceGenerator(
            name = "organisation_sequence",
            sequenceName = "organisation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "organisation_sequence"
    )
    private Long orgId;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String address;
    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;
    @Column(
            name = "phone",
            nullable = false,
            unique = true
    )
    private String phone;
    @Column(
            name = "active",
            nullable = false
    )
    private boolean active;

//    relationships
    @JsonIgnore
    @OneToMany(mappedBy = "organisation")
    private Set<User> users = new HashSet<>();

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<User> getUsers() {
        return users;
    }
}
