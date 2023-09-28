package App.controller;


import App.domain.ProjectType;
import App.query.ProjectTypequery;
import App.service.IProjectTypeService;
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
@RequestMapping("/projecttype")
public class ProjectTypeController {
    @Autowired
    private IProjectTypeService projectTypeService;
    @RequestMapping(value = "/getProjectType", method = RequestMethod.POST)
    public JsonResult getProjectType(@RequestBody ProjectTypequery query){
        try {
            Page<ProjectType> page = new Page<>(query.getPage(), query.getLimit());
            QueryWrapper<ProjectType> wrapper = new QueryWrapper<ProjectType>();
            page = projectTypeService.page(page, wrapper);
            return JsonResult.success(new PageList<ProjectType>(page.getTotal(), page.getRecords()));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败，我们正在殴打程序员！" + e.getMessage());
        }
    }
    @RequestMapping(value = "/getProjectType", method = RequestMethod.GET)
    public JsonResult getProjectType(){
        try {
            QueryWrapper<ProjectType> wrapper = new QueryWrapper<ProjectType>();
            List<ProjectType> list = projectTypeService.list(wrapper);
            return JsonResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败，我们正在殴打程序员！" + e.getMessage());
        }
    }
}
