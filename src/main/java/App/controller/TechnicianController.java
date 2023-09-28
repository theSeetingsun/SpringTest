package App.controller;

import App.domain.Technician;
import App.query.TechnicianQuery;
import App.service.ITechnicianService;
import App.utils.JsonResult;
import App.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technician")
public class TechnicianController {
    @Autowired
    private ITechnicianService iTechnicianService;
    @PostMapping("/getTechnicians")
    public JsonResult getTechnicians(@RequestBody TechnicianQuery query){
        try {
            List<Technician> list = iTechnicianService.getTechnicians(query);
            return JsonResult.success(list);

        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败！我们正在殴打程序员！"+e.getMessage());
        }
    }
    @PostMapping("/getTechniciansPage")
    public JsonResult getTechniciansPage(@RequestBody TechnicianQuery query){
        try {
            PageList<Technician> pageList = iTechnicianService.getTechniciansPage(query);
            return JsonResult.success(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败！我们正在殴打程序员！"+e.getMessage());
        }
    }
    /**
     * 后端接口  新增或修改技师信息
     * @param technician
     * @return
     */
    @PostMapping("/save")
    public JsonResult save(@RequestBody Technician technician){
        try {
            if(technician.getId() == null){
                //id为空 表示新增
                iTechnicianService.save(technician);
            }else{
                //id不为空 表示修改
                iTechnicianService.updateById(technician);
            }
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            if(technician.getId() == null){
                return JsonResult.error("新增失败！" + e.getMessage());
            }else{
                return JsonResult.error("修改失败！" + e.getMessage());
            }
        }
    }

}
