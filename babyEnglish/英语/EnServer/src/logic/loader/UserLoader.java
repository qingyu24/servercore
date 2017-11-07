package logic.loader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.LogRecord;

import utility.Debug;
import logic.LogRecords;
import logic.PackBuffer;
import logic.Reg;
import logic.userdata.bs_globalmsg;
import logic.userdata.bs_user;
import logic.userdata.logindata;
import manager.LoaderManager;
import core.DBLoaderEx;
import core.DBMgr;
import core.User;
import core.detail.impl.OnlineUserSelector;
import core.detail.impl.socket.SendMsgBuffer;
import core.remote.PS;

public class UserLoader extends DBLoaderEx<bs_user> {

	private static ArrayList<String> m_codes = new ArrayList<String>();
	private static String sql_add = "insert into bs_globalmsg(Type, Title, Content, DateTime)values(%d, %s, %s, %d)";
	
	public UserLoader(bs_user p_Seed) {
		super(p_Seed);
	}
	
	public void packData(SendMsgBuffer buffer){
		Iterator<bs_user> it = this.m_Datas.iterator();
		buffer.Add((short)this.m_Datas.size());
		while(it.hasNext()){
			bs_user g = it.next();
			g.packData(buffer,true);
		}
	}
	
	public bs_user getUser(long uid){
		Iterator<bs_user> it = this.m_Datas.iterator();
		while(it.hasNext()){
			bs_user user = it.next();
			if(user.RoleID.Get() == uid){
				return user;
			}
		}
		return null;
	}
	
	public void addUser(bs_user user){
		this.m_Datas.add(user);
	}
	
	public void packUserList(SendMsgBuffer buffer, int page, int groupId, String area, String Province, String city, String tel){
		ArrayList<bs_user> list = new ArrayList<bs_user>();
		Iterator<bs_user> it = this.m_Datas.iterator();
		while(it.hasNext()){
			bs_user user = it.next();
			System.out.print(tel == "");
			if(user.GroupID.Get() == groupId 
					&& (area.compareTo(user.Area.Get()) == 0 || area.compareTo("") == 0)
					&& (Province.compareTo(user.Province.Get()) == 0 || Province.compareTo("") == 0)
					&& (city.compareTo(user.City.Get()) == 0 || city.compareTo("") == 0)
					&& (tel.compareTo(user.Tel.Get()) == 0 || tel.compareTo("") == 0)){
				list.add(user);
			}
		}
		
		float pageCount = 4f;
		int count = 4;
		int maxPage = (int)(list.size() / pageCount);
		float temp = (float)list.size() / pageCount;
	
		if(temp > maxPage){
			maxPage += 1;
		}
		if(page >= maxPage - 1){
			count = (int) (list.size() % pageCount);
			page = maxPage - 1;
		}
		count = Math.min(count, list.size());
		//buffer.Add((short)count);
		
		buffer.Add((short)count);
		//if(page < maxPage)
		{
			for(int i =  page * 10 ; i < page * 10 + count; ++ i){
				list.get(i).packData(buffer,false);
			
			}
		}
		buffer.Add(maxPage);
	}
	 public int lostUser(int GroupId ,long beginTime,long lostTime) {
		// TODO Auto-generated method stub
		 int i=0;
		 Iterator<bs_user> it = this.m_Datas.iterator();
		 while (it.hasNext()){
			 bs_user next = it.next();
			 long millis = next.LoginTime.GetMillis()+Long.parseLong("2678400000");
			 if(millis>beginTime&&millis<lostTime){
				 i++;
			 }
		 }
      return i;
	}

	 public int sumNumber(int gid) {
		 int count=0;
		 Iterator<bs_user> it = this.m_Datas.iterator();
		 while (it.hasNext()) {
			bs_user next = it.next();
			if(next.GroupID.Get()==gid){
				count++;
			}
		}
		 return count;	
	}
	 
	 
		private static final String m_user_query = "select * from bs_user where Tel = '%s'";
	 
	 public boolean hasUser(String name ,String passwrd) {
		// TODO Auto-generated method stub
		 
		 LogRecords.Log(null,"即将验证账户信息"+"帐户名"+name+"密码"+passwrd);
			Iterator<bs_user> it = this.m_Datas.iterator();
			while(it.hasNext()){
				bs_user user = it.next();
				if(user.UserName.Get().equals(name)&&user.Password.Get().equals(passwrd)){
					LogRecords.Log(null,"密码，即将被顶掉");
					return true;
				
				}
			}
			
			
			bs_user[] ds = DBMgr.ReadSQL(new bs_user(), String.format(m_user_query, name));
			
			 LogRecords.Log(null,"即将验证账户信息"+"电话名"+name+"密码"+passwrd);
				Iterator<bs_user> its = this.m_Datas.iterator();
				while(its.hasNext()){
					bs_user user = its.next();
					if(user.Tel.Get().equals(name)&&user.Password.Get().equals(passwrd)){
						LogRecords.Log(null,"验证密码正确，即将被顶掉");
						return true;
					
					}
				}
				
			
			
			return false;

	}

	 //
	public bs_user getUserByTel(String tel) {
		// TODO Auto-generated method stub
		Iterator<bs_user> it = this.m_Datas.iterator();
		while(it.hasNext()){
			bs_user user = it.next();
			if(user.Tel.Get() .equals(tel) ){
				return user;
			}
		}
		return null;
	
	}
	 
	 
}
