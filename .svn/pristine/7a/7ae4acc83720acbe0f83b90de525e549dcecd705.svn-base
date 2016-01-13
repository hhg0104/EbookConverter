package com.formalworks.test.ebook.converter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.formalworks.test.ebook.web.service.ExtraLogic;
import com.formalworks.test.ebook.web.service.RecognizingTableOfContents;

public class TestByBlankLineService {

	private List<String> prepareList() {
		List<String> test = new ArrayList<String>();
		test.add("1. 한겨레 21 정재숙 기자의 인물탐험 .");
		test.add("한국축구 뒤의 여자, 오은미");
		test.add("");
		test.add("‘월드컵 내조’ 목숨 걸었다");
		test.add("");
		test.add("사람들은 그를 “한국축구의 뒤에 서 있는 여자”라고 부른다.");
		return test;
	}

	@Test
	public void testinsertCompletedParagraph() {
		StringBuffer paragraph = new StringBuffer();
		for (String i : prepareList()) {
			paragraph.append(i);
		}

		String expected = "1. 한겨레 21 정재숙 기자의 인물탐험 .한국축구 뒤의 여자, 오은미‘월드컵 내조’ 목숨 걸었다사람들은 그를 “한국축구의 뒤에 서 있는 여자”라고 부른다.";
		String actual = paragraph.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void testRecognizeTableOfContent() {
		RecognizingTableOfContents table = new RecognizingTableOfContents();
		String actual = null;

		for (String line : prepareList()) {
			if (table.isTableOfContents(line)) {
				actual = line;
			}
		}
		String expected = "1. 한겨레 21 정재숙 기자의 인물탐험 .";
		assertEquals(actual, expected);
	}

	@Test
	public void testNeedBlank() {
		StringBuffer paragraph = new StringBuffer();
		ExtraLogic extra = new ExtraLogic();
		for (String line : prepareList()) {
			if (!line.equals("") && extra.isAppendingBlankNecessary(line)) {
				line += " ";
			}
			paragraph.append(line);
		}

		String expected = "1. 한겨레 21 정재숙 기자의 인물탐험 . 한국축구 뒤의 여자, 오은미‘월드컵 내조’ 목숨 걸었다 사람들은 그를 “한국축구의 뒤에 서 있는 여자”라고 부른다. ";
		String actual = paragraph.toString();
		assertEquals(actual, expected);
	}

	@Test
	public void testApplyBlankLine() {
		List<String> actual = new ArrayList<String>();
		List<String> expected = new ArrayList<String>();
		StringBuffer paragraph = new StringBuffer();

		for (int i = 0; i < prepareList().size(); i++) {
			String line = prepareList().get(i).trim();
			if (i != prepareList().size() - 1 && !line.equals("")) {
				String nextLine = prepareList().get(i + 1).trim();
				paragraph = seperateByLineCase(actual, paragraph, line, nextLine);
			} else {
				actual.add(line);
			}
		}

		expected.add("1. 한겨레 21 정재숙 기자의 인물탐험 .한국축구 뒤의 여자, 오은미");
		expected.add("");
		expected.add("‘월드컵 내조’ 목숨 걸었다");
		expected.add("");
		expected.add("사람들은 그를 “한국축구의 뒤에 서 있는 여자”라고 부른다.");
		assertEquals(actual, expected);
	}

	private StringBuffer seperateByLineCase(List<String> actual, StringBuffer paragraph, String line, String nextLine) {
		if (nextLine.equals("")) {
			mergeLine(line, paragraph, actual);
			paragraph = new StringBuffer();
		} else {
			paragraph.append(line);
		}
		return paragraph;
	}

	private void mergeLine(String line, StringBuffer paragraph, List<String> actual) {
		paragraph.append(line);
		actual.add(paragraph.toString());
	}
}
