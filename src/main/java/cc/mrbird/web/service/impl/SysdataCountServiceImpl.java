package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.SysdataCountMapper;
import cc.mrbird.web.domain.CountTypeEnum;
import cc.mrbird.web.domain.SysdataCount;
import cc.mrbird.web.service.SysdataCountService;
import cc.mrbird.web.service.WebUserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("sysdataCountService")
public class SysdataCountServiceImpl extends BaseService<SysdataCount> implements SysdataCountService {
    @Autowired
    private SysdataCountMapper sysdataCountMapper;
    @Autowired
    private WebUserService   webUserService;



    @Override
    public String dayCount() {
        //数据只展示近一个月内的每日数据
        //每日新增用户
       List<Integer> userAddList= sysdataCountMapper.getDayCountByType(CountTypeEnum.USERADD_NUM.getCode());
        //每日发帖数量
        List<Integer> postAddList= sysdataCountMapper.getDayCountByType(CountTypeEnum.POSTADD_NUM.getCode());
        //每日登录人数
        List<Integer> loginList= sysdataCountMapper.getDayCountByType(CountTypeEnum.LOGINUSER_NUM.getCode());
        Map<String,Object> countMap=new HashMap<String,Object>();
        countMap.put(CountTypeEnum.USERADD_NUM.getCode(),userAddList);
        countMap.put(CountTypeEnum.POSTADD_NUM.getCode(),postAddList);
        countMap.put(CountTypeEnum.LOGINUSER_NUM.getCode(),loginList);
        //X轴坐标时间取一个月
        List dayList=getDayList();
        countMap.put("xAxis",dayList);
        System.out.print(dayList);
        String str= JSONObject.toJSONString(countMap);
        return str;
    }



    private List<String> getDayList() {
        Calendar begin = Calendar.getInstance();// 得到一个Calendar的实例
        begin.setTime(new Date()); // 设置时间为当前时间
        begin.add(Calendar.MONTH, -1);// 月份减1
        begin.add(Calendar.DATE, +1);// 日期加1
        Date result = begin.getTime();
        Calendar end = Calendar.getInstance();
        Long startTime = begin.getTimeInMillis();
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;// 一天的时间转化为ms
        List dates = new ArrayList<>();
        Long time = startTime;
        int i = 0;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dates.add(i, df.format(d));
            i++;
            time += oneDay;
        }
        return dates;
    }


}
