package com.formalworks.test.ebook.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.dao.ParagraphDao;
import com.formalworks.test.ebook.web.model.EBookFactory;
import com.formalworks.test.ebook.web.service.ByBlankLineService;
import com.formalworks.test.ebook.web.service.ByIndentService;
import com.formalworks.test.ebook.web.service.ByOriginalService;
import com.formalworks.test.ebook.web.service.ByPeriodService;
import com.formalworks.test.ebook.web.service.MakeFullMDContents;
import com.formalworks.test.ebook.web.service.RecognizingTableOfContents;

@Controller
@RequestMapping("/book/{bookId}/option/")
public class ParagraphController {
//	@Autowired
//	ParagraphDao paraDaoByOrigin;
//	@Autowired
//	ParagraphDao paraDaoByPeriod;
//	@Autowired
//	ParagraphDao paraDaoByIndent;
//	@Autowired
//	ParagraphDao paraDaoByBlankLine;

	ParagraphDao paraDao;
	EBook ebook;
	@Autowired
	EBookFactory ebookFactory;
	@Autowired
	MakeFullMDContents fullMD;
	@Autowired
	RecognizingTableOfContents tableContents;
	Map<String, String> previewContents;

	@RequestMapping(value = "arrangement", method = RequestMethod.GET)
	public String setArrangementOption(Model model) {
		ebook = ebookFactory.getInstance();
		previewContents = new HashMap<String, String>();
		makePreviewContents(previewContents, ebook);
		model.addAttribute("previewContents", previewContents);
		model.addAttribute("option", ebook.getParagarphOption());
		return "paragraph";
	}

	@RequestMapping(value = "arrangement", method = RequestMethod.POST)
	public String finishSetting(@RequestParam("optionsRadios") String option, Model model) {

		optionApply(ebook);
		ebook.setFullMDContents(fullMD.mergeFullMDContents(ebook));
		ebook.setParagraphOption(option);

		return "redirect:../option/coverImg";
	}

	public void optionApply(EBook ebook) {

		String option = ebook.getParagarphOption();

		if (option.equals("origin")) {
			paraDao = new ByOriginalService();
			ebook.setMDContents(paraDao.arrangeParagraph(ebook));

		} else if (option.equals("indent")) {
			paraDao = new ByIndentService();
			ebook.setMDContents(paraDao.arrangeParagraph(ebook));

		} else if (option.equals("period")) {
			paraDao = new ByPeriodService();
			ebook.setMDContents(paraDao.arrangeParagraph(ebook));

		} else if (option.equals("blank")) {
			paraDao = new ByBlankLineService();
			ebook.setMDContents(paraDao.arrangeParagraph(ebook));
		}
	}

	private void makePreviewContents(Map<String, String> previewContents,
			EBook ebook) {

		paraDao = new ByOriginalService();
		setFullContentsInCaseSkipOptions(ebook);
		previewContents.put("origin", listToString(ebook.getFullMDContents()));

		paraDao = new ByPeriodService();
		setFullContentsInCaseSkipOptions(ebook);
		previewContents.put("period", listToString(ebook.getFullMDContents()));

		paraDao = new ByIndentService();
		setFullContentsInCaseSkipOptions(ebook);
		previewContents.put("indent", listToString(ebook.getFullMDContents()));

		paraDao = new ByBlankLineService();
		setFullContentsInCaseSkipOptions(ebook);
		previewContents.put("blank", listToString(ebook.getFullMDContents()));
	}

	private void setFullContentsInCaseSkipOptions(EBook ebook) {
		ebook.setMDContents(paraDao.arrangeParagraph(ebook));
		ebook.setFullMDContents(fullMD.mergeFullMDContents(ebook));
	}

//	private ParagraphDao GetParaDaoInstance(EBook ebook) {
//
//		if (ebook.getParagarphOption().equals("origin"))
//			paraDao = new ByOriginalService();
//		else if (ebook.getParagarphOption().equals("period"))
//			paraDao = new ByPeriodService();
//		else if (ebook.getParagarphOption().equals("inden"))
//			paraDao = new ByIndentService();
//		else if (ebook.getParagarphOption().equals("blank"))
//			paraDao = new ByBlankLineService();
//
//		return paraDao;
//	}

	private String listToString(List<String> listContents) {
		StringBuffer contents = new StringBuffer();

		for (int i = 0; i < listContents.size(); i++) {
			String line = listContents.get(i).trim();
			if (!line.isEmpty() && (line.charAt(0) == '#' || line.charAt(0) == '%')) {
				char firstChar = line.charAt(0);
				line = toHTML(line, firstChar);

				if (i < listContents.size() && listContents.get(i + 1).isEmpty())
					contents.append(line);
				else
					contents.append(line + "<br>");
			} else
				contents.append(line + "<br>");
		}
		return contents.toString();
	}

	private String toHTML(String line, char firstChar) {
		line += "</h3>";

		return StringUtils.replace(line, firstChar + "", "<h3>");
	}

//	private String decodeString(String str) {
//
//		String decodedString = null;
//		try {
//			decodedString = new String(str.getBytes("8859_1"), "UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
//		return decodedString;
//	}
}
