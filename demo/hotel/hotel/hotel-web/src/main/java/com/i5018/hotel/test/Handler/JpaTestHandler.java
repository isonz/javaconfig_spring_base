package com.i5018.hotel.test.Handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i5018.hotel.test.bean.Employee;
import com.i5018.hotel.test.service.DepartmentService;
import com.i5018.hotel.test.service.JpaTestService;

@Controller
public class JpaTestHandler {

	@Autowired
	private JpaTestService jpaTestService;

	@Autowired
	private DepartmentService departmentService;
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		if(id!=null){
			Employee e = jpaTestService.get(id);
			e.setDepartment(null);
			map.put("employee", e);
		}
	}
	
	@RequestMapping("/list")
	public String list(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
			Map<String, Object> map) {

		int pageNo = 1;

		try {
			pageNo = Integer.parseInt(pageNoStr);
			if (pageNo < 1) {
				pageNo = 1;
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Page<Employee> page = jpaTestService.getPage(pageNo, 5);
		map.put("page", page);
		
		return "list";

	}

	@RequestMapping(value="input",method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("departments", departmentService.getAll());
		map.put("employee", new Employee());
		return "input";
	}
	
	@ResponseBody
	@RequestMapping(value="ajax",method=RequestMethod.POST)
	public String ajax(@RequestParam(value="lastName",required=true) String lastName){
		Employee e = jpaTestService.getByLastName(lastName);
		if(e==null){
			return "0";
		}else{
			return "1";
		}
	}
	
	@RequestMapping(value="input",method=RequestMethod.POST)
	public String save(Employee e){
		jpaTestService.save(e);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/input/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String,Object> map){
		Employee e = jpaTestService.get(id);
		map.put("employee", e);
		map.put("departments", departmentService.getAll());
		return "input";
	}
	
	@RequestMapping(value="/input/{id}",method=RequestMethod.PUT)
	public String update(Employee e){
		jpaTestService.save(e);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/input/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		jpaTestService.delete(id);
		return "redirect:/list";
	} 
}
