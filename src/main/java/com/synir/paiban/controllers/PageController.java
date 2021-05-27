package com.synir.paiban.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.synir.paiban.models.Schedule;
import com.synir.paiban.models.ScheduleService;
import com.synir.paiban.models.UserService;
import com.synir.paiban.models.Users;
import com.synir.paiban.models.Utility;


@RestController
public class PageController {
	@Autowired
	private UserService userservice;
	
	@Autowired
	private ScheduleService scheduleservice;
	
	@RequestMapping(value = "/home")
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView("home");
		return modelAndView;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("user",new Users());
		return modelAndView;
	}
	
	@RequestMapping(value = "/dologin")
	public ModelAndView dologin(HttpServletRequest request,HttpSession session){
		String name = request.getParameter("username");
	    String password = request.getParameter("password");
		Users user = userservice.findByUsernameAndPassword(name, password);
		
		if(user == null) {
			session.invalidate();
			ModelAndView modelAndView = new ModelAndView("redirect:/login");
			modelAndView.addObject("user",new Users());
			return modelAndView;
		}
		else {
			session.setAttribute("userName",name);
			ModelAndView modelAndView = new ModelAndView("redirect:/mainpage");
			return modelAndView;
		} 
	}
	
	@RequestMapping(value = "/mainpage")
	public ModelAndView mainpage() {
		ModelAndView modelAndView = new ModelAndView("mainpage");
		return modelAndView;
	}
	
	@RequestMapping(value = "/createschedule")
	public ModelAndView createschedule(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("createschedule");
		String name = request.getParameter("name");
		String year = request.getParameter("year");
	    String month = request.getParameter("month");
	    modelAndView.addObject("name", name);
	    modelAndView.addObject("year", year);
	    modelAndView.addObject("month", month);
		return modelAndView;
	}

	@RequestMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request){
		Object username = request.getSession().getAttribute("userName");
    	Users user = userservice.findByUsername((String)username);
    	if(user.getAdminlevel()!= 0 && user.getAdminlevel()!= 1) {
    		ModelAndView modelAndView = new ModelAndView("mainpage");
    		return modelAndView;
    	}
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("users",new Users());
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveschedule")
	public String saveschedule(HttpServletRequest request) throws IOException {
		
		String array = request.getParameter("array");
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		

		JSONArray scheduleList = new JSONArray(array);
		
    	Object username = request.getSession().getAttribute("userName");
    	Users user = userservice.findByUsername((String)username);

		Schedule schedule = new Schedule();
		Utility utility = new Utility();
		String timestamp = utility.getTimestamp();
		String uuid = utility.gen_uuid(user.getUsername(), timestamp);
		schedule.setattr(title,year,month,user.getUsername(),user.getName(),timestamp,uuid,user.getAdminlevel());
		scheduleservice.add(schedule);
		utility.savetoData(scheduleList, uuid);
		//return System.getProperty("user.dir");
		return user.getName()+" "+year+" "+month;
	}
	
	@RequestMapping(value = "/checkschedule")
	public ModelAndView checkschedule() {
		ModelAndView modelAndView = new ModelAndView("redirect:/showschedule");
		return modelAndView;
	}
	@RequestMapping(value = "/showschedule")
	public ModelAndView showschedule(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("showschedule");
		Object username = request.getSession().getAttribute("userName");
    	Users user = userservice.findByUsername((String)username);
		List<Schedule> list = scheduleservice.findByCreatorusernameOrAdminlevelGreaterThan(user.getUsername(), user.getAdminlevel());
		modelAndView.addObject("list",list);
		return modelAndView;
	}
	@RequestMapping(value = "/checkedit")
	public ModelAndView checkedit() {
		ModelAndView modelAndView = new ModelAndView("redirect:/showedit");
		
		return modelAndView;
	}
	@RequestMapping(value = "/showedit")
	public ModelAndView showedit(HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView("showedit");
		Utility utility = new Utility();
		String title = request.getParameter("title");
		String uuid = request.getParameter("uuid");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String dataarray[] = utility.loadData(uuid);
		modelAndView.addObject("title", title);
		modelAndView.addObject("uuid", uuid);
		modelAndView.addObject("year", year);
		modelAndView.addObject("month", month);
		modelAndView.addObject("dataarray", dataarray);
		return modelAndView;
	}
	
	@RequestMapping(value = "/save")
	public ModelAndView save(@Valid Users users,BindingResult result,Model model) {
		if(result != null && result.hasErrors()) {
			ModelAndView modelandview = new ModelAndView("register");
			modelandview.addObject(model);
			return modelandview;
		}else {
			userservice.add(users);
			List<Users> list = userservice.getList();
			ModelAndView modelandview = new ModelAndView("save");
			modelandview.addObject("list",list);
			return modelandview;
		}
	}
	
}
