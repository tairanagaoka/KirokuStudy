package weekly_goal_controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.JpaConst;
import models.User;
import models.WeeklyGoal;
import utils.DBUtil;

@WebServlet("/SettingWeeklyGoal")

public class SettingWeeklyGoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingWeeklyGoalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        User u = (User)request.getSession().getAttribute("login_user");
        LocalDate reportDate = LocalDate.now();

        //該当のユーザーから目標期間を取得
        try {
            WeeklyGoal weeklyGoal = em.createNamedQuery(JpaConst.Q_WEE_GET_BY_USER_STARTDATE, WeeklyGoal.class)
                    .setParameter(JpaConst.JPQL_PARM_CODE, u.getCode())
                    .setParameter(JpaConst.JPQL_PARM_STARTDATE, reportDate)
                    .getSingleResult();

            response.sendRedirect(request.getContextPath() + "/EditWeeklyGoal?id="+ weeklyGoal.getId());
        } catch (NoResultException e) {
            // 新規作成の場合
            response.sendRedirect(request.getContextPath() + "/NewWeeklyGoal");
        }
    }

}