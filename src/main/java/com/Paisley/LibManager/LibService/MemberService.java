package com.Paisley.LibManager.LibService;

import com.Paisley.LibManager.LibEntity.Member;
import com.Paisley.LibManager.LibRepo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepo memberRepo;

    public List<Member> getAllMembers() {
        return memberRepo.findAll();
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
