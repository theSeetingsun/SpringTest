package App.dao;

import App.domain.Employee;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;
    /*
    * 测试插入
    * */
    @Test
    public void testSave() throws Exception{
        Employee employee = new Employee();
        employee.setName("张三");
        employee.setAge(24);
        employee.setPhone("123456789");
        employeeMapper.insert(employee);
    }
    /*
    * 测试更新
    * */
    @Test
    public void testUpdate() throws Exception{
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("张三");
        employee.setAge(24);
        employee.setPhone("987654321");
        employeeMapper.updateById(employee);
    }
    /*
    * 测试删除
    * */
    @Test
    public void testDelete() throws Exception{
        employeeMapper.deleteById(2L);
    }
    /*
    * 测试查询
    * */
    @Test
    public void testSelectByID() throws Exception{
        System.out.println(employeeMapper.selectById(1L));
    }
    /*
    * 数量查询
    * */
    @Test
    public void testSelectCount() throws Exception{
        System.out.println(employeeMapper.selectCount(null));
    }
    /*
    * 分页查询
    * mysql分页查询，在SQL语句后面拼接limit关键字，后面再跟两个整数，以逗号隔开
    * 第一个整数：表示从第几行开始取数据  （当前页码 - 1） * 每页条数
    * 第二个整数：表示取多少行数据
    * 在启动类配置MybatisPlusInterceptor拦截器后，调用selectPage方法会自动分页
    *       执行两条SQL语句
    * 一条查询行数                SELECT COUNT(*) FROM t_employee
    * 一条查询当前页的数据        SELECT id,name,phone,age FROM t_employee LIMIT ?,?
    * */
    @Test
    public void testQueryPage() throws Exception{
        Page page = new Page(2,2);
        page = employeeMapper.selectPage(page, null);
        System.out.println("当前页码：" + page.getCurrent());
        System.out.println("每页条数：" + page.getSize());
        System.out.println("总条数：" + page.getTotal());
        System.out.println("本页数据集合：" + page.getRecords());
    }
}