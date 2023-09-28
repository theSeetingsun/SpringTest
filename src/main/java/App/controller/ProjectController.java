package App.controller;

import App.domain.Project;
import App.query.ProjectQuery;
import App.service.IProjectService;
import App.utils.JsonResult;
import App.utils.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @RequestMapping(value = "/getProinfos", method = RequestMethod.POST)
    public JsonResult getProinfos(@RequestBody ProjectQuery query) {
        try {
            List<Project> list = projectService.getProinfos(query);
            return JsonResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败，我们正在殴打程序员！" + e.getMessage());
        }
    }
    @RequestMapping(value = "/getProinfosPage", method = RequestMethod.POST)
    public JsonResult getProinfosPage(@RequestBody ProjectQuery query) {
        try {
            PageList<Project> pageList = projectService.getProinfosPage(query);
            return JsonResult.success(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败，我们正在殴打程序员！" + e.getMessage());
        }
    }
    /**
     * 新增或修改产品信息
     *  如果id为null就是新增，否则就是修改
     * @param project
     * @return
     */
    @PostMapping("/save")
    public JsonResult save(@RequestBody Project project){
        try {
            if(project.getId() == null){
                //新增
                projectService.save(project);
            }else{
                //修改
                projectService.updateById(project);
            }
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            if(project.getId() == null){
                //新增
                return JsonResult.error("新增操作失败！");
            }else{
                //修改
                return JsonResult.error("修改操作失败！");
            }
        }
    }
    /**
     * 查询全表数据 不分页  不连表
     * @param tecid  技师id：如果传入了技师id，就要查询当前技师是否拥有某个服务项目
     * @return
     */
    @GetMapping("/listByIsOwn")
    public List<Project> list(Long tecid){
        return projectService.listByIsOwn(tecid);
    }

}
