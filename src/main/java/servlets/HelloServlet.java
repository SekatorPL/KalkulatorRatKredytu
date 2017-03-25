package servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException{
		
		ManagerKonwertera managerKonwertera = new ManagerKonwertera(httpServletRequest, httpServletResponse);
		managerKonwertera.pokazTabele();
	}
}