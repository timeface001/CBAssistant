package com.crossborder.utils.oss;

import com.aliyun.oss.OSSClient;
import com.crossborder.utils.FileType;
import com.crossborder.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class OSSUtils {

    private static OSSClient ossClient = new OSSUtils().init();

    public static String upload(InputStream inputStream) throws IOException {
        inputStream.mark(0);
        String path = UUID.randomUUID() + "." + FileType.getFileTypeByStream(inputStream);

        inputStream.reset();
        // 上传文件流
        ossClient.putObject("cbassistant", "product/" + path, inputStream);

        // 关闭client
        //ossClient.shutdown();

        return path;

    }


    public static String upload(File file) throws IOException {
        String path = "product/" + UUID.randomUUID() + "." + FileType.getFileTypeByStream(FileUtils.File2byte(file));
        // 上传文件流
        ossClient.putObject("cbassistant", path, file);

        //LogUtils.info("key:" + path);

        return path;

    }


    protected OSSClient init() {
        //return new OSSClient(aliyunConfig.getEndpoint(), aliyunConfig.getAccessKeyId(), aliyunConfig.getAccessKeySecret());
        return new OSSClient("oss-cn-beijing.aliyuncs.com", "LTAIxXSFIYTJ7VIC", "xxsi6SugWHS4t97M8m8eFXP095VOPZ");
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static final String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if ("bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";
        if ("gif".equalsIgnoreCase(fileExtension)) return "image/gif";
        if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension) || "png".equalsIgnoreCase(fileExtension))
            return "image/jpeg";
        if ("html".equalsIgnoreCase(fileExtension)) return "text/html";
        if ("txt".equalsIgnoreCase(fileExtension)) return "text/plain";
        if ("vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";
        if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension))
            return "application/vnd.ms-powerpoint";
        if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension))
            return "application/msword";
        if ("xml".equalsIgnoreCase(fileExtension)) return "text/xml";
        return "text/html";
    }


}


