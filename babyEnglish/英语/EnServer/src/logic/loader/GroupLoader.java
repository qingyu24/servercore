package logic.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.LogRecord;

import core.DBLoader;
import core.DBLoaderEx;
import core.DBMgr;
import core.db.DBInt;
import core.detail.impl.socket.SendMsgBuffer;
import logic.userdata.bs_globalmsg;
import logic.userdata.bs_group;
import logic.userdata.tx_cardrecord;
import logic.userdata.tx_group;
import manager.LoaderManager;
import utility.Debug;

public class GroupLoader extends DBLoaderEx<bs_group> {

	public GroupLoader(bs_group p_Seed) {
		super(p_Seed, true);
	}

	public void packData(SendMsgBuffer buffer) {
		Iterator<bs_group> it = this.m_Datas.iterator();
		buffer.Add((short) this.m_Datas.size());
		while (it.hasNext()) {
			bs_group g = it.next();
			g.packData(buffer);
		}
	}

	public bs_group getGroup(long uid) {
		Iterator<bs_group> it = this.m_Datas.iterator();
		while (it.hasNext()) {
			bs_group user = it.next();
			if (user.RoleID.Get() == uid) {
				return user;
			}
		}
		return null;
	}

	public void updateIncome(int gid) {
		bs_group group = this.getGroup(gid);
		if (null != group) {
			int total = group.CardUsedCount1.Get() * 200 + group.CardUsedCount2.Get() * 12;
			group.Income.Set(total);
		}
	}

	public bs_group getGroup(int gid) {
		Iterator<bs_group> it = this.m_Datas.iterator();
		while (it.hasNext()) {
			bs_group user = it.next();
			if (user.GID.Get() == gid) {
				return user;
			}
		}
		return null;
	}

	public void addUser() {
		Debug.Assert(false, "todo");
	}

	public boolean removeGroup(int id) {
		Iterator<bs_group> it = this.m_Datas.iterator();
		while (it.hasNext()) {
			bs_group user = it.next();
			if (user.GID.Get() == id) {
				it.remove();
				
				return true;
			}
		}
		return false;
	}

	public void addGroup(bs_group msg) {
		
		this.m_Datas.add(msg);
	}

	public void updateUserCount(int gid, int count) {
		bs_group group = this.getGroup(gid);
		if (null != group) {
			group.Number.Set(group.Number.Get() + count);
			System.out.println("用户数量加+111");
		}
	}

	// 更新下载的数量;
	public void updateDownloadCount(int gid, int count) {

	}

	public void findDownloadCount(int groupId, int count) {
		Iterator<bs_group> iterator = this.m_Datas.iterator();
		GroupLoader group = DBMgr.CreateGlobalRoleDataByGID(new GroupLoader(new bs_group()));

	}

	public void packGroupList(SendMsgBuffer buffer) {
		buffer.Add((short) this.m_Datas.size());
		Iterator<bs_group> it = this.m_Datas.iterator();
		while (it.hasNext()) {
			it.next().packData(buffer);
			// bs_group user = it.next();
			// buffer.Add(user.Area.Get());
			// buffer.Add(user.Province.Get());
			// buffer.Add(user.City.Get());
			// buffer.Add(user.Tel.Get());
			// buffer.Add(user.Addr.Get());
			// buffer.Add(user.Name.Get());
		}
	}

	public List<tx_group> sumAll(int page, List<tx_cardrecord> list) {
		ArrayList<tx_group> tx_list = new ArrayList<tx_group>();
		Iterator<bs_group> it = this.m_Datas.iterator();
		HashMap<Integer, tx_cardrecord> hashMap = new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			tx_cardrecord tx = list.get(i);
			hashMap.put(tx.GID, tx);

		}
		while (it.hasNext()) {
			bs_group next = it.next();

			tx_group result = new tx_group(next);
			tx_cardrecord card = new tx_cardrecord();
			if (hashMap.get(result.GID) != null) {
				card = hashMap.get(result.GID);
			}
			result.AddUserCount += card.AddUserCount;
			result.CardUsedCount1 += card.CardUsedCount1;
			result.CardUsedCount2 += card.CardUsedCount2;
			result.ChargeCount += card.ChargeCount;
			result.ChargeNum += card.ChargeNum;
			result.DownloadCount += card.DownloadCount;
			result.FreeUsedCount1 += card.FreeUsedCount1;
			result.FreeUsedCount2 += card.FreeUsedCount2;
			result.LostUserCount += card.LostUserCount;
			result.Income += result.CardUsedCount1 * 1.2 + result.CardUsedCount2 * 20;
			tx_list.add(result);
		}
		return tx_list;

	}

	public void packData(SendMsgBuffer buffer, int page, List<tx_group> list) {

		float pageCount = 10f;
		int count = 10;
		int maxPage = (int) (list.size() / pageCount);
		float temp = (float) list.size() / pageCount;

		if (temp > maxPage) {
			maxPage += 1;
		}
		if (page >= maxPage - 1) {
			count = (int) (list.size() % pageCount);
			page = maxPage - 1;
		}
		count = Math.min(count, list.size());

		// buffer.Add((short) 1);
		tx_group[] msgs = new tx_group[list.size()];
		list.toArray(msgs);
		for (int i = page * 10; i < page * 10 + 1; ++i) {
			msgs[i].packData(buffer);
		}
		buffer.Add(maxPage);
	}

	public void sumNumber() {
		Iterator<bs_group> it = this.m_Datas.iterator();
		UserLoader loader2 = (UserLoader) LoaderManager.getInstance().getLoader("Users");
		while (it.hasNext()) {
			bs_group next = it.next();
			int i = loader2.sumNumber(next.GID.Get());
			next.Number.Set(i);
		}

	}

}
