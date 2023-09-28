package App.controller;

import App.domain.Business;
import App.domain.Project;
import App.domain.ProjectType;
import App.domain.User;
import App.query.BusinessQuery;
import App.service.IBusinessService;
import App.utils.JsonResult;
import App.utils.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private IBusinessService businessService;
    @RequestMapping(value = "/getBusiness", method = RequestMethod.POST)
    public JsonResult getBusiness(@RequestBody BusinessQuery query) {
        try {
            Page<Business> page = new Page<>(query.getPage(), query.getLimit());
            QueryWrapper<Business> wrapper = new QueryWrapper<Business>();
            page = businessService.page(page, wrapper);
            return JsonResult.success(new PageList<Business>(page.getTotal(), page.getRecords()));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败，我们正在殴打程序员！" + e.getMessage());
        }
    }
    @RequestMapping(value = "/getBusiness", method = RequestMethod.GET)
    public JsonResult getBusiness() {
        try {
            QueryWrapper<Business> wrapper = new QueryWrapper<Business>();
            List<Business> list = businessService.list(wrapper);
            return JsonResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败，我们正在殴打程序员！" + e.getMessage());
        }
    }
}
