package App.controller;

import App.domain.User;
import App.query.UserQuery;
import App.service.IUserservice;
import App.utils.JsonResult;
import App.utils.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserservice userService;

    @PostMapping("/userListPage")
    public JsonResult userListPage(@RequestBody UserQuery query) {
        Page<User> page = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .like(StringUtils.hasText(query.getUsername()), "username", query.getUsername())
                .like(StringUtils.hasText(query.getNickname()), "nickname",query.getNickname())
                .eq(StringUtils.hasText(query.getPhone()), "phone",query.getPhone());
        page = userService.page(page, wrapper);
        //Map  实体类
        return JsonResult.success(new PageList<User>(page.getTotal(), page.getRecords()));
    }

}
