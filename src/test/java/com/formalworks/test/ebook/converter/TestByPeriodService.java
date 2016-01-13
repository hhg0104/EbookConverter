package com.formalworks.test.ebook.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.formalworks.test.ebook.web.service.ExtraLogic;
import com.formalworks.test.ebook.web.service.RecognizingTableOfContents;

public class TestByPeriodService {

	private List<String> prepareList() {
		List<String> test = new ArrayList<String>();
		test.add("1. 썩은 과일, 스물다섯");
		test.add("“아기를 낳기 위해서 결혼할 생각은 없어요, 엄마.”");
		test.add("얘, 너 때문에 못살겠다.");
		test.add("어머니가 가슴을 움켜 쥔다");
		test.add("할머니가 실망해서 혀를 찬다.");
		test.add("어머니의 목소리에 정신을");
		return test;
	}

	private List<String> prepareList2() {
		List<String> test = new ArrayList<String>();
		test.add("1. 썩은 과일, 스물다섯“아기를 낳기 위해서 결혼할 생각은 없어요, 엄마.”얘, 너 때문에 못살겠다.");
		test.add("어머니가 가슴을 움켜 쥔다할머니가 실망해서 혀를 찬다.");
		test.add("어머니의 목소리에 정신을");
		return test;
	}

	@Test
	public void testByPeriod() {
		StringBuffer paragraph = new StringBuffer();
		List<String> actual = new ArrayList<String>();
		List<String> expected = new ArrayList<String>();

		for (int i = 0; i < prepareList().size(); i++) {
			String line = prepareList().get(i).trim();

			paragraph = endsWithPeriod(paragraph, actual, line);
		}

		if (!paragraph.toString().equals("")) {
			actual.add(paragraph.toString());
		}

		expected = prepareList2();
		assertEquals(actual, expected);
	}

	private StringBuffer endsWithPeriod(StringBuffer paragraph, List<String> actual, String line) {
		if (line.endsWith(".")) {
			paragraph.append(line);
			actual.add(paragraph.toString());
			paragraph = new StringBuffer();
		} else {
			paragraph = makeParagraph(line, paragraph);
		}
		return paragraph;
	}

	private StringBuffer makeParagraph(String line, StringBuffer paragraph) {
		paragraph.append(line);
		return paragraph;
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
		String expected = "1. 썩은 과일, 스물다섯";
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

		String expected = "1. 썩은 과일, 스물다섯“아기를 낳기 위해서 결혼할 생각은 없어요, 엄마.” 얘, 너 때문에 못살겠다. 어머니가 가슴을 움켜 쥔다 할머니가 실망해서 혀를 찬다. 어머니의 목소리에 정신을 ";
		String actual = paragraph.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void saveFirstCharOfDialogLines() {

		String line = "\"하늘에 어찌 두 태양이 있으리오가을";
		boolean isDialog = false;

		if ((line.startsWith("\"") && !line.endsWith("\"")) ||
				(line.startsWith("'") && !line.endsWith("'")) ||
				(line.startsWith("`") && !line.endsWith("`")) ||
				(line.startsWith("-") && !line.endsWith("-")) ||
				(line.startsWith("(") && !line.endsWith(")")) ||
				(line.startsWith("“") && !line.endsWith("”"))) {
			isDialog = true;
		}
		assertTrue(isDialog);
	}

	@Test
	public void isLastLineOfParagraph() {
		String line = "하늘에 어찌 두 태양이 있으리오.";
		boolean isDialog = false;
		if (line.endsWith(".")) {
			isDialog = true;
		}
		assertTrue(isDialog);
	}
}
