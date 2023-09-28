package App.query;

import lombok.Data;

@Data
public class BaseQuery {
    private Integer page = 1;
    private Integer limit = 10;
    public Integer getStartIndex(){
        return (page - 1) * limit;
    }
}
