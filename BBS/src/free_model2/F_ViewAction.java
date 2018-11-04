package free_model2;

import free_model1.BoardDAO;
import free_model1.BoardTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class F_ViewAction implements F_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("T_viewAction 호출");
		
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		
		BoardDAO dao = new BoardDAO();
		to = dao.boardView(to);
		
		request.setAttribute("to", to);
	}
}