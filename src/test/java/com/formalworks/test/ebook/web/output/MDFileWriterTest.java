package com.formalworks.test.ebook.web.output;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.dao.MDFileWriter;
import com.formalworks.test.ebook.web.service.MDFileWriterFactory;
import com.formalworks.test.ebook.web.service.TxtFileReaderService;


public class MDFileWriterTest {

	private String filePath = "src/test/java/k316.txt"; //$NON-NLS-1$//$NON-NLS-2$

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 테스트용 파일 생성
	 * 
	 * @return EBook
	 */
	private EBook testFileCreate(String filePath) {

		EBook ebook = TxtFileReaderService.read(filePath);

		List<String> contents = TxtFileReaderService.readFile(filePath);
		ebook.setFullMDContents(contents);

		return ebook;
	}


	@Test
	public void testGetUniqueOutputFilePath() {

		// 경로로부터 지정된 파일명과 이미 동일한 파일명이 존재할 때 새로 얻어온 파일명 비교

		EBook ebook = testFileCreate(filePath);

		MDFileWriter writer = MDFileWriterFactory.newInstance();

		// 현재 경로에 k316.txt 파일에 대한 MD파일이 존재하지 않은 경우 writer()를 통해 MD 파일 생성
		if (!new File(ebook.getOutputMdFilePath()).exists()) {
			try {
				writer.write(ebook);
			} catch (IOException e) {
				e.printStackTrace();
				fail();
			}
		}

		String result = ebook.getOutputMdFilePath();

		try {
			writer.write(ebook);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		// 파일경로에 의해 최초 지정된 파일명과 동일한 파일명 존재 시 새롭게 지정된 파일명 비교
		assertFalse(result.equals(ebook.getOutputMdFilePath()));

		// 테스트 파일 삭제
		new File(result).delete();
		new File(ebook.getOutputMdFilePath()).delete();

	}

	@Test
	public void testWriteContents_nullFilePath() throws IOException {

		EBook ebook = testFileCreate(filePath);

		MDFileWriter writer = MDFileWriterFactory.newInstance();

		String tempPath = null;

		try {
			writer.writeContents(tempPath, ebook.getMDContents());
			fail();
		} catch (NullPointerException e) {
			// Exception caught
		}


	}


	@Test
	public void testWrite_checkFileExist() {

		EBook ebook = testFileCreate(filePath);

		MDFileWriter writer = MDFileWriterFactory.newInstance();

		String testFilePath = writer.getUniqueOutputFilePath(ebook.getOutputMdFilePath());

		try {
			writer.write(ebook);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}


		boolean expected = true;
		boolean actual = new File(ebook.getOutputMdFilePath()).exists();

		// 파일이 생성되는지 확인
		assertEquals(expected, actual);

		// 테스트 파일 삭제
		new File(testFilePath).delete();

	}


	@Test
	public void testWrite_compareFileContents() {

		EBook ebook = testFileCreate(filePath);

		MDFileWriter writer = MDFileWriterFactory.newInstance();

		String testFilePath = writer.getUniqueOutputFilePath(ebook.getOutputMdFilePath());

		try {
			writer.write(ebook);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		List<String> expecteds = TxtFileReaderService.readFile(filePath);
		List<String> actuals = ebook.getFullMDContents();

		// write() 메소드의 입력과 출력의 내용이 같은지 확인
		assertArrayEquals(expecteds.toArray(new String[0]), actuals.toArray(new String[0]));

		new File(testFilePath).delete();

	}


	@Test
	public void testWrite_checkFileOfRepeatedWrite() {

		EBook ebookFirst = testFileCreate(filePath);
		EBook ebookSecond = testFileCreate(filePath);

		MDFileWriter writer = MDFileWriterFactory.newInstance();

		try {
			writer.write(ebookFirst);
			writer.write(ebookSecond);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		// 첫 번째 저장한 MD 파일 경로
		String firstSaveFilePath = ebookFirst.getOutputMdFilePath();

		// 두 번째 저장한 MD 파일 경로
		String secondSaveFilePath = ebookSecond.getOutputMdFilePath();

		// 두 개의 파일이 다른 파일명으로 저장되었는지 확인
		assertFalse(firstSaveFilePath.equals(secondSaveFilePath));

		List<String> firstResult = TxtFileReaderService.readFile(firstSaveFilePath);
		List<String> secondResult = TxtFileReaderService.readFile(secondSaveFilePath);

		// 서로 다른 이름의 두 개의 파일 내용이 같은지 확인
		assertArrayEquals(firstResult.toArray(new String[0]), secondResult.toArray(new String[0]));

		// 테스트 파일 삭제
		new File(firstSaveFilePath).delete();
		new File(secondSaveFilePath).delete();

	}


	@Test
	public void testWrite_checkFileContentsOfRepeatedWrite() {

		EBook ebookFirst = testFileCreate(filePath);
		EBook ebookSecond = testFileCreate(filePath);

		MDFileWriter writer = MDFileWriterFactory.newInstance();
		try {
			writer.write(ebookFirst);
			writer.write(ebookSecond);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		// 첫 번째 저장한 MD 파일 경로
		String firstSaveFilePath = ebookFirst.getOutputMdFilePath();

		// 두 번째 저장한 MD 파일 경로
		String secondSaveFilePath = ebookSecond.getOutputMdFilePath();

		List<String> firstResult = TxtFileReaderService.readFile(firstSaveFilePath);
		List<String> secondResult = TxtFileReaderService.readFile(secondSaveFilePath);

		// 하나의 입력에 대한 두 번의 write() 호출 시 생성 된 파일의 내용 비교
		assertArrayEquals(firstResult.toArray(new String[0]), secondResult.toArray(new String[0]));

		// 테스트 파일 삭제
		new File(firstSaveFilePath).delete();
		new File(secondSaveFilePath).delete();

	}

}
