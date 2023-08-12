package com.example.cyworld.model.member;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Member {
    private String member_id;
    private String password;
    private String name;
    private GenderType gender;
    private LocalDate birth;
    private String email;
    private RoleType role;
    private String provider;
    private Long dotori=0L;
    private String nickname;
    private String homp_title;
    
    @Builder
	public Member(String member_id, String password, String name, GenderType gender, LocalDate birth, String email,
			RoleType role, String provider,Long dotori, String nickname, String homp_title) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.dotori = dotori;
		this.nickname=nickname;
		this.homp_title=homp_title;
	}
    
    

}
