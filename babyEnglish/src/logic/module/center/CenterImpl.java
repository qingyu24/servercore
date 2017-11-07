package logic.module.center;

import java.util.List;

import core.DBMgr;
import core.detail.impl.socket.SendMsgBuffer;
import core.remote.PI;
import core.remote.PL;
import core.remote.PS;
import core.remote.PU;
import core.remote.RFC;
import logic.LogRecords;
import logic.MyUser;
import logic.PackBuffer;
import logic.Reg;
import logic.loader.CodeLoader;
import logic.loader.GroupLoader;
import logic.loader.MessageLoader;
import logic.loader.RecordLoader;
import logic.loader.UserLoader;
import logic.userdata.bs_globalmsg;
import logic.userdata.bs_user;
import logic.userdata.tx_cardrecord;
import logic.userdata.tx_group;
import logic.userdata.handler.PlayerCenterData;
import manager.LoaderManager;

public class CenterImpl implements CenterInterface {

	// @Override
	// @RFC(ID = 1)
	// public void Register(@PU MyUser p_user, @PS String tel, @PI int code,
	// @PS String pass, @PI int isMember, @PI int groupID) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	@RFC(ID = 2)
	public void GetAds(@PU MyUser p_user) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 2);
		send.Add("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 4)
	public void RetrievePass(@PU MyUser p_user, @PS String tel, @PI int code, @PS String pass) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 4);
		PlayerCenterData center = p_user.getCenterData();
		center.changePass(pass);
		send.Add(1);
		send.Send(p_user);
		}

	@Override
	@RFC(ID = 5)
	public void FullInfo(@PU MyUser p_user, @PS String mail, @PI int groupId) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 5);
		PlayerCenterData center = p_user.getCenterData();
		center.updateUserInfo(mail);
		center.changeCoin(10);

		send.Add(1);
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 6)
	public void GetIcon(@PU MyUser p_user) {
		// TODO Auto-generated method stub
		// 获得金币;
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 6);
		PlayerCenterData center = p_user.getCenterData();
		send.Add(center.getCoin());
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 7)
	public void ExchangeCode(@PU MyUser p_user, @PS String code) {
		// TODO Auto-generated method stub
		// 兑换码;
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 7);
		PlayerCenterData center = p_user.getCenterData();
		boolean ret = center.queryCode(code);
		center.changeCoin(10);
		send.Add(ret ? 1 : 0);
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 8)
	public void ShareAward(@PU MyUser p_user, @PI int canShare) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_SHAREAWARD);
		PlayerCenterData center = p_user.getCenterData();
		if (1 == canShare) {
			boolean ret = center.hasShare();
			send.Add(ret ? 1 : 0);
			send.Send(p_user);
		} else {
			int money = center.updateShareTime();
			send.Add(money);
			send.Send(p_user);
		}
			}

	@Override
	@RFC(ID = 9)
	public void EvalAward(@PU MyUser p_user) {
		// TODO Auto-generated method stub

			}

	@Override
	@RFC(ID = 10)
	public void LoginAward(@PU MyUser p_user) {
		// TODO Auto-generated method stub

			}

	@Override
	@RFC(ID = 11)
	public void MsgList(@PU MyUser p_user, @PI int page, @PI int type) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_MSGLIST);
		MessageLoader loader = (MessageLoader) LoaderManager.getInstance().getLoader(LoaderManager.Message);
		loader.packData(send, page, type);
		PlayerCenterData center = p_user.getCenterData();
		center.packData(send);
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 12)
	public void QueryGroup(@PU MyUser p_user, @PI int groupId) {
		// TODO Auto-generated method stub

			}

	@Override
	@RFC(ID = 13)
	public void View(@PU MyUser p_user, @PI int type, @PS String title, @PS String msg) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 13);
		PlayerCenterData center = p_user.getCenterData();
		center.addPersonalMsg(title, msg);
		send.Add(1);
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 14)
	public void ModifyPass(@PU MyUser p_user, @PS String oldpass, @PS String newpss) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 14);
		PlayerCenterData center = p_user.getCenterData();
		int ret = 0;
		if (center.getPass().equals(oldpass)) {
			center.changePass(newpss);
			ret = 1;
		}
		send.Add(ret);
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 15)
	public void AboutUs(@PU MyUser p_user) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 15);
		PlayerCenterData center = p_user.getCenterData();
		send.Add(center.getAboutUs());
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 17)
	public void GetGroup(@PU MyUser p_user) {
		// TODO Auto-generated method stub

			}

	@Override
	@RFC(ID = 3)
	public void UnlockGate(@PU MyUser p_user, @PI int type, @PI int gateId) {
		// TODO Auto-generated method stub
		// 解锁关卡;
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 3);
		PlayerCenterData center = p_user.getCenterData();
		int ret = 0;
		if (type == 1) {
			// 全部解锁;
			ret = center.unlockGate(-1);
		} else {
			ret = center.unlockGate(gateId);
		}

		send.Add(center.getCoin());
		// send.Add(center.getGate());
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 18)
	public void unlockSmallGate(@PU MyUser p_user, @PI int gate, @PI int smallgate) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, 18);
		PlayerCenterData center = p_user.getCenterData();
		String ret = center.unlockSmallGate(gate, smallgate);
		if (ret == null) {
			send.Add(0);
		} else {
			send.Add(1);

		}
		// send.Add(ret);
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 20)
	public void AddGroup(@PU MyUser p_user, @PS String area, @PS String Province, @PS String city, @PS String name,
			@PS String pass, @PI int anthLevel, @PI int parent) {
		// TODO Auto-generated method stub
		//
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_ADD_GROUP);
		PlayerCenterData center = p_user.getCenterData();
		int id = center.addGroup(area, Province, city, name, pass, anthLevel, parent);
		send.Add(id); // 返回盟商的ID
		// todo;
		send.Send(p_user);

			}

	@Override
	@RFC(ID = 21)
	public void GetGroupList(@PU MyUser p_user) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_GET_GROUPLIST);
		PlayerCenterData center = p_user.getCenterData();
		GroupLoader loader = (GroupLoader) LoaderManager.getInstance().getLoader(LoaderManager.Group);
		loader.packGroupList(send);
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 22)
	public void GetUserList(@PU MyUser p_user, @PI int page, @PI int groupId, @PS String area, @PS String Province,
			@PS String city, @PS String tel) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_GET_USERLIST);
		UserLoader loader = (UserLoader) LoaderManager.getInstance().getLoader(LoaderManager.Users);
		loader.packUserList(send, page, groupId, area, Province, city, tel);
		send.Send(p_user);

			}

	@Override
	@RFC(ID = 23)
	public void RemoveGroup(@PU MyUser p_user, @PI int id) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_REMOVE_GROUP);
		PlayerCenterData center = p_user.getCenterData();
		center.removeGroup(id);
		send.Add(1);
		send.Send(p_user);
		//刷新数据库	
			}

	@Override
	@RFC(ID = 25)
	public void UpdateGroup(@PU MyUser p_user, @PI int groupId, @PS String area, @PS String Province, @PS String city,
			@PS String name, @PS String pass, @PI int anthLevel) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_UPDATE_GROUP);
		PlayerCenterData center = p_user.getCenterData();
		System.out.println("收到了  修改消息");
		center.updateGroup(groupId, area, Province, city, name, pass, anthLevel);
		send.Add(1);
		send.Send(p_user);
		LoaderManager.getInstance().getLoader(LoaderManager.Group).update();
			}

	@Override
	@RFC(ID = 24)
	public void AddMsg(@PU MyUser p_user, @PI int type, @PS String title, @PS String content, @PI int isTop,
			@PL long topTime, @PL long showTime, @PS String area, @PI int groupId, @PS String linkurl,
			@PS String imgurl) {
		// TODO Auto-generated method stub
	
		SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_ADD_MSG);

		MessageLoader loader = (MessageLoader) LoaderManager.getInstance().getLoader(LoaderManager.Message);
		int msgId = loader.addSystemMessage(type, title, content, isTop, topTime, showTime, area, groupId, linkurl,
				imgurl);

		buffer.Add(msgId);
		buffer.Send(p_user);
			}

	@Override
	@RFC(ID = 26)
	public void AddUser(@PU MyUser p_user, @PS String tel, @PS String mail, @PS String area, @PS String name,
			@PS String pass, @PI int money, @PI int groupId, @PS String groupName, @PS String province,
			@PS String city) {
		// TODO Auto-generated method stub
		GroupLoader loader = (GroupLoader) LoaderManager.getInstance().getLoader(LoaderManager.Group);
		loader.updateUserCount(groupId, 1);

		SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_ADD_USER);
		PlayerCenterData center = p_user.getCenterData();
		long userId = center.addUser(tel, mail, area, name, pass, money, groupId, groupName, province, city);
		loader.updateUserCount(groupId, 0);
		buffer.Add(userId);
		buffer.Send(p_user);

		loader.sumNumber();
		/*       LoaderManager.getInstance().getLoader(LoaderManager.Users).update();
		 */

			}

	@Override
	@RFC(ID = 27)
	public void AddUserMoney(@PU MyUser p_user, @PL long roleId, @PI int money) {
		// TODO Auto-generated method stub
		SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_ADD_USER_SCORE);
		PlayerCenterData center = p_user.getCenterData();
		center.changeUserMoney(roleId, money);
		buffer.Add(1);
		buffer.Send(p_user);


			}

	@Override
	@RFC(ID = 28)
	public void InitAuth(@PU MyUser p_user, @PS String content) {
		// TODO Auto-generated method stub
		SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_INIT_AUTH);
		PlayerCenterData center = p_user.getCenterData();
		boolean ret = center.initAuth(content);
		buffer.Add(ret ? 1 : 0);
		buffer.Send(p_user);
			}

	@Override
	@RFC(ID = 29)
	public void ChangeAuth(@PU MyUser p_user, @PS String name) {
		// TODO Auto-generated method stub
		SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_CHANGE_AUTH);
		PlayerCenterData center = p_user.getCenterData();
		boolean ret = center.updateAuth(name);
		buffer.Add(ret ? 1 : 0);
		buffer.Send(p_user);
			}

	@Override
	@RFC(ID = 30)
	public void ReadAuth(@PU MyUser p_user, @PS String params) {
		// TODO Auto-generated method stub
		SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_READ_AUTH);
		PlayerCenterData center = p_user.getCenterData();
		String ret = center.readAuth();
		buffer.Add(ret);
		buffer.Send(p_user);
			}

	@Override
	@RFC(ID = 31)
	public void UpdateUser(@PU MyUser p_user, @PL long userId, @PS String area, @PS String Province, @PS String city,
			@PS String name, @PS String mail, @PI int isEbl, @PI int groupId, @PS String groupName, @PI int age,@PS String tel) {
		// TODO Auto-generated method stub
		
	/*	LogRecords.Log(null, "+++"+p_user.GetRoleGID()+"sss"+userId);*/
		GroupLoader loader = (GroupLoader) LoaderManager.getInstance().getLoader(LoaderManager.Group);
		loader.updateUserCount(groupId, 1);
		
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_UPDATE_USER);
		PlayerCenterData center = p_user.getCenterData();
		int oldMoney = center.getCoin(userId);
		int oldGroupId = center.updateUser(userId, area, Province, city, name, mail, isEbl, groupId, groupName, age);
		if (oldGroupId == -1) {
			send.Add(oldMoney);
			send.Send(p_user);
			return;
		}
		loader.updateUserCount(oldGroupId, -1);
		int newMoney = center.getCoin(userId);
		send.Add(newMoney - oldMoney);
		send.Send(p_user);

			}

	@Override
	@RFC(ID = 32)
	public void FinishedGate(@PU MyUser p_user, @PI int gate, @PI int smallgate) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_FINISHED_GATE);
		PlayerCenterData center = p_user.getCenterData();
		String ret = center.finishedSmallGate(gate, smallgate);
		if (ret == null) {
			send.Add(0);
		} else {
			send.Add(1);
		}
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 33)
	public void GeneralCode(@PU MyUser p_user, @PI int groupId, @PI int type, @PI int price, @PI int count) {
		// TODO Auto-generated method stub
		CodeLoader loader = (CodeLoader) LoaderManager.getInstance().getLoader(LoaderManager.Code);
		String ret = loader.generalCode(groupId, type, price, count);
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_GENERAL_CODE);
		send.Add(ret);
		send.Send(p_user);
			}

	@Override
	@RFC(ID = 34)
	public void UseCode(@PU MyUser p_user, @PS String code) {
		// TODO Auto-generated method stub
		CodeLoader loader = (CodeLoader) LoaderManager.getInstance().getLoader(LoaderManager.Code);
		int groupId = p_user.getCenterData().getGroupId();
		boolean ret = loader.useCode(p_user, groupId, code);

		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_USE_CODE);
		if (ret) {
			send.Add(1);
			send.Add(p_user.getCenterData().getCoin());
		} else {
			send.Add(0);
			send.Add(p_user.getCenterData().getCoin());
		}
		send.Send(p_user);
		LogRecords.Log(null, "使用一张卡");



			}

	@Override
	@RFC(ID = 35)
	public void TagReadMsg(@PU MyUser p_user, @PI int msgid) {
		// TODO Auto-generated method stub
		MessageLoader loader = (MessageLoader) LoaderManager.getInstance().getLoader(LoaderManager.Message);
		bs_globalmsg msg = loader.getMsg(msgid);
		if (null != msg) {
			msg.IsRead.Set(1);
			SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_TAG_MSG_READ);
			send.Add(1);
			send.Send(p_user);
		}

			}

	@Override
	@RFC(ID = 36)
	public void LogOut(@PU MyUser p_user) {
		// TODO Auto-generated method stub
		p_user.Close(1, 1);
			}

	@Override
	@RFC(ID = 37)
	public void UpdateGroupField(@PU MyUser p_user, @PI int groupId, @PS String Area, @PS String Province,
			@PS String City, @PS String Tel, @PS String Addr, @PS String Name, @PS String Password, @PI int Number,
			@PI int Income, @PI int ChargeNum, @PI int ChargeCount, @PI int LostUserCount, @PI int AddUserCount,
			@PI int DownloadCount, @PI int CardUsedCount1, @PI int CardUsedCount2, @PI int FreeUsedCount1, @PI int Auth,
			@PI int isSelf, @PI int Parent) {
		// TODO Auto-generated method stub
		System.out.println("shouru"+Income);
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_UPDATE_GROUPFIELD);
		PlayerCenterData center = p_user.getCenterData();
		center.updateGroupFields(groupId, Area, Province, City, Tel, Addr, Name, Password, Number, Income, ChargeNum,
				ChargeCount, LostUserCount, AddUserCount, DownloadCount, CardUsedCount1, CardUsedCount2, FreeUsedCount1,
				Auth, isSelf, Parent);
		send.Add(1);
		send.Send(p_user);

			}

	@Override
	@RFC(ID = 38)
	public void ModifyUser(@PU MyUser p_user, @PL String tel, @PS String area, @PS String Province, @PS String city,
			@PS String name, @PS String mail, @PI int isEbl, @PI int groupId, @PS String groupName, @PI int age) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("收到要修改用户"+tel);
		GroupLoader loader = (GroupLoader) LoaderManager.getInstance().getLoader(LoaderManager.Group);
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_MODIFY_USER);
		PlayerCenterData center = p_user.getCenterData();

		boolean ret = center.modifyUser(tel, area, Province, city, name, mail, isEbl, groupId, groupName, age);
		send.Add(ret ? 1 : 0);
		send.Send(p_user);

			}

	@Override
	@RFC(ID = 39)
	public void RemoveGlobalMsg(@PU MyUser p_user, @PI int gid) {
		// TODO Auto-generated method stub
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_REMOVE_GLOBALMSG);
		MessageLoader loader = (MessageLoader) LoaderManager.getInstance().getLoader(LoaderManager.Message);
		boolean ret = loader.removeMsg(gid);
		send.Add(ret ? 1 : 0);
		send.Send(p_user);

			}

	@Override
	@RFC(ID = 16)
	public void GetRecordByTime(@PU MyUser p_user, @PI int page, @PL long begintime, @PL long endtime) {
		// TODO Auto-generated method stub
		RecordLoader loader = (RecordLoader) LoaderManager.getInstance().getLoader(LoaderManager.Record);
		GroupLoader group = (GroupLoader) LoaderManager.getInstance().getLoader(LoaderManager.Group);
		List<tx_cardrecord> tx = loader.selectRecord(page, begintime, endtime);
		List<tx_group> sumAll = group.sumAll(page, tx);
		SendMsgBuffer send = PackBuffer.GetInstance().Clear().AddID(Reg.CENTER, CenterInterface.MID_GET_RECORD_BY_TIME);
		group.packData(send, page, sumAll);
		send.Send(p_user);
		
	
	}

}
