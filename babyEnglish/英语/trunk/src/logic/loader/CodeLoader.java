package logic.loader;

import java.util.ArrayList;
import java.util.Iterator;

import core.DBLoaderEx;
import core.DBMgr;
import logic.MyUser;
import logic.userdata.bs_code;
import manager.LoaderManager;

public class CodeLoader extends DBLoaderEx<bs_code> {

	private static String createsql = "insert into bs_code(GroupID,Type, Price, Code)values(%d, %d, %d, %s)";
	//private static ArrayList<bs_code> m_codes = new ArrayList<bs_code>();
	private static ArrayList<String> codes = new ArrayList<String>();
	public CodeLoader(bs_code p_Seed) {
		super(p_Seed);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void OnLoad() throws Exception {
		// TODO Auto-generated method stub
		bs_code[] cs = DBMgr.ReadSQL(new bs_code(),"select * from bs_code");
		for(int i = 0; i < cs.length; ++ i){
			this.m_Datas.add(cs[i]);
			codes.add(cs[i].Code.Get());
		}
		
	}

	
	public String generalCode(int groupId,int type, int price, int count){
		int index = 0;
//		while(index < count){
//			String ret = RandomId.randomId(groupId);
//			String sql = String.format(createsql, groupId,type, price, ret);
//			DBMgr.ExecuteSQL(sql);
//			index ++;
//		}
		int r = (int) (Math.random() * 900000) + 100000;
		while(index++ < count){
			String c = String.valueOf(groupId) + String.valueOf(r);
			while(hasRoomId(c)){
				r = (int) (Math.random() * 900000) + 100000;
				c = String.valueOf(groupId) + String.valueOf(r);
			}
			String sql = String.format(createsql, groupId,type, price, c);
			DBMgr.ExecuteSQL(sql);
			codes.add(c);
		}
		
		bs_code[] list = DBMgr.ReadSQL(new bs_code(), "select * from bs_code order by GID desc limit " + count);
		String str = "";
		for(int i = 0; i < list.length; ++ i){
			this.m_Datas.add(list[i]);
			str += list[i].Code.Get() + "|";
		}
		return str.substring(0, str.length() - 1);
	}
	
	private boolean hasRoomId(String id){
		for(int i = 0; i < codes.size(); ++i){
			if(codes.get(i).compareTo(id) == 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean useCode(MyUser user, int groupId, String code){
		Iterator<bs_code> it = this.m_Datas.iterator();
		RecordLoader loader = (RecordLoader) LoaderManager.getInstance().getLoader(LoaderManager.Record);
		
		while(it.hasNext()){
			bs_code c = it.next();
			if(c.Code.Get().compareTo(code) == 0 && c.Used.Get() == 0){
				if(c.GroupID.Get() != 0 && c.GroupID.Get() != groupId){
					return false;
				}
				c.Used.Set(1);
				loader.useRecord(user, c);
				user.getCenterData().changeCoin(c.Price.Get());
				user.getCenterData().changeCardUsedCount(c.Type.Get(), 1);
				return true;
			}
		}
		return false;
		
	}

	
	
	

}
