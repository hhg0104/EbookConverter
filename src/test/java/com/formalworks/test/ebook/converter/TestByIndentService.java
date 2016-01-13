package com.formalworks.test.ebook.converter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.formalworks.test.ebook.web.service.ExtraLogic;
import com.formalworks.test.ebook.web.service.RecognizingTableOfContents;

public class TestByIndentService {

	private List<String> prepareList() {
		List<String> test = new ArrayList<String>();
		test.add("  문화의 개념만큼");
		test.add("개념사용의 혼란에도 불구하고");
		test.add("  우리는 국가 및");
		test.add("1.유지하며 공존하고 있다.");
		return test;
	}

	@Test
	public void testCountBlank() {
		int count = 0;
		String line = "    우리는 국가 및";

		while (line.charAt(count) == ' ') {
			count++;
		}
		int expected = 4;
		int actual = count;
		assertEquals(expected, actual);
	}

	@Test
	public void testMergeByIndent() {
		StringBuffer paragraph = new StringBuffer();
		List<String> actual = new ArrayList<String>();
		List<String> expected = new ArrayList<String>();

		for (int i = 0; i < prepareList().size(); i++) {
			String line = prepareList().get(i);
			if (i != prepareList().size() - 1 && !line.equals("")) {
				String nextLine = prepareList().get(i + 1);
				paragraph = caseBlankGapDifferent(paragraph, actual, line, nextLine);
			} else {
				mergeLine(line.trim(), paragraph, actual);
			}
		}

		expected.add("문화의 개념만큼개념사용의 혼란에도 불구하고");
		expected.add("우리는 국가 및1.유지하며 공존하고 있다.");

		assertEquals(actual, expected);
	}

	private void mergeLine(String line, StringBuffer paragraph, List<String> actual) {
		paragraph.append(line);
		actual.add(paragraph.toString());
	}

	private StringBuffer caseBlankGapDifferent(StringBuffer paragraph, List<String> actual, String line, String nextLine) {

		if (countBlank(line) > countBlank(nextLine)) {
			paragraph.append(line.trim());
		} else if (countBlank(line) < countBlank(nextLine)) {
			mergeLine(line.trim(), paragraph, actual);
			paragraph = new StringBuffer();
		}
		return paragraph;
	}

	public int countBlank(String line) {

		int count = 0;
		while (line.charAt(count) == ' ') {
			count++;
		}
		return count;
	}

	@Test
	public void testinsertCompletedParagraph() {
		StringBuffer paragraph = new StringBuffer();
		for (String i : prepareList()) {
			paragraph.append(i.trim());
		}

		String expected = "문화의 개념만큼개념사용의 혼란에도 불구하고우리는 국가 및1.유지하며 공존하고 있다.";
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
		String expected = "1.유지하며 공존하고 있다.";
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

		String expected = "  문화의 개념만큼 개념사용의 혼란에도 불구하고   우리는 국가 및 1.유지하며 공존하고 있다. ";
		String actual = paragraph.toString();
		assertEquals(actual, expected);
	}

}
