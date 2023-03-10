package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.WebUser;
import cc.mrbird.web.service.WebUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by cui on 2018/10/25.
 */
@Controller
public class WebUserController  extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WebUserService webUserService;

    @RequestMapping("webuser")
    @RequiresPermissions("webuser:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "web/user/user";
    }

    @RequestMapping("webuser/checkUserByName")
    @ResponseBody
    public boolean checkUserName(String username, Integer id) {
        if (id !=null){
            return true;
        }
        WebUser result = this.webUserService.findUserByName(username);
        return result == null;
    }


    @RequestMapping("webuser/checkUserByEmail")
    @ResponseBody
    public boolean checkUserEmail(String email, Integer id) {
        if (id !=null){
            return true;
        }
        WebUser result = this.webUserService.findUserByEmail(email);
        return result == null;
    }
//
    @RequestMapping("webuser/getUser")
    @ResponseBody
    public ResponseBo getUser(Integer id) {
        try {
            WebUser user = this.webUserService.findById(id);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("??????????????????", e);
            return ResponseBo.error("????????????????????????????????????????????????");
        }
    }

    @Log("??????????????????")
    @RequestMapping("webuser/list")
    @RequiresPermissions("webuser:list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request, WebUser user) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<WebUser> list = this.webUserService.findWebUserByPage(user, request);
        PageInfo<WebUser> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
}

//    @RequestMapping("user/excel")
//    @ResponseBody
//    public ResponseBo userExcel(User user) {
//        try {
//            List<User> list = this.webUserService.findUserWithDept(user, null);
//            return FileUtils.createExcelByPOIKit("?????????", list, User.class);
//        } catch (Exception e) {
//            log.error("??????????????????Excel??????", e);
//            return ResponseBo.error("??????Excel????????????????????????????????????");
//        }
//    }
//
//    @RequestMapping("user/csv")
//    @ResponseBody
//    public ResponseBo userCsv(User user) {
//        try {
//            List<User> list = this.webUserService.findUserWithDept(user, null);
//            return FileUtils.createCsv("?????????", list, User.class);
//        } catch (Exception e) {
//            log.error("??????????????????Csv??????", e);
//            return ResponseBo.error("??????Csv????????????????????????????????????");
//        }
//    }
//
//    @RequestMapping("user/regist")
//    @ResponseBody
//    public ResponseBo regist(User user) {
//        try {
//            User result = this.webUserService.findByName(user.getUsername());
//            if (result != null) {
//                return ResponseBo.warn("???????????????????????????");
//            }
//            this.webUserService.registUser(user);
//            return ResponseBo.ok();
//        } catch (Exception e) {
//            log.error("????????????", e);
//            return ResponseBo.error("??????????????????????????????????????????");
//        }
//    }
//
//    @Log("????????????")
//    @RequestMapping("user/theme")
//    @ResponseBody
//    public ResponseBo updateTheme(User user) {
//        try {
//            this.webUserService.updateTheme(user.getTheme(), user.getUsername());
//            return ResponseBo.ok();
//        } catch (Exception e) {
//            log.error("??????????????????", e);
//            return ResponseBo.error();
//        }
//    }
//
    @Log("????????????")
    @RequiresPermissions("webuser:add")
    @RequestMapping("webuser/add")
    @ResponseBody
    public ResponseBo addUser(WebUser user) {
        try {
            this.webUserService.addUser(user);
            return ResponseBo.ok("?????????????????????");
        } catch (Exception e) {
            log.error("??????????????????", e);
            return ResponseBo.error("????????????????????????????????????????????????");
        }
    }
//
    @Log("????????????")
    @RequiresPermissions("webuser:update")
    @RequestMapping("webuser/update")
    @ResponseBody
    public ResponseBo updateUser(WebUser user) {
        try {
            this.webUserService.updateUser(user);
            return ResponseBo.ok("?????????????????????");
        } catch (Exception e) {
            log.error("??????????????????", e);
            return ResponseBo.error("????????????????????????????????????????????????");
        }
    }
//
//
//    @RequestMapping("user/checkPassword")
//    @ResponseBody
//    public boolean checkPassword(String password) {
//        User user = getCurrentUser();
//        String encrypt = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
//        return user.getPassword().equals(encrypt);
//    }
//
//
//    @RequestMapping("user/profile")
//    public String profileIndex(Model model) {
//        User user = super.getCurrentUser();
//        user = this.webUserService.findUserProfile(user);
//        String ssex = user.getSsex();
//        if (User.SEX_MALE.equals(ssex)) {
//            user.setSsex("????????????");
//        } else if (User.SEX_FEMALE.equals(ssex)) {
//            user.setSsex("????????????");
//        } else {
//            user.setSsex("???????????????");
//        }
//        model.addAttribute("user", user);
//        return "system/user/profile";
//    }




}
