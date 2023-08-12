package com.example.cyworld.controller;

import com.example.cyworld.config.PrincipalDetails;
import com.example.cyworld.config.UserInfo;
import com.example.cyworld.model.post.PhotoUpdateForm;
import com.example.cyworld.model.post.Post;
import com.example.cyworld.repository.MemberRepository;
import com.example.cyworld.service.MemberService;
import com.example.cyworld.service.PostService;

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
    private  PhotoUpdateForm photoUpdateForm;
  
    
    @GetMapping("myhomp")
	public String minihonP(@AuthenticationPrincipal PrincipalDetails userInfo, Model model) {
		model.addAttribute("loginUser", userInfo);
		return "minihomP/minihonP_main";
	}
    
    @GetMapping("upload")
    public String miniHome(
    					   Model model) {
    	model.addAttribute("photoForm", new PhotoUpdateForm());
        return "miniHome/upload";
    }
    
    
    @PostMapping("upload")
    public String write(
              @Validated @ModelAttribute(name="photoForm") PhotoUpdateForm photoUpdateForm,
              BindingResult result,
            @RequestParam(name = "photo", required = false) MultipartFile file
            ){

        // 파라미터로 받은 BoardWriteForm 객체를 Board 타입으로 변환한다.
        Post post = PhotoUpdateForm.toPost(photoUpdateForm);
        
        log.info("안녕3");
        log.info("post:{}",post);
        // board 객체를 저장한다.
        
        postService.saveReview(post, file);

        // board/list 로 리다이렉트한다.
        return "redirect:/";
    }

}
