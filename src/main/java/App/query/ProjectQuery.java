package App.query;

import lombok.Data;

@Data
public class ProjectQuery extends BaseQuery{
    //价格区间
    private Double propriceMin;
    private Double propriceMax;

    //产品状态
    private Integer prostatus;
    //公司id
    private Long busid;
    //产品类型
    private Long protypeid;
    //产品名称
    private String proname;
}
