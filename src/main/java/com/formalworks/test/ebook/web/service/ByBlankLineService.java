package com.formalworks.test.ebook.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.dao.ParagraphDao;

public class ByBlankLineService implements ParagraphDao {
	RecognizingTableOfContents tableContents;
	ExtraLogic extraLogic;
	private List<String> MDContent;
	private StringBuffer paragraph;

	public ByBlankLineService() {
		tableContents = new RecognizingTableOfContents();
		extraLogic = new ExtraLogic();
		paragraph = new StringBuffer();
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
			if (i != originContent.size() - 1 && !line.equals("")) {

				String nextLine = originContent.get(i + 1).trim();
				separateByLineCase(line, nextLine);
			} else {
				flushString();
			}
		}
		return MDContent;
	}

	private void flushString() {
		if (!StringUtils.equals(paragraph.toString(), "")) {
			MDContent.add(paragraph.toString());
			paragraph = new StringBuffer();
		}
	}

	private void separateByLineCase(String line, String nextLine) {

		if (tableContents.isAlreadyRecognizedAsTableOfContents(line) || tableContents.isTableOfContents(line)) {
			flushString();
			insertTableOfContent(line);

		} else if (nextLine.equals("")) {
			insertCompletedParagraph(line);

		} else {
			makeParagraph(line);
		}
	}

	@Override
	public void insertCompletedParagraph(String line) {
		paragraph.append(line);
		MDContent.add(paragraph.toString());
		MDContent.add("");
		paragraph = new StringBuffer();
	}

	private void makeParagraph(String line) {
		if (extraLogic.isAppendingBlankNecessary(line)) {
			line += " ";
		}
		paragraph.append(line);
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
}
