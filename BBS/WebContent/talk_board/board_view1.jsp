<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="talk_model1.BoardTO" %>

<%
	BoardTO to = (BoardTO)request.getAttribute("to");
	
	String seq = to.getSeq();
	String subject = to.getSubject();
	String writer = to.getWriter();
	String mail = to.getMail();
	String wip = to.getWip();
	String wdate = to.getWdate();
	String hit = to.getHit();
	String content = to.getContent();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/board_view.css">
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/custom.css">

<style type="text/css">
body {
	background: url(./images/border.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	padding-top: 70px;
}

#footer {
	background-color: #000000;
	position: absolute;
	bottom: 0;
	left: 0;
	right: 0;
}
</style>
</head>

<body>
<!-- 상단 게시판 -->
<%
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
%>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
			 data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only"></span>
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand navbar-lg" href="./index.do"> 
			<img src="./images/brand.jpg" alt="Team 피노키오" class="img-responsive">
			</a>
		</div>

		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="./home.do">홈</a><span class="sr-only"></span></li>
				<li><a href="./introduction.do">게임소개</a></li>
				<li><a href="./team.do">팀원소개</a></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">게시판<span class="caret"></span></a>
					<ul class="dropdown-menu" style="background-color: black;">
						<li><a style="color: white;" class="active" href="./free_list.do">자유게시판</a></li>
						<li><a style="color: white;" href="./talk_list.do">토론게시판</a></li>
					</ul>
				</li>
			</ul>

			<%
				if (userID == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="./login.do">로그인</a></li>
						<li><a href="./join.do">회원가입</a></li>
					</ul></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="./logout.do">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
		</div>
	</div>
</nav>

<!-- 게시판 -->
<div id="board" class="container">
	<div class="con_title">
		<h3>게시판</h3>
		<p>HOME &gt; 게시판 &gt; <strong>토론게시판</strong></p>
	</div>
	<div class="con_txt">
		<div class="contents_sub">
			<!--게시판-->
			<div class="board_view">
				<table>
				<tr>
					<th width="10%">제목</th>
					<td width="60%"><%=subject %></td>
					<th width="10%">등록일</th>
					<td width="20%"><%=wdate %></td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td><%=writer %><%=mail %>(<%=wip %>)</td>
					<th>조회</th>
					<td><%=hit %></td>
				</tr>
				<tr>
					<td colspan="4" height="200" valign="top" style="padding: 20px; line-height: 160%"><%=content %></td>
				</tr>
				</table>
			</div>
	
			<div class="btn_area">
				<div class="align_left">
					<button type="button" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./talk_list.do'">목록</button>
				</div>
				<div class="align_right">
					<button type="button" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./talk_modify.do?seq=<%= seq %>'">수정</button>
					<button type="button" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='./talk_delete.do?seq=<%= seq %>'">삭제</button>
					<button type="button" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='./talk_write.do'">쓰기</button>
				</div>
			</div>
			<!--//게시판-->
		</div>
	</div>
</div>
<!-- 하단 디자인 -->
<!-- footer -->
<div id="bs-example-navbar-collapse-1">
	<footer id="footer" class="footer" style="color: #ffffff"> <!-- style = css 추가 -->
		<div class="container">
			<br>
			<div class="row">
				<div class="col-sm-2" style="text-align: center;">
					<h4>Copyright &copy; <br><br>Team 피노키오</h4><h4>2018. 05</h4>
				</div>
				<div class="col-sm-4" style="text-align: center;">
					<h4>Team 소개</h4><p>팀 피노키오는 열정이 넘치는 <br><br>4인의 팀원으로 구성된 팀입니다.</p>
				</div>
				<div class="col-sm-2"><h4 style="text-align: center;">소개</h4>
					<div class="list-group">
						<a href="./introduction.do" class="list-group-item">게임소개</a>
						<a href="./team.do" class="list-group-item">팀소개</a>
					</div>
				</div>
				
				<div class="col-sm-2"><h4 style="text-align: center;">게시판</h4>
					<div class="list-group">
						<a href="./freeboard.do" class="list-group-item">자유게시판</a>
						<a href="./talkboard.do" class="list-group-item">토론게시판</a>
					</div>
				</div>
				
				<div class="col-sm-2">
					<h4 style="text-align: center;">
						<img class="img-thumbnail" src="./images/19.png" alt="Team 피노키오" class="img-responsive">
					</h4>
				</div>
			</div>
		</div>
	</footer>
</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="./js/bootstrap.js"></script>
</body>
</html>
