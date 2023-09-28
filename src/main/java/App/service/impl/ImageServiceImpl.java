package App.service.impl;

import App.dao.ImageMapper;
import App.domain.Image;
import App.service.IImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ImageServiceImpl extends
        ServiceImpl<ImageMapper, Image> implements IImageService {

}
