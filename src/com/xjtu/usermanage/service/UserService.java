package com.xjtu.usermanage.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.xjtu.usermanage.exception.UserNotFound;
import com.xjtu.usermanage.model.User;


public interface UserService {
	//����û�
    public void addUser(User user);
    //��ѯ�����û�
    public List<User> getUsers();
    //ɾ���û�
    public  User  deleteUser(Integer id) throws UserNotFound;
    //�޸��û�
    public User updateUser(User user) throws  UserNotFound;
    //��ѯ�����û�
  	public User getUser(Integer id) throws  UserNotFound;
  	
  	
  //����ҳ��������ѯ
  	public List<User> getUsersByConditionNoPage(String phone,String address);
  	
  	//����ҳ������ѯ(��Ҫ�õ��û��б��ҵõ���ҳ��Ϣ)
  	public Page<User> getUsersByConditionWithPage(String phone,String address,Integer page,Integer pageSize);
   
}
