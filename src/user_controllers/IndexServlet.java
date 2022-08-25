package user_controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.JpaConst;
import models.Goal;
import models.Report;
import models.User;
import models.WeeklyGoal;
import utils.DBUtil;
import viewmodels.TopPageInfo;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");

        }
        EntityManager em = DBUtil.createEntityManager();

        User u = (User)request.getSession().getAttribute("login_user");

        LocalDate reportDate = LocalDate.now();


        try {
            WeeklyGoal wg = em.createNamedQuery(JpaConst.Q_WEE_GET_BY_USER_STARTDATE, WeeklyGoal.class)
                    .setParameter(JpaConst.JPQL_PARM_CODE, u.getCode())
                    .setParameter(JpaConst.JPQL_PARM_STARTDATE, reportDate)
                    .getSingleResult();
            request.setAttribute("weekly_goal",wg);


        } catch (NoResultException e) {


        }

        LocalDate today = LocalDate.now();

        String userCode = u.getCode();
        //Goalを該当のユーザーからリストで取得
        List<Goal> goals = em.createNamedQuery(JpaConst.Q_GOA_GET_ALL_BY_USER, Goal.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, userCode)

                .setMaxResults(3)
                .getResultList();

        List<TopPageInfo> infos = new ArrayList<TopPageInfo>();
        for (Goal goal : goals) {
            TopPageInfo info = new TopPageInfo();
            Integer goalId =  goal.getId();
            int todayStudyHours = 0;
            Long totalHours =  0L;
            try {
                //該当のユーザー、goalIdからReportデータを取得
                Report report = em.createNamedQuery(JpaConst.Q_RPT_GET_BY_USER_REPORTDATE_GOALID, Report.class)
                        .setParameter(JpaConst.JPQL_PARM_CODE, userCode)
                        .setParameter(JpaConst.JPQL_PARM_REPORTDATE, today)
                        .setParameter(JpaConst.JPQL_PARM_GOALID, goalId)
                        .getSingleResult();

                todayStudyHours = report.getHours();

                // ユーザー、goal_idから該当したReportデータの総計を取得
                totalHours = em.createNamedQuery(JpaConst.Q_RPT_GET_HOURSSUM_BY_USER_GOALID, Long.class)
                        .setParameter(JpaConst.JPQL_PARM_CODE, userCode)
                        .setParameter(JpaConst.JPQL_PARM_GOALID, goalId)
                        .getSingleResult();

            } catch (NoResultException e) {

            }

            info.setGoalId(goalId);
            info.setGoalTitle(goal.getTitle());
            info.setTodayStudyHours(todayStudyHours);
            info.setTotalStudyHours(totalHours);

            infos.add(info);
        }

        request.setAttribute("infos", infos);
             RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
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
