package com.formalworks.test.ebook.web.dao;

import java.util.List;

public interface ParagraphDao {

	void insertTableOfContent(String line);

	void insertCompletedParagraph(String line);

	List<String> arrangeParagraph(EBook ebook);
}
