package com.niit.dao;



import java.util.List;

//import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.MessagePrivate;

@Repository
public class MessageDaoImpl implements MessageDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void sendMessage(MessagePrivate message) {
		Session session=sessionFactory.openSession();
		session.save(message);
		session.flush();
		session.close();
		}
	
	
   public List<MessagePrivate> getMessage(String toUser, String fromUser) {
	   Session session=sessionFactory.openSession();
	   SQLQuery query=session.createSQLQuery("select * from MessagePrivate where (toUser=? AND fromUser=?) OR (toUser=? AND fromUser=?)");
	   query.setString(0, toUser);
	   query.setString(1, fromUser);
	   query.setString(2, fromUser);
	   query.setString(3, toUser);
	   query.addEntity(MessagePrivate.class);
	   @SuppressWarnings("unchecked")
	   List<MessagePrivate> messages=query.list();
	   session.close();
	   return messages;
	}
}
