package constants;

public interface JpaConst {

    String PERSISTENCE_UNIT_NAME = "kiroku_study";

    String TABLE_USE = "users"; //テーブル名
    String TABLE_GOA = "goals";
    String TABLE_WEE = "weekly_goals";
    String TABLE_REP = "reports";

    //ユーザーテーブル
    String USE_COL_ID = "id"; //id
    String USE_COL_CODE = "code"; //社員番号
    String USE_COL_NAME = "name"; //氏名
    String USE_COL_PASS = "password";
    String USE_COL_CREATED_AT = "created_at"; //登録日時

    //学習目標テーブル
    String GOA_COL_ID = "id";
    String GOA_COL_TITLE = "title";
    String GOA_COL_ACTION = "action";
    String GOA_COL_REASON = "reason";
    String GOA_COL_CREATED_AT = "created_at";
    String GOA_COL_USE = "user_id"; //日報を作成したユーザーのid


    //週間目標テーブル
    String WEE_COL_ID = "id";
    String WEE_COL_STA = "study_startdate"; //始まりの日付
    String WEE_COL_END = "study_enddate"; //終わりの日付
    String WEE_COL_HOURS = "hours";
    String WEE_COL_CREATED_AT = "created_at";
    String WEE_COL_USE = "user_id";
    String WEE_COL_GOA = "goal_id";

    //学習記録テーブル

    String REP_COL_ID = "id";
    String REP_COL_GOA = "goal_id";
    String REP_COL_WEE = "weekly_goal_id";
    String REP_COL_DATE = "report_date";
    String REP_COL_HOURS = "hours";
    String REP_COL_USE = "user_id";
    String REP_COL_CREATED_AT = "created_at";

    String ENTITY_USE = "user";
    String ENTITY_GOA = "goal";
    String ENTITY_WEE = "weekly_goal";
      String ENTITY_RPT = "report";

    String JPQL_PARM_CODE = "code"; //番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_USERS = "users";
    String JPQL_PARM_STARTDATE = "study_startdate";
    String JPQL_PARM_GOALID = "goal_id";
    String JPQL_PARM_REPORTDATE = "report_date";



    //ユーザーjpql
    String Q_USE_GET_ALL = ENTITY_USE + ".getAll"; //name
    String Q_USE_GET_ALL_DEF = "SELECT u FROM User AS u ORDER BY u.id DESC"; //query

    String Q_USE_GET_BY_CODE_AND_PASS = ENTITY_USE + ".getByCodeAndPass";
    String Q_USE_GET_BY_CODE_AND_PASS_DEF = "SELECT u FROM User AS u WHERE u.code = :" + JPQL_PARM_CODE
            + " AND u.password = :" + JPQL_PARM_PASSWORD;

    String Q_USE_COUNT_REGISTERED_BY_CODE = ENTITY_USE + ".countRegisteredByCode";
    String Q_USE_COUNT_REGISTERED_BY_CODE_DEF = "SELECT COUNT(u) FROM User AS u WHERE u.code = :" + JPQL_PARM_CODE;

    String Q_GOA_GET_ALL = ENTITY_GOA + ".getAll"; //name
    String Q_GOA_GET_ALL_DEF = "SELECT g FROM Goal AS g ORDER BY g.id DESC";

    String Q_GOA_GET_COUNT_BY_USER = ENTITY_GOA + "getCount";
    String Q_GOA_GET_COUNT_BY_USER_DEF = "SELECT COUNT(g) FROM Goal AS g WHERE g.user.code = :" + JPQL_PARM_CODE;

    String Q_GOA_GET_ALL_BY_USER = ENTITY_GOA + ".getByUser"; //name
    String Q_GOA_GET_ALL_BY_USER_DEF = "SELECT g FROM Goal AS g WHERE g.user.code = :" + JPQL_PARM_CODE +" ORDER BY g.id DESC";

    String Q_WEE_GET_ALL = ENTITY_WEE + ".getAll"; //name
    String Q_WEE_GET_ALL_DEF = "SELECT w FROM WeeklyGoal AS w WHERE w.user.code = :" + JPQL_PARM_CODE;


    String Q_WEE_GET_BY_USER_STARTDATE = ENTITY_WEE + ".getByUserAndStartdate"; //name
    String Q_WEE_GET_BY_USER_STARTDATE_DEF = "SELECT w FROM WeeklyGoal AS w WHERE w.user.code = :" + JPQL_PARM_CODE +" AND w.study_startdate <= :" + JPQL_PARM_STARTDATE +" AND w.study_enddate >= :" + JPQL_PARM_STARTDATE ;

    String Q_RPT_GET_BY_USER_REPORTDATE_GOALID = ENTITY_RPT + ".getByUserAndReportdate"; //name
    String Q_RPT_GET_BY_USER_REPORTDATE_GOALID_DEF = "SELECT r FROM Report AS r WHERE r.user.code = :" + JPQL_PARM_CODE +" AND r.reportDate = :" + JPQL_PARM_REPORTDATE +" AND r.goal.id = :" + JPQL_PARM_GOALID;


    String Q_RPT_GET_BY_USER_GOALID = ENTITY_RPT + ".getByUserAndGoalId"; //name
    String Q_RPT_GET_BY_USER_GOALID_DEF = "SELECT r FROM Report AS r WHERE r.user.code = :" + JPQL_PARM_CODE +" AND r.goal.id = :" + JPQL_PARM_GOALID;

    String Q_RPT_GET_HOURSSUM_BY_USER_GOALID = ENTITY_RPT + ".getHoursSumByUserAndReportdate"; //name
    String Q_RPT_GET_HOURSSUM_BY_USER_GOALID_DEF = "SELECT SUM(r.hours) FROM Report AS r WHERE r.user.code = :" + JPQL_PARM_CODE + " AND r.goal.id = :" + JPQL_PARM_GOALID;

}
