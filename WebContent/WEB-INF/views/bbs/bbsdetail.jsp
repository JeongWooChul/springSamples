<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<table border="1" class="list_table" style="width: 85%">
<colgroup>
	<col style="width: 150">
	<col style="width: 600">
</colgroup>

<tr>
	<th>작성자</th>
	<td>${getBbs.id}</td>
</tr>

<tr>
	<th>제목</th>
	<td>${getBbs.title}</td>
</tr>

<tr>
	<th>작성일</th>
	<td>${getBbs.wdate}</td>
</tr>

<tr>
	<th>조회수</th>
	<td>${getBbs.readcount}</td>
</tr>

<tr>
	<th>정보</th>
	<td>${getBbs.ref}-${getBbs.step}-${getBbs.depth}</td>
</tr>

<tr>
	<th>내용</th>
	<td align="center">
		<textarea rows="10" cols="90" readonly="readonly">${getBbs.content}</textarea>
	</td>
</tr>



<tr>
	<td colspan="2">
		<%-- <button type="button" onclick="updateBbs(${getBbs.seq})">수정</button> --%>
		<%-- <button type="button" onclick="location.href='bbsdelete.do?=seq${getBbs.seq}'">삭제</button> --%>
		<c:if test="${login.id == getBbs.id }">
		<a href="#none" id="_update" title="수정">
			<img alt="" src="./image/bupdate.png">
		</a>
		<a href="#none" id="_delete" title="삭제">
			<img alt="" src="./image/del.png">
		</a>
		</c:if>
		<a href="#none" id="_reply" title="답글">
			<img alt="" src="./image/breply.png">
		</a>
	</td>
</tr>

</table>


<script type="text/javascript">
$('#_update').click(function() {
	var result = confirm("글을 수정 합니다");
	if(result) {
		location.href="bbsupdate.do?seq="+${getBbs.seq};
	}
});

$('#_delete').click(function() {
	var result = confirm("글을 삭제 합니다");
	if(result) {
		location.href="bbsdelete.do?seq="+${getBbs.seq};
	}
});

$('#_reply').click(function() {
	location.href="answer.do?seq="+${getBbs.seq};
})
</script>



</body>
</html>