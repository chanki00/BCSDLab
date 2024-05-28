package com.example.demo.repository;

import com.example.demo.domain.Member;

public interface MemberRepository {
    void saveMember(Member member);
    Member findById(long id);
    void updateMember(long id, Member member);
    void deleteById(long id);

}
