package com.example.cyworld.model.member;



import java.io.Serializable;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Getter
@Setter
public class Member implements Serializable {
	@NonNull
    private String member_id;
	@NonNull
    private String password;
	@NonNull
    private String name;
	@NonNull
    private GenderType gender;
    private Long dotori;
    @NonNull
    private String nickname;
    private String homp_title;
    private String homp_explanation;
    private String profile_img;

    @Builder
	public Member(String member_id, String password, String name, GenderType gender,  
			Long dotori, String nickname, String homp_title) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.dotori = dotori;
		this.nickname=nickname;
		this.homp_title=homp_title;
	}
}
