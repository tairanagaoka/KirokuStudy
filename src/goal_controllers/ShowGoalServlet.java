package goal_controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Goal;
import utils.DBUtil;

/**
 * Servlet implementation class ShowGoalServlet
 */
@WebServlet("/ShowGoal")
public class ShowGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGoalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());
        EntityManager em = DBUtil.createEntityManager();

        // 該当のIDのメッセージ1件のみをデータベースから取得
        Goal g = em.find(Goal.class, Integer.parseInt(request.getParameter("id")));
        em.close();

        request.setAttribute("goal", g);
        // 取得したデータからgoal_idをセッションスコープへ
        if(g !=null) {
            request.getSession().setAttribute("goal_id",g.getId());
    }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/goals/ShowGoal.jsp");
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
