package free_model2;

import free_model1.BoardDAO;
import free_model1.BoardTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class F_ModifyOkAction implements F_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("F_ModifyOkAction 호출");
		
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		to.setPassword(request.getParameter("password"));
		to.setSubject(request.getParameter("subject"));
		to.setMail(request.getParameter("mail1") + "@" + request.getParameter("mail2"));
		to.setContent(request.getParameter("content"));
		
		BoardDAO dao = new BoardDAO();
		int flag = dao.boardModifyOk(to);

		request.setAttribute("flag", flag);
		request.setAttribute("seq", request.getParameter("seq"));
	}
}