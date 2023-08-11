package com.example.cyworld.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.cyworld.model.AttachedImg;
import com.example.cyworld.model.post.Post;
import com.example.cyworld.repository.PostMapper;
import com.example.cyworld.util.FileService;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.FileDataSource;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {
	
    private final PostMapper postMapper;
    private final FileService fileService;

    @Value("${uploadPath}")
    private String uploadPath;

    public void saveReview(Post post, MultipartFile file) {
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String memberId = userDetails.getUsername();
            log.info("memberId={}", memberId);
            post.setMemberId(memberId);
            }
        LocalDateTime currentTime = LocalDateTime.now();
        post.setPostDate(currentTime);
        
    	if (file != null) {
            String originalFilename = file.getOriginalFilename();
            post.setPhoto(originalFilename);
        }
    	postMapper.savePost(post);
        // 파일을 저장한다.
        if (file != null && file.getSize() > 0) {
        	AttachedImg attachedImg = fileService.saveFile(file);
        	//attachedImg.setReview_id(post.getReview_id());
        	//postMapper.saveImg(attachedImg);
        }
       
    }

}	
