package logic.loader;

import java.util.ArrayList;
import java.util.Iterator;

import logic.userdata.bs_global;
import core.DBLoader;
import core.DBLoaderEx;
import core.DBMgr;

public class GlobalLoader implements DBLoader {

	private static ArrayList<String> m_codes = new ArrayList<String>();
	private static String sql_query = "select * from bs_global";
	private static String aboutus = "";
	public GlobalLoader() {
		DBMgr.AddDBLoader(this);
	}
	
	public String getContact(){
		return aboutus;
	}
	
	public boolean queryCode(String code){
		Iterator<String> it = m_codes.iterator();
		while(it.hasNext()){
			String c = it.next();
			if(code == c){
				it.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public void OnLoad() throws Exception {
		// TODO Auto-generated method stub
		bs_global[] g = DBMgr.ReadSQL(new bs_global(),sql_query);
		aboutus = g[0].AboutUs.Get();
		String[] codes = g[0].Code.Get().split("[|]");
		for(int i = 0; i < codes.length; ++ i){
			m_codes.add(codes[i]);
		}
		
	}

	@Override
	public void OnSave() {
		// TODO Auto-generated method stub
		//todo;
	}
	
	

}
