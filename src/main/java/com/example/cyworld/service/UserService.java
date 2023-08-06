package com.example.cyworld.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cyworld.config.PrincipalDetails;
import com.example.cyworld.config.UserInfo;
import com.example.cyworld.model.member.Member;
import com.example.cyworld.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * 로그인 폼에서 아이디와 패스워드를 입력하고 로그인 요청을 하면
 * UserDetailsService의
 * loadUserByUsername 메소드를 자동으로 호출한다.
 * 
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String member_id) throws UsernameNotFoundException {
		log.info("loadUserByUsername: {}", member_id);
		Member member = memberRepository.findMemberById(member_id);
		log.info("member:{}",member);
		if(member != null) {
			return new PrincipalDetails(member);
		}
		throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
	}

}
