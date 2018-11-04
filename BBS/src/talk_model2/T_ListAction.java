package talk_model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import talk_model1.BoardTO;
import talk_model1.BoardDAO;

public class T_ListAction implements T_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("t_ListAction 호출");

		BoardDAO dao = new BoardDAO();
		ArrayList<BoardTO> lists = dao.boardList();

		request.setAttribute("lists", lists);
	}
}