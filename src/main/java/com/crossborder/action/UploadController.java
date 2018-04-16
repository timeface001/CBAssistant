package com.crossborder.action;

import com.crossborder.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {

    @RequestMapping("/image")
    public @ResponseBody
    ResponseDto upload(HttpServletRequest request, MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String localPath = GeneralUtils.getUUID16() + FileType.getFileTypeByStream(file.getBytes());
        FileUtils.byte2File(file.getBytes(), GeneralUtils.absPath() + "/upload/", localPath);

        return ResponseGen.genSuccessData(localPath);
    }

}
