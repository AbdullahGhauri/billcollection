package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "Agent")
public class Agent implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agentId")
    private long agentId;


    @Column(name = "agentName")
    private String agentName;
    @Column(name = "agentUserId")
    private String agentUserId;
    @Column(name = "agentPassword")
    private String agentPassword;
    @Column(name = "agentCnic")
    private String agentCnic;
    @Column(name = "agentContactNo")
    private String agentContactNo;
    @Column(name = "agentEmail")
    private String agentEmail;
    @Column(name = "agentLoginActivate")
    private String agentLoginActivate;
    @Column(name = "agentLoginTime")
    private String agentLoginTime;
    @Column(name = "status")
    private String status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId")
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departmentId")
    private Department department;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @JsonIgnore
    @OneToOne(mappedBy = "agent")
    private OrderDetail orderDetail;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return agentPassword;
    }

    @Override
    public String getUsername() {
        return agentUserId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
