package talk_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import talk_model1.BoardDAO;
import talk_model1.BoardTO;

public class T_DeleteAction implements T_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("t_DeleteAction 호출");
		
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		
		BoardDAO dao = new BoardDAO();
		to = dao.boardDelete(to);
		
		request.setAttribute("to", to);
	}
}