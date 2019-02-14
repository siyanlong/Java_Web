package siyl.cit.spring.dao;

import siyl.cit.spring.model.LogInfo;

public interface IMessageDao {

	@LogInfo("添加了留言信息！")
	public void add();

	public void delete();

	public void load();
}
