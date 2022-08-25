package viewmodels;

import lombok.Getter;
import lombok.Setter;

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
public class TopPageInfo {

    private Integer goalId;

    private String goalTitle;

    private Integer todayStudyHours;

    private Long totalStudyHours;



}
