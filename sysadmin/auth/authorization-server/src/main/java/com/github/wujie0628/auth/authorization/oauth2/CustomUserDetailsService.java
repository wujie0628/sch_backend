package com.github.wujie0628.auth.authorization.oauth2;

import com.github.wujie0628.auth.authorization.entity.Role;
import com.github.wujie0628.auth.authorization.service.IRoleService;
import com.github.wujie0628.auth.authorization.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.github.wujie0628.auth.authorization.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String uniqueId) {

        User user = userService.getByUniqueId(uniqueId);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                this.obtainGrantedAuthorities(user)
        );

    }

    private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
        List<Role> roles = roleService.queryUserRolesByUserId(user.getId());

        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getCode())).collect(Collectors.toSet());
    }

}
