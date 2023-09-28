package App.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("project")
public class Project {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String proexplain;
    private Double proprice;
    private String prostep;
    private Long protypeid;
    private String proname;
    private Long imageid;
    private Integer prostatus = 1;
    private Long busid;
    private Long tecid;
    @TableField(exist = false)//表示临时字段，不与数据表中映射
    private Image image;
    @TableField(exist = false)
    private ProjectType projectType;
    @TableField(exist = false)
    private String imageurl;
    @TableField(exist = false)
    private Boolean imgIsChanged;   //修改时 图片是否被改变

    @TableField(exist = false)
    private Integer isOwn;

}
