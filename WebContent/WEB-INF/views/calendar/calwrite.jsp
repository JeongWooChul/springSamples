<%@page import="bit.com.a.model.CalDateParam"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

/* 
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day"); 
*/
CalDateParam jcal = (CalDateParam)request.getAttribute("jcal"); 

String year=""+jcal.getYear();
String month=""+jcal.getMonth();
String day=""+jcal.getDay();

//범위를 설정하는 날짜. 기준
Calendar cal = Calendar.getInstance();

int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH) + 1;	// 0 ~ 11
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR_OF_DAY);
int tmin = cal.get(Calendar.MINUTE);
    
%>
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
	<col style="width:150px">
	<col style="width:auto">
</colgroup>
<tr>
	<th>아이디</th>
	<td style="text-align: left">
		${login.id }
		<input type="hidden" name="id" value="${login.id }">		
	</td>
</tr>
<tr>
	<th>제목</th>
	<td style="text-align: left">
		<input type="text" size="60" id="_title" name="title">
	</td>
</tr>
<tr>
	<th>일정</th>
	<td style="text-align: left">
		<select name="year">
		<%
			for(int i = tyear - 5;i < tyear + 6 ; i++){
				%>	
				<option <%=year.equals(i + "")?"selected='selected'":"" %>
								value="<%=i %>">
					<%=i %>	
				</option>
				<%
			}		
		%>		
		</select>년
		
		<select name="month">
		<%
			for(int i = 1;i <= 12 ; i++){
				%>	
				<option <%=month.equals(i + "")?"selected='selected'":"" %>
								value="<%=i %>">
					<%=i %>	
				</option>
				<%
			}		
		%>		
		</select>월
		
		<select name="day">
		<%
			for(int i = 1;i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
				%>	
				<option <%=day.equals(i + "")?"selected='selected'":"" %>
								value="<%=i %>">
					<%=i %>	
				</option>
				<%
			}		
		%>		
		</select>일
		
		<select name="hour">
		<%
			for(int i = 0;i < 24; i++){
				%>	
				<option <%=(thour + "").equals(i + "")?"selected='selected'":"" %>
								value="<%=i %>">
					<%=i %>	
				</option>
				<%
			}		
		%>		
		</select>시
		
		<select name="min">
		<%
			for(int i = 0;i < 60; i++){
				%>	
				<option <%=(tmin + "").equals(i + "")?"selected='selected'":"" %>
								value="<%=i %>">
					<%=i %>	
				</option>
				<%
			}		
		%>		
		</select>분		
	</td>
</tr>

<tr>
	<th>내용</th>
	<td style="text-align: left">
		<textarea rows="20" cols="60" id="_content" name="content"></textarea>
	</td>
</tr>

<tr>
	<td colspan="2">
		<button type="button" id="btnCalWrite">일정추가</button>
	</td>
</tr>
</table>
</form>
</div>



<script type="text/javascript">
//setday();
$("select[name='day']").val("<%=day %>");

$(document).ready(function () {	
	$("select[name='year']").change( setday );	
	$("select[name='month']").change( setday );	
});

function setday() {
	// 해당년도의 월을 통해서 마지막 날짜를 구한다.
	var year = $("select[name='year']").val();
	var month = $("select[name='month']").val();
	
	var lastday = (new Date(year, month, 0)).getDate();
//	alert(lastday);
	
	// select 날짜 적용
	var str = "";
	for(i = 1;i <= lastday; i++){
		str += "<option value='" + i + "'>" + i + "</option>";
	}
	$("select[name='day']").html( str );
}


$('#btnCalWrite').click(function() {
	if($('#_title').val().trim() == "") {
		alert("제목을 입력해 주세요");
		$('#_title').focus();
	}
	else if ($('#_content').val().trim() == "") {
		alert("내용을 입력해 주세요");
		$('#_content').focus();
	}
	else {
//		$('#_fromForm').attr("action","calwriteAf.do").submit();
		$.ajax({
			url:"calWriteAf.do",
			type:"POST",
			data: $('#_frmForm').serialize(),
			success:function(result) {
				if(result.trim() == "N") {
					alert("일정이 추가 되지 않았습니다");
				} else {
					alert("일정이 추가 되었습니다");
					location.href="calendarlist.do?";
				}		
			},
			error:function() {
				alert("error");
			}	


		});
	}

	
})
</script>







</body>
</html>