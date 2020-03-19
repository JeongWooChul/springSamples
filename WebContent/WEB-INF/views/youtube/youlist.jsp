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






<script type="text/javascript">


</script>