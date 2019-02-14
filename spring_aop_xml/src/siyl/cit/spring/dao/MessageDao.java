package siyl.cit.spring.dao;

import org.springframework.stereotype.Repository;

@Repository("messageDao")
public class MessageDao implements IMessageDao {

	@Override
	public void add() {
		System.out.println("msg add");
	}

	@Override
	public void delete() {
		System.out.println("msg delete");
	}

	@Override
	public void load() {
		System.out.println("msg load");
	}

}
