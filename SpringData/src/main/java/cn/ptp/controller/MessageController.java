package cn.ptp.controller;

import cn.ptp.entity.Message;
import cn.ptp.service.MessageService;
import com.alibaba.fastjson.JSONArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController
{
    private final MessageService service;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("items", service.findAll());	//addAttribute不允许空值
        return "message/index";
    }

    /**
     * page	Page you want to retrieve.
     * size	Size of the page you want to retrieve.
     * sort	Properties that should be sorted by in the format property,property(,ASC|DESC). Default sort direction is ascending. Use multiple sort parameters if you want to switch directions, e.g. ?sort=firstname&sort=lastname,asc.
     * @param model
     * @return
     */
    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public String paged(
            @RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="size", defaultValue="20") int size,
            //@RequestParam(value="sort", defaultValue="id,ASC") String sort,
            //Pageable pageable,
            HttpServletRequest request,
            Model model
    ){
        if(page<1) page=1;
        page = page-1;
        Pageable pageable = new PageRequest(page, size);

        Page<Message> paged = service.paged(pageable);
        long total = paged.getTotalElements();
        int total_page = paged.getTotalPages();

        Iterator<Message> items = paged.iterator();
        //while (items.hasNext()) System.out.println(items.next().getName());
        //System.out.println(page.hasNext());

        model.addAttribute("items", items);

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

        return "message/paged";
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

    @RequestMapping(value = "/json", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String json(){
        Iterable<Message> items = service.findAll();
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> info = new HashMap<>();
        String json = "";
        JSONArray jsonArray = new JSONArray();
        int i = 0;
        for (Message item: items) {
            info.put("id", item.getId());
            info.put("name", item.getName());
            info.put("msg", item.getMsg());
            info.put("create_at", item.getCreate_at());
            jsonArray.add(JSON.toJSON(info));
            i++;
        }
        result.put("data",jsonArray);
        result.put("count", i);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="msg", defaultValue="") String msg,
            Message message
    ){
        message.setName(name);
        message.setMsg(msg);
        service.save(message);
        return "redirect:/message/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, Message message)
    {
        boolean status = service.delete(id);
        return "redirect:/message/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model, Message message)
    {
        model.addAttribute("message", service.findOne(id));
        return "message/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
            @RequestParam(value="id", required=true) long id,
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="msg", defaultValue="") String msg,
            Message message
    )
    {
        service.save(message);
        return "redirect:/message/";
    }

    @ResponseBody
    @RequestMapping(value = "/sql", method = RequestMethod.GET)
    public String sql(@RequestParam(value="name", required=true) String name, Message message)
    {
        message = service.sql(message);
        return message.getMsg();
    }

}
