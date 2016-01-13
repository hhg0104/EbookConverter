package com.formalworks.test.ebook.web.model;

import org.springframework.stereotype.Component;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.service.TxtFileReaderService;

@Component
public class EBookFactory {
	private EBook ebook;
	private String filePath;

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public EBook getInstance() {
			return ebook;
	}
	
	public EBook newInstance(){
		ebook = TxtFileReaderService.read(this.filePath);
		return ebook;
	}
}
