package App.utils.jwt;


import lombok.Data;

import java.util.List;

@Data
public class PayLoadData {
    private Object user;//放的Loininfo
    private List<String> permissions;
    private List menus;
}
