package com.example.cyworld.controller;

import com.example.cyworld.config.PrincipalDetails;
import com.example.cyworld.config.UserInfo;
import com.example.cyworld.model.AttachedFile;
import com.example.cyworld.model.AttachedImg;
import com.example.cyworld.model.member.Member;
import com.example.cyworld.model.post.PhotoUpdateForm;
import com.example.cyworld.model.post.Post;
import com.example.cyworld.repository.MemberRepository;
import com.example.cyworld.service.MemberService;
import com.example.cyworld.service.PostService;
import com.example.cyworld.util.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("miniHome")
@Controller
public class MiniHomeController {

    private final PostService postService;
    
    private final MemberService memberService;
  
    @Autowired
	private FileService fileService;
    
    @GetMapping("myhomp")
	public String minihomP(@AuthenticationPrincipal PrincipalDetails userInfo, Model model) {
    	Member member = userInfo.getMember();
		model.addAttribute("member", member);
		log.info("member: {}", member);
		return "minihomP/minihomP_main";
	}
    
    @GetMapping("update")
    public String hompUpload(@AuthenticationPrincipal PrincipalDetails userInfo, Model model) {
    	Member member = userInfo.getMember();
//    	log.info("member: {}", member);
    	model.addAttribute("member", member);
    	return "minihomP/minihomP_main_update";
    }
    
    @Transactional(readOnly = true)
    @PostMapping("update")
    public String hompSave(@AuthenticationPrincipal PrincipalDetails userInfo, @RequestParam(required = false) String explanation, @RequestParam(required = false) MultipartFile file) {
//    	log.info("img: {}", file);
//    	log.info("explanation: {}", explanation);
    	if (explanation != null) {
    		userInfo.getMember().setHomp_explanation(explanation);
    	}
    	if (file != null && !file.isEmpty()) {
            AttachedFile saveFile = fileService.saveFile(file);
            String fullPath = "/uploadImg/" + saveFile.getSaved_filename();
//            customer.setCustomer_img(fullPath);
            userInfo.getMember().setProfile_img(fullPath);
         }
    	memberService.updateMember(userInfo);
    	return "redirect:/miniHome/myhomp";
    }
    
    @GetMapping("album")
    public String album(Model model) {
        return "minihomP/minihomP_album";
    }
    
    @GetMapping("upload")
    public String miniHome(Model model) {
    	model.addAttribute("photoForm", new PhotoUpdateForm());
        return "lee/upload";
    }
    
    
    @PostMapping("upload")
    public String write(@Validated @ModelAttribute(name="photoForm") PhotoUpdateForm photoUpdateForm,
              BindingResult result,
            @RequestParam(name = "photo", required = false) MultipartFile file
            ){
        Post post = PhotoUpdateForm.toPost(photoUpdateForm);
        // board 객체를 저장한다.
        postService.saveReview(post, file);
        return "redirect:/";
    }
   

}
