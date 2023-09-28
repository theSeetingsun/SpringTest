package App.service.impl;

import App.dao.ImageMapper;
import App.dao.ProjectMapper;
import App.domain.Image;
import App.domain.Project;
import App.query.ProjectQuery;
import App.service.IProjectService;
import App.utils.PageList;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {
    @Autowired
    private ImageMapper imageMapper;
    @Value("${file.upload.img.project}")
    private String projectImgPath;

    @Override
    public List<Project> getProinfos(ProjectQuery query) {
        return baseMapper.getProinfos(query);
    }

    @Override
    public PageList<Project> getProinfosPage(ProjectQuery query) {
        //调用mapper层的方法，查询总数量
        Long total = baseMapper.getProinfosCount(query);
        //调用mapper层的方法，查询当前页的数据集合
        List<Project> rows =  baseMapper.getProinfosPage(query);
        return new PageList<Project>(total, rows);
    }

    @Override
    public List<Project> listByIsOwn(Long tecid) {
        return baseMapper.listByIsOwn(tecid);
    }

    /**
     * service层重写save方法：因为这个操作不止是按照基本操作。
     * @param project
     * @return
     */
    @Override
    @Transactional
    public boolean save(Project project) {
        //先保存image表  返回主键值
        Image image = new Image();
        image.setImageurl(project.getImageurl());
        image.setImagestate(1);
        image.setImagetitle("产品");
        image.setImagetype("pro");
        //mybatisplus由于单表操作没有SQL语句，尤其是新增，它不确定你是否需要返回主键值，所以就统一只要是insert单行数据都会默认返回主键
        imageMapper.insert(image);

        //再保存project表的数据
        project.setImageid(image.getId());
        return super.save(project);
    }
    /**
     * 重写updateById
     * @param project
     * @return
     */
    @Override
    @Transactional
    public boolean updateById(Project project) {
        if(project.getImgIsChanged()){
            //删除原先的图片文件
            String imageurl = project.getImage().getImageurl();
            System.out.println(imageurl);
            imageurl = imageurl.substring(imageurl.lastIndexOf("/") + 1);
            String path1 = "D:/Java/SpringTest/src/main/resources" + projectImgPath + imageurl;
            String path2 = "D:/Java/SpringTest/target/classes" + projectImgPath + imageurl;
            File file1 = new File(path1);
            File file2 = new File(path2);
            if(file1.exists())  file1.delete();
            if(file2.exists())  file2.delete();

            //先删除原先的旧图片
            imageMapper.deleteById(project.getImageid());

            //再保存新的图片数据
            Image image = new Image();
            image.setImageurl(project.getImageurl());
            image.setImagestate(1);
            image.setImagetitle("产品");
            image.setImagetype("pro");
            //mybatisplus由于单表操作没有SQL语句，尤其是新增，它不确定你是否需要返回主键值，所以就统一只要是insert单行数据都会默认返回主键
            imageMapper.insert(image);
            project.setImageid(image.getId());
        }
        return super.updateById(project);
    }
}

