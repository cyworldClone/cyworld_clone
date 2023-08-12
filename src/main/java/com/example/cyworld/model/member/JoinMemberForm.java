package com.example.cyworld.model.member;

import lombok.Data;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Data
public class JoinMemberForm {
    @Size(min = 4, max = 70)
    private String member_id;
    @Size(min = 4, max = 70)
    private String password;
    @NotBlank
    private String name;
    private GenderType gender;
    private Long dotori;
    private String nickname;
    private String homp_title;
    private String homp_explanation;
    private String profile_img;
    
    public static Member toMember(JoinMemberForm joinMemberForm) {
        Member member = new Member();
        member.setMember_id(joinMemberForm.getMember_id());
        member.setPassword(joinMemberForm.getPassword());
        member.setName(joinMemberForm.getName());
        member.setGender(joinMemberForm.getGender());
        member.setNickname(joinMemberForm.getNickname());
        
        return member;
    }
}
