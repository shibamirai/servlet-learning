package servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/kuku")
public class KukuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer count = (Integer)session.getAttribute("count");
		if (count == null) {

			session.setAttribute("count", Integer.valueOf(1));
			session.setAttribute("start", Long.valueOf(System.currentTimeMillis()));

		} else if (count.intValue() == 10) {

			// 終了時刻を取得
			Long end = System.currentTimeMillis();
			
			// 開始時刻を取得（これも本来はバリデーションすべき）
			Long start = (Long)session.getAttribute("start");
			
			// 単位を"秒"にする。double 型として割り算をしないと小数点以下がクリアされてしまうので注意
			double time = (end - start) / (double)1000;
			request.setAttribute("time", Double.valueOf(time));

			// 次の問題にそなえて今のセッションを終了し、セッション属性をクリアしておく
			session.invalidate();

			// 結果ページへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
			
			// ここで処理を終了させるのを忘れない
			return;

		} else {
			session.setAttribute("count", count + 1);
		}

		Random rnd = new Random();
		request.setAttribute("x", rnd.nextInt(1, 10));
		request.setAttribute("y", rnd.nextInt(1, 10));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/kuku.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * パラメータ x, y, z を受け取り、数値に変換する。
		 * 
		 * 本来は、まず受信するパラメータすべてに対して、値が正しいかどうかを
		 * 確認（バリデーション）しなければならないが、ここでは省略している。
		 */
		int x = Integer.parseInt(request.getParameter("x"));
		int y = Integer.parseInt(request.getParameter("y"));
		int z = Integer.parseInt(request.getParameter("z"));

		if (x * y == z) {

			// 正解なので次の問題へ
			response.sendRedirect("/arithmetic/kuku");

		} else {

			// 不正解なので同じ問題を表示
			request.setAttribute("x", x);
			request.setAttribute("y", y);
			request.setAttribute("wrong", "wrong");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/kuku.jsp");
			dispatcher.forward(request, response);

		}
	}
}
