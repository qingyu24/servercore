package logic.loader;

import java.util.ArrayList;
import java.util.Iterator;

import utility.Debug;
import utils.RandomId;

import logic.MyUser;
import logic.PackBuffer;
import logic.Reg;
import logic.userdata.bs_cardrecord;
import logic.userdata.bs_code;
import logic.userdata.bs_globalmsg;
import logic.userdata.bs_group;
import logic.userdata.bs_user;
import manager.LoaderManager;
import core.DBLoaderEx;
import core.DBMgr;
import core.User;
import core.detail.impl.OnlineUserSelector;
import core.detail.impl.socket.SendMsgBuffer;

public class RecordLoader extends DBLoaderEx<bs_cardrecord> {

	public RecordLoader(bs_cardrecord p_Seed) {
		super(p_Seed);
		// TODO Auto-generated constructor stub
	}
	
	public boolean addRecord(MyUser user, String code){
		bs_cardrecord cr = DBMgr.CreateGlobalRoleDataByGID(new bs_cardrecord());
		cr.Code.Set(code);
		cr.GroupID.Set( user.getCenterData().getGroupId());
		cr.RoleID.Set(user.GetRoleGID());
		this.m_Datas.add(cr);
		return true;
	}
	
	public void addRecordRegister(MyUser user){
		bs_cardrecord cr = DBMgr.CreateGlobalRoleDataByGID(new bs_cardrecord());
		cr.RoleID.Set(user.GetRoleGID());
		cr.Register.Set(1);
		this.m_Datas.add(cr);
	}
	
	public bs_cardrecord createRecord(MyUser user){
		bs_cardrecord cr = DBMgr.CreateGlobalRoleDataByGID(new bs_cardrecord());
		cr.RoleID.Set(user.GetRoleGID());
		cr.GroupID.Set(user.getCenterData().getGroupId());
		this.m_Datas.add(cr);
		return cr;
	}
	
	public void packData(SendMsgBuffer buffer, long start, long end){
		Iterator<bs_cardrecord> it = this.m_Datas.iterator();
		ArrayList<bs_cardrecord> list = new ArrayList<bs_cardrecord>();
		while(it.hasNext()){
			bs_cardrecord cr = it.next();
			if(cr.DateTime.GetMillis() > start && cr.DateTime.GetMillis() <= end){
				list.add(cr);
			}
		}
		buffer.Add(list.size());
		if(list.size() > 0){
			buffer.Add((short)list.size());
			it = list.iterator();
			while(it.hasNext()){
				it.next().packData(buffer);
			}
		}
	}
	
	

}
