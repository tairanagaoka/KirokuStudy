package weekly_goal_controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.WeeklyGoal;
import utils.DBUtil;

/**
 * Servlet implementation class EditWeeklyGoalServlet
 */
@WebServlet("/EditWeeklyGoal")
public class EditWeeklyGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditWeeklyGoalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 該当のIDのメッセージ1件のみをデータベースから取得
        WeeklyGoal wg = em.find(WeeklyGoal.class, Integer.parseInt(request.getParameter("id")));

        em.close();


        request.setAttribute("weekly_goal", wg);
        request.setAttribute("_token", request.getSession().getId());

        if(wg !=null) {
            request.getSession().setAttribute("weekly_goal_id",wg.getId());
    }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/weekly_goals/EditWeeklyGoal.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
