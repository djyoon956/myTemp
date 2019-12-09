package com.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.Photo;


@Controller
@RequestMapping("/image/upload.do")
public class ImageController {
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return "image/image";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(Photo photo, HttpServletRequest request) throws IOException {

		/*
		 * 1. Photo DTO 받기 
		 * 		1. 자동화 <input 태그 name 속성값이 Photo member filed 명과 같다. 
		 * 		2.submit 
		 * 		Photo photo = new Photo(); 
		 * 		photo.setName("홍길동"); 
		 * 		photo.setAge(100);
		 * 		photo.setImage("업로드한 파일명") // 자동 주입 x 파일이름 추출해서 별도로 작업해야함.
		 * 
		 * 별도의 추가 작업 : 파일 이름, 파일 크기, 원본 이름
		 */

		System.out.println(photo.getFile().getName());
		System.out.println(photo.getFile().getContentType());
		System.out.println(photo.getFile().getOriginalFilename());
		System.out.println(photo.getFile().getBytes().length);

		photo.setImage(photo.getFile().getName());

		// 실 파일 업로드 (웹서버의 특정 경로)
		String filename=photo.getFile().getOriginalFilename();
		String path = request.getServletContext().getRealPath("/upload");		
		String fpath = path+"\\"+filename;
		System.out.println(fpath);
		FileOutputStream outputStream = new FileOutputStream(fpath);
		outputStream.write(photo.getFile().getBytes());
		outputStream.close();
		
		// DB 작업
		
		return "image/image";
	}
}
