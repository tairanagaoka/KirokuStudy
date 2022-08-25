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

import models.User;
import models.WeeklyGoal;
import utils.DBUtil;

/**
 * Servlet implementation class CreateWeeklyGoalServlet
 */
@WebServlet("/CreateWeeklyGoal")
public class CreateWeeklyGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateWeeklyGoalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();


            WeeklyGoal wg = new WeeklyGoal();

            User user = (User) request.getSession().getAttribute("login_user");
            wg.setUser(user);



            LocalDate start = LocalDate.parse(request.getParameter("study_startdate"));
            wg.setStudy_startdate(start);


            LocalDate end = LocalDate.parse(request.getParameter("study_enddate"));
            wg.setStudy_enddate(end);

            Integer hours =Integer.parseInt(request.getParameter("hours"));
            wg.setHours(hours);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            wg.setCreated_at(currentTime);

            request.getSession().setAttribute("flush","今週の目標を設定しました。");


            em.persist(wg);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");

    }

    }
}
