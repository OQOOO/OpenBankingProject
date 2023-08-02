package kr.ac.kopo.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	public HandlerMapping(String propLoc) {
		mappings = new HashMap<>();
		Properties prop = new Properties();
		System.out.println("핸들러 매핑 실행 성공, prop : " + prop);
		//"C:\\Users\\User\\eclipse-workspaceJSP\\OpenBanking\\bean.properties"
		try {
			InputStream is = new FileInputStream(propLoc);
			prop.load(is);
			Set<Object> keys = prop.keySet();
			
			for(Object key : keys) {
				String className = prop.getProperty(key.toString());
				//String front = "kr.ac.kopo.";
				//className = front + className;
				System.out.println(className);
				
				//System.out.println(key + ": "+className);
				
				Class<?> clz = Class.forName(className);
				Constructor<?> cons = clz.getConstructor();
				
				mappings.put(key.toString(), (Controller)cons.newInstance());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//mappings.put("/IndexMain.do", new IndexMainController());
	}
	
	/**
	 * path 받아서 mappings에 key값으로 넣어 객체 반환
	 * @param path
	 * @return
	 */
	public Controller getController(String path) {
		return mappings.get(path);
	}
	
}
