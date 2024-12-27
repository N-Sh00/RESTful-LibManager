package com.Paisley.LibManager.service;

import com.Paisley.LibManager.entity.Member;
import com.Paisley.LibManager.entity.UserPrincipal;
import com.Paisley.LibManager.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepo memberRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepo.findByUsername(username);
        if (member == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(member);
    }
}
