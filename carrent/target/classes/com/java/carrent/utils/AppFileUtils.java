package com.java.carrent.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文件工具类
 */
public class AppFileUtils {

    public static final String PATH;

    static {
        PATH = AppFileUtils.class.getResource("/").getPath() + "resources/";
    }

    public static void deleteFile(String path) {
        File f = new File(path);
//        如果文件存在，才删除
        if(f.exists()) {
            f.delete();
        }
    }

    /**
     * 文件下载
     * @param response
     * @param path
     * @param oldName
     * @return
     */
    public static ResponseEntity<Object> downloadFile(HttpServletResponse response, String path, String oldName) {
        File file = new File(AppFileUtils.PATH, path);
        System.out.println(file.getAbsolutePath());
//        判断文件是否存在
        if(file.exists()) {
//            如果文件有中文，设置字符集
            try {
                URLEncoder.encode(oldName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//            把file转成一个byte[]
            try {
                byte[] bytes = FileUtils.readFileToByteArray(file);
                HttpHeaders header = new HttpHeaders();
//            封装响应内容类型（APPLICATION_OCTET_STREAM响应的内容不限定）
                header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            设置下载文件的名称
                header.setContentDispositionFormData("attachment", oldName);
//            创建ResponseEntity对象
                ResponseEntity<Object> entity = new ResponseEntity<>(bytes, header, HttpStatus.CREATED);
                return entity;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            PrintWriter pw;
            try {
                pw = response.getWriter();
                pw.write("文件不存在");
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 修改文件名称
     * @param carimg
     * @param suffix
     * @return
     */
    public static String updateFileName(String carimg, String suffix) {
        File file = new File(PATH, carimg);
        if(file.exists()) {
            file.renameTo(new File(PATH, carimg.replace(suffix, "")));
            return carimg.replace(suffix, "");
        }

        return null;
    }
}
