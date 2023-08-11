package com.example.cyworld.model.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

@Data
public class PhotoUpdateForm {
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
