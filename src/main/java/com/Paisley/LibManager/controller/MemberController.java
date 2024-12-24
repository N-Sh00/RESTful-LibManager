package com.Paisley.LibManager.controller;

import com.Paisley.LibManager.entity.Member;
import com.Paisley.LibManager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    // Implement CRUD operations for User
    // Use UserService to interact with UserRep
    @Autowired
    MemberService memberService;

    private static final int ITEMS_PER_PAGE = 10;

    @GetMapping
    public Page<Member> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "name") String sort) {
        Pageable pageable = PageRequest.of(page,ITEMS_PER_PAGE, Sort.by(sort));
        return memberService.getAllMembers(pageable);
    }

    @GetMapping("api/members/{id}")
    public Member getUserById(@PathVariable (value = "id") Long id) {
        return memberService.getMember(id).orElse(null);
    }

    @PostMapping("/api/members")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member savedMember = memberService.createMember(member);
        return ResponseEntity.ok(savedMember);
    }

    @DeleteMapping("/api/members/{id}")
    public void deleteUser(@PathVariable (value = "id") Long id) {
        memberService.deleteMember(id);
    }
}
