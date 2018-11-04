package free_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import free_model1.BoardDAO;
import free_model1.BoardTO;

public class F_DeleteOkAction implements F_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("F_DeleteOkAction 호출");
		BoardTO to = new BoardTO();
		
		to.setSeq(request.getParameter("seq"));
		to.setPassword(request.getParameter("password"));
		
		BoardDAO dao = new BoardDAO();
		int flag = dao.boardDeleteOk(to);
		
		request.setAttribute("flag", flag);
	}
}