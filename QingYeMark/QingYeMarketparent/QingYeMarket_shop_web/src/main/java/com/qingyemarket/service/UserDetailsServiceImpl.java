package com.qingyemarket.service;

import com.qingyemarket.pojo.TbSeller;
import com.qingyemarket.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @Author: qingye
 * 商家认证类接口
 * @Date: 2019/4/14 0014 20:01
 * @Version 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService{
    private SellerService sellerService;
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //GrantedAuthority用户下的每个角色。
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //添加商家角色,拥有ROLE_SELLER用户的才能登陆
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        //得到商家对象
        TbSeller seller = sellerService.findOne(username);
        if(seller!=null){
            if(seller.getStatus().equals("1")){
                //判断是否是盛和通过的商家登录
                //判断用户登录
                return new User(username,seller.getPassword(),grantedAuthorities);
            }else {
                return null;
            }

        }else {
            return null;
        }

    }
}
