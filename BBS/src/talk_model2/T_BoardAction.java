package talk_model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface T_BoardAction {
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);
}