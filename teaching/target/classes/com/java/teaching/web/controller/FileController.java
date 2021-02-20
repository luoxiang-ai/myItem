package com.java.teaching.web.controller;

import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.SysUser;
import com.java.teaching.utils.AppFileUtils;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.RandomUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传下载控制器
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    /**
     * 单个文件上传
     * @param mf
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/upload.action")
    public DataGridView uploadFile(MultipartFile mf) {
//        获取图片上传路径
        String uploadPath = AppFileUtils.PATH;
//        使用当前日期作为上传的文件夹名称
        String dirName = RandomUtils.getCurrentDateForString();

//      构造文件夹对象
        File dirFile = new File(uploadPath, dirName);
//        判断这个路径是否存在，如果不存在，则创建
        if(!dirFile.exists()) {
            dirFile.mkdirs();
        }
//        得到原文件名
        String oldName = mf.getOriginalFilename();
//        根据原文件名得到新文件名
        String newName = RandomUtils.createFileNameUseTime(oldName);
//        构建最终路径
        File dest = new File(dirFile, newName);
//      上传
        try {
            mf.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("src", dirName + File.separator + newName);
        return new DataGridView(map);
    }

    /**
     * 上传作业
     * @param mf
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/uploadWork.action")
    public DataGridView uploadWork(MultipartFile mf) {
//        获取图片上传路径
        String uploadPath = AppFileUtils.PATH;
//        使用当前日期作为上传的文件夹名称
        String dirName = RandomUtils.getCurrentDateForString();

//      构造文件夹对象
        File dirFile = new File(uploadPath, dirName);
//        判断这个路径是否存在，如果不存在，则创建
        if(!dirFile.exists()) {
            dirFile.mkdirs();
        }
//        得到原文件名
        String oldName = mf.getOriginalFilename();
//        根据原文件名得到新文件名
        String newName = RandomUtils.createFileNameUseTime(oldName);
//        构建最终路径
        File dest = new File(dirFile, newName);
//      上传
        try {
            mf.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("src", dirName + File.separator + newName);
        return new DataGridView(map);
    }

    /**
     * 批量文件上传
     * @param mfs
     * @return
     */
    @PostMapping(value = "/uploadFiles.action")
    @ResponseBody
    public DataGridView uploadFiles(MultipartFile[] mfs) {

        //        获取图片上传路径
        String uploadPath = AppFileUtils.PATH;
       try {
           for (MultipartFile mf : mfs) {
//            构建上传路径
               String orignalFilename = uploadPath + "excelData" + File.separator + mf.getOriginalFilename();
               System.out.println(orignalFilename);
               mf.transferTo(new File(orignalFilename));

               Map<String, Object> map = new HashMap<>();
               map.put("src", orignalFilename);
               return new DataGridView(map);
           }

       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }

    /**
     * 上传用户头像
     * @param mf
     * @return
     */
    @PostMapping(value = "/uploadUserFace.action")
    @ResponseBody
    public DataGridView uploadUserFace(MultipartFile mf, HttpSession session) {
//        获取用户唯一的登录名称
        SysUser user = (SysUser)session.getAttribute("sysUser");
        String dirName = SysConstast.USER_FACE_DIRECTORY + File.separator + user.getLoginname();
//        得到文件上传路径（每个用户的头像都存放在以自己帐号名称命名的文件夹中）
        String uploadPath = AppFileUtils.PATH + File.separator + dirName;
//        获取上传文件的原始名称
        String oldName = mf.getOriginalFilename();
//        根据当前时间生成新的文件名
        String newName = RandomUtils.createFileNameUseTime(oldName, SysConstast.DEFAULT_UPLOAD_TEMP);
        File dest = new File(uploadPath, newName);
        try {
            mf.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("src", dirName + File.separator + newName);
        return new DataGridView(map);
    }

    /**
     * 只显示图片，不下载
     * @param path
     * @param response
     * @return
     */
    @GetMapping(value = "/downloadShowFile.action")
    public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response) {
        return AppFileUtils.downloadFile(response, path, "");
    }

    /**
     * 下载图片
     * @param path
     * @param response
     * @return
     */
    public ResponseEntity<Object> downloadFile(String path, HttpServletResponse response) {

        return null;
    }

    /**
     * 文件下载
     * @param path
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/download.action")
    public ResponseEntity<Object> download(String fileName,String path, HttpServletResponse response) throws IOException {
//        图片路径
        String imgFullPath = AppFileUtils.PATH + path;
        String suffix = path.substring(path.lastIndexOf("."));
        String name = fileName + suffix;
        byte[] imgBody = FileUtils.readFileToByteArray(new File(imgFullPath));
//        中文名，乱码
        String download = new String(name.getBytes("utf-8"),"iso-8859-1");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment",download);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(imgBody, httpHeaders, HttpStatus.OK);
    }

}
