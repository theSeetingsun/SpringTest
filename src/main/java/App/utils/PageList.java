package App.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*封装分页数据*/
@Data
@AllArgsConstructor//全参构造
@NoArgsConstructor//无参构造
public class PageList<T> {

    /*总条数*/
    private Long total = 0L;
    /*当前页数据*/
    private List<T> list = new ArrayList<>();
}
