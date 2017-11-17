package com.Spring.App.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.Spring.App.Domain.SysPermissionMapper;
import com.Spring.App.Domain.SysUserMapper;
import com.Spring.App.Domain.UserMapper;
import com.Spring.App.Model.*;

/**
 * Created by jingkun Xu on 17/8/16.
 * Authentication 身份认证
 */
@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    SysUserMapper userDao;
    @Autowired
    SysPermissionMapper permissionDao;

    public UserDetails loadUserByUsername(String username) {
    	com.Spring.App.Model.Sys_User user = userDao.findByName(username);
        if (user != null) {
            List<Sys_Permission> permissions = permissionDao.findByName(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (Sys_Permission permission : permissions) {
                if (permission != null && permission.getModuleName()!=null) {

                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getModuleName());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }

}