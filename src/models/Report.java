package models;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.Getter;
import lombok.Setter;

@Table(name=JpaConst.TABLE_REP)
@NamedQueries({
    @NamedQuery(
            name=JpaConst.Q_RPT_GET_BY_USER_REPORTDATE_GOALID,
            query = JpaConst.Q_RPT_GET_BY_USER_REPORTDATE_GOALID_DEF),
    @NamedQuery(
            name=JpaConst.Q_RPT_GET_HOURSSUM_BY_USER_GOALID,
            query = JpaConst.Q_RPT_GET_HOURSSUM_BY_USER_GOALID_DEF),
    @NamedQuery(
    name =JpaConst.Q_RPT_GET_BY_USER_GOALID,
    query= JpaConst.Q_RPT_GET_BY_USER_GOALID_DEF
    )

})
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@Entity
public class Report {

    @Id
    @Column(name = JpaConst.REP_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id

    @ManyToOne
    @JoinColumn(name = JpaConst.REP_COL_USE, nullable = false)
    private User  user; //

    @ManyToOne
    @JoinColumn(name=JpaConst.REP_COL_GOA,nullable=false)
    private Goal goal;

    @ManyToOne
    @JoinColumn(name=JpaConst.REP_COL_WEE,nullable=false)
    private WeeklyGoal weekly_goal;

    @Column(name=JpaConst.REP_COL_DATE)
    private LocalDate reportDate;

    @Column(name=JpaConst.REP_COL_HOURS)
    private Integer hours;

    @Column(name=JpaConst.REP_COL_CREATED_AT)
    private Timestamp created_at;
}
