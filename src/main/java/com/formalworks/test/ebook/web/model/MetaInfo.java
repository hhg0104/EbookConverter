package com.formalworks.test.ebook.web.model;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.formalworks.test.ebook.web.service.RecognizingTableOfContents;
import com.formalworks.test.ebook.web.service.TxtFileReaderService;

@Component
public class MetaInfo {

	private final File file;
	private String fileName = "";
	private String contributor;
	private String author;
	private String coverage;
	private String creator;
	private String date;
	private String description;
	private String format;
	private String identifier;
	private String language;
	private String publisher;
	private String relation;
	private String rights;
	private String source;
	private String subject;
	private String title;
	private String type;
	private String outputPath;
	private String hashCode;

	private MetaInfo(File file) {
		this.file = file;
		this.fileName = "";
		this.hashCode = "";
		this.author = getAuthorFromContent(file.getAbsolutePath());
		this.title = checkTitle(file.getAbsolutePath());
		this.contributor = "";
		this.coverage = "";
		this.creator = "";
		this.date = "";
		this.description = "";
		this.format = "";
		this.identifier = "";
		this.language = "";
		this.publisher = "";
		this.relation = "";
		this.rights = "";
		this.source = "";
		this.subject = "";
		this.type = "";
	}

	public MetaInfo() {
		this.file = null;
		this.title = "";
		this.contributor = "";
		this.coverage = "";
		this.creator = "";
		this.date = "";
		this.description = "";
		this.format = "";
		this.identifier = "";
		this.language = "";
		this.publisher = "";
		this.relation = "";
		this.rights = "";
		this.source = "";
		this.subject = "";
		this.type = "";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContributor() {
		return contributor;
	}

	public void setContributor(String contributor) {
		this.contributor = contributor;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilePath() {
		return this.file.getAbsolutePath();
	}

	public long getFileSize() {
		return file.length();
	}

	public static MetaInfo extract(String filePath) {

		File file = new File(urlDecoder(filePath));

		return new MetaInfo(file);
	}


	public static String checkTitle(String filePath) {
		String result = null;

		filePath = urlDecoder(filePath);

		if (filePath.isEmpty()) {
			System.out.println("filePath is empty");
		} else {
			if (isPackedFile(filePath)) {
				result = getTitleFromFileName(filePath);
			} else if (StringUtils
					.deleteWhitespace(getTitleFromFileName(filePath))
					.equals(StringUtils
							.deleteWhitespace(getTitleFromFirstLineOfContent(filePath)))) {
				result = getTitleFromFileName(filePath);
			} else {
				result = getTitleFromFirstLineOfContent(filePath).trim();
			}
		}
		return result;

	}

	public static String urlDecoder(String filePath) {
		try {
			String dec = "UTF-8";
			filePath = URLDecoder.decode(filePath, dec);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filePath;
	}

	private static boolean isPackedFile(String filePath) {
		return false;
	}

	private static String getTitleFromFileName(String filePath) {
		File file = new File(filePath);
		String titleName = StringUtils.removeEndIgnoreCase(file.getName(),
				".txt");
		return removeSpecial(titleName);
	}

	private static String removeSpecial(String str) {
		str = removeNoMeaning(str);
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		str = str.replaceAll(match, "");
		return str;
	}

	static String[] filter_title = { "장편소설", "책이름:", "도서명:", "책명:" };

	private static String removeNoMeaning(String str) {
		String str_imsi = null;

		for (int i = 0; i < filter_title.length; i++) {
			str_imsi = str.replaceAll(filter_title[i], "");
			str = str_imsi;
		}

		return str;
	}

	private static String getTitleFromFirstLineOfContent(String filePath) {
		int i = 0;
		String result = null;
		do {
			result = TxtFileReaderService.readFile(filePath).get(i++);
		} while (result.isEmpty());

		while (StringUtils.deleteWhitespace(removeSpecial(result)).isEmpty()) {
			result = TxtFileReaderService.readFile(filePath).get(i++);
		}
		return removeSpecial(result);
	}


	private String getAuthorFromContent(String filePath) {
		List<String> listForAuthor = TxtFileReaderService.readFile(filePath);
		String author = null;
		int length = 0;
		if (listForAuthor.size() > 50)
			length = 50;
		else
			length = listForAuthor.size();

		author = filteringAuthor(listForAuthor, author, length);

		return author;
	}

	private String filteringAuthor(List<String> listForAuthor, String author, int length) {
		author = "";
		String[] filter_author = { "저자", "글쓴이", "작가", "지은이" };
		for (int i = 0; i < length; i++) {
			String line = StringUtils.deleteWhitespace(listForAuthor.get(i));
			
			for (String filter : filter_author) {

				if (StringUtils.containsIgnoreCase(line, filter)){
					author = removeSpecialForAuthor(line).replace(filter, "");
					return author;
				}
			}
		}
		return author;
	}

	public String removeSpecialForAuthor(String str) {
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		str = str.replaceAll(match, "");
		return str;
	}

	public static List<String> extractTableOfContents(List<String> originalTextContents) {
		List<String> results = new ArrayList<String>();

		int startParagraphLine = findStartPargraphline(originalTextContents);

		int i = 0;
		while (i < startParagraphLine) {
			results.add(originalTextContents.get(i++).trim());
		}
		return results;
	}

	public static int findStartPargraphline(List<String> originalTextContents) {
		RecognizingTableOfContents table = new RecognizingTableOfContents();
		int result = 0;
		while (originalTextContents.get(result).length() < table
				.findLongestLineLength(originalTextContents) * 8 / 10) {
			result++;
		}
		return result;
	}

	public String getOutputMdFilePath() {

		if (this.outputPath == null) {
			outputPath = file.getParentFile().getAbsolutePath()
					+ File.separator + getTitle() + ".md";
		}
		return outputPath;
	}

	public void setOutputMdFilePath(String path) {

		this.outputPath = path;

	}

}
