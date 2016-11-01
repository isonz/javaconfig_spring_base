package cn.ptp.controller;

import cn.ptp.Page;
import cn.ptp.entity.User;
import cn.ptp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController
{
    private final UserService service;

    @RequestMapping("/")
    public String index(Model model)
    {
        model.addAttribute("items", service.findAllOrderByIdDesc());
        model.addAttribute("depts", service.findAllDept());
        model.addAttribute("roles", service.findAllRole());

        return "user/index";
    }

    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public String paged(@RequestParam(value="pageNum", defaultValue="1") int pageNum, @RequestParam(value="pageSize", defaultValue="20") int count, Model model)
    {
        if(pageNum < 1) pageNum = 1;
        if(count < 1) count = 1;
        int start = (pageNum - 1) * count;
        Page<User> page = service.paging(start, count);
        List<User> items = page.getList();
        int total = (int)page.getTotal();
        int pages = (int)page.getPages();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("allpage", pages);

        model.addAttribute("depts", service.findAllDept());
        model.addAttribute("roles", service.findAllRole());

        return "user/paged";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value="roles_id", required=true) String roles_id, User user)
    {
        service.save(user, roles_id);
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
        model.addAttribute("depts", service.findAllDept());
        model.addAttribute("roles", service.findAllRole());
        return "user/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(value="roles_id", required=true) String roles_id, User user)
    {
        service.save(user, roles_id);
        return "redirect:/user/";
    }

}
