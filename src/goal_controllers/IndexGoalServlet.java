package goal_controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.JpaConst;
import models.Goal;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class IndexGoalServlet
 */
@WebServlet("/IndexGoal")
public class IndexGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexGoalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u = (User) request.getSession().getAttribute("login_user");
        String userCode = u.getCode();

        //最初の画面や最大数を決める
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
        }

        List<Goal> goals = em.createNamedQuery(JpaConst.Q_GOA_GET_ALL_BY_USER, Goal.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, userCode)
                .setFirstResult(5 * (page - 1))
                .setMaxResults(5)
                .getResultList();
        //goalのユーザーごとのデータ数を数える
        long goals_count = (long) em.createNamedQuery(JpaConst.Q_GOA_GET_COUNT_BY_USER, Long.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, userCode)

                .getSingleResult();

        em.close();

        request.setAttribute("goals", goals);
        request.setAttribute("goals_count", goals_count);
        request.setAttribute("page", page);
        em.close();

        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/goals/IndexGoal.jsp");
        rd.forward(request, response);
    }



}
