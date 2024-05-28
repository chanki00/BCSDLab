package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepositoryimpl implements MemberRepository{

    private Map<Long, Member> memberMap = new HashMap<>();
    private Long id = 1L;

    public MemberRepositoryimpl() {
        memberMap.put(id++, new Member("회원1", "email-1", "pwd1"));
    }

    @Override
    public void saveMember(Member member) {
        memberMap.put(id++, member);
    }

    @Override
    public Member findById(long id) {
        return memberMap.get(id);
    }

    @Override
    public void updateMember(long id, Member member) {
        memberMap.put(id, member);
    }

    @Override
    public void deleteById(long id) {
        memberMap.remove(id);
    }
}
