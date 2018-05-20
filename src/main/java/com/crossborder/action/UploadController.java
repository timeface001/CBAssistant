package com.crossborder.action;

import com.crossborder.utils.FileUtils;
import com.crossborder.utils.ResponseGen;
import com.crossborder.utils.oss.OSSUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {

    @RequestMapping("/image")
    @ResponseBody
    public String upload(HttpServletRequest request, MultipartFile file) throws IOException {
        String localPath = OSSUtils.upload(new ByteArrayInputStream(file.getBytes()));
        return ResponseGen.genSuccessData(localPath);
    }

}
