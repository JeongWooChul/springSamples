<%@page import="java.util.List"%>
<%@page import="bit.com.a.model.PollSubDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<%
List<PollSubDto> list = (List<PollSubDto>)request.getAttribute("pollsublist");

// json 생성
String jsonData = "[";
for(PollSubDto p : list) {
	jsonData += "{ name:'" + p.getAnswer() + "', y: " + p.getAcount() + "}, ";
}

jsonData = jsonData.substring(0, jsonData.lastIndexOf(","));
jsonData += "]";

System.out.println(jsonData);

request.setAttribute("jsonData", jsonData);
%>




<table class="list_table" style="width: 95%">
<colgroup>
	<col width="200px">
	<col width="500px">
</colgroup>

<tr>
	<th>투표번호</th>
	<td style="text-align: left;">
		<input type="text" value="${poll.pollid }" size="50" readonly="readonly">
	</td>
</tr>

<tr>
	<th>아이디</th>
	<td style="text-align: left;">
		<input type="text" value="${login.id }" size="50" readonly="readonly">
	</td>
</tr>

<tr>
	<th>투표기한</th>
	<td style="text-align: left;">
		${poll.sdate } ~ ${poll.edate }
	</td>
</tr>

<tr>
	<th>투표내용</th>
	<td style="text-align: left;">
		<textarea rows="10" cols="50" readonly="readonly">${poll.question }</textarea>	
	</td>
</tr>

<tr>
	<th>투표결과</th>
	<td>
		<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto;">
		
		</div>
	</td>
</tr>


</table>




<script type="text/javascript">


Highcharts.chart('container', {
    chart: {
        type: 'column'
    },
    title: {
        text: '좋아하는 과일은??'
    },
    subtitle: {
        text: '추가 링크: <a href="http://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
    },
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45,
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: '투표수 (표)'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: 'Poll Count: <b>{point.y:.1f} 표</b>'
    },
    series: [{
        name: 'Population',
        data: <%=request.getAttribute("jsonData") %>,
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:.1f}', // one decimal
            y: 10, // 10 pixels down from the top
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    }]
});


</script >