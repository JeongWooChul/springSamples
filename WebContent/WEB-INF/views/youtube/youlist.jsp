<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">

<form action="" name="frmForm1" id="_frmForm" method="get">
<table style="margin-left: auto;margin-right: auto;margin-top: 3px;margin-bottom: 3px; border: 0; padding: 0">

<tr>
	<td>검색:</td>
	<td style="padding-left: 5px">
		<input type="text" id="_s_keyword" name="s_keyword" value="${empty s_keyword?'':s_keyword }">
	</td>
	<td style="padding-left: 5px">
		<span class="button blue">
			<button type="button" id="_btnSearch">검색</button>
		</span>
	</td>
</tr>
</table>
</form>
</div>

<div id="_youtube_">
	<iframe id="_youtube" width="640" height="360" src="http://www.youtube.com/embed/" frameborder="0" allowfullscreen>
		
	</iframe>
</div>

<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width: 70px">
	<col style="width: auto">
	<col style="width: 100px">
	<col style="width: 50px">
</colgroup>

<thead>
	<tr>
		<th>번호</th> <th>제목</th> <th>유튜브 고유번호</th> <th>저장</th>
	</tr>
</thead>

<tbody>


</tbody>
<c:if test="${empty yulist }">
<tr>
	<td colspan="4">작성된 목록이 없습니다</td>
</tr>
</c:if>

<c:forEach items="${yulist }" var="you" varStatus="vs">
<tr class="_hover_tr">
	<td>${vs.count }</td>
	<td style="text-align: left;" id="_v${you.vname }ed2" onclick="getyoutube2('${you.vname}')">
		<div class="c_vname" vname='${you.vname }'>${you.title }</div>
	</td>
	<td>${you.vname }</td>
	<td onclick="getyoutube('${login.id}', '${you.vname }')">
		<img alt="" src="image/save.png" class="ck_seq" vname='${you.vname }' id='_v${you.vname }ed' 
			loginId='${login.id }' title="${you.title }" keyword='${empty s_keyword?"":s_keyword }'/>
	<td>
</tr>

</c:forEach>

</table>




<script type="text/javascript">
$(document).ready(function() {
	$("#_youtube_").hide();
	$("._hover_tr").mouseover(function(){
		$(this).children().css("backgroundcolor", "#f0f5ff")
	}).mouseout(function() {
		$(this).children().css("backgroundcolor", "#ffffff")
	});
});

$("#_btnSearch").click(function() {
//	alert("_btnSearch");
	$("#_frmForm").attr("action", "yutube.do").submit();
})

$(".c_vname").click(function() {
	$("#_youtube_").show();
	$("#_youtube").attr("src", "http://www.youtube.com/embed/" + $(this).attr("vname"));

	
});


</script>