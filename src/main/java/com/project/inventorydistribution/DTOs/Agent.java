package com.project.inventorydistribution.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "Agent", uniqueConstraints = @UniqueConstraint(columnNames = "agentUserId"))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Agent implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agentId")
    private long agentId;


    @Column(name = "agentName")
    private String agentName;
    @Column(name = "agentUserId",nullable = false, unique = true)
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
    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId")
    private Role role;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "agent",cascade = CascadeType.ALL)
    private List<BillCollection> billCollections;


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
        return true;
    }
}
