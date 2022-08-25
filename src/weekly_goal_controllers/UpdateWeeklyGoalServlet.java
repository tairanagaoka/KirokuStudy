package weekly_goal_controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.WeeklyGoal;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateWeeklyGoalServlet
 */
@WebServlet("/UpdateWeeklyGoal")
public class UpdateWeeklyGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWeeklyGoalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            em.getTransaction().begin();


            // セッションスコープからメッセージのIDを取得して
            // 該当のIDのメッセージ1件のみをデータベースから取得
            WeeklyGoal wg = em.find(WeeklyGoal.class, (Integer) (request.getSession().getAttribute("weekly_goal_id")));

            // フォームの内容を各フィールドに上書き
            LocalDate start = LocalDate.parse(request.getParameter("study_startdate"));
            wg.setStudy_startdate(start);

            LocalDate end = LocalDate.parse(request.getParameter("study_enddate"));
            wg.setStudy_enddate(end);

            Integer hours = Integer.parseInt(request.getParameter("hours"));
            wg.setHours(hours);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            wg.setCreated_at(currentTime);

            request.getSession().setAttribute("flush","今週の目標を再設定しました。");


            em.persist(wg);
            em.getTransaction().commit();
            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("weekly_goal_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }

    }
}
