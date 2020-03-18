<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="center">

<form action="" method="post" id="_frmForm">
<input type="hidden" name="seq" value="${getBbs.seq }">
			
<table  border="1" class="list_table" style="width: 85%">
<colgroup>
	<col style="width:100px">
	<col style="width:auto">
</colgroup>
<tr>
	<th>아이디</th>
	<td>
		<input type="text" name="id" readonly="readonly" size="50" 
			value="${getBbs.id }"> 		
	</td>	
</tr>
<tr>
	<th>제목</th>
	<td>
		<input type="text" id="_title" name="title" size="50" value="${getBbs.title }">		
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="50" id="_content" name="content">${getBbs.content }</textarea>		
	</td>
</tr>
<tr>
	<td colspan="2">
		<a href="#none" id="_btnUpdate" title="글수정">
			<img alt="" src="./image/bupdate.png">
		</a>
	</td>
</tr>
</table>
</form>
</div>

<script type="text/javascript">
$('#_btnUpdate').click(function() {
	if($('#_title').val().trim() == "") {
		alert("제목을 입력해 주세요");
		$('#_title').focus();
	}
	else if ($('#_content').val().trim() == "") {
		alert("내용을 입력해 주세요");
		$('#_content').focus();
	}
	else {
		$.ajax({
			url:"bbsupdateAf.do",
			type:"GET",
			data: $("#_frmForm").serialize(),
			success:function(result) {
				if(result.trim() == "N") {
					alert("수정 실패");
				} else {
					alert("수정 성공");
					location.href="bbsdetail.do?seq="+result;
				}		
			},
			error:function() {
				alert("error");
			}	

		});

	}
});

</script>

</body>
</html>