package cn.LBS.Dao;

import cn.LBS.Model.User;

public interface UserDao {

	User find(User user);//�����û�

	void add(User user);//�����û�

	void update(User user);

}