package com.formalworks.test.ebook.web.input;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.formalworks.test.ebook.web.model.MetaInfo;
import com.formalworks.test.ebook.web.service.TxtFileReaderService;

public class MetaInfoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckTitle() {

		String filePath = this.getClass().getResource("/세계를 움직인 맞수.txt").getPath();

		String expected = "세계를 움직인 맞수";

		String actual = MetaInfo.checkTitle(filePath);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckTitle_prefix() {

		String filePath = this.getClass().getResource("/서울 피터팬.txt").getPath();

		String expected = "서울 피터팬";

		String actual = MetaInfo.checkTitle(filePath);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckTitle_special() {

		String filePath = this.getClass().getResource("/최후의 증인 1.txt").getPath();

		String expected = "최후의 증인 1";

		String actual = MetaInfo.checkTitle(filePath);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckTitle_notSame() {

		String filePath = this.getClass().getResource("/k316.txt").getPath();

		String expected = "러시아 문화의 이해";

		String actual = MetaInfo.checkTitle(filePath);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckTitle_notSameAndSpecial() {

		String filePath = this.getClass().getResource("/k709.txt").getPath();

		String expected = "삶의 행복을 주는 114가지 지혜";

		String actual = MetaInfo.checkTitle(filePath);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckTitle_serchNextLine() {

		String filePath = this.getClass().getResource("/빙벽1.txt").getPath();

		String expected = "빙벽1  고원정저";

		String actual = MetaInfo.checkTitle(filePath);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckTitle_noMeaning() {

		String filePath = this.getClass().getResource("/k851.txt").getPath();

		String expected = "정선 아리랑";

		String actual = MetaInfo.checkTitle(filePath);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckTitle_null() {
		String actual = null;
		try {
			actual = MetaInfo.checkTitle(null);
		} catch (NullPointerException e) {
			// System.out
			// .println("TestCode: testCheckTitle_null \"NullPointerException\"");
			Assert.assertNull(actual);
		}
	}

	@Test
	public void testCheckTitle_emptyString() {
		String filePath = "";

		String actual = null;
		try {
			actual = MetaInfo.checkTitle(filePath);
		} catch (NullPointerException e) {
			Assert.assertNull(actual);
		}
	}

	@Test
	public void testCheckTitle_notNull() {
		String filePath = this.getClass().getResource("/세계를 움직인 맞수.txt").getPath();

		String actual = null;
		try {
			actual = MetaInfo.checkTitle(filePath);
		} catch (NullPointerException e) {
			Assert.assertNull(actual);
		}
	}

	@Test
	public void testExtractTableOfContents() {

		String filePath = this.getClass().getResource("/k1774.txt").getPath();

		List<String> expecteds = new LinkedList<String>();
		expecteds.add("책이름: 서울 피터팬");
		expecteds.add("지은이: 야설록");
		expecteds.add("");
		expecteds.add("본 데이터의 무단 전재 및 복제를 금합니다.");
		expecteds.add("");
		expecteds.add("");
		expecteds.add("----- 차 례 -----");
		expecteds.add("");
		expecteds.add("⊙ 작가 소개");
		expecteds.add("1. 신을 경배하다");
		expecteds.add("2. 무한한 자유를 위하여");
		expecteds.add("3. 마침내 날다");
		expecteds.add("");
		expecteds.add("");
		expecteds.add("⊙ 작가 소개");
		expecteds.add("- 야설록");
		expecteds.add("");

		List<String> actuals = MetaInfo.extractTableOfContents(TxtFileReaderService
				.readFile(filePath));
		Assert.assertArrayEquals(expecteds.toArray(new String[0]),
				actuals.toArray(new String[0]));
	}

	@Test
	public void testExtractMeta() {
		String title = "러시아 문화의 이해";

		URL url = this.getClass().getResource("/k316.txt"); // file:/C:/Users/Seokgyu/workspace/EBook/bin/k316.txt
		File fileName = new File(url.getFile()); // C:\Users\Seokgyu\workspace\EBook\bin\k316.txt

		long length = 235473;

		MetaInfo actuals = MetaInfo.extract(fileName.getAbsolutePath());

		Assert.assertEquals(title, actuals.getTitle());
		Assert.assertEquals(fileName.getAbsolutePath(), actuals.getFilePath());
		Assert.assertEquals(length, actuals.getFileSize());
	}

	@Test
	public void testExtractMeta_korFilePath() {

		String title = "세계를 움직인 맞수";
		String filePath = "";
		long length = 255173;

		URL url = this.getClass().getResource("/세계를 움직인 맞수.txt");
		File fileName = new File(url.getFile());
		try {
			filePath = URLDecoder.decode(fileName.getAbsolutePath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		MetaInfo actuals = MetaInfo.extract(filePath);

		Assert.assertEquals(title, actuals.getTitle());
		Assert.assertEquals(filePath, actuals.getFilePath());
		Assert.assertEquals(length, actuals.getFileSize());

	}

}
