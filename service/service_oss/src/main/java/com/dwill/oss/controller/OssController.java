package com.dwill.oss.controller;




import com.dwill.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dwill.commonutils.R;
@RestController
@CrossOrigin
@RequestMapping("/oss")
public class OssController {
    @Autowired
    private OssService ossService;
    @PostMapping("/fileoss")
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return  R.ok().data("url",url);
    }
}
