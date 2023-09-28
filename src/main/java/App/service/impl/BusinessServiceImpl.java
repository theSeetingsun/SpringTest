package App.service.impl;

import App.dao.BusinessMapper;
import App.domain.Business;
import App.domain.Project;
import App.query.BusinessQuery;
import App.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

}
