package App.service.impl;

import App.dao.TechnicianMapper;
import App.domain.Technician;
import App.query.TechnicianQuery;
import App.service.ITechnicianService;
import App.utils.PageList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
@Service
public class TechnicianServiceImpl extends ServiceImpl<TechnicianMapper, Technician> implements ITechnicianService {

    @Override
    public List<Technician> getTechnicians(TechnicianQuery query) {
        return baseMapper.getTechnicians(query);
    }

    @Override
    public PageList<Technician> getTechniciansPage(TechnicianQuery query) {
        //调用mapper层的方法，查询总数量
        Long total = baseMapper.getTechniciansPageCount(query);
        //调用mapper层的方法，查询当前页的数据集合
        List<Technician> rows =  baseMapper.getTechniciansPage(query);
        return new PageList<Technician>(total, rows);
    }
}
