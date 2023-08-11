package com.example.cyworld.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cyworld.model.member.Member;
import com.example.cyworld.model.post.Post;

@Mapper
public interface PostMapper {
    // 회원가입
    void savePost(Post post);

    // 아이디로 회원정보 검색
    Member findMemberById(String member_id);
}
