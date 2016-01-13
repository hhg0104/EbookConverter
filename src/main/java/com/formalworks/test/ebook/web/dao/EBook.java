package com.formalworks.test.ebook.web.dao;

import java.util.List;
import java.util.Map;

import com.formalworks.test.ebook.web.model.MetaInfo;


public interface EBook {
	
	String getTableOfContentsByOrderNum();

	void setTableOfContentsByOrderNum(String startLineOfTable);

	String getParagarphOption();
	
	void setParagraphOption(String option);
	
	List<String> getOriginalContents();

	MetaInfo getMetaInfo();
	
	List<String> getTableOfContents();
	
	void setContents(List<String> content);

	List<String> getMDContents();

	void setMDContents(List<String> mdContents);

	List<String> getFullMDContents();

	void setFullMDContents(List<String> fullMDContents);
	
	String getFilePath();

	String getOutputMdFilePath();

	void setMetaInfo(MetaInfo metaInfo);
	
	void setContributor(String contributor);
	void setCoverage(String coverage);
	
	int getStartLine();

	Map<String, List<String>> getPreviewContents();

	void setPreviewContents(Map<String, List<String>> previewContents);

	String getSampleContents();

	void setSampleContents(String sampleContents);

	void setStartLine(int startLine);
	
	void setOutputMdFilePath(String path);

	void setCreator(String creator);

	void setDate(String date);

	void setDescription(String description);

	void setFormat(String format);

	void setIdentifier(String identifier);

	void setLanguage(String language);

	void setPublisher(String publisher);

	void setRelation(String relation);

	void setRights(String rights);

	void setSource(String source);

	void setSubject(String subject);

	void setTitle(String title);

	void setType(String type);

	void setOutputPath(String outputPath);
	
	// 책 표지 이미지
	void setCoverImg(String cover);

	String getCoverImg();
	
}
