package user_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserAction {
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);
}
