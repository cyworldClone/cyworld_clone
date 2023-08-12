package com.example.cyworld.model.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

@Data
public class PhotoUpdateForm {
	
	
	//사진 업로드만 하는 폼. 사진/방명록/게시판? 이거 그냥 포스트 하나 나두고 업데이트 폼으로 여러개 나누면 될거 같아서 시도해봄
	
    private String title;
    private String contents;
    private MultipartFile photo;
    
    public static Post toPost(PhotoUpdateForm photoUpdateForm) {
    	Post post = new Post();
    	post.setTitle(photoUpdateForm.getTitle());
    	post.setContents(photoUpdateForm.getContents());
    	post.setPhoto(photoUpdateForm.getPhoto().getName());
    	
        return post;
    }
}	
