package talk_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import talk_model1.BoardDAO;
import talk_model1.BoardTO;

public class T_ViewAction implements T_BoardAction {
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