package goal_controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.JpaConst;
import models.Goal;
import models.Report;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyGoalServlet
 */
@WebServlet("/DestroyGoal")
public class DestroyGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyGoalServlet() {
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
        //csrf対策
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();

            Goal g = em.find(Goal.class, (Integer) (request.getSession().getAttribute("goal_id")));

            User u = (User) request.getSession().getAttribute("login_user");

            try {

                Report report = null;
                /** データベースにすでに登録されたReportを取得 */

                report = em.createNamedQuery(JpaConst.Q_RPT_GET_BY_USER_GOALID, Report.class)
                        .setParameter(JpaConst.JPQL_PARM_CODE, u.getCode())
                        .setParameter(JpaConst.JPQL_PARM_GOALID, g.getId())
                        .getSingleResult();

                //データ削除
                em.getTransaction().begin();
                em.remove(report);
                em.remove(g);
                em.getTransaction().commit();
            } catch (NoResultException e) {
                em.getTransaction().begin();
                em.remove(g);
                em.getTransaction().commit();


            }
            // アプリケーションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("goal_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/IndexGoal");
        }

    }
}
