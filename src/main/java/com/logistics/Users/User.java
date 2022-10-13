package com.logistics.Users;


import com.logistics.Organisations.Organisation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long userId;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
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
            name = "password",
            nullable = false,
            unique = true
    )
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organisation_orgId",referencedColumnName = "orgId")
    private Organisation organisation;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @Column(
            name = "active",
            nullable = false
    )
    private boolean active;

    public User(String firstName, String lastName, String address, String email, String phone, String password, Organisation organisation, Roles roles, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.organisation = organisation;
        this.roles = roles;
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.name());
        return Collections.singletonList(authority);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
