package com.java.carrent.task;

import com.java.carrent.constast.SysConstast;
import com.java.carrent.utils.AppFileUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 回收temp后缀文件的任务
 */
@Component
@EnableScheduling   // 开启定时任务
public class RecyleTempFileTask {

    @Scheduled(cron = "0 0 0/10 * * ? ")
    public void recyleFile() {
//        清除汽车临时图片
        recyleTempFile(AppFileUtils.PATH + File.separator + SysConstast.CAR_DIRECTORY);
//        清除用户头像临时图片
        recyleTempFile(AppFileUtils.PATH + File.separator + SysConstast.USER_FACE_DIRECTORY);
    }

    /**
     * 回收文件
     */
    public void recyleTempFile(String path) {
        File file = new File(path);
        recyleTempFile(file);
    }

    /**
     * 回收文件
     * @param path
     */
    public void recyleTempFile(File path) {
//        遍历该目录下的所有文件
        for (File f : path.listFiles()) {
//            如果该路径是一个文件的话
            if (f.isFile()) {
                if(f.getName().endsWith(SysConstast.DEFAULT_UPLOAD_TEMP)) {
                    //  并且后缀是以_temp结尾的, 删除该文件
                    f.delete();
                }
            } else {   // 如是一目录，递归
                recyleTempFile(f);
            }
        }
    }


}
