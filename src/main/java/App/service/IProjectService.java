package App.service;

import App.domain.Project;
import App.query.ProjectQuery;
import App.utils.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IProjectService extends IService<Project> {
    List<Project> getProinfos(ProjectQuery query);

    PageList<Project> getProinfosPage(ProjectQuery query);

    List<Project> listByIsOwn(Long tecid);
}
