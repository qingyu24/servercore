package logic.loader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.DBLoaderEx;
import core.DBMgr;
import core.detail.impl.socket.SendMsgBuffer;
import logic.MyUser;
import logic.userdata.bs_cardrecord;
import logic.userdata.bs_code;
import logic.userdata.tx_cardrecord;
import manager.LoaderManager;

public class RecordLoader extends DBLoaderEx<bs_cardrecord> {

	public RecordLoader(bs_cardrecord p_Seed) {
		super(p_Seed);

	}

	public boolean addRecord(MyUser user, String code) {
		bs_cardrecord cr = DBMgr.CreateGlobalRoleDataByGID(new bs_cardrecord());
		cr.Code.Set(code);
		cr.GroupID.Set(user.getCenterData().getGroupId());
		cr.RoleID.Set(user.GetRoleGID());
		cr.DateTime.Set(System.currentTimeMillis());
		this.m_Datas.add(cr);
		return true;
	}

	public boolean useRecord(MyUser user, bs_code c) {
		bs_cardrecord cr = DBMgr.CreateGlobalRoleDataByGID(new bs_cardrecord());
		cr.Code.Set(c.Code.Get());
	/*	cr.GID.Set(c.GID.Get());*/
		cr.GroupID.Set(user.getCenterData().getGroupId());
		cr.RoleID.Set(user.GetRoleGID());
		cr.ChargeNum.Set(c.Price.Get());
		cr.ChargeCount.Set(1);
		int get = c.Type.Get();
		switch (get) {
		case 1:
			cr.CardUsedCount1.Set(1);

			break;
		case 2:
			cr.CardUsedCount2.Set(1);

			break;
		case 3:
			cr.FreeUsedCount1.Set(1);

			break;
		case 4:
			cr.FreeUsedCount2.Set(1);

			break;
		default:
			break;
		}
		cr.DateTime.Set(System.currentTimeMillis());
		this.m_Datas.add(cr);
		return true;
	}

	public List<tx_cardrecord> selectRecord(int page, long begintime, long endtime) { 
		ArrayList<tx_cardrecord> list = new ArrayList<tx_cardrecord>();
		Iterator<bs_cardrecord> it = this.m_Datas.iterator();
		UserLoader user = (UserLoader) LoaderManager.getInstance().getLoader(LoaderManager.Users);
		
		while (it.hasNext()) {
			bs_cardrecord next = it.next();
			if (next.DateTime.GetMillis() > begintime && next.DateTime.GetMillis() < endtime) {
				tx_cardrecord tx = new tx_cardrecord();
				tx.GroupID=next.GroupID.Get();
				tx.AddUserCount += next.AddUserCount.Get();
				tx.CardUsedCount1 += next.CardUsedCount1.Get();
				tx.CardUsedCount2 += next.CardUsedCount2.Get();
				tx.ChargeCount += next.ChargeCount.Get();
				tx.ChargeNum += next.ChargeNum.Get();
				tx.DownloadCount += next.DownloadCount.Get();
				tx.FreeUsedCount1 += next.FreeUsedCount1.Get();
				tx.FreeUsedCount2 += next.FreeUsedCount2.Get();
				tx.Register += next.Register.Get();
				tx.LostUserCount = user.lostUser(next.GroupID.Get(), begintime, endtime);
				list.add(tx);
			}
		}
		return list;
	}

	
	
	
	
	
	public void addRecordRegister(MyUser user) {
		bs_cardrecord cr = DBMgr.CreateGlobalRoleDataByGID(new bs_cardrecord());
		cr.RoleID.Set(user.GetRoleGID());
		cr.Register.Set(1);
		cr.DateTime.Set(System.currentTimeMillis());
		this.m_Datas.add(cr);
	}

	public bs_cardrecord createRecord(MyUser user) {
		bs_cardrecord cr = DBMgr.CreateGlobalRoleDataByGID(new bs_cardrecord());
		cr.RoleID.Set(user.GetRoleGID());
		cr.GroupID.Set(user.getCenterData().getGroupId());
		cr.DateTime.Set(System.currentTimeMillis());
		this.m_Datas.add(cr);
		return cr;
	}

	public void packData(SendMsgBuffer buffer, long start, long end) {
		Iterator<bs_cardrecord> it = this.m_Datas.iterator();
		ArrayList<bs_cardrecord> list = new ArrayList<bs_cardrecord>();
		while (it.hasNext()) {
			bs_cardrecord cr = it.next();
			if (cr.DateTime.GetMillis() > start && cr.DateTime.GetMillis() <= end) {
				list.add(cr);
			}
		}
		buffer.Add(list.size());
		if (list.size() > 0) {
			buffer.Add((short) list.size());
			it = list.iterator();
			while (it.hasNext()) {
				it.next().packData(buffer);
			}
		}
	}

}
