package com.formalworks.test.ebook.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import com.formalworks.test.ebook.web.dao.EBook;

@Component
public class RecognizingTableOfContents {
	EBook ebook;

	private List<String> originContent;
	private List<String> MDContent;
	private int longestLineLength;
	private String firstTableLine;
	private boolean isFirstTableContainNum;
	List<String> tableOfContentsOrder;

	public boolean isFirstTableContainNum() {
		return isFirstTableContainNum;
	}

	public boolean seemsTableLineWithoutNum(String line) {
		return ((line.length() < longestLineLength * 7 / 10) && !line.contains("."));
	}

	public boolean isTableOfContents(String line) {
		String regex = "(\\d+\\.|\\(\\d+\\)|\\d+\\)|제\\d+|\\d+\\부)(.*)";
//		boolean result = false;
//		if (firstTableLine.isEmpty()) {
//			result = setFirstTableLine(line, regex);
//		} else {
//			return containsNumFirstTableLine(line, regex);
//		}
		return !line.startsWith("#") && line.matches(regex) && line.length() < 30
				&& (!line.endsWith("'") || !line.endsWith("\"") || !line.endsWith("”"));
	}

	public void insertSharpToTableOfContentsNotHaveNumber(EBook eBook) {
		this.ebook = eBook;
		this.originContent = ebook.getOriginalContents();
		int startLine = ebook.getStartLine();
		String startLineOfTable = containsTableOfContentsOrderBeforeParagraphStart(startLine);
		if (null != startLineOfTable) {
			int startLineBeforeParagraph = Integer.parseInt(startLineOfTable);
			setTableOfContentsOrderBeforeParagraphStart(startLine, startLineBeforeParagraph);
		}
	}

	public String containsTableOfContentsOrderBeforeParagraphStart(int startLinePara) {
		String startLineOfTableOfContents;
		String[] tableKeyword = { "목차", "차례", "순서" };
		for (int i = 0; i < startLinePara; i++) {
			String line = originContent.get(i);
			for (String keyword : tableKeyword) {
				if (StringUtils.contains(StringUtils.deleteWhitespace(line), keyword)) {
					startLineOfTableOfContents = i + "";
					return startLineOfTableOfContents;
				}
			}
		}
		return null;
	}

	public void setTableOfContentsOrderBeforeParagraphStart(int startLine, int startLinePara) {
		tableOfContentsOrder = new ArrayList<String>();
		for (int i = startLinePara + 1; i < startLine; i++) {
			if (!originContent.get(i).trim().isEmpty())
				tableOfContentsOrder.add(originContent.get(i));
		}
		setTableOfContentsByOrder(startLine);
	}

	private void setTableOfContentsByOrder(int startLine) {
		for (int i = startLine; i < originContent.size(); i++) {
			String mainLine = originContent.get(i);
			for (String tableLine : tableOfContentsOrder) {
				tableLine = StringUtils.deleteWhitespace(tableLine);
				if (StringUtils.contains(StringUtils.deleteWhitespace(mainLine), tableLine)) {
					originContent.set(i, "#" + mainLine);
				}
			}
		}
	}

	private boolean setFirstTableLine(String line, String regex) {
		boolean result;
		result = allCaseAsTableLine(line, regex);
		if (result) {
			firstTableLine = line;
		}
		return result;
	}

	private boolean allCaseAsTableLine(String line, String regex) {
		return line.matches(regex) || ((line.length() < longestLineLength * 7 / 10)
				&& (containsNum(line) || !line.contains(".")));
	}

	private boolean containsNumFirstTableLine(String line, String regex) {
		if (containsNum(firstTableLine)) {
			isFirstTableContainNum = true;
			return line.matches(regex);
		} else {
			return allCaseAsTableLine(line, regex);
		}
	}

	private boolean containsNum(String line) {
		char[] charArray = line.toCharArray();
		boolean result = false;

		for (int i = 0; i < charArray.length; i++) {
			if (NumberUtils.isNumber(charArray[i] + "")) {
				result = true;
				return result;
			}
		}
		return result;
	}

	public int findLongestLineLength(List<String> originContent) {

		int max = 0;
		for (int i = 0; i < originContent.size() && i < 50; i++) {
			int size = originContent.get(i).length();
			if (max < size) {
				max = size;
			}
		}
		return max;
	}

	public boolean isAlreadyRecognizedAsTableOfContents(String line) {
		if (line.startsWith("#"))
			return true;
		else
			return false;
	}
}
