package com.formalworks.test.ebook.web.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.model.EBookFactory;

@Controller
@RequestMapping("/book/{bookId}/option/")
public class CoverImageController {

	private EBook ebook;
	@Autowired
	private EBookFactory ebookFactory;

	@RequestMapping(value = "coverImg", method = RequestMethod.GET)
	public String setCoverImage() {
		ebook = ebookFactory.getInstance();
		return "setCoverImg";
	}

	@RequestMapping(value = "coverImg", method = RequestMethod.POST)
	public String setCoverImage(@RequestParam(value = "imgFile", required = false) MultipartFile file,
			@RequestParam("imgOption") String imgOption, Model model) {

		String tempFileSavePath = "D:" + File.separator;

		String epubImagePath = tempFileSavePath + "cover.jpg";
		File epubImageFile = new File(epubImagePath);

		if (imgOption.equals("default")) { // 기본 표지 이미지
			File defaultImgFile = new File(tempFileSavePath + "DefaultCover.jpg");
			try {
				FileUtils.copyFile(defaultImgFile, epubImageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ebook.setCoverImg("default");
		}
		else if (imgOption.equals("selection")) { // 표지 이미지 업로드
			try {
				file.transferTo(epubImageFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ebook.setCoverImg("selection");
		}
		else {
			ebook.setCoverImg("none");
		}

		return "redirect:./confirm";
	}
}
