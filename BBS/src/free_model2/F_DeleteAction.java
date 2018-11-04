package free_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import free_model1.BoardDAO;
import free_model1.BoardTO;

public class F_DeleteAction implements F_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("F_DeleteAction 호출");
		
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		
		BoardDAO dao = new BoardDAO();
		to = dao.boardDelete(to);
		
		request.setAttribute("to", to);
	}
}