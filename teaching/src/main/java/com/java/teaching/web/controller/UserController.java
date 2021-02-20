package com.java.teaching.web.controller;

import com.java.teaching.common.vo.UserVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.SysUser;
import com.java.teaching.service.UserService;
import com.java.teaching.utils.AppFileUtils;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * 用户控制器
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 修改密码
     * @param userVo
     * @return
     */
    @PostMapping(value = "/updatePwd.action")
    public ResultObj updatePwd(UserVo userVo) throws Exception {
        try {
            this.userService.updatePwd(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    @PostMapping(value = "/queryUserByUserId.action")
    public SysUser queryUserByUserId(Integer userId) {
        return this.userService.queryUserByUserId(userId);
    }

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @PostMapping(value = "/updateUser.action")
    public ResultObj updateUser(UserVo userVo, HttpSession session) {
        try {
            this.userService.updateUser(userVo);
//            session.removeAttribute("sysUser");
//            从数据库中查询修改后的值，然后放到session中
            SysUser sysUser = this.userService.queryUserByUserId(userVo.getUserid());
            session.setAttribute("sysUser", sysUser);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 验证用户旧密码
     * @param userVo
     * @return
     */
    @PostMapping(value = "/queryUserByUserIdAndPassword.action")
    public ResultObj queryUserByUserIdAndPassword(UserVo userVo) {
        try {
            int count = this.userService.queryUserByUserIdAndPassword(userVo);
            if(count > 0) {
                return ResultObj.UPDATE_SUCCESS;
            }
            return ResultObj.UPDATE_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 更新头像
     * @param croppedCanvas
     * @param session
     * @return
     */
    @PostMapping(value = "/updateUserFace.action")
    public ResultObj updateUserFace(String croppedCanvas, HttpSession session) {
        OutputStream os = null;
        try {
            System.out.println(croppedCanvas);
            SysUser user = (SysUser) session.getAttribute("sysUser");
            String suffix = croppedCanvas.substring(croppedCanvas.indexOf("/") + 1, croppedCanvas.indexOf(";"));
            String content = croppedCanvas.substring(croppedCanvas.indexOf(",") + 1);
            String fileName = UUID.randomUUID().toString() + ".";
            String facePath = File.separator + SysConstast.USER_FACE_DIRECTORY + File.separator + user.getLoginname() + File.separator + fileName + suffix;
            File f = new File(AppFileUtils.PATH + facePath);
            os = new FileOutputStream(f);
//            Notice：写入数据时，必须使用base64进行解码写入
//            os.write(Base64.decode(content.getBytes()));
            os.write(new BASE64Decoder().decodeBuffer(content));

            UserVo userVo = new UserVo();
            userVo.setUserid(user.getUserid());
            userVo.setUserface(facePath);
            this.userService.updateUser(userVo);

            SysUser sysUser = this.userService.queryUserByUserId(user.getUserid());
            session.setAttribute("sysUser", sysUser);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        } finally {
//            关流
            try {
                if(os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
