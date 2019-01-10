package siyl.cit.msg.dao;

import siyl.cit.msg.model.Message;
import siyl.cit.msg.model.Pager;

public interface IMessageDao {
	public void add(Message msg,int userId);
	public void update(Message msg);
	public void delete(int id);
	public Message load(int id);
	public Pager<Message> list();
	public int getCommentCount(int msgId);
}
