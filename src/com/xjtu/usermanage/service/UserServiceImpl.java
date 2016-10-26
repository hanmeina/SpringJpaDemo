package com.xjtu.usermanage.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xjtu.usermanage.exception.UserNotFound;
import com.xjtu.usermanage.model.User;
import com.xjtu.usermanage.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository  userReposity;
	
	/**
	 * 添加用户
	 * @param User
	 * 
	 */
	@Override
	public void addUser(User user) {
		userReposity.save(user);	
	}
	/**
	 * 查询所有用户
	 * @return List<User>
	 */
	@Override
	public List<User> getUsers() {
		return userReposity.findAll();	 
	}
	/**
	 * 删除用户
	 * @param id
	 * @return User
	 */
	@Override
	public  User deleteUser(Integer id) throws UserNotFound {
	User userDelete = userReposity.findOne(id);
	if(userDelete == null){
		throw  new  UserNotFound();
		
	}
	userReposity.delete(userDelete);
	return userDelete;
	
		
	}
	/**
	 * 修改用户
	 * @param User
	 * @return User
	 */
	@Override
	public User updateUser(User user) throws UserNotFound {
		User userUpdate = userReposity.findOne(user.getId());
		if(userUpdate == null){
			throw  new  UserNotFound();			
		}
		if(user.getName() != null){
			userUpdate.setName(user.getName());
		}
		if(user.getPhone() != null){
			userUpdate.setPhone(user.getPhone());
		}
		if(user.getAddress() == null){
			userUpdate.setAddress(user.getAddress());
		}
		userReposity.save(userUpdate);
		return userUpdate;
	    
	}
	/**
	 * 查询单个用户
	 */
	@Override
	public User getUser(Integer id) throws UserNotFound {
		User user= userReposity.findOne(id);
		if(user == null){
			throw  new  UserNotFound();			
		}
		
		return user;
	}
	
	/**
	 * 不分页带条件查询
	 */
	@Override
	public List<User> getUsersByConditionNoPage(String phone, String address) {
		return userReposity.findTop2ByPhoneStartingWithAndAddressContainingOrderByPhoneDesc(phone, address);
		
	}
	/**
	 * 带分页条件查询(需要得到用户列表并且得到分页信息)
	 */
	@Override
	public Page<User> getUsersByConditionWithPage(String phone, String address, Integer page, Integer pageSize) {
		         //不排序
				Page<User> userPage = userReposity.findByPhoneStartingWithAndAddressContaining(phone,address,new PageRequest(page, pageSize));
	            return userPage;
	
	}

}
