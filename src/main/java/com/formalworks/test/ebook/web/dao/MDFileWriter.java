package com.formalworks.test.ebook.web.dao;

import java.io.IOException;
import java.util.List;



public interface MDFileWriter {

	/**
	 * EBook클래스에서 MD파일 내용과 저장될 파일 경로를 얻어와 MD파일로 기록한다. ...
	 * 
	 * @param ebook
	 * @return TODO
	 * @throws IOException
	 */
	void write(EBook ebook) throws IOException;

	String getUniqueOutputFilePath(String outputMdFilePath);

	void writeContents(String tempPath, List<String> mdContents);

}