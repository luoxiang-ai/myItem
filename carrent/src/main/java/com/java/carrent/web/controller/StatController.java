package com.java.carrent.web.controller;

import com.java.carrent.common.vo.CustomerVo;
import com.java.carrent.entity.BaseEntity;
import com.java.carrent.entity.BusCustomer;
import com.java.carrent.entity.BusRent;
import com.java.carrent.service.CustomerService;
import com.java.carrent.service.RentService;
import com.java.carrent.service.StatService;
import com.java.carrent.utils.ExportCustomerUtils;
import com.java.carrent.utils.ExportRentUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计分析控制器
 */
@Controller
@RequestMapping(value = "/stat")
public class StatController {

    @Autowired
    private StatService statService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RentService rentService;

    /**
     * 跳转到客户地址统计页面
     * @return
     */
    @GetMapping(value = "/toCustomerAreaStat.action")
    @RequiresPermissions(value = "stat:statistics")
    public String toCustomerAreaStat() {

        return "stat/customerAreaStat";
    }

    /**
     * 加载客户地区数据
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/loadCustomerAreaStatJson.action")
    public List<BaseEntity> loadCustomerAreaStatJosn() {
        return this.statService.loadCustomerAreaStatList();
    }

    /**
     * 跳转到业务员年度统计页面
     */
    @RequestMapping(value = "toOpernameYearGradeStat.action")
    @RequiresPermissions(value = "stat:statistics")
    public String toOpernameYearGradeStat() {

        return "stat/opernameYearGradeStat";
    }

    /**
     * 加载业务员年度统计数据
     * @param year
     * @return
     */
    @GetMapping(value = "/opernameYearGradeStatJson.action")
    @ResponseBody
    public Map<String, Object> opernameYearGradeStatJson(String year) {

        List<BaseEntity> entityList = this.statService.loadOpernameYearGradeStatList(year);
        Map<String, Object> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for (BaseEntity baseEntity : entityList) {
            names.add(baseEntity.getName());
            values.add(baseEntity.getValue());
        }

        map.put("name", names);
        map.put("value", values);
        return map;
    }

    /**
     * 跳转到公司年度业绩统计页面
     */
    @RequestMapping(value = "/toCompanyYearGradeStat")
    @RequiresPermissions(value = "stat:statistics")
    public String toCompanyYearGradeStat() {

        return "stat/companyYearGradeStat";
    }

    /**
     * 公司年度业绩统计数据
     * @param year
     * @return
     */
    @GetMapping(value = "/companyYearGradeStatJson.action")
    @ResponseBody
    public List<Double> companyYearGradeStatJson(String year) {
        List<Double> list = this.statService.loadCompanyYearGradeStatList(year);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == null) {
                list.set(i, 0.0d);
            }
        }

        return list;
    }

    /**
     * 导出客户数据
     * @param customerVo
     * @param response
     * @return
     */
    @RequestMapping(value = "exportCustomer.action")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo, HttpServletResponse response) {
        List<BusCustomer> customerList = this.customerService.queryAllCustomerForList(customerVo);
        String fileName = "客户数据.xls";
        String sheetName = "客户数据";
        ByteArrayOutputStream bos = ExportCustomerUtils.exportCustomer(customerList, sheetName);
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
//            创建封装响应头信息的对象
            HttpHeaders header = new HttpHeaders();
            // 封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 设置下载的文件的名称
            header.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 导出出租单信息
     * @param rentId
     * @return
     */
    @GetMapping(value = "/exportRent.action")
    public ResponseEntity<Object> exportRent(String rentId) {
        try {
            //        1.根据出租单号查询出租信息
            BusRent rent = this.rentService.queryRentByRentId(rentId);
//           2.根据身份证查询客户信息(出租单号中有客户的身份证号)
            BusCustomer customer = this.customerService.queryCustomerByIdentity(rent.getIdentity());

            String fileName = customer.getCustname() + "-的出租单.xls";
            String sheetName = customer.getCustname() + "出租单";
//          内存流
            ByteArrayOutputStream baos = ExportRentUtils.exportRent(rent, customer, sheetName);
            fileName = URLEncoder.encode(fileName, "UTF-8");
//            创建封装响应头信息的对象
            HttpHeaders header = new HttpHeaders();
            // 封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 设置下载的文件的名称
            header.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<Object>(baos.toByteArray(), header, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
