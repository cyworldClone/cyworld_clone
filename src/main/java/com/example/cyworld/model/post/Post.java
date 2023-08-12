package com.example.cyworld.model.post;


import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Post {
	
	//사진/방명록/게시판? 이거 그냥 포스트 하나 나두고 업데이트 폼으로 여러개 나누면 될거 같아서 시도해봄

	
	@NotBlank
    private Long postId;
	@NotBlank
    private String categoryId;
    private String title;
    private String photo;
    private String contents;
    private String memberId;		//이거 그냥 MEMBER로 해보고싶은데....
    private LocalDateTime postDate;
    
  
}
