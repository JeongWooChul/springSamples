<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="list_table" style="width: 85%">
<colgroup>
	<col width="50">	<col width="100">	<col width="300">	<col width="50">
	<col width="50">	<col width="50">	<col width="100">	<col width="50">
</colgroup>

<thead>
<tr>
	<th>번호</th>
	<th>작성자</th>
	<th>제목</th>
	<th>다운로드</th>
	<th>조회수</th>
	<th>다운수</th>
	<th>작성일</th>
	<th>삭제</th>
</tr>
</thead>

<tbody>

<c:forEach var="pds" items="${pdslist }" varStatus="vs">
<tr class="_hover_tr">
	<td>${vs.count }</td>
	<td>${pds.id }</td>
	<td style="text-align: left;">
		<a href="pdsdetail.do?seq=${pds.seq }">
			${pds.title }
		</a>
	</td>
	<td>
		<input type="button" name="btnDown" value="다운로드" onclick="filedowns('${pds.filename}', '${pds.seq }')">
	</td>
	<td>${pds.readcount }</td>
	<td>${pds.downcount }</td>
	<td>
		<font size="1">${pds.regdate }</font>
	</td>
	<td>
		<img alt="" src="image/del.png" data_file_seq="${pds.seq }" class="btn_fileDelete">
	</td>
</tr>

</c:forEach>
</tbody>
</table>

<!-- 추가 버튼 -->
<div id="button.wrap">
	<span class="button blue">
		<button type="button" id="_btnAdd">자료추가</button>
	</span>
</div>



<script type="text/javascript">
$('#_btnAdd').click(function(){
	//alert("_btnAdd");
	location.href="pdswrite.do";
})

</script>