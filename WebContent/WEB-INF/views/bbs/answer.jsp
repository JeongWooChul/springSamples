<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1 align="left">원글</h1>
<table border="1" class="list_table" style="width: 85%">
<colgroup>
	<col style="width:100px">
	<col style="width:auto">
</colgroup>
<tr>
	<th>작성자</th>
	<td>${_bbs.id }</td>
</tr>

<tr>
	<th>제목</th>
	<td>${_bbs.title }</td>
</tr>

<tr>
	<th>작성일</th>
	<td>${_bbs.wdate }</td>
</tr>

<tr>
	<th>조회수</th>
	<td>${_bbs.readcount }</td>
</tr>

<tr>
	<th>정보</th>
	<td>${_bbs.ref }-${_bbs.step }-${_bbs.depth }</td>
</tr>

<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="70" readonly="readonly">${_bbs.content }</textarea>
	</td>
</tr>
</table>



<hr>


<h1 align="left">답글</h1>

<form action="" method="get" id="_frmForm">
<input type="hidden" name="seq" value="${_bbs.seq }">

<table border="1" class="list_table" style="width: 85%">
<colgroup>
	<col style="width:100px">
	<col style="width:auto">
</colgroup>
<tr>
	<th>아이디</th>
	<td>
		<input type="text" name="id" readonly="readonly" size="50"
			value="${login.id }">
	</td>
</tr>

<tr>
	<th>제목</th>
	<td>
		<input type="text" id="_title" name="title" size="50">
	</td>
</tr>

<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="70" id="_content" name="content"></textarea>
	</td>
</tr>
	
<tr>
	<td colspan="2" align="center">
		<a href="#none" id="_reply" title="답글">
			<img alt="" src="./image/breply.png">
		</a>
	</td>
</tr>


</table>

</form>
</body>

<script type="text/javascript">
$('#_reply').click(function() {
	if($('#_title').val().trim() == "") {
		alert("제목을 입력 해주세요")
		$('#_title').focus();
	}
	else if($('#_content').val().trim() == "") {
		alert("내용을 입력 해주세요")
		$('#_content').focus();
	}
	else {
		$.ajax({
			url:"answerAf.do",
			type:"GET",
			data:$('#_frmForm').serialize(),
			success:function(result) {
				//alert(result);
				if(result.trim() == "Y") {
					alert("댓글이 추가 되었습니다");
					location.href="bbslist.do";
				} else {
					alert("댓글이 추가되지 않았습니다");
				}
			},
			error:function(){
				alert("error");
			}
				
		})
	//	location.href="answer.do?seq="+${getBbs.seq};
	}
})
</script>




</html>