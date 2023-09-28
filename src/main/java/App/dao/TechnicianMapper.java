package App.dao;

import App.domain.Technician;
import App.query.TechnicianQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnicianMapper extends BaseMapper<Technician> {
    List<Technician> getTechnicians(TechnicianQuery query);

    Long getTechniciansPageCount(TechnicianQuery query);

    List<Technician> getTechniciansPage(TechnicianQuery query);
    /**
     * 保存技师与产品之间的中间表数据：循环添加   mapper层xml中批量添加
     * mybatis要求mapper层接口最多支持一个参数，非要传递多个参数怎么办呢？
     *  必须使用mybatis的@Param注解
     *  底层原理：将这些参数封装成一个HashMap，以@Param注解值为key，以参数值为value
     * @param tecid
     * @param proids
     */
    void insertBatchTecpro(@Param("tecid") Long tecid, @Param("proids") List<Long> proids);

    /**
     * 根据技师id删除所有数据
     * @param tecid
     */
    void deleteBatchTecproByTecid(Long tecid);
}
