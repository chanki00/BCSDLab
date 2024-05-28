package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.JdbcMemberRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(DataSource dataSource) {
        this.memberRepository = new JdbcMemberRepository(dataSource);
    }

    public void saveMember(Member member) {
        memberRepository.saveMember(member);
    }

    public Member findById(long id) {
        return memberRepository.findById(id);
    }

    public void updateMember(long id, Member member) {
        memberRepository.updateMember(id, member);
    }

    public void deleteById(long id) {
        memberRepository.deleteById(id);
    }

}
