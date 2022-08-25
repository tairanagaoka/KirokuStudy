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

@Table(name = JpaConst.TABLE_WEE)
@NamedQueries({
        @NamedQuery(name = JpaConst.Q_WEE_GET_ALL, query = JpaConst.Q_WEE_GET_ALL_DEF),
        @NamedQuery(
            name=JpaConst.Q_WEE_GET_BY_USER_STARTDATE,
            query = JpaConst.Q_WEE_GET_BY_USER_STARTDATE_DEF)
})
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@Entity
public class WeeklyGoal {

    @Id
    @Column(name = JpaConst.WEE_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id

    @ManyToOne
    @JoinColumn(name = JpaConst.WEE_COL_USE, nullable = false)
    private User user;

    @Column(name = JpaConst.WEE_COL_STA)
    private LocalDate study_startdate;

    @Column(name = JpaConst.WEE_COL_END)
    private LocalDate study_enddate;

    @Column(name = JpaConst.WEE_COL_HOURS)
    private Integer hours;

    @Column(name = JpaConst.WEE_COL_CREATED_AT)
    private Timestamp created_at;

}
