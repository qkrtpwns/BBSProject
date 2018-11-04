package free_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface F_BoardAction {
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);
}