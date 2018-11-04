package user_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user_model1.UserDAO;
import user_model1.UserTO;

public class LoginAction implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("LoginAction 호출");
		UserTO to = new UserTO();
		to.setSeq(request.getParameter("seq"));
		
		UserDAO dao = new UserDAO();
		int flag = dao.login(to.getUserID(), to.getUserPassword());
		
		request.setAttribute("flag", flag);
	}

}
