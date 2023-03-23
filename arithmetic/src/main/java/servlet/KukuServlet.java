package servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kuku")
public class KukuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random rnd = new Random();
		request.setAttribute("x", rnd.nextInt(1, 10));
		request.setAttribute("y", rnd.nextInt(1, 10));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/kuku.jsp");
		dispatcher.forward(request, response);
	}

}
