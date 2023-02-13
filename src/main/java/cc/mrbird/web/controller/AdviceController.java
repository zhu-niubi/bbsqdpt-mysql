package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.Advice;
import cc.mrbird.web.service.AdviceService;
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
 * @Auther: jerry.feng
 * @Date: 2018/10/31
 * @Description: 投诉建议管理
 */
@Controller
public class AdviceController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdviceService adviceService;

    @RequestMapping("advice")
    @RequiresPermissions("advice:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "web/advice/advice";
    }

    /**
     * 获取反馈意见
     * @param request
     * @param advice
     * @return 记录
     */
    @Log("获取用户意见")
    @RequestMapping("advice/list")
    @RequiresPermissions("advice:list")
    @ResponseBody
    public Map<String, Object> adviceList(QueryRequest request, Advice advice) {
        log.info("[Advice AIP] param={}", advice);
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Advice> list = this.adviceService.AdviceFindPage(advice, request);
        PageInfo<Advice> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    /**
     * 删除指定反馈意见
     * @param ids
     * @return 影响行数
     */
    @Log("删除反馈意见")
    @RequiresPermissions("advice:delete")
    @RequestMapping("advice/delete")
    @ResponseBody
    public ResponseBo deleteAdvice(Integer ids) {
        try {
            Integer id = Integer.valueOf(ids);
            this.adviceService.deleteAdviceById(id);
            return ResponseBo.ok("删除意见成功！");
        } catch (Exception e) {
            log.error("删除意见失败", e);
            return ResponseBo.error("删除意见失败，请联系网站管理员！");
        }
    }
}
