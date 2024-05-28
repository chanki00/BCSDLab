package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Primary
@Repository
public class JdbcMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
            );
            return member;
        };
    }

    @Override
    public void saveMember(Member member) {
        String sql = "insert into member (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword());
    }

    @Override
    public Member findById(long id) {
        String sql = "select * from member where id = ?";
        Member member = jdbcTemplate.queryForObject(sql, memberRowMapper(), id);
        return member;
    }

    @Transactional
    @Override
    public void updateMember(long id, Member member) {
        String sql = "update member set name=?, email=?, password=? where id=?";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword(), id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        String sql = "delete from member where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
