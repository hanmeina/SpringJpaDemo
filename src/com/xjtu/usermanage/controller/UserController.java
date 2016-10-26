package com.xjtu.usermanage.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xjtu.usermanage.exception.UserNotFound;
import com.xjtu.usermanage.model.User;
import com.xjtu.usermanage.service.UserService;



@Controller
@RequestMapping(value="/user")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	/**
	 * 添加用户第一步
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addUserPage(){
		ModelAndView modelAndView = new ModelAndView("add-user-form");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	/**
	 * 添加用户第二步
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingUser(@ModelAttribute User user){
		ModelAndView  modelAndView = new ModelAndView("home");
		userService.addUser(user);
		String message = "用户添加成功！！！";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	/**
	 * 查看所有用户
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView getUsers(){
		ModelAndView  modelAndView = new ModelAndView("list-of-users");
		List<User> users = userService.getUsers();
		modelAndView.addObject("users", users);
		return modelAndView;
		
	}
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws UserNotFound
	 */
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer id) throws UserNotFound{
		ModelAndView  modelAndView = new ModelAndView("home");
		userService.deleteUser(id);;
		String message = "用户删除成功！！！";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	/**
	 * 编辑修改第一步
	 * @param id
	 * @return
	 * @throws UserNotFound
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable Integer id) throws UserNotFound{
		ModelAndView  modelAndView = new ModelAndView("edit-user-form");		
	    User user = userService.getUser(id);		
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	/**
	 * 编辑修改第二步
	 * @param User
	 * @return
	 * @throws UserNotFound
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user,@PathVariable Integer id) throws UserNotFound{
		ModelAndView  modelAndView = new ModelAndView("home");		
        userService.updateUser(user);		
		String message = "用户修改成功";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
