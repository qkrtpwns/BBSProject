package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import free_model2.F_BoardAction;
import free_model2.F_DeleteAction;
import free_model2.F_DeleteOkAction;
import free_model2.F_ListAction;
import free_model2.F_ModifyAction;
import free_model2.F_ModifyOkAction;
import free_model2.F_ViewAction;
import free_model2.F_WriteAction;
import free_model2.F_WriteOkAction;

import talk_model2.T_BoardAction;
import talk_model2.T_DeleteAction;
import talk_model2.T_DeleteOkAction;
import talk_model2.T_ListAction;
import talk_model2.T_ModifyAction;
import talk_model2.T_ModifyOkAction;
import talk_model2.T_ViewAction;
import talk_model2.T_WriteAction;
import talk_model2.T_WriteOkAction;

import user_model2.UserAction;
import user_model2.HomeAction;
import user_model2.TeamAction;
import user_model2.IndexAction;
import user_model2.IntroductionAction;
import user_model2.JoinAction;
import user_model2.JoinOkAction;
import user_model2.LoginAction;
import user_model2.LoginOkAction;
import user_model2.LogoutAction;

/**
 * Servlet implementation class URIController
 */
@WebServlet("*.do")
public class URIController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			request.setCharacterEncoding("utf-8");
			
			String path = request.getRequestURI().replaceAll(request.getContextPath(), "");
			
			String url = "";
			UserAction useraction = null;
			F_BoardAction f_boardaction = null;
			T_BoardAction t_boardaction = null;
			
			// 홈페이지
			if(path.equals("/*.do") || path.equals("/index.do")) {
				useraction = new IndexAction();
				useraction.execute(request, response);

				url = "/home_page/index.jsp";
				
			} else if(path.equals("/home.do")) {	
				useraction = new HomeAction();
				useraction.execute(request, response);
				
				url = "/home_page/home.jsp";
			
			} else if(path.equals("/introduction.do")) {	
				useraction = new IntroductionAction();
				useraction.execute(request, response);
				
				url = "/home_page/introduction.jsp";
			
			} else if(path.equals("/team.do")) {	
				useraction = new TeamAction();
				useraction.execute(request, response);
				
				url = "/home_page/team.jsp";
			
			} else if(path.equals("/join.do")) {	
				useraction = new JoinAction();
				useraction.execute(request, response);
				
				url = "/home_page/join.jsp";
			
			} else if(path.equals("/join_ok.do")) {	
				useraction = new JoinOkAction();
				useraction.execute(request, response);
				
				url = "/home_page/join_ok.jsp";
			
			} else if(path.equals("/login.do")) {	
				useraction = new LoginAction();
				useraction.execute(request, response);
				
				url = "/home_page/login.jsp";
			
			} else if(path.equals("/login_ok.do")) {	
				useraction = new LoginOkAction();
				useraction.execute(request, response);
				
				url = "/home_page/login_ok.jsp";
			
			} else if(path.equals("/logout.do")) {	
				useraction = new LogoutAction();
				useraction.execute(request, response);
				
				url = "/home_page/logout.jsp";
						
			// 자유 게시판
			} else if(path.equals("/free_list.do")) {	
				f_boardaction = new F_ListAction();
				f_boardaction.execute(request, response);
				
				url = "/free_board/board_list1.jsp";
			
			} else if(path.equals("/free_view.do")) {
				f_boardaction = new F_ViewAction();
				f_boardaction.execute(request, response);
				
				url = "/free_board/board_view1.jsp";
			
			} else if(path.equals("/free_write.do")) {
				f_boardaction = new F_WriteAction();
				f_boardaction.execute(request, response);
				
				url = "/free_board/board_write1.jsp";
			
			} else if(path.equals("/free_write_ok.do")) {
				f_boardaction = new F_WriteOkAction();
				f_boardaction.execute(request, response);
				
				url = "/free_board/board_write1_ok.jsp";
			
			} else if(path.equals("/free_modify.do")) {
				f_boardaction = new F_ModifyAction();
				f_boardaction.execute(request, response);
				
				url = "/free_board/board_modify1.jsp";
			
			} else if(path.equals("/free_modify_ok.do")) {
				f_boardaction = new F_ModifyOkAction();
				f_boardaction.execute(request, response);
				
				url = "/free_board/board_modify1_ok.jsp";
			
			} else if(path.equals("/free_delete.do")) {
				f_boardaction = new F_DeleteAction();
				f_boardaction.execute(request, response);
				
				url = "/free_board/board_delete1.jsp";
				
			} else if(path.equals("/free_delete_ok.do")) {
				f_boardaction = new F_DeleteOkAction();
				f_boardaction.execute(request, response);
				
				url = "/free_board/board_delete1_ok.jsp";
			
			} else if(path.equals("/talk_list.do")) {	
				t_boardaction = new T_ListAction();
				t_boardaction.execute(request, response);
				
				url = "/talk_board/board_list1.jsp";
			
			} else if(path.equals("/talk_view.do")) {
				t_boardaction = new T_ViewAction();
				t_boardaction.execute(request, response);
				
				url = "/talk_board/board_view1.jsp";
			
			} else if(path.equals("/talk_write.do")) {
				t_boardaction = new T_WriteAction();
				t_boardaction.execute(request, response);
				
				url = "/talk_board/board_write1.jsp";
			
			} else if(path.equals("/talk_write_ok.do")) {
				t_boardaction = new T_WriteOkAction();
				t_boardaction.execute(request, response);
				
				url = "/talk_board/board_write1_ok.jsp";
			
			} else if(path.equals("/talk_modify.do")) {
				t_boardaction = new T_ModifyAction();
				t_boardaction.execute(request, response);
				
				url = "/talk_board/board_modify1.jsp";
			
			} else if(path.equals("/talk_modify_ok.do")) {
				t_boardaction = new T_ModifyOkAction();
				t_boardaction.execute(request, response);
				
				url = "/talk_board/board_modify1_ok.jsp";
			
			} else if(path.equals("/talk_delete.do")) {
				t_boardaction = new T_DeleteAction();
				t_boardaction.execute(request, response);
				
				url = "/talk_board/board_delete1.jsp";
				
			} else if(path.equals("/talk_delete_ok.do")) {
				t_boardaction = new T_DeleteOkAction();
				t_boardaction.execute(request, response);
				
				url = "/talk_board/board_delete1_ok.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("[에러] : " + e.getMessage());
		}
		
	}
}
