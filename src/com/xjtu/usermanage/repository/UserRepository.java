package com.xjtu.usermanage.repository;

import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xjtu.usermanage.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
		
	//��ѯ���� �����ݿ��в�ѯ�绰����(phone)��ָ���ַ�����ʼ(���磺136)�ģ����ҵ�ַ(address)�а���ָ���ַ��������磺·���ļ�¼
	//select * from user where phone like '136%' and address like '%·%' order by phone desc limit 0,2
	List<User> findTop2ByPhoneStartingWithAndAddressContainingOrderByPhoneDesc(String phone,String address);
	List<User> findTop2ByPhoneStartingWithAndAddressContaining(String phone,String address,Sort sort);
	
	Page<User> findByPhoneStartingWithAndAddressContaining(String phone,String address,Pageable pageable);
	
 	
}
