package logic.loader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import utility.TimeMethod;

import logic.PackBuffer;
import logic.Reg;
import logic.userdata.bs_cardrecord;
import logic.userdata.bs_code;
import logic.userdata.bs_globalmsg;
import core.DBLoaderEx;
import core.DBMgr;
import core.User;
import core.detail.impl.OnlineUserSelector;
import core.detail.impl.socket.SendMsgBuffer;

public class MessageLoader extends DBLoaderEx<bs_globalmsg> {

	private static ArrayList<String> m_codes = new ArrayList<String>();
	private static String sql_add = "insert into bs_globalmsg(Title, Content, Type, IsTop, TopTime, ShowTime, Area, GroupId, Linkurl, Imgurl)values('%s', '%s', %d, %d, %d, %d, %d, %d, '%s', %d, '%s', '%s')";
	private static String sql_remove = "delete from bs_globalmsg where GID=%d";
	
	private int m_gid;
	public MessageLoader(bs_globalmsg p_Seed) {
		super(p_Seed);
		// TODO Auto-generated constructor stub
		//String.format(sql_add, 1, "测试标题", "测试内容", System.currentTimeMillis());
		//DBMgr.ExecuteSQL(sql_add);
//		bs_globalmsg msg = DBMgr.CreateGlobalRoleDataByGID(new bs_globalmsg());
//		msg.DateTime.Set(System.currentTimeMillis());
//		msg.Title.Set("aaaaa");
//		msg.Content.Set("bbbbbbb");
//		msg.Type.Set(1);
//		DBMgr.UpdateRoleData(msg);
		
	}
	
	@Override
	public void OnLoad() throws Exception {
		// TODO Auto-generated method stub
		bs_globalmsg[] cs = DBMgr.ReadSQL(new bs_globalmsg(),"select * from bs_globalmsg");
		for(int i = 0; i < cs.length; ++ i){
			this.m_Datas.add(cs[i]);
		}
		this.m_gid = cs[cs.length - 1].GID.Get();
		
	}
	
	public void packData(SendMsgBuffer buffer, int page, int type){
		
		Iterator<bs_globalmsg> it = this.m_Datas.iterator();
		ArrayList<bs_globalmsg> list = new ArrayList<bs_globalmsg>();
		while(it.hasNext()){
			bs_globalmsg cr = it.next();
			if(cr.Type.Get() == type || type == -1){
				list.add(cr);
			}
		}
		
		
		float pageCount = 10f;
		int count = 10;
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
		
		buffer.Add((short)count);
		bs_globalmsg[] msgs = new bs_globalmsg[list.size()];
		list.toArray(msgs);
		for(int i =  page * 10 ; i < page * 10 + count; ++ i){
			msgs[i].packData(buffer);
		}
		buffer.Add(maxPage);
	}
	
	public bs_globalmsg getMsg(int msgid){
		Iterator<bs_globalmsg> it = this.m_Datas.iterator();
		while(it.hasNext()){
			bs_globalmsg g = it.next();
			if(g.GID.Get() == msgid){
				return g;
			}
		}
		return null;
	}
	
	public boolean removeMsg(int msgid){
		Iterator<bs_globalmsg> it = this.m_Datas.iterator();
		boolean ret = false;
		while(it.hasNext()){
			bs_globalmsg g = it.next();
			if(g.GID.Get() == msgid){
				ret = true;
				it.remove();
			}
		}
		String sql = String.format(sql_remove, msgid);
		ret = DBMgr.ExecuteSQL(sql);
		return ret;
	}
	
	
	public int addSystemMessage(int type, String title,
			String content, int isTop, long topTime,
			long showTime, String area, int groupId,
			String linkurl, String imgurl){
		//我要进行广播；
		//Timestamp timeStamp = new Timestamp(date.getTime());
		//new Timestamp(m_Value)
		//timeStamp.
		
		String tt = TimeMethod.GetDayTimeStr(System.currentTimeMillis());
		String st = TimeMethod.GetDayTimeStr(System.currentTimeMillis());
		
		String str = "insert into bs_globalmsg(Title, Content,Type, IsTop, TopTime,ShowTime, Area, GroupId, Linkurl, Imgurl)values('%s', '%s', %d, %d, '%s', '%s', '%s', %d, '%s', '%s')";
		String sql = String.format(str, title, content, type, isTop, tt,st, area, groupId, linkurl, imgurl);
		boolean ret = DBMgr.ExecuteSQL(sql);
		
		//bs_globalmsg msg = DBMgr.CreateGlobalRoleDataByGID(new bs_globalmsg());
		if(ret){
			bs_globalmsg[] msgs = DBMgr.ReadSQL(new bs_globalmsg(), "select * from bs_globalmsg order by DateTime desc limit 1");
			bs_globalmsg msg = msgs[0];
			this.Add(msg);
			return msg.GID.Get();
		}
		return 0;
		/*
		bs_globalmsg msg = DBMgr.CreateGlobalRoleDataByGID(new bs_globalmsg());
		msg.Area.Set(area);
		msg.Content.Set(content);
		msg.TopTime.Set(System.currentTimeMillis());
		msg.GroupId.Set(0);
		DBMgr.Commit();
		return 0;
		*/
		/*
		ArrayList<User> users = OnlineUserSelector.GetInstance().GetSelectUsers();
		Iterator<User> it = users.iterator();
		while(it.hasNext()){
			SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER,19);
			User user = it.next();
			msg.packData(buffer);
			buffer.Send(user);
		}
		*/
	}
	
	

}
