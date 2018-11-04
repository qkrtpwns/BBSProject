<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="talk_model1.BoardTO" %>
<%@ page import="talk_model1.BoardListTO" %>
<%@ page import="talk_model1.BoardDAO" %>
<%@ page import="java.io.PrintWriter"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>freeboard list</title>
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/custom.css">
<link rel="stylesheet" href="./css/board_list.css">
<style type="text/css">
body {
	background: url(./images/border.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	padding-top: 50px;
}

#footer {
	background-color: #000000;
	position: absolute;
	bottom: 0;
	left: 0;
	right: 0;
}

@media screen and (max-height: 500px){
	#footer {
		background-color: #000000;
		position: relative;
		bottom: 0;
		left: 0;
		right: 0;
	}
}

</style>

</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	
	int cpage = 1;
	if(request.getParameter("cpage") != null
			&& !request.getParameter("cpage").equals("")) {
		cpage = Integer.parseInt(request.getParameter("cpage"));
	}
	
	BoardListTO listTO = new BoardListTO();
	listTO.setCpage(cpage);
	
	BoardDAO dao = new BoardDAO();
	listTO = dao.boardList(listTO);
	
	int totalRecord = listTO.getTotalRecord();
	int totalPage = listTO.getTotalPage();
	int blockPerPage = listTO.getBlockPerPage();
		
	StringBuffer html = new StringBuffer();
	
	ArrayList<BoardTO> boardLists = listTO.getBoardLists();
	
	for(int i=0 ; i<boardLists.size() ; i++) {
		BoardTO to = boardLists.get(i);
		
		String seq = to.getSeq();
		// System.out.println(seq);
		String subject = to.getSubject();
		String writer = to.getWriter();
		String wdate = to.getWdate();
		String hit = to.getHit();
		int wgap = to.getWgap();
			
		html.append("<tr class='test'>");
		html.append("	<td class='test'>" + seq + "</td>");
		html.append("	<td class='test'>");
		html.append("		<a href='./talk_view.do?seq=" + seq + "'>" + subject + "</a>&nbsp;");
		if(wgap == 0) {
			html.append("	<img src='./images/icon_hot.gif' alt='HOT'>");
		}
		html.append("	</td>");
		html.append("	<td class='test'>" + writer + "</td>");
		html.append("	<td class='test'>" + wdate + "</td>");
		html.append("	<td class='test'>" + hit + "</td>");
		html.append("</tr>");
	}
%>

<!-- 상단 디자인 -->
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
						<li><a style="color: white;" href="./free_list.do">자유게시판</a></li>
						<li><a style="color: white;" class="active" href="./talk_list.do">토론게시판</a></li>
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


<div id="board" class="container">
	<div class="con_title" style="color: white;">
		<h3>토론게시판</h3>
	</div>	
	<div class="row">
		<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th width="7%" style="background-color: #eeeeee; text-align: center;">No</th>
					<th width="45%" style="background-color: #eeeeee; text-align: center;">글 제목</th>
					<th style="background-color: #eeeeee; text-align: center;">작성자</th>
					<th width="20%" style="background-color: #eeeeee; text-align: center;">작성일</th>
					<th width="7%" style="background-color: #eeeeee; text-align: center;">조회</th>
				</tr>
			</thead>
			<tbody>
				<%= html %>	
			</tbody>
		</table>
	</div>	

	<div class="align_right">
		<button type="button" class="btn_write btn_txt01" style="cursor: pointer; border-style: solid; border-color: white;" 
		onclick="location.href='./talk_write.do'">쓰기</button>
	</div>
</div>

<!--페이지넘버-->
<div class="paginate_regular">
	<div class="board_pagetab" align="absmiddle">
		<%
			int startBlock = ((cpage-1) / blockPerPage) * blockPerPage + 1;
			int endBlock = ((cpage-1) / blockPerPage) * blockPerPage + blockPerPage;
			if(endBlock >= totalPage) {
				endBlock = totalPage;
			}
			// href="./controller?action=list"
			if(startBlock == 1) {
				out.println("<span style='color: white;' class='off'>&lt;&lt;</span>");
			} else {
				out.println("<span style='color: white;' class='on'><a style='color: white;' href='./talk_list.do?cpage=" + (startBlock - blockPerPage) + "'>&lt;&lt;</a></span>");
			}
		%>			
						&nbsp;
		<%
			if(cpage == 1) {
				out.println("<span style='color: white;' class='off'>&lt;</span>");		
			} else {
				out.println("<span style='color: white;' class='on'><a style='color: white;' href='./talk_list.do?cpage=" + (cpage - 1) + "'>&lt;</a></span>");
			}
		%>				
						&nbsp;&nbsp;
		<%
			for(int i=startBlock ; i<=endBlock ; i++) {
				if(cpage == i) {
					out.println("<span style='color: white;' class='off'>[ " + i + " ]</span>");	
				} else {
					out.println("<span style='color: white;' class='on'><a style='color: white;' href='./talk_list.do?cpage=" + i + "'>" + i + "</a></span>");
				}
			}	
		%>
						&nbsp;&nbsp;
		<%				
			if(cpage == totalPage) {
				out.println("<span style='color: white;' class='off'>&gt;</span>");		
			} else {
				out.println("<span style='color: white;' class='on'><a style='color: white;' href='./talk_list.do?cpage=" + (cpage + 1) + "'>&gt;</a></span>");
			}
		%>
						&nbsp;
		<%
			if(endBlock == totalPage) {
				out.println("<span style='color: white;' class='off'>&gt;&gt;</span>");
			} else {
				out.println("<span style='color: white;' class='on'><a style='color: white;' href='./talk_list.do?cpage=" + (startBlock + blockPerPage) + "'>&gt;&gt;</a></span>");
			}
		%>				
				
	</div>
</div>
<!--//페이지넘버-->


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
						<a href="./free_list.do" class="list-group-item">자유게시판</a>
						<a href="./talk_list.do" class="list-group-item">토론게시판</a>
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