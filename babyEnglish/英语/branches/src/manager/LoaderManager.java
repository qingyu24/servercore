package manager;

import java.util.HashMap;
import java.util.Map;

import core.DBLoader;
import logic.loader.CodeLoader;
import logic.loader.GlobalLoader;
import logic.loader.GroupLoader;
import logic.loader.MessageLoader;
import logic.loader.RecordLoader;
import logic.loader.UserLoader;
import logic.userdata.bs_cardrecord;
import logic.userdata.bs_code;
import logic.userdata.bs_global;
import logic.userdata.bs_globalmsg;
import logic.userdata.bs_group;
import logic.userdata.bs_user;
import core.DBLoader;

public class LoaderManager {
	
	private static LoaderManager _instance;
	private static Map<String,DBLoader> m_list = new HashMap<String, DBLoader>();
	public static String Global = "Global";
	public static String Message = "Message";
	public static String Users = "Users";
	public static String Group = "Group";
	public static String Code = "Code";
	public static String Record = "Record";
	
	public static LoaderManager getInstance(){
		if(_instance != null){
			return _instance;
		}
		return _instance = new LoaderManager();
	}
	
	public void loadAll(){
		GlobalLoader loader = new GlobalLoader();
		MessageLoader msg = new MessageLoader(new bs_globalmsg());
		UserLoader users = new UserLoader(new bs_user());
		GroupLoader groups = new GroupLoader(new bs_group());
		CodeLoader code = new CodeLoader(new bs_code());
		RecordLoader record = new RecordLoader(new bs_cardrecord());
		m_list.put(Global, loader);
		m_list.put(Message, msg);
		m_list.put(Users, users);
		m_list.put(Group, groups);
		m_list.put(Code, code);
		m_list.put(Record, record);
	}
	
	public DBLoader getLoader(String name){
		return m_list.get(name);
	}
	
}
