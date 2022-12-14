package com.formalworks.test.ebook.web.input;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.formalworks.test.ebook.web.service.TxtFileReaderService;


public class TxtFileReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadFile() {
		
		String filePath = this.getClass().getResource("/testInput.txt").getPath();
		
		List<String> expecteds = new LinkedList<String>();
		expecteds.add("testInput");
		expecteds.add("1234312432");
		expecteds.add("fdsafdsafdsa");
		expecteds.add("ㅅㄱㄷㅄㄱㅂㄷ");
		expecteds.add("ㄹㅇㄴ;ㅣ머라이;ㄴㅁ");
		expecteds.add("ㄹ;아ㅓㄴㅁㄹ;ㅏㅣㅇㄴㅁ");
		expecteds.add("ㄹㅇㄴㅁ;ㅣㅏ렁ㄴ;ㅁ");

		List<String> actuals = TxtFileReaderService.readFile(filePath);
		
		Assert.assertArrayEquals(expecteds.toArray(new String[0]), actuals.toArray(new String[0]));
	}
	
	@Test
	public void testReadFile_emptyFile(){
		
		String filePath = this.getClass().getResource("/emptyFile.txt").getPath();
		
		List<String> expecteds = new LinkedList<String>();
		
		List<String> actuals = TxtFileReaderService.readFile(filePath);
		
		Assert.assertArrayEquals(expecteds.toArray(new String[0]), actuals.toArray(new String[0]));
	}
	
	@Test
	public void testReadFile_notNull(){
		
		String filePath = this.getClass().getResource("/testInput.txt").getPath();
		
		List<String> actuals = TxtFileReaderService.readFile(filePath);
		
		Assert.assertNotNull(actuals);	
	}
	
	@Test
	public void testReadFile_null(){
		
		List<String> actuals = TxtFileReaderService.readFile(null);
		
		Assert.assertNull(actuals);	
	}
	
}
