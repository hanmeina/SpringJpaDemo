package com.xjtu.usermanage.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.xjtu.usermanage.exception.UserNotFound;
import com.xjtu.usermanage.model.User;


public interface UserService {
	//添加用户
    public void addUser(User user);
    //查询所有用户
    public List<User> getUsers();
    //删除用户
    public  User  deleteUser(Integer id) throws UserNotFound;
    //修改用户
    public User updateUser(User user) throws  UserNotFound;
    //查询单个用户
  	public User getUser(Integer id) throws  UserNotFound;
  	
  	
  //不分页带条件查询
  	public List<User> getUsersByConditionNoPage(String phone,String address);
  	
  	//带分页条件查询(需要得到用户列表并且得到分页信息)
  	public Page<User> getUsersByConditionWithPage(String phone,String address,Integer page,Integer pageSize);
   
}
