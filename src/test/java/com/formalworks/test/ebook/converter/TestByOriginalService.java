package com.formalworks.test.ebook.converter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.formalworks.test.ebook.web.service.ExtraLogic;
import com.formalworks.test.ebook.web.service.RecognizingTableOfContents;

public class TestByOriginalService {

	private List<String> prepareList() {
		List<String> test = new ArrayList<String>();
		test.add("1. 하늘에 어찌 두 태양이 있으리오");
		test.add("가을 하늘은 세상의 변화에도 불구하고 예나 지금이나 변함없이");
		test.add("청명했다.");
		test.add("2. 꿈틀거리는 국토");
		test.add("다행히 그의 형인 조준이 조선의 일등 개국공신으로 지금도 자신과 함께");
		test.add("일하고 있었다.");
		return test;
	}

	private void prepareList2(List<String> expected) {
		expected.add("1. 하늘에 어찌 두 태양이 있으리오");
		expected.add("");
		expected.add("가을 하늘은 세상의 변화에도 불구하고 예나 지금이나 변함없이");
		expected.add("");
		expected.add("청명했다.");
		expected.add("");
		expected.add("2. 꿈틀거리는 국토");
		expected.add("");
		expected.add("다행히 그의 형인 조준이 조선의 일등 개국공신으로 지금도 자신과 함께");
		expected.add("");
		expected.add("일하고 있었다.");
		expected.add("");
	}

	@Test
	public void testByOriginal() {
		List<String> actual = new ArrayList<String>();
		List<String> expected = new ArrayList<String>();

		for (int i = 0; i < prepareList().size(); i++) {
			String line = prepareList().get(i).trim();
			actual.add(line);
			actual.add("");
		}

		prepareList2(expected);

		assertEquals(actual, expected);
	}

	@Test
	public void testRecognizeTableOfContent() {
		RecognizingTableOfContents table = new RecognizingTableOfContents();
		String actual = "";

		for (String line : prepareList()) {
			if (table.isTableOfContents(line)) {
				actual += line;
			}
		}
		String expected = "1. 하늘에 어찌 두 태양이 있으리오2. 꿈틀거리는 국토";
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

		String expected = "1. 하늘에 어찌 두 태양이 있으리오 가을 하늘은 세상의 변화에도 불구하고 예나 지금이나 변함없이 청명했다. 2. 꿈틀거리는 국토"
				+ "다행히 그의 형인 조준이 조선의 일등 개국공신으로 지금도 자신과 함께 일하고 있었다. ";
		String actual = paragraph.toString();
		assertEquals(actual, expected);
	}
}
