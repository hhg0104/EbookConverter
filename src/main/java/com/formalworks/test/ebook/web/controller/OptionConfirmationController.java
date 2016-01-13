package com.formalworks.test.ebook.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.model.EBookFactory;

@Controller
@RequestMapping("/book/{bookId}/option/")
public class OptionConfirmationController {
	private EBook ebook;
	@Autowired
	private EBookFactory ebookFactory;

	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	public String confirmOption(Model model) {

		ebook = ebookFactory.getInstance();

		String option = ebook.getParagarphOption();
		String optionName = null;

		if (option.equals("origin")) {
			optionName = "원본 기준";
		} else if (option.equals("indent")) {
			optionName = "들여쓰기 기준";
		} else if (option.equals("period")) {
			optionName = "마침표 기준";
		} else if (option.equals("blank")) {
			optionName = "빈줄 기준";
		}

		model.addAttribute("title", ebook.getMetaInfo().getTitle());
		model.addAttribute("author", ebook.getMetaInfo().getAuthor());
		model.addAttribute("option", optionName);

		return "confirmOption";
	}
}
