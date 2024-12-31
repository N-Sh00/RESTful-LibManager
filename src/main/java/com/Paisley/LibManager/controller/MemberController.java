package com.Paisley.LibManager.controller;

import com.Paisley.LibManager.dto.MemberDTO;
import com.Paisley.LibManager.entity.Member;
import com.Paisley.LibManager.service.JwtService;
import com.Paisley.LibManager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    // Implement CRUD operations for User
    // Use UserService to interact with UserRep
    @Autowired
    MemberService memberService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private static final int ITEMS_PER_PAGE = 10;

    @GetMapping
    public ResponseEntity<Page<Member>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "name") String sort) {
        Pageable pageable = PageRequest.of(page, ITEMS_PER_PAGE, Sort.by(sort));
        Page<Member> members = memberService.getAllMembers(pageable);

        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getUserById(@PathVariable Long id) {
        Optional<Member> member = memberService.getMember(id);
        if (member.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(member.get());
    }

    @PostMapping("/register")
    public ResponseEntity<Member> addMember(@RequestBody MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setUsername(memberDTO.getUsername());
        member.setPassword(memberDTO.getPassword());

        Member savedMember = memberService.createMember(member);
        URI location = URI.create(String.format("api/members/" + savedMember.getId()));
        return ResponseEntity.created(location).body(savedMember);
    }

    @PostMapping("login")
    public String login(@RequestBody MemberDTO memberDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(memberDTO.getUsername(), memberDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(memberDTO.getUsername());
        }
        else
            return "Loggin failed";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        Optional<Member> existingMember = memberService.getMember(id);
        if (existingMember.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Member member = existingMember.get();
        member.setName(updatedMember.getName());

        Member savedMember = memberService.updateMember(member);
        return ResponseEntity.ok(savedMember);
    }
}
