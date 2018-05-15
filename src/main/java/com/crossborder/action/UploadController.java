package com.crossborder.action;

import com.crossborder.utils.FileType;
import com.crossborder.utils.FileUtils;
import com.crossborder.utils.GeneralUtils;
import com.crossborder.utils.ResponseGen;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {

    @RequestMapping("/image")
    @ResponseBody
    public String upload(HttpServletRequest request, MultipartFile file) throws IOException {
        String localPath = GeneralUtils.getUUID16() + "." + FileType.getFileTypeByStream(file.getBytes());
        String fileName = request.getRealPath("/") + "upload/";
        File file11 = new File(fileName);
        if (!file11.exists()) {
            file11.mkdir();
        }
        FileUtils.byte2File(file.getBytes(), request.getRealPath("/") + "upload/", localPath);
        return ResponseGen.genSuccessData(localPath);
    }

}
