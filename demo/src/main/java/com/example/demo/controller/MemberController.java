package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> readMember(@PathVariable("id") long id) {
        Member member = memberService.findById(id);
        if (member == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(member);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("id") long id, @RequestBody Member newMember) {
        Member member = memberService.findById(id);
        if (member == null) return ResponseEntity.notFound().build();
        memberService.updateMember(id, newMember);
        return ResponseEntity.ok(newMember);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Member> deleteById(@PathVariable("id") long id) {
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
