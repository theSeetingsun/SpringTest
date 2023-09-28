package App.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors
@Data
@TableName("image")
public class Image {
    @TableId(type = IdType.AUTO)
    private long id;
    private String imageurl;
    private String imagetitle;
    private String imagetype;
    private Integer imagestate;



}
