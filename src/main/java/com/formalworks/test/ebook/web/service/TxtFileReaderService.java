package com.formalworks.test.ebook.web.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.model.EBookImp;
import com.formalworks.test.ebook.web.model.MetaInfo;


public class TxtFileReaderService {
	
	public static EBook read(String filePath) {
		MetaInfo metaInfo = MetaInfo.extract(filePath);
		List<String> contents = readFile(filePath);
		return new EBookImp(metaInfo, contents);
	}

	public static List<String> readFile(String filePath) {

		try {

			File file = new File(filePath);
			return FileUtils.readLines(file, "EUC-KR");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}
}
