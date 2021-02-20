package com.java.carrent.web.controller;

import com.java.carrent.common.vo.UserVo;
import com.java.carrent.constast.SysConstast;
import com.java.carrent.entity.SysUser;
import com.java.carrent.service.UserService;
import com.java.carrent.utils.AppFileUtils;
import com.java.carrent.utils.DataGridView;
import com.java.carrent.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 加载全部用户
     * @param userVo
     * @return
     */
    @GetMapping(value = "/loadAllUser.action")
    public DataGridView loadAllUser(UserVo userVo) {

        return this.userService.queryAllUser(userVo);
    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @PostMapping(value = "/addUser.action")
    public ResultObj addUser(UserVo userVo) {
        try {
            this.userService.addUser(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
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
            SysUser user = (SysUser) session.getAttribute("sysUser");
//            从数据库中查询修改后的值，然后放到session中
            SysUser sysUser = this.userService.queryUserByUserId(String.valueOf(userVo.getUserid()));
            session.setAttribute("sysUser", sysUser);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @PostMapping(value = "/deleteUser.action")
    public ResultObj deleteUser(Integer userId) {
        try {
            this.userService.deleteUser(userId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param userVo
     * @return
     */
    @PostMapping(value = "/batchDeleteUser.action")
    public ResultObj batchDeleteUser(UserVo userVo) {
        try {
            this.userService.batchDeleteUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 保存用户和角色的关系
     * @param userVo
     * @return
     */
    @PostMapping(value = "/saveUserRole.action")
    public ResultObj saveUserRole(UserVo userVo) {
        System.out.println(userVo);
        try {
            this.userService.saveUserRole(userVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

    /**
     * 加载用户拥有的角色
     * @param userVo
     * @return
     */
    @GetMapping(value = "/loadUserRole.action")
    public DataGridView loadUserRole(UserVo userVo) {
        return this.userService.queryUserRole(userVo);
    }

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    @PostMapping(value = "/queryUserByUserId.action")
    public SysUser queryUserByUserId(String userId) {
        return this.userService.queryUserByUserId(userId);
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

            SysUser sysUser = this.userService.queryUserByUserId(String.valueOf(user.getUserid()));
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
