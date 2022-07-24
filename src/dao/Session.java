package dao;

import java.util.HashMap;
import java.util.HashSet;

public class Session {
	private static HashMap<String, Object> loginDatas = new HashMap<String, Object>();
	
	public static Object get(String key) {
		return loginDatas.get(key);
	}
	
	public static void put(String key,Object data) {
		loginDatas.put(key, data);
	}
}
