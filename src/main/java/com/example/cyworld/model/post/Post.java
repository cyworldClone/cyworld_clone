package com.example.cyworld.model.post;


import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Post {

	@NotBlank
    private Long postId;
	@NotBlank
    private String categoryId;
    private String title;
    private String photo;
    private String contents;
    private String memberId;
    private LocalDateTime postDate;
    
  
}
