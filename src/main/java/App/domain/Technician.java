package App.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Data
@TableName("technician")
public class Technician {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String techexplain;

    private String techname;

    private Long busid;//外键

    private String imageurl;
    //多对一关系
    @TableField(exist = false)
    private Business business;

    //一对多关系
    @TableField(exist = false)
    private List<Project> projects = new ArrayList<>();
    @TableField(exist = false)
    private List<Long> proids = new ArrayList<>();
    @TableField(exist = false)
    private Boolean imgIsChanged;

    @TableField(exist = false)
    private String oldImageurl;

}
