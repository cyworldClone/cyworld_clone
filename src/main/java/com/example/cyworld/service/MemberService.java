package com.example.cyworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cyworld.config.PrincipalDetails;
import com.example.cyworld.model.AttachedFile;
import com.example.cyworld.model.member.Member;
import com.example.cyworld.repository.MemberRepository;
import com.example.cyworld.util.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // 생성자 주입 방식
@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	// 아이디로 회원정보 조회
	public Member findMemberById(String member_id) {
		return memberRepository.findMemberById(member_id);
	}
	
	// 회원정보 등록
	public void saveMember(Member member) {
		String rawPassword = member.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		member.setPassword(encPassword);
//		log.info("encPassword: {}", encPassword);
		member.setHomp_title(member.getName() + "님의 미니홈피");
        member.setHomp_explanation(member .getName() + "님의 미니홈피에 오신 것을 환영합니다!");
        member.setProfile_img("/uploadImg/basic image");
        
        log.info("member: {}", member);
		memberRepository.saveMember(member);
	}
	
	
	// 회원정보 수정
	public void updateMember(@AuthenticationPrincipal PrincipalDetails userInfo) {
		Member member = userInfo.getMember();
//		log.info("service member: {}", member);
		memberRepository.updateMember(member);
	}
	
}
