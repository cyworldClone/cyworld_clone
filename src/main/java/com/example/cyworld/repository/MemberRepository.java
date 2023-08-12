package com.example.cyworld.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cyworld.model.member.Member;

@Mapper
public interface MemberRepository {
    // 회원가입
    void saveMember(Member member);

    // 아이디로 회원정보 검색
    Member findMemberById(String member_id);
    
    void updateMember(Member member);
}
