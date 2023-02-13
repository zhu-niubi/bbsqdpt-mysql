package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.MainForum;
import cc.mrbird.web.service.MainForumService;
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

import java.util.Date;
import java.util.List;
import java.util.Map;
@Controller
public class MainForumController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MainForumService mainForumService;

    private static final String ON = "on";
    @Log("生成分类视图")
    @RequestMapping("mainForum")
    @RequiresPermissions("mainForum:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("mainForum", user);
        return "web/mainForum/mainForum";
    }

    @Log("获取分类信息")
    @RequestMapping("mainForum/list")
    @RequiresPermissions("mainForum:list")
    @ResponseBody
    public Map<String, Object> mainForumList(QueryRequest request, MainForum MainForum) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<MainForum> list = this.mainForumService.findMainForumByPage(MainForum, request);
        PageInfo<MainForum> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("获取要修改的分类信息")
    @RequestMapping("mainForum/getMainForum")
    @ResponseBody
    public ResponseBo getDict(Integer id) {
        try {
            MainForum mainForum = this.mainForumService.findById(id);
            return ResponseBo.ok(mainForum);
        } catch (Exception e) {
            log.error("获取分类信息失败", e);
            return ResponseBo.error("获取分类信息失败，请联系网站管理员！");
        }
    }
    @Log("新增分类 ")
    @RequiresPermissions("mainForum:add")
    @RequestMapping("mainForum/add")
    @ResponseBody
    public ResponseBo addMainForum(MainForum mainForum) {
        mainForum.setAddDate(new Date());
        try {
            this.mainForumService.addMainForum(mainForum);
            return ResponseBo.ok("新增分类成功！");
        } catch (Exception e) {
            log.error("新增分类失败", e);
            return ResponseBo.error("新增分类失败，请联系网站管理员！");
        }
    }

    @Log("删除分类")
    @RequiresPermissions("mainForum:delete")
    @RequestMapping("mainForum/delete")
    @ResponseBody
    public ResponseBo deleteMainForums(String ids) {
        try {
            this.mainForumService.deleteMainForums(ids);
            return ResponseBo.ok("删除分类成功！");
        } catch (Exception e) {
            log.error("删除分类失败", e);
            return ResponseBo.error("删除分类失败，请联系网站管理员！");
        }
    }

    @Log("修改分类 ")
    @RequiresPermissions("mainForum:update")
    @RequestMapping("mainForum/update")
    @ResponseBody
    public ResponseBo updateMainForum(MainForum mainForum) {
        mainForum.setEditDate(new Date());
        try {
            this.mainForumService.updateMainForum(mainForum);
            return ResponseBo.ok("修改分类成功！");
        } catch (Exception e) {
            log.error("修改分类失败", e);
            return ResponseBo.error("修改分类失败，请联系网站管理员！");
        }
    }
}
