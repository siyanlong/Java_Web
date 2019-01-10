package siyl.cit.shopping.test;

import java.util.Random;

import siyl.cit.shopping.dao.DAOFactory;
import siyl.cit.shopping.dao.IUserDao;
import siyl.cit.shopping.model.User;

public class TestInit {
	static String[] firstName = new String[] { "张", "刘", "牛", "李", "吕", "付", "副", "王", "汪", "赵", "孔", "谭", "贪", "夏侯",
			"令狐", "上官", "欧阳", "司马", "诸葛", "曹", "关", "孙", "甘" };
	static String[] centerName = new String[] { "立", "祝", "共", "都", "高", "路", "恶", "人", "达", "玉", "莫", "靓", "宇" };
	static String[] lastName = new String[] { "强", "颖", "龙", "备", "亮", "鸣", "明", "名", "凤", "云", "冲", "娇", "丽", "美", "波",
			"博", "栋", "野", "磊", "雷" };
	static Random random = new Random();

	public static void main(String[] args) {
		IUserDao userDao = DAOFactory.getUserDao();
		for (int i = 0; i < 500; i++) {
			User user = new User();
			user.setNickname(randomName());
			user.setPassword("123");
			user.setUsername("user" + i);
			user.setType(1);
			userDao.add(user);
		}
	}

	private static String randomName() {
		return firstName[random.nextInt(firstName.length)] + centerName[random.nextInt(centerName.length)]
				+ lastName[random.nextInt(lastName.length)];
	}

}
