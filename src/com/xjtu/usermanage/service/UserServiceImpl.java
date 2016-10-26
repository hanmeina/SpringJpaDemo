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
	 * ����û�
	 * @param User
	 * 
	 */
	@Override
	public void addUser(User user) {
		userReposity.save(user);	
	}
	/**
	 * ��ѯ�����û�
	 * @return List<User>
	 */
	@Override
	public List<User> getUsers() {
		return userReposity.findAll();	 
	}
	/**
	 * ɾ���û�
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
	 * �޸��û�
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
	 * ��ѯ�����û�
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
	 * ����ҳ��������ѯ
	 */
	@Override
	public List<User> getUsersByConditionNoPage(String phone, String address) {
		return userReposity.findTop2ByPhoneStartingWithAndAddressContainingOrderByPhoneDesc(phone, address);
		
	}
	/**
	 * ����ҳ������ѯ(��Ҫ�õ��û��б��ҵõ���ҳ��Ϣ)
	 */
	@Override
	public Page<User> getUsersByConditionWithPage(String phone, String address, Integer page, Integer pageSize) {
		         //������
				Page<User> userPage = userReposity.findByPhoneStartingWithAndAddressContaining(phone,address,new PageRequest(page, pageSize));
	            return userPage;
	
	}

}
