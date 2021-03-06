package com.formalworks.test.ebook.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.model.EBookFactory;

@Controller
@RequestMapping("/book/{bookId}/")
public class MetaInfoController {
	@Autowired
	EBookFactory ebookFactory;
	EBook ebook;

	@RequestMapping(value = "meta", method = RequestMethod.GET)
	public String sendTitle(@PathVariable("bookId") String bookId, HttpServletRequest request, Model model) {
		ebook = ebookFactory.getInstance();
		if (StringUtils.equals(ebook.getMetaInfo().getTitle(), null))
			ebook.getMetaInfo().setTitle("");
		if (StringUtils.equals(ebook.getMetaInfo().getAuthor(), null))
			ebook.getMetaInfo().setAuthor("");

		model.addAttribute("title", ebook.getMetaInfo().getTitle().trim());
		model.addAttribute("author", ebook.getMetaInfo().getAuthor().trim());
		model.addAttribute("bookId", bookId);
		model.addAttribute("fileName", ebook.getMetaInfo().getFileName());
		return "bookInfo";
	}

	@RequestMapping(value = "meta", method = RequestMethod.POST)
	public String sendTitle(@RequestParam("title") String title,
			@RequestParam("author") String author, Model model) {

//		String decodedBookId = null;
//		try {
//			decodedBookId = new String(bookId.getBytes("8859_1"), "UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		ebook.getMetaInfo().setTitle(title);
		ebook.getMetaInfo().setAuthor(author);
//		model.addAttribute("bookId", decodedBookId);
		return "redirect:/book/{bookId}/option/startLine";
	}
}
