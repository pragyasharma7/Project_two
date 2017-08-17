package com.niit.dao;

import java.util.List;

import com.niit.model.MessagePrivate;

public interface MessageDao {
	void sendMessage(MessagePrivate message);
	public List<MessagePrivate> getMessage(String toUser,String fromUser);

}
