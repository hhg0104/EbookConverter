<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<td width="150" height="400">
<form:form action="/EbookConverter/bookInfo" method="PUT">
<input type = "submit" value="step1 제목&저자">
</form:form>
<form:form action="/EbookConverter/paragraph/startLine" method="PUT">
<input type = "submit" value="step2 첫문단찾기">
</form:form>
<form:form action="/EbookConverter/paragraph/option" method="PUT">
<input type = "submit" value="step3 문단정렬방식">
</form:form>
<input type = "submit" value="step4 다운로드"><br>
<br>
<input type = "button" value="바로변환" onclic="" ><br>
            
        
        </td>
        <td>