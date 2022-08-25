package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.Getter;
import lombok.Setter;

@Table(name = JpaConst.TABLE_USE)
  @NamedQueries({
    @NamedQuery(
            name=JpaConst.Q_USE_GET_ALL,
            query = JpaConst.Q_USE_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_USE_COUNT_REGISTERED_BY_CODE,
            query = JpaConst.Q_USE_COUNT_REGISTERED_BY_CODE_DEF),
    @NamedQuery(
            name = JpaConst.Q_USE_GET_BY_CODE_AND_PASS,
            query = JpaConst.Q_USE_GET_BY_CODE_AND_PASS_DEF)

})

@Getter
@Setter
@Entity
public class User {

    @Id
    @Column(name = JpaConst.USE_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = JpaConst.USE_COL_CODE, nullable = false, unique = true)
    private String code;

    @Column(name = JpaConst.USE_COL_NAME, nullable = false)
    private String name;

    @Column(name = JpaConst.USE_COL_PASS, length = 64, nullable = false)
    private String password;

    @Column(name = JpaConst.USE_COL_CREATED_AT)
    private Timestamp created_at;
}
