package free_model2;

import free_model1.BoardDAO;
import free_model1.BoardTO;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class F_ListAction implements F_BoardAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("F_ListAction 호출");

		BoardDAO dao = new BoardDAO();
		ArrayList<BoardTO> lists = dao.boardList();

		request.setAttribute("lists", lists);
	}
}