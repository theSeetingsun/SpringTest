package App.dao;

import App.domain.Project;
import App.query.ProjectQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface ProjectMapper extends BaseMapper<Project> {
    List<Project> getProinfos(ProjectQuery query);

    Long getProinfosCount(ProjectQuery query);

    List<Project> getProinfosPage(ProjectQuery query);

    List<Project> listByIsOwn(Long tecid);

}
