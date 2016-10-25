package cn.ptp.controller;

import cn.ptp.entity.User;
import cn.ptp.service.UserService;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController
{
    private final UserService service;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("items", service.findAll());	//addAttribute不允许空值
        return "user/index";
    }

    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public String paged(@RequestParam(value="pageNum", defaultValue="1") int pageNum, @RequestParam(value="pageSize", defaultValue="20") int count, Model model)
    {

        int start = (pageNum - 1) * count;
        Page<User> page = service.paged(new PageRequest(start, count));
        long total = page.getTotalElements();
        int allpage = page.getTotalPages();
        Iterator<User> items = page.iterator();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("allpage", allpage);

        return "user/paged";
    }

    @RequestMapping(value = "/json", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String json()
    {
        Iterable<User> items = service.findAll();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> info = new HashMap<>();
        String json = "";
        int i = 0;
        for (User item: items) {
            info.put("id", item.getId());
            info.put("openid", item.getOpenid());
            info.put("username", item.getUsername());
            info.put("userid", item.getUserid());
            info.put("roles", item.getRole());
            info.put("dept", item.getDepartment());
            if(0==i){
                json = JSON.toJSONString(info);
            }else{
                json += ","+JSON.toJSONString(info);
            }
            i++;
        }
        result.put("data", "[" +json+ "]");
        result.put("count", i);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(
            @RequestParam(value="openid", required=true) String openid,
            @RequestParam(value="username", defaultValue="") String username,
            @RequestParam(value="userid", defaultValue="") String userid,
            User user
    ){
        user.setOpenid(openid);
        user.setUserid(userid);
        user.setUsername(username);
        service.save(user);
        return "redirect:/user/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, User user)
    {
        boolean status = service.delete(id);
        return "redirect:/user/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model, User user)
    {
        model.addAttribute("user", service.findOne(id));
        return "user/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam(value="id", required=true) long id,
            @RequestParam(value="openid", required=true) String openid,
            @RequestParam(value="username", defaultValue="") String username,
            @RequestParam(value="userid", defaultValue="") String userid,
            User user
    )
    {
        service.save(user);
        return "redirect:/user/";
    }

    @ResponseBody
    @RequestMapping(value = "/sql", method = RequestMethod.GET)
    public String sql(@RequestParam(value="name", required=true) String name, User user)
    {
        user = service.sql(user);
        return user.getUsername();
    }

}
