package com.task_mini.task_mini.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task_mini.task_mini.models.UserEntity;
import com.task_mini.task_mini.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findUserEntityByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("El usuario " + username + " No existe"));

        List<SimpleGrantedAuthority> authoririesList = new ArrayList<>();

        userEntity.getRoles()
        .forEach(roles -> authoririesList.add(new SimpleGrantedAuthority("ROLE_".concat(roles.getRoleEnum().name()))));

        userEntity.getRoles().forEach(role -> 
            role.getPermisosList().forEach(permission -> authoririesList.add(new SimpleGrantedAuthority(permission.getName()))));

        return new User(userEntity.getUsername(),
                        userEntity.getPassword(),
                        userEntity.isEnabled(),
                        userEntity.isAccountNoExpired(),
                        userEntity.isAccountNoLocked(),
                        userEntity.isCredentialNoExpired(),
                        authoririesList);
    }

}
