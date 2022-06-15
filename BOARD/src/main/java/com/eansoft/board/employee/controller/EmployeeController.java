package com.eansoft.board.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eansoft.board.employee.domain.Employee;
import com.eansoft.board.employee.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService eService;
	
	@RequestMapping(value="/employee/registerView.eansoft", method=RequestMethod.GET)
	public ModelAndView registerView(ModelAndView mv) {
		mv.setViewName("employee/register");
		return mv;
	}
	
	@RequestMapping(value="/employee/register.eansoft", method=RequestMethod.POST)
	public ModelAndView memberRegister(ModelAndView mv
			, @ModelAttribute Employee employee) {
		try {
			int result = eService.registerEmployee(employee);
			if(result > 0) {
				mv.setViewName("employee/login");
			}else {
				mv.addObject("msg", "회원가입에 실패하였습니다.");
				mv.setViewName("common/errorPage");
			}
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
}
