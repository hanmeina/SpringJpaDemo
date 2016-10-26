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
	 * ����û���һ��
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addUserPage(){
		ModelAndView modelAndView = new ModelAndView("add-user-form");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	/**
	 * ����û��ڶ���
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingUser(@ModelAttribute User user){
		ModelAndView  modelAndView = new ModelAndView("home");
		userService.addUser(user);
		String message = "�û���ӳɹ�������";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	/**
	 * �鿴�����û�
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
	 * ɾ���û�
	 * @param id
	 * @return
	 * @throws UserNotFound
	 */
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable Integer id) throws UserNotFound{
		ModelAndView  modelAndView = new ModelAndView("home");
		userService.deleteUser(id);;
		String message = "�û�ɾ���ɹ�������";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	/**
	 * �༭�޸ĵ�һ��
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
	 * �༭�޸ĵڶ���
	 * @param User
	 * @return
	 * @throws UserNotFound
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user,@PathVariable Integer id) throws UserNotFound{
		ModelAndView  modelAndView = new ModelAndView("home");		
        userService.updateUser(user);		
		String message = "�û��޸ĳɹ�";
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
