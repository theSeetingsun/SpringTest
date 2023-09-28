package App.controller;

import App.domain.Image;
import App.query.ImageQuery;
import App.service.IImageService;
import App.utils.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(tags = "图片接口控制器")
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private IImageService iImageService;

    /**
     * 根据图片类型查找图片数据
     * @return
     */
    @ApiOperation(tags = "根据图片类型查找图片数据", value = "根据图片类型查找图片数据")
    @RequestMapping(value = "/queryImageByType", method = RequestMethod.POST)
    public JsonResult queryImageByType(@RequestBody ImageQuery imageQuery){
        try {
            QueryWrapper<Image> queryWrapper = new QueryWrapper<Image>()
                    .eq(StringUtils.hasText(imageQuery.getImagetype()), "imagetype",imageQuery.getImagetype())
                    .eq(imageQuery.getImagestate() != null, "imagestate", imageQuery.getImagestate());
            List<Image> list = iImageService.list(queryWrapper);
            return JsonResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("操作失败，我们正在殴打程序员！"+ e.getMessage());
        }

    }
}
