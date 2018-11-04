package talk_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import talk_model1.BoardDAO;
import talk_model1.BoardTO;

public class T_WriteOkAction implements T_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("T_WriteOkAction 호출");
		
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