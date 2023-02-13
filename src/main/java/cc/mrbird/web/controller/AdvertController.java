package cc.mrbird.web.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.Advert;
import cc.mrbird.web.service.AdvertService;
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
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class AdvertController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdvertService advertService;

    private static final String ON = "on";
    @Log("生成广告视图")
    @RequestMapping("advert")
    @RequiresPermissions("advert:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("advert", user);
        return "web/advert/advert";
    }

    @Log("获取广告信息")
    @RequestMapping("advert/list")
    @RequiresPermissions("advert:list")
    @ResponseBody
    public Map<String, Object> advertList(QueryRequest request, Advert advert) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Advert> list = this.advertService.findAdvertByPage(advert, request);
        for(Advert advert1:list){
            String url = advert1.getPhoto().replaceAll("\\\\", "//");
            String photo = "<img src=toshowPic?photo="+url+" style=cursor:pointer;width:140px;height:140px;> </img>";
            advert1.setPhoto(photo);
        }
        PageInfo<Advert> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("获取要修改的广告信息")
    @RequestMapping("advert/getAdvert")
    @ResponseBody
    public ResponseBo getDict(Integer id) {
        try {
            Advert advert = this.advertService.findById(id);
            return ResponseBo.ok(advert);
        } catch (Exception e) {
            log.error("获取广告信息失败", e);
            return ResponseBo.error("获取广告信息失败，请联系网站管理员！");
        }
    }
    @Log("新增广告 ")
    @RequiresPermissions("advert:add")
    @RequestMapping("advert/add")
    @ResponseBody
    public ResponseBo addadvert(Advert advert) {
        try {
        String photoUrl = advert.getPhoto();
        //对字节数组字符串进行Base64解码并生成图片
        if (photoUrl == null || photoUrl == "") { //图像数据为空
            return ResponseBo.error("新增广告失败，图片不能为空！");
        }
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        byte[] b = decoder.decodeBuffer(photoUrl.replace("data:image/jpeg;base64,", ""));
        //idPhotoUrl = idPhotoUrl.replace("data:image/jpeg;base64,", "");
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
          }
            //生成jpg图片
            Properties prop = new Properties();
            InputStream ins = this.getClass().getResourceAsStream("/config/photoUrl.properties");
            prop.load(ins);
            String imgFilePath = prop.getProperty("PHOTOURL") + UUID.randomUUID() + ".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            advert.setPhoto(imgFilePath);
            advert.setAddDate(new Date());
            this.advertService.addAdvert(advert);
            return ResponseBo.ok("新增广告成功！");
        } catch (Exception e) {
            log.error("新增广告失败", e);
            return ResponseBo.error("新增广告失败，请联系网站管理员！");
        }
    }

    @Log("删除广告")
    @RequiresPermissions("advert:delete")
    @RequestMapping("advert/delete")
    @ResponseBody
    public ResponseBo deleteadverts(String ids) {
        try {
            this.advertService.deleteAdverts(ids);
            return ResponseBo.ok("删除广告成功！");
        } catch (Exception e) {
            log.error("删除广告失败", e);
            return ResponseBo.error("删除广告失败，请联系网站管理员！");
        }
    }

    @Log("修改广告 ")
    @RequiresPermissions("advert:update")
    @RequestMapping("advert/update")
    @ResponseBody
    public ResponseBo updateadvert(Advert advert) {
        try {
            Advert byId = advertService.findById(advert.getId());
           if(!byId.getPhoto().equals(advert.getPhoto())) {
               String photoUrl = advert.getPhoto();
               BASE64Decoder decoder = new BASE64Decoder();
               //Base64解码
               byte[] b = decoder.decodeBuffer(photoUrl.replace("data:image/jpeg;base64,", ""));
               //idPhotoUrl = idPhotoUrl.replace("data:image/jpeg;base64,", "");
               for (int i = 0; i < b.length; ++i) {
                   if (b[i] < 0) {//调整异常数据
                       b[i] += 256;
                   }
               }
               //生成jpg图片
               Properties prop = new Properties();
               InputStream ins = this.getClass().getResourceAsStream("/config/photoUrl.properties");
               prop.load(ins);
               String imgFilePath = prop.getProperty("PHOTOURL") + UUID.randomUUID() + ".jpg";//新生成的图片
               OutputStream out = new FileOutputStream(imgFilePath);
               out.write(b);
               out.flush();
               out.close();
               advert.setPhoto(imgFilePath);
           }
            advert.setEditDate(new Date());
            this.advertService.updateAdvert(advert);
            return ResponseBo.ok("修改广告成功！");
        } catch (Exception e) {
            log.error("修改广告失败", e);
            return ResponseBo.error("修改广告失败，请联系网站管理员！");
        }
    }
    @Log("展示广告图片 ")
    @RequestMapping("toshowPic")
    @ResponseBody
    public void toshowPic(HttpServletResponse response, String photo) {
        File imgFile = new File(photo);
        FileInputStream fin = null;
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            fin = new FileInputStream(imgFile);
            byte[] arr = new byte[1024 * 10];
            int n;
            while ((n = fin.read(arr)) != -1) {
                output.write(arr, 0, n);
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
