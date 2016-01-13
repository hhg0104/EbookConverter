package com.formalworks.test.ebook.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.model.EBookFactory;
import com.formalworks.test.ebook.web.service.HashCodeExtractor;
import com.formalworks.test.ebook.web.service.RecognizingTableOfContents;

@Controller
public class MainController {
	@Autowired
	EBookFactory ebookFactory;
	EBook ebook;
	@Autowired
	RecognizingTableOfContents tableContents;
	@Autowired
	HashCodeExtractor hashCode;

	@RequestMapping(value = "/test1")
	@ResponseBody
	public void test() {

		String result = null;

		if (ebookFactory == null) {
			result = "Null"; //$NON-NLS-1$
		} else {
			result = "Not Null"; //$NON-NLS-1$
		}
		System.out.println(result);

	}

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "book", method = RequestMethod.GET)
	public String inputFile(Model model) {
		return "fileInput";
	}

	@RequestMapping(value = "book", method = RequestMethod.POST)
	public String inputFile(@RequestParam("textFile") MultipartFile file, Model model, HttpSession session,
			HttpServletRequest request) {

		// MultiPartFile의 해쉬코드를 추출하고, 임시폴더에 저장한다.
		String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
		String fileHashCode = hashCode.getHashcode(fileName.getBytes());
		String filePath = "c:/users/hhg/desktop" + fileHashCode;
		File dest = new File(filePath);
		saveMultiPartFile(file, dest);

		// 텍스트파일의 원본내용과 임의의 책 제목과 저자를 추출하고, 파일 경로 및 해쉬코드, 파일이름을 저장한다.
		ebookFactory.setFilePath(dest.getAbsolutePath());
		ebook = ebookFactory.newInstance();
		ebook.getMetaInfo().setHashCode(fileHashCode);
		ebook.getMetaInfo().setFileName(file.getName());

		// 디폴트 옵션 값 정해준다.
		ebook.setParagraphOption("origin");
		ebook.setCoverImg("none");
		ebook.setStartLine(0);

		model.addAttribute("bookId", fileHashCode);

		if (dest.exists()) {
			return "redirect:book/{bookId}/meta";
		} else {
			return "redirect:book";
		}
	}

	private void saveMultiPartFile(MultipartFile file, File dest) {
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
