package com.sun.honor.context;

import com.sun.honor.dao.mapper.WechatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author sunjian.
 */
@Component
public class MyUserDetailService implements UserDetailsService {

    private final WechatUserMapper wechatUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailService(WechatUserMapper wechatUserMapper, PasswordEncoder passwordEncoder) {
        this.wechatUserMapper = wechatUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /*
        根据用户名查询用户,将用户信息以User返回
        对比的是用户登陆输入的用户名密码和这里返回的是否一致
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        wechatUserMapper
        // 暂时不做用户查询,直接返回用户对象
        return new User(username, "user", AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
    }
}