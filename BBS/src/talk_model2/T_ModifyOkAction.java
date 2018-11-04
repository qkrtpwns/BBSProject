package talk_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import talk_model1.BoardDAO;
import talk_model1.BoardTO;

public class T_ModifyOkAction implements T_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("T_ModifyOkAction 호출");
		
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