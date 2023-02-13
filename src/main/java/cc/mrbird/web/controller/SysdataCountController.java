package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.web.service.PostService;
import cc.mrbird.web.service.SysdataCountService;
import cc.mrbird.web.service.WebUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据统计控制层
 */
@Controller
public class SysdataCountController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysdataCountService sysdataCountService;
    @Autowired
    private WebUserService webUserService;
    @Autowired
    private PostService postService;

    @RequestMapping("sysdataCount")
    public String index(Model model){
        //用户总数
        int userConut=webUserService.getUserCount(null);
        //论贴总数
        int postConut=postService.getPostCount(null);
        model.addAttribute("userConut", userConut);
        model.addAttribute("postConut", postConut);
        return "web/dataCount/index";
    }

    /**
     *，每日数据
     * @return
     */
    @RequestMapping("dayCount")
    @ResponseBody
    public String getDayCount() {

        return sysdataCountService.dayCount();
    }
}
