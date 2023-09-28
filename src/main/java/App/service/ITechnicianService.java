package App.service;

import App.domain.Technician;
import App.query.TechnicianQuery;
import App.utils.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ITechnicianService extends IService<Technician> {
    List<Technician> getTechnicians(TechnicianQuery query);

    PageList<Technician> getTechniciansPage(TechnicianQuery query);
}
