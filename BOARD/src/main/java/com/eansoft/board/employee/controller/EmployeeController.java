package com.eansoft.board.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView employeeRegister(ModelAndView mv
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
	
	@RequestMapping(value="/employee/login.eansoft", method=RequestMethod.POST)
	public ModelAndView employeeLogin(ModelAndView mv
			, @RequestParam("emplId") String emplId
			, @RequestParam("emplPw") String emplPw
			, HttpServletRequest request) {
		try {
			Employee employee = new Employee();
			employee.setEmplId(emplId);
			employee.setEmplPw(emplPw);
			Employee empLogin = eService.loginEmployee(employee);
			if(empLogin != null) {
				HttpSession session = request.getSession();
				session.setAttribute("emplId", empLogin.getEmplId());
				session.setAttribute("emplPw", empLogin.getEmplPw());
				session.setAttribute("emplName", empLogin.getEmplName());
				mv.setViewName("home");
			}else {
				mv.addObject("msg", "<strong>로그인에 실패했습니다.</strong> 입력하신 정보가 정확한지 다시 한 번 확인해주세요!");
				mv.setViewName("common/errorPage");
			}
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
