package com.formalworks.test.ebook.web.input;

import static org.junit.Assert.*;

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

import com.formalworks.test.ebook.web.dao.EBook;
import com.formalworks.test.ebook.web.model.MetaInfo;
import com.formalworks.test.ebook.web.service.TxtFileReaderService;

public class EBookInitTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadEBook_getOriginalContents() {

		String filePath = this.getClass().getResource("/testInput.txt").getPath();

		List<String> expecteds = new LinkedList<String>();
		expecteds.add("testInput");
		expecteds.add("1234312432");
		expecteds.add("fdsafdsafdsa");
		expecteds.add("ㅅㄱㄷㅄㄱㅂㄷ");
		expecteds.add("ㄹㅇㄴ;ㅣ머라이;ㄴㅁ");
		expecteds.add("ㄹ;아ㅓㄴㅁㄹ;ㅏㅣㅇㄴㅁ");
		expecteds.add("ㄹㅇㄴㅁ;ㅣㅏ렁ㄴ;ㅁ");

		EBook ebook = TxtFileReaderService.read(filePath);

		List<String> actuals = ebook.getOriginalContents();

		Assert.assertArrayEquals(expecteds.toArray(new String[0]),
				actuals.toArray(new String[0]));
	}

	@Test
	public void testReadFile_emptyFile() {

		String filePath = this.getClass().getResource("/emptyFile.txt").getPath();

		List<String> actuals = null;

		List<String> expecteds = new LinkedList<String>();

		try {

			EBook ebook = TxtFileReaderService.read(filePath);

			actuals = ebook.getOriginalContents();

		} catch (NullPointerException e) {
			Assert.assertArrayEquals(expecteds.toArray(new String[0]),
					actuals.toArray(new String[0]));
		} catch (IndexOutOfBoundsException e) {

		}
	}

	@Test
	public void testReadEBook_notNull() {

		String filePath = this.getClass().getResource("/testInput.txt").getPath();

		EBook ebook = TxtFileReaderService.read(filePath);

		List<String> actuals = ebook.getOriginalContents();

		Assert.assertNotNull(actuals);
	}

	@Test
	public void testReadEBook_null() {

		List<String> actuals = null;

		try {

			EBook ebook = TxtFileReaderService.read(null);

			actuals = ebook.getOriginalContents();

		} catch (NullPointerException e) {

			Assert.assertNull(actuals);
		}
	}

	@Test
	public void testCheckEBookTitle() {

		String filePath = this.getClass().getResource("/세계를 움직인 맞수.txt").getFile();

		filePath = decodeKo(filePath);
		
		String expected = "세계를 움직인 맞수";

		EBook ebook = TxtFileReaderService.read(filePath);

		String actual = ebook.getMetaInfo().getTitle();

		Assert.assertEquals(expected, actual);
	}

	private String decodeKo(String filePath) {
		try {
			filePath = URLDecoder.decode(filePath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			fail();
		}
		return filePath;
	}

	@Test
	public void testCheckEBookTitle_prefix() {

		String filePath = this.getClass().getResource("/서울 피터팬.txt").getPath();
		
		filePath = decodeKo(filePath);
		
		String expected = "서울 피터팬";
		
		EBook ebook = TxtFileReaderService.read(filePath);

		String actual = ebook.getMetaInfo().getTitle();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckEBookTitle_special() {

		String filePath = this.getClass().getResource("/최후의 증인 1.txt").getPath();
		filePath = decodeKo(filePath);
		String expected = "최후의 증인 1";

		EBook ebook = TxtFileReaderService.read(filePath);

		String actual = ebook.getMetaInfo().getTitle();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckEBookTitle_notSame() {

		String filePath = this.getClass().getResource("/k316.txt").getPath();

		String expected = "러시아 문화의 이해";

		EBook ebook = TxtFileReaderService.read(filePath);

		String actual = ebook.getMetaInfo().getTitle();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckEBookTitle_notSameAndSpecial() {

		String filePath = this.getClass().getResource("/k709.txt").getPath();

		String expected = "삶의 행복을 주는 114가지 지혜";

		EBook ebook = TxtFileReaderService.read(filePath);

		String actual = ebook.getMetaInfo().getTitle();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckEBookTitle_serchNextLine() {

		String filePath = this.getClass().getResource("/빙벽1.txt").getPath();
		
		filePath = decodeKo(filePath);
			
		String expected = "빙벽1  고원정저";

		EBook ebook = TxtFileReaderService.read(filePath);

		String actual = ebook.getMetaInfo().getTitle();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCheckEBookTitle_noMeaning() {

		String filePath = this.getClass().getResource("/k851.txt").getPath();

		String expected = "정선 아리랑";

		EBook ebook = TxtFileReaderService.read(filePath);

		String actual = ebook.getMetaInfo().getTitle();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testExtractEBookTableOfContents() {

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

		EBook ebook = TxtFileReaderService.read(filePath);

		List<String> actuals = ebook.getTableOfContents();

		Assert.assertArrayEquals(expecteds.toArray(new String[0]),
				actuals.toArray(new String[0]));
	}

	@Test
	public void testExtractEBookMeta() {

		String title = "세계를 움직인 맞수";
		URL fileUrl = System.class.getResource("/세계를 움직인 맞수.txt");

		File file = new File(fileUrl.getFile());

		String filePath = new String();
		try {
			filePath = URLDecoder.decode(file.getAbsolutePath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Assert.fail();
		}

		long length = 255173;

		EBook ebook = TxtFileReaderService.read(filePath);

		MetaInfo actuals = ebook.getMetaInfo();

		Assert.assertEquals(title, actuals.getTitle());
		Assert.assertEquals(filePath, actuals.getFilePath());
		Assert.assertEquals(length, actuals.getFileSize());
	}

	@Test
	public void testSetMetaData() {
		String filePath = this.getClass().getResource("/k1774.txt").getPath();

		EBook ebook = TxtFileReaderService.read(filePath);

		MetaInfo meta = new MetaInfo();
		meta.setContributor("zzz");
		meta.setCoverage("123");
		meta.setCreator("456");
		meta.setDate("789");
		meta.setDescription("ppp");
		meta.setFormat("aaa");
		meta.setIdentifier("bbb");
		meta.setLanguage("ccc");
		meta.setPublisher("ddd");
		meta.setRelation("eee");
		meta.setRights("fff");
		meta.setSource("ggg");
		meta.setSubject("hhh");
		meta.setType("iii");

		ebook.setMetaInfo(meta);

		List<String> expecteds = new LinkedList<String>();
		expecteds.add("zzz");
		expecteds.add("123");
		expecteds.add("456");
		expecteds.add("789");
		expecteds.add("ppp");
		expecteds.add("aaa");
		expecteds.add("bbb");
		expecteds.add("ccc");
		expecteds.add("ddd");
		expecteds.add("eee");
		expecteds.add("fff");
		expecteds.add("ggg");
		expecteds.add("hhh");
		expecteds.add("iii");

		List<String> actuals = new LinkedList<String>();
		actuals.add(ebook.getMetaInfo().getContributor());
		actuals.add(ebook.getMetaInfo().getCoverage());
		actuals.add(ebook.getMetaInfo().getCreator());
		actuals.add(ebook.getMetaInfo().getDate());
		actuals.add(ebook.getMetaInfo().getDescription());
		actuals.add(ebook.getMetaInfo().getFormat());
		actuals.add(ebook.getMetaInfo().getIdentifier());
		actuals.add(ebook.getMetaInfo().getPublisher());
		actuals.add(ebook.getMetaInfo().getRelation());
		actuals.add(ebook.getMetaInfo().getRights());
		actuals.add(ebook.getMetaInfo().getSource());
		actuals.add(ebook.getMetaInfo().getSubject());
		actuals.add(ebook.getMetaInfo().getType());

		Assert.assertArrayEquals(actuals.toArray(new String[0]),
				actuals.toArray(new String[0]));
	}
}