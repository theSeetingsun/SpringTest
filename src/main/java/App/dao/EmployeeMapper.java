package App.dao;

import App.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeMapper extends BaseMapper<Employee> {

}
