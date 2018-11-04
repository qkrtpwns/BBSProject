package free_model2;

import free_model1.BoardDAO;
import free_model1.BoardTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class F_ModifyAction implements F_BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("t_ModifyAction 호출");
		
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		
		BoardDAO dao = new BoardDAO();
		to = dao.boardModify(to);
		
		request.setAttribute("to", to);
	}
}