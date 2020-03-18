<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- paggin 연산 -->

<!-- << < 2 3 3 4 > >> --> 
<%
int totalRecordCount;	// 전체 글의 수		23 -> 3
int pageNumber;			// 현재 페이지		0 ~ 9 -> [1] ~ [10]	
int pageCountPerScreen; // 스크린당 페이지수	[1]~[10], [1]~[5]	-> 10
int recordCountPerPage;	// 페이지당 글의 수   [1] -> 10

String st1 = request.getParameter("totalRecordCount");
if(st1==null) totalRecordCount = 0;
else totalRecordCount = Integer.parseInt(st1);

String st2 = request.getParameter("pageNumber");
if(st2==null) pageNumber =0;
else pageNumber = Integer.parseInt(st2);

String st3 = request.getParameter("pageCountPerScreen");
if(st3==null) pageCountPerScreen = 0;
else pageCountPerScreen = Integer.parseInt(st3);

String st4 = request.getParameter("recordCountPerPage");
if(st4==null) recordCountPerPage = 0;
else recordCountPerPage = Integer.parseInt(st4);

/* (레코드수 = 글의 수)  != 페이지수 */
// 총 페이지의 수 	[1][2][3] => 3
int totalPageCount = totalRecordCount / recordCountPerPage;
//		2			    전체 개시글 수(23)		    페이지당 글 수(10)

/* 개시글의 수가 페이지 갯수를 넘으면 페이지 추가 */
if((totalRecordCount % recordCountPerPage) != 0 ) {
	totalPageCount++;
}

//시작 페이지 세팅 [1] 	[11] 	[21]
int screenStartPageIndex = ((pageNumber +1)/pageCountPerScreen) * pageCountPerScreen;            
//			1						0					10				10
//			10						9					10				10

// 끝 페이지 [10]	[20]	[30]
int screenEndPageIndex = (((pageNumber +1)/pageCountPerScreen) * pageCountPerScreen) +pageCountPerScreen ;
//			1					0					10					10						10			

// 끝 페이지는 다시 계산 -> [1]~[10] [1]~[3]
if(screenEndPageIndex > totalPageCount){
	screenEndPageIndex = totalPageCount;
}

// 0 ~ 9  ->  1 ~ 10
if((pageNumber + 1) % pageCountPerScreen == 0){
	screenStartPageIndex = (((pageNumber + 1) /pageCountPerScreen) * pageCountPerScreen) - pageCountPerScreen;
	screenEndPageIndex = pageNumber + 1; // 0 -> 1             9 ->10
}




%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div style="float: left;width: 96%; text-align: center;">
	<!-- << -->
	<a href="#none" title="처음페이지" onclick="goPage(0)">
		<img alt="" src="./image/arrow_first.gif" style="width: 9px; height: 9px">
	</a>
	<!-- < [11]~[20]  ->  [1] ~ [10] -->
	<%
		if(screenStartPageIndex > 1) {
	%>
		<a href="#" title="이젠페이지" onclick="goPage(<%=screenStartPageIndex-1%>)">
			<img alt="" src="./image/arrow_back.gif" style="width: 9px; height: 9px" >
		</a>
	
	<% 
	} %>
	
	

	
	<!-- [1] 2 [3] -->
	<%
	for(int i = screenStartPageIndex; i < screenEndPageIndex; i++) {
		if(i == pageNumber){ // 현재 페이지 2 
			%>
			<span style="font-size: 9pt; color: #000000;font-weight: bold;">
				<%=i+1 %>
			</span>
			<%
		}else {	// 그외 페이지 [1] 2 [3] [4]
			%>
			<a href="#" title="<%=i+1%>페이지" onclick="goPage(<%=i%>)" style="font-size: 7.5pt; color: #000000; font-weight: normal;">
				[<%=i+1 %>]
			</a>
			<%
		}
	}
	
	%>
	<!-- > 1~10 0> 11~ 20-->
	<%
	if(screenEndPageIndex < totalPageCount){	//[11][12][13]
		%>
		<a href="#" title="다음페이지" onclick="goPage(<%=screenEndPageIndex%>)">
			<img alt="" src="./image/arrow_next.gif" style="width: 9px; height: 9px">
		</a>
		<%
	}
	int end_page = 0;
	if(totalPageCount > 0) {
		end_page = totalPageCount - 1;
	}
	
	
	%>
	
	<!-- >> -->
	
	<a href="#" title="마지막 페이지" onclick="goPage(<%=end_page%>)">
		<img alt="" src="./image/arrow_end.gif"  style="width: 9px; height: 9px">
	</a>
	
	
	
	
	
	

</div>


</body>
</html>