package goal_controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Goal;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class CreateGoalSerclet
 */
@WebServlet(name = "CreateGoalServlet", urlPatterns = { "/CreateGoal" })
public class CreateGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGoalServlet() {
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

            //Goalのインスタンス
            Goal g = new Goal();

            User user = (User) request.getSession().getAttribute("login_user");
            g.setUser(user);

            String title = request.getParameter("title");
            g.setTitle(title);

            String action = request.getParameter("action");
            g.setAction(action);

            String reason = request.getParameter("reason");
            g.setReason(reason);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            g.setCreated_at(currentTime);

            request.getSession().setAttribute("flush", "目標を設定しました。");

            em.persist(g);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");

        }
    }
}
