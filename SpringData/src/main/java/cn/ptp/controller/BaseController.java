package cn.ptp.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController
{
    @Value("${asset.public}")
    private String _public_;

    @Value("${asset.private}")
    private String _asset_;

    @Value("${host.url}")
    protected String host_url;


    /*
    @Value("${com.onionh.value}")
    private String value;
    @Value("${com.onionh.rang}")
    private int rang;
    */

    private int client_app_id = 0;
    private String wx_userid = null;
    public String getWxUserid(){
        if(client_app_id < 1) return null;
        if(null==wx_userid || wx_userid.equals("") || wx_userid.length()<3) wx_userid = null;
        return wx_userid;
    }

    private String wx_name = null;
    public String getWxName(){
        return wx_name;
    }

    private String device;
    public String getDevice(Device device){
        if (device.isMobile()) return "mo/";
        if (device.isTablet()) return "mo/";
        return "pc/";
    }

    public void asset(Model model)
    {
        model.addAttribute("__PUBLIC__", _public_);
        model.addAttribute("__ASSET__", _asset_);
    }
    public void asset(ModelAndView mv)
    {
        mv.addObject("__PUBLIC__", _public_);
        mv.addObject("__ASSET__", _asset_);
    }

    public void auth(Model model, HttpSession httpSession)
    {
        if(null!=httpSession.getAttribute("client_app_id")) client_app_id = (int)httpSession.getAttribute("client_app_id");
        wx_userid = (String) httpSession.getAttribute("wx_userid");
        wx_name = (String) httpSession.getAttribute("wx_name");
        model.addAttribute("wx_userid", wx_userid);
        model.addAttribute("wx_name", wx_name);

        model.addAttribute("weixinid", (String) httpSession.getAttribute("weixinid"));
        model.addAttribute("department", (JSONArray) httpSession.getAttribute("department"));
        model.addAttribute("position", (String) httpSession.getAttribute("position"));
        model.addAttribute("mobile", (String) httpSession.getAttribute("mobile"));
        model.addAttribute("gender", (String) httpSession.getAttribute("gender"));
        model.addAttribute("email", (String) httpSession.getAttribute("email"));
        model.addAttribute("avatar", (String) httpSession.getAttribute("avatar"));
    }
    public void auth(ModelAndView mv, HttpSession httpSession)
    {
        if(null!=httpSession.getAttribute("client_app_id")) client_app_id = (int)httpSession.getAttribute("client_app_id");
        wx_userid = (String) httpSession.getAttribute("wx_userid");
        wx_name = (String) httpSession.getAttribute("wx_name");
        mv.addObject("wx_userid", wx_userid);
        mv.addObject("wx_name", wx_name);

        mv.addObject("weixinid", (String) httpSession.getAttribute("weixinid"));
        mv.addObject("department", (JSONArray) httpSession.getAttribute("department"));
        mv.addObject("position", (String) httpSession.getAttribute("position"));
        mv.addObject("mobile", (String) httpSession.getAttribute("mobile"));
        mv.addObject("gender", (String) httpSession.getAttribute("gender"));
        mv.addObject("email", (String) httpSession.getAttribute("email"));
        mv.addObject("avatar", (String) httpSession.getAttribute("avatar"));
    }


    public void paging(long total, int total_page, int page, int size, HttpServletRequest request, Model model)
    {
        model.addAttribute("total", total);
        model.addAttribute("total_page", total_page);
        model.addAttribute("page", page+1);
        model.addAttribute("size", size);
        String currentUrl = request.getRequestURL().toString() + "?";
        if(null != request.getQueryString()){
            String[] params = {"page","size"};
            String remvoed = removeParams(request.getQueryString(),params);
            if(!remvoed.equals("")) currentUrl += remvoed+"&";
        }
        model.addAttribute("currentUrl", currentUrl);
    }

    private String removeParams(String url, String[] params) {
        String reg = null;
        for (int i = 0; i < params.length; i++) {
            reg = "(?<=[\\?&])" + params[i] + "=[^&]*&?";       //(?<=something1)something2  ,表示匹配something2 ,但这个something2 有个前提就是它的前面（不是前部）有个something1 比如 abcbc 用(?<=a)bc 只能匹配第一个 bc 而不能匹配第二个bc
            url = url.replaceAll(reg, "");
        }
        reg = "page=[^&]*&?";
        url = url.replaceAll(reg, "");
        url = url.replaceAll("&+$", "");
        return url;
    }

}
