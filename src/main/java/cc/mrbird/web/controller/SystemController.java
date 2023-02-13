package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.service.SystemService;
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
import cc.mrbird.web.domain.System;
import java.util.List;
import java.util.Map;
@Controller
public class SystemController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SystemService systemService;


    @RequestMapping("system")
    @RequiresPermissions("system:list")
    public String index(Model model){
        return "web/system/system";
    }


    //
    @RequestMapping("system/getSystem")
    @ResponseBody
    public ResponseBo getSystem(Integer id) {
        try {
            System user = this.systemService.findById(id);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取消息失败", e);
            return ResponseBo.error("获取消息失败，请联系网站管理员！");
        }
    }

    @Log("获取消息详情")
    @RequestMapping("system/list")
    @RequiresPermissions("system:list")
    @ResponseBody
    public Map<String, Object> systemList(QueryRequest request, System system) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<System> list = this.systemService.findSystemByPage(system, request);
        PageInfo<System> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("发送消息")
    @RequestMapping("system/send")
    @ResponseBody
    public ResponseBo systemSend(String userIds,String systemId) {
        try {
            this.systemService.sendSystem(systemId,userIds);
            return ResponseBo.ok("发送消息成功！");
        } catch (Exception e) {
            log.error("发送消息失败", e);
            return ResponseBo.error("发送消息失败，请联系网站管理员！");
        }
    }

    @Log("新增消息")
    @RequiresPermissions("system:add")
    @RequestMapping("system/add")
    @ResponseBody
    public ResponseBo addSystem(System system) {
        try {
            User user=super.getCurrentUser();
            system.setCreaterId(Integer.parseInt(user.getUserId()+""));
            system.setCreaterName(user.getUsername());
            this.systemService.addSystem(system);
            return ResponseBo.ok("新增消息成功！");
        } catch (Exception e) {
            log.error("新增消息失败", e);
            return ResponseBo.error("新增消息失败，请联系网站管理员！");
        }
    }

    @Log("删除消息")
    @RequiresPermissions("system:delete")
    @RequestMapping("system/delete")
    @ResponseBody
    public ResponseBo deleteSystem(String ids) {
        try {
            this.systemService.deleteSystems(ids);
            return ResponseBo.ok("删除消息成功！");
        } catch (Exception e) {
            log.error("删除消息失败", e);
            return ResponseBo.error("删除消息失败，请联系网站管理员！");
        }
    }


}
