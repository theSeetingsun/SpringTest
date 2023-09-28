package App.controller;

import App.utils.JsonResult;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${file.upload.img.project}")
    private String projectImgPath;
    @Value("${file.upload.img.technician}")
    private String technicianImgPath;

/*
     * 统一的文件上传接口
     * @param file
     * @param type 1表示产品图片  2表示技师图片
     * @return

 */


    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file, Integer type, HttpServletRequest request){
        try {
            System.out.println(file);
            System.out.println(file.getOriginalFilename());

            String originalFilename = file.getOriginalFilename();
            //1.截取字符串，获得后缀名
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

            //2.生成新的文件名称 要求永不重复 避免重复上传导致文件名称重复
            String filename = UUID.randomUUID().toString() + ext;

            String fileSavePath = "";
            String returnPath = "";
            if(type == 1){
                //产品图片
                fileSavePath = projectImgPath;
                returnPath = "project/" + filename;
            }else if(type == 2){
                //技师图片
                fileSavePath = technicianImgPath;
                returnPath = "technician/" + filename;
            }


            //3.采用输出流将文件二进制数据输出到新文件名称对应的文件中
            //开发阶段需要将此文件存储到src/main/resources下和target/classes下，项目上线后就只需要存储到target/classes下
            String path1 = "D:/Java/SpringTest/src/main/resources" + fileSavePath;
            String path2 = "D:/Java/SpringTest/target/classes" + fileSavePath;

            File file1 = new File(path1);
            if(!file1.exists()){
                file1.mkdirs();     //如果文件夹不存在，则创建文件夹，并且父级也会一起创建
            }
            File file2 = new File(path2);
            if(!file2.exists()){
                file2.mkdirs();     //如果文件夹不存在，则创建文件夹，并且父级也会一起创建
            }

            file1 = new File(path1, filename);
            file2 = new File(path2, filename);

            IOUtils.copy(file.getInputStream(), new FileOutputStream(file1));
            IOUtils.copy(file.getInputStream(), new FileOutputStream(file2));
            System.out.println(file1.getAbsolutePath());
            System.out.println(file2.getAbsolutePath());
            return JsonResult.success(returnPath);
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.error("文件上传失败！");
        }
    }

}
