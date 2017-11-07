package logic.loader;

import java.util.ArrayList;
import java.util.Iterator;

import utility.Debug;

import logic.PackBuffer;
import logic.Reg;
import logic.userdata.bs_globalmsg;
import logic.userdata.bs_group;
import logic.userdata.bs_user;
import manager.LoaderManager;
import core.DBLoaderEx;
import core.DBMgr;
import core.User;
import core.detail.impl.OnlineUserSelector;
import core.detail.impl.socket.SendMsgBuffer;

public class GroupLoader extends DBLoaderEx<bs_group> {

	
	public GroupLoader(bs_group p_Seed) {
		super(p_Seed, true);
	}
	
	public void packData(SendMsgBuffer buffer){
		Iterator<bs_group> it = this.m_Datas.iterator();
		buffer.Add((short)this.m_Datas.size());
		while(it.hasNext()){
			bs_group g = it.next();
			g.packData(buffer);
		}
	}
	
	public bs_group getGroup(long uid){
		Iterator<bs_group> it = this.m_Datas.iterator();
		while(it.hasNext()){
			bs_group user = it.next();
			if(user.RoleID.Get() == uid){
				return user;
			}
		}
		return null;
	}
	
	public void updateIncome(int gid){
		bs_group group = this.getGroup(gid);
		if(null != group){
			int total = group.CardUsedCount1.Get() * 200 + group.CardUsedCount2.Get() * 12;
			group.Income.Set(total);
		}
	}
	
	public bs_group getGroup(int gid){
		Iterator<bs_group> it = this.m_Datas.iterator();
		while(it.hasNext()){
			bs_group user = it.next();
			if(user.GID.Get() == gid){
				return user;
			}
		}
		return null;
	}
	
	public void addUser(){
		Debug.Assert(false, "todo");
	}
	
	public boolean removeGroup(int id ){
		Iterator<bs_group> it = this.m_Datas.iterator();
		while(it.hasNext()){
			bs_group user = it.next();
			if(user.GID.Get() == id){
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public void addGroup(bs_group msg){
		this.m_Datas.add(msg);
	}
	
	public void updateUserCount(int gid, int count){
		bs_group group = this.getGroup(gid);
		if(null != group){
			group.Number.Set(group.Number.Get() + count);
		}
	}
	
	//更新下载的数量;
	public void updateDownloadCount(int gid, int count){
		
	}
	
	public void packGroupList(SendMsgBuffer buffer){
		buffer.Add((short)this.m_Datas.size());
		Iterator<bs_group> it = this.m_Datas.iterator();
		while(it.hasNext()){
			it.next().packData(buffer);
//			bs_group user = it.next();
//			buffer.Add(user.Area.Get());
//			buffer.Add(user.Province.Get());
//			buffer.Add(user.City.Get());
//			buffer.Add(user.Tel.Get());
//			buffer.Add(user.Addr.Get());
//			buffer.Add(user.Name.Get());
		}
	}
	

}
