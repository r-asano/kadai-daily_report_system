package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import models.Yoine;
import utils.DBUtil;

/**
 * Servlet implementation class Yoine
 */
@WebServlet("/yoine")
public class YoineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public YoineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        Yoine y = em.createNamedQuery("getSelectYoine", Yoine.class)
                  .setParameter("report_id", r)
                  .getSingleResult();

        // 初回起動判定
        // アプリケーションスコープに値がなければnewする
        if( y == null) {
            y = new Yoine();
        }

        ////////////////////////////////////////////////////////////

        y.setReportID();
        y.setYoineCount();

        // リクエストパラメーターの取得
        request.setCharacterEncoding("UTF-8");
        String yoine = request.getParameter("action");

        // いいねボタン押されたら
        if (yoine != null) {
	        // いいねを加算
	        Yoine yl = new Yoine();
	        yl.yoinePlus(y);
        }

        em.getTransaction().begin();
        em.persist(y);
        em.getTransaction().commit();
        em.close();


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
	}
}