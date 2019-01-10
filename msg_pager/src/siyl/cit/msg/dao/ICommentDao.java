package siyl.cit.msg.dao;

import siyl.cit.msg.model.Comment;
import siyl.cit.msg.model.Pager;

public interface ICommentDao {
	public void add(Comment comment,int userId,int msgId);
	public void delete(int id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Comment load(int id);
	/**
	 * 根据留言获取该留言的所有评论
	 * @param msgId
	 * @return
	 */
	public Pager<Comment> list(int msgId);
}
