package App.query;

import lombok.Data;

@Data
public class TechnicianQuery extends BaseQuery{
    private String techexplain;

    private String techname;

    private Long busid;//外键
}
