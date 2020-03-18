<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>

<form action="" method="post" id="_frmForm">

<table border="1" class="list_table" style="width: 85%">
<colgroup>
	<col style="width:100px">
	<col style="width:auto">
</colgroup>
<tr>
	<th>아이디</th>
	<td>
		<%-- <input type="text" name="id" size="50px" value="<%=mem.getId() %>"> --%>
		<input type="text" name="id" size="70px" value=${login.id } readonly="readonly">
	</td>
</tr>

<tr>
	<th>제목</th>
	<td>
		<input type="text" id="_title" name="title" size="70px">
	</td>
</tr>

<tr>
	<th>내용</th>
	<td>
		<textarea rows="20" cols="50px" id="_content" name="content"></textarea>
	</td>
</tr>
<tr>
	<td colspan="2">
		<a href="#none" id="_btnWrite" title="글쓰기">
			<img alt="" src="./image/bwrite.png">
		</a>
	</td>
</tr>

</table>

</form>
</div>


<script type="text/javascript">
$('#_btnWrite').click(function() {
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
			url:"writeAf.do",
			type:"POST",
			data: $("#_frmForm").serialize(),
			success:function(result) {
				if(result.trim() == "N") {
					alert("글쓰기 실패");
				} else {
					alert("글쓰기 성공");
					location.href="bbsdetail.do?seq="+result;
				}		
			},
			error:function() {
				alert("error");
			}	
		})
	}
})

</script>





</body>
</html>