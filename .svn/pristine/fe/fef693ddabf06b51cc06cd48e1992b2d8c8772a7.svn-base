package com.formalworks.test.ebook.converter;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.formalworks.test.ebook.web.service.TxtFileReaderService;

public class TestInputFileController {

	@Test
	public void testContainsString() {
		String test1 = "저자";
		String test2 = "이 저자: ";
		boolean actual = StringUtils.containsIgnoreCase(test2, test1);
		boolean expected = true;
		assertEquals(actual, expected);
	}

	@Test
	public void getAuthorFromContent() {

		String filePath = this.getClass().getResource("/k316.txt").getPath();
		String[] filter_author = { "저자", "글쓴이", "작가", "지은이" };
		List<String> listForAuthor = TxtFileReaderService.readFile(filePath);
		String author = null;

		for (int i = 0; i < 50; i++) {
				String line = StringUtils.deleteWhitespace(listForAuthor.get(i));

				for (String filter : filter_author) {

					if (StringUtils.containsIgnoreCase(line, filter))
						author = removeSpecialForAuthor(line).replace(filter, "");
			}
		}

		String actual = author;
		String expected = "김수희";
		assertEquals(expected, actual);

	}

	private String removeSpecialForAuthor(String str) {
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		str = str.replaceAll(match, "");
		return str;
	}

}
