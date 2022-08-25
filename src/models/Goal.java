package models;

import java.sql.Timestamp;

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

@Table(name=JpaConst.TABLE_GOA)

@NamedQueries({
    @NamedQuery(
            name=JpaConst.Q_GOA_GET_ALL,
            query = JpaConst.Q_GOA_GET_ALL_DEF),
    @NamedQuery(
           name=JpaConst.Q_GOA_GET_COUNT_BY_USER,
           query=JpaConst.Q_GOA_GET_COUNT_BY_USER_DEF  ),
    @NamedQuery(
            name=JpaConst.Q_GOA_GET_ALL_BY_USER,
            query=JpaConst.Q_GOA_GET_ALL_BY_USER_DEF  )

})
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@Entity
public class Goal {

    @Id
    @Column(name = JpaConst.GOA_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id

    @ManyToOne
    @JoinColumn(name = JpaConst.GOA_COL_USE, nullable = false)
    private User  user; //

    @Column(name = JpaConst.GOA_COL_CREATED_AT, nullable = false)
    private Timestamp created_at;

    @Column(name=JpaConst.GOA_COL_TITLE, nullable = false)
    private String title;

    @Column(name=JpaConst.GOA_COL_ACTION,nullable=false)
    private String action;

    @Column(name=JpaConst.GOA_COL_REASON, nullable=false)
    private String reason;


}
