package report_controllers;

import java.io.IOException;
import java.sql.Timestamp;
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

/**
 * Servlet implementation class NewReportServlet
 */
@WebServlet("/SettingReport")
public class SettingReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingReportServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Integer reportId = null;
        try {
            reportId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        EntityManager em = DBUtil.createEntityManager();

        User u = (User)request.getSession().getAttribute("login_user");

        List<Goal> goals = getGoalbyUser(em, u.getCode());

        request.setAttribute("goals",  goals);

        // 今週の目標が設定されているかチェック
        WeeklyGoal weeklyGoal = new WeeklyGoal();
        Report report = new Report();
        LocalDate date = null;
        if (reportId == null) {
            date = LocalDate.now();
            report.setReportDate(date);

        } else {
            report = em.find(Report.class, reportId);
            if (report == null || report.getUser().getId() != u.getId()) {
                request.setAttribute("illegal_operation",
                        "不正な操作です。再度画面を開き直してください。");

                request.setAttribute("report",  new Report());

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/report/SettingReport.jsp");
                rd.forward(request, response);

            }
            date = report.getReportDate();
            weeklyGoal = report.getWeekly_goal();
        }

        try {
            weeklyGoal = getWeeklyGoal(em, u.getCode(), date);

        } catch (NoResultException e) {
            // 学習期間が設定されていない場合
            request.setAttribute("illegal_operation",
                    "学習期間が設定されていません。目標を設定してから、再度登録してください。");

            request.setAttribute("report",  report);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/report/SettingReport.jsp");
            rd.forward(request, response);
        }

        report.setWeekly_goal(weeklyGoal);
        request.setAttribute("report",  report);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/report/SettingReport.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        User u = (User)request.getSession().getAttribute("login_user");

        Report report = new Report();

        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            List<String> errors = new ArrayList<String>();
            /** 入力チェック */
            // 目標の必須
            String goalIdStr = request.getParameter("goal");
            int goalId = 0;
            if(goalIdStr == null || goalIdStr.equals("")) {
                errors.add("目標を入力してください。");
            } else {
                goalId = Integer.parseInt(goalIdStr);
            }


            Goal goal = new Goal();
            try {
                goal = em.find(Goal.class, goalId);
            } catch (NoResultException e) {
                // 目標が設定されていない場合、エラー
                errors.add("削除された目標が設定されています。目標を設定し直してください。");
            }

            // 日付の必須
            String reportDateStr = request.getParameter("reportDate");
            LocalDate reportDate = null;
            if(reportDateStr == null || reportDateStr.equals("")) {
                errors.add("日付を入力してください。");
            } else {
                reportDate = LocalDate.parse(reportDateStr);
                report.setReportDate(reportDate);
            }

            /** 今週の目標を取得 */
            WeeklyGoal weeklyGoal = new WeeklyGoal();

            try {
                weeklyGoal = getWeeklyGoal(em, u.getCode(), report.getReportDate());

            } catch (NoResultException e) {
                // 今週の目標が設定されていない場合
                errors.add(
                        "学習期間が設定されていません。目標を設定してから、再度登録してください。");
            }



            String hoursStr = request.getParameter("hours");
            Integer hours = 0;
            if(hoursStr != null && !hoursStr.equals("")) {
                hours = Integer.parseInt(hoursStr);
            }
            report.setReportDate(reportDate);
            report.setGoal(goal);
            report.setWeekly_goal(weeklyGoal);
            report.setHours(hours);


            if (errors.size() == 0) {

                /** データベースにすでに登録されたReportを取得 */
                try {
                    report = em.createNamedQuery(JpaConst.Q_RPT_GET_BY_USER_REPORTDATE_GOALID, Report.class)
                            .setParameter(JpaConst.JPQL_PARM_CODE, u.getCode())
                            .setParameter(JpaConst.JPQL_PARM_REPORTDATE, report.getReportDate())
                            .setParameter(JpaConst.JPQL_PARM_GOALID, goalId)
                            .getSingleResult();

                } catch (NoResultException e) {
                    // 本日の学習時間が取得できない場合、新規登録
                }

                report.setReportDate(reportDate);
                report.setGoal(goal);
                report.setWeekly_goal(weeklyGoal);
                report.setHours(hours);
                report.setUser(u);
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                report.setCreated_at(currentTime);

                em.persist(report);
                em.getTransaction().commit();
                em.close();

                response.sendRedirect(request.getContextPath() + "/index");
            } else {
                List<Goal> goals = getGoalbyUser(em, u.getCode());

                request.setAttribute("goals",  goals);

                request.setAttribute("report",  report);
                request.setAttribute("errors",  errors);
                request.setAttribute("_token", request.getSession().getId());
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/report/SettingReport.jsp");
                rd.forward(request, response);

            }

        }
    }

    private WeeklyGoal getWeeklyGoal(EntityManager em, String userCode, LocalDate date) {

        WeeklyGoal weeklyGoal = em.createNamedQuery(JpaConst.Q_WEE_GET_BY_USER_STARTDATE, WeeklyGoal.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, userCode)
                .setParameter(JpaConst.JPQL_PARM_STARTDATE, date)
                .getSingleResult();
        return weeklyGoal;
    }

    private List<Goal> getGoalbyUser(EntityManager em, String userCode) {
        // 大きな目標の選択肢を取得
        List<Goal> goals = em.createNamedQuery(JpaConst.Q_GOA_GET_ALL_BY_USER, Goal.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, userCode)
                .getResultList();
        return goals;
    }

}
