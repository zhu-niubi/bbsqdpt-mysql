package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.Dict;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.Notice;
import cc.mrbird.web.domain.WebUser;
import cc.mrbird.web.service.NoticeService;
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
public class NoticeController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NoticeService noticeService;

    private static final String ON = "on";
    @Log("生成公告视图")
    @RequestMapping("notice")
    @RequiresPermissions("notice:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("notice", user);
        return "web/notice/notice";
    }

    @Log("获取公告信息")
    @RequestMapping("notice/list")
    @RequiresPermissions("notice:list")
    @ResponseBody
    public Map<String, Object> noticeList(QueryRequest request, Notice notice) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Notice> list = this.noticeService.findWebUserByPage(notice, request);
        PageInfo<Notice> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("获取要修改的公告信息")
    @RequestMapping("notice/getNotice")
    @ResponseBody
    public ResponseBo getDict(Integer id) {
        try {
            Notice notice = this.noticeService.findById(id);
            return ResponseBo.ok(notice);
        } catch (Exception e) {
            log.error("获取公告信息失败", e);
            return ResponseBo.error("获取公告信息失败，请联系网站管理员！");
        }
    }
    @Log("新增公告 ")
    @RequiresPermissions("notice:add")
    @RequestMapping("notice/add")
    @ResponseBody
    public ResponseBo addNotice(Notice notice) {
        User user = super.getCurrentUser();
        Long id = user.getUserId();
        int adminId = id.intValue();
        notice.setGreatNum(0);
        notice.setReplayNum(0);
        notice.setNoticeDate(new Date());
        notice.setAdminId(adminId);
        try {
            this.noticeService.addNotice(notice);
            return ResponseBo.ok("新增公告成功！");
        } catch (Exception e) {
            log.error("新增公告失败", e);
            return ResponseBo.error("新增公告失败，请联系网站管理员！");
        }
    }

    @Log("删除公告")
    @RequiresPermissions("notice:delete")
    @RequestMapping("notice/delete")
    @ResponseBody
    public ResponseBo deleteNotices(String ids) {
        try {
            this.noticeService.deleteNotices(ids);
            return ResponseBo.ok("删除公告成功！");
        } catch (Exception e) {
            log.error("删除公告失败", e);
            return ResponseBo.error("删除公告失败，请联系网站管理员！");
        }
    }

    @Log("修改公告 ")
    @RequiresPermissions("notice:update")
    @RequestMapping("notice/update")
    @ResponseBody
    public ResponseBo updateNotice(Notice notice) {
        try {
            this.noticeService.updateNotice(notice);
            return ResponseBo.ok("修改公告成功！");
        } catch (Exception e) {
            log.error("修改公告失败", e);
            return ResponseBo.error("修改公告失败，请联系网站管理员！");
        }
    }
}
