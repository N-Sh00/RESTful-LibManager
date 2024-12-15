package com.Paisley.LibManager.LibController;

import com.Paisley.LibManager.LibEntity.Member;
import com.Paisley.LibManager.LibService.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class MemberController {
    // Implement CRUD operations for User
    // Use UserService to interact with UserRep
    @Autowired
    MemberService memberService;

    @GetMapping
    public List<Member> getAllUsers() {
        return memberService.getAllMembers();
    }

    @GetMapping("api/members/{id}")
    public Member getUserById(@RequestParam (value = "id") Long id) {
        return memberService.getMember(id).orElse(null);
    }

    @PostMapping("/api/members")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member savedMember = memberService.createMember(member);
        return ResponseEntity.ok(savedMember);
    }

    @DeleteMapping("/api/users{id}")
    public void deleteUser(@RequestParam(value = "id") Long id) {
        memberService.deleteMember(id);
    }
}
