package com.formalworks.test.ebook.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.model.EBookFactory;
import com.formalworks.test.ebook.web.service.RecognizingTableOfContents;

@Controller
@RequestMapping("/book/{bookId}/option/")
public class StartLineOfParagraphController {

	EBook ebook;
	@Autowired
	EBookFactory ebookFactory;
	@Autowired
	RecognizingTableOfContents tableContents;

	@RequestMapping(value = "startLine", method = RequestMethod.GET)
	public String setStartLine(Model model, HttpServletRequest request) {
		ebook = ebookFactory.getInstance();
		List<String> origin = ebook.getOriginalContents();
		StringBuffer sample = new StringBuffer();

		if (origin.size() > 150) {

			for (int i = 0; i <= 150; i++) {
				String line = origin.get(i);
				sample.append(makeCount(i) + line + "\n");
			}
		} else {
			for (int i = 0; i < origin.size(); i++) {
				String line = origin.get(i);
				sample.append(makeCount(i) + line + "\n");
			}
		}
		ebook.setSampleContents(sample.toString());
		model.addAttribute("sample", ebook.getSampleContents());
		model.addAttribute("startLine", ebook.getStartLine());
		return "checkFirstLine";
	}

	@RequestMapping(value = "startLine", method = RequestMethod.POST)
	public String setStartLine(@RequestParam("startLine") String startLine, Model model) {
		ebook.setStartLine(Integer.parseInt(startLine));

		tableContents.insertSharpToTableOfContentsNotHaveNumber(ebook);

		return "redirect:./arrangement";
	}

	private String makeCount(int count) {
		if (count < 10)
			return "00" + (count + "" + ".   ");
		else if (count < 100)
			return "0" + (count + "" + ".   ");
		else
			return count + "" + ".   ";
	}
}
