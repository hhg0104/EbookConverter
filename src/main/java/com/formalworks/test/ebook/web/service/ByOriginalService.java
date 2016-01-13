package com.formalworks.test.ebook.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.dao.ParagraphDao;

public class ByOriginalService implements ParagraphDao {
	@Autowired
	RecognizingTableOfContents tableContents;
	@Autowired
	ExtraLogic extraLogic;
	private List<String> MDContent;

	public ByOriginalService() {
		extraLogic = new ExtraLogic();
		tableContents = new RecognizingTableOfContents();
		MDContent = new ArrayList<String>();
	}

	@Override
	public List<String> arrangeParagraph(EBook ebook) {
		// 중복으로 텍스트 내용이 들어가지 않게 초기화한다.
		if (!MDContent.isEmpty())
			MDContent = new ArrayList<String>();

		List<String> originContent = ebook.getOriginalContents();
		int startLine = ebook.getStartLine();

		extraLogic.isIntroduction(originContent, startLine);
		for (int i = startLine; i < originContent.size(); i++) {

			String line = originContent.get(i).trim();

			if (tableContents.isAlreadyRecognizedAsTableOfContents(line) || tableContents.isTableOfContents(line))
				insertTableOfContent(line);
			else
				insertCompletedParagraph(line);
		}
		return MDContent;
	}

	@Override
	public void insertTableOfContent(String line) {
		if (line.startsWith("#")) {
			MDContent.add(line);
			MDContent.add("");
		} else {
			MDContent.add("#" + line);
			MDContent.add("");
		}
	}

	@Override
	public void insertCompletedParagraph(String line) {
		MDContent.add(line);
	}
}
