package free_model2;

import free_model1.BoardDAO;
import free_model1.BoardTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class F_WriteOkAction implements F_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("F_WriteOkAction 호출");
		
		BoardTO to = new BoardTO();
		to.setSubject(request.getParameter("subject"));
		to.setWriter(request.getParameter("writer"));
		to.setMail(request.getParameter("mail1") + "@" + request.getParameter("mail2"));
		to.setPassword(request.getParameter("password"));
		to.setContent(request.getParameter("content"));
		to.setWip(request.getRemoteAddr());
		
		BoardDAO dao = new BoardDAO();
		int flag = dao.boardWriteOk(to);
		
		request.setAttribute("flag", flag);
	}
}