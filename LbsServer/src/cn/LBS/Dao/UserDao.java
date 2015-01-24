package cn.LBS.Dao;

import cn.LBS.Model.User;

public interface UserDao {

	User find(User user);//查找用户

	void add(User user);//增加用户

	void update(User user);

}