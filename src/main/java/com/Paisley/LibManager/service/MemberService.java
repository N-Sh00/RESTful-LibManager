package com.Paisley.LibManager.service;

import com.Paisley.LibManager.entity.Member;
import com.Paisley.LibManager.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepo memberRepo;

    public Page<Member> getAllMembers(Pageable pageable) {
        return memberRepo.findAll(pageable);
    }

    public Optional<Member> getMember(Long id) {
        return memberRepo.findById(id);
    }

    public Member createMember(Member member){
        memberRepo.save(member);
        return member;
    }


    public void deleteMember(Long id) {
        memberRepo.deleteById(id);
    }
}
