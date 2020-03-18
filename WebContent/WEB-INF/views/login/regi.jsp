<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" />  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css"/>

<!-- cookie -->
<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>

<style type="text/css">
#login_wrap {
	margin:10px;
}
#login_wrap th {
	font-weight:bold;
}
#main_wrap { 
	width:800px; 
	margin-left:auto; 
	margin-right:auto; padding:0px; 
}			
#content_wrap { 
	width: 100%; 
	height: 500px; 
	background-image:url("image/backa.jpg"); 
	background-repeat:no-repeat; 
	background-position:top center;  
}
			
.login_title_warp {
	width:500px; 
	color:#FFFFFF; 
	text-align:center; 
	background-color:#3e5fba; 
	border:solid 1px #EFEFEF; 
	font-weight:bold; 
	height:60px;
}

/* table셋팅 */
.content_table { width:98%; border-bottom:1px solid #EFEFEF; border-right:1px solid #EFEFEF; border-collapse:collapse; margin-left:auto; margin-right:auto;  clear:both; }
.content_table td, .content_table th { text-align:center; border-top:1px solid #EFEFEF; border-left:1px solid #EFEFEF; padding:0.3em; }
.content_table th { background-color:#4D6BB3; color:#FFFFFF; line-height: 1.7em; font-weight:normal;}
.content_table td { padding-left:5px; text-align:left; line-height: 1.7em; }
.content_table td.contents { width:100%; height:400px; overflow:auto; }
.content_table th, .content_table td { vertical-align:middle; }

.content_table select { height:19px; border:#CCCCCC solid 1px; vertical-align:middle; line-height: 1.8em; padding-left:0px; }
.content_table select option { margin-right:10px; }

</style>

</head>
<body>

<div id="main_wrap">
	<div id="middle_wrap">
		<div id="content_wrap">
			
			<div style="width: 502px; height: 166px; margin-left: auto; margin-right: auto;
						position: relative; top: 100px;">
								
			<div class="login_title_warp">
				<div style="margin-top: 15px">
					<h2>회원가입</h2>
				</div>			
			</div>
			
			<div id="login_warp">
				<form action="" method="post" id="_frmForm" name="froForm">
					<table class="content_table" style="width: 75%">
					<colgroup>
						<col style="width:30%">
						<col style="width:70%">
					</colgroup>
					
					<tr>
						<th>아이디 확인</th>
						<td>
						
							<input type="text" name="sid" id="_id" size="30">
							<a href="#none" id="_btnGetId" title="id확인">
								<img alt="" src="./image/idcheck.png">
							</a>
							<div id="_rgetid"></div>
						</td>
					</tr>
					
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" name="id" id="_userid" size="30" readonly="readonly">
						</td>
					</tr>
					
					<tr>
						<th>패스워드</th>
						<td>
							<input type="text" name="pwd" id="_pwd" size="30">
						</td>
					</tr>
					
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="name" id="_name" size="30">
						</td>
					</tr>
					
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" name="email" id="_email" size="30">
						</td>
					</tr>
					
					<tr>
						<td colspan="2" style="height: 50px;text-align: center;">
							<a href="#none" id="_btnRegi" title="회원가입">
								<img alt="" src="./image/regi.jpg">
							</a>
							
							<a href="#none" id="_btnLogin" title="로그인">
								<img alt="" src="./image/login_btn.jpg">
							</a>
							
						</td>
					</tr>
					</table>
				</form>
			</div>
			</div>		
		</div>	
	</div>
</div>

<script type="text/javascript">
$(function () {
	$('#_btnGetId').click(function () {
		//alert("확인");
		$.ajax({
			type:"post",
			url:"./idCheck.do",
			data:{"id":$("#_id").val()},
			success:function(data) {
				if(data.trim() == "N"){
					//$("#idcheck").css("color", "#0000ff");
					alert("사용 가능한 id입니다");
					$("#_userid").val($('#_id').val());
				}else{
					//$("#idcheck").css("color", "#ff0000")
					alert("사용중인 id입니다");
					$("#_btnGetId").val('');
					$("#_userid").val('');
				}		
			},
			error:function() {
				alert("error");
			}
		})
	})
})



$('#_btnRegi').click(function() {
	//alert("확인");
	if($("#_userid").val().trim() == ""){
		alert("id를 체크해 주십시오");		
		$("#_btnGetId").focus();
	}
	else if($("#_pwd").val().trim() == ""){
		alert("Password를 입력해 주십시오");
		$("#_pwd").focus();
	}
	else if($("#_name").val().trim() == ""){
		alert("이름을 입력해 주십시오");
		$("#_name").focus();
	}
	else if($("#_email").val().trim() == ""){
		alert("이메일을 입력해 주십시오");
		$("#_name").focus();
	}
	else{
		//var formData = $('#fromForm').serialize();
		$.ajax({
			url:"regiAf.do",
			type:"POST",
			data: $("#_frmForm").serialize(),
			success:function(result) {
				//alert(result);
				if(result.trim() == "Y") {
					alert("회원가입 완료");
					location.href="login.do";
				} else {
					alert("회원가입 실패");
				}				
			},
			error:function() {
				alert("error");
			}
		})
		//$("#_frmForm").attr({ "action":"regiAf.do", "target":"_self" }).submit();
	}		
});	

</script>
</body>
</html>





