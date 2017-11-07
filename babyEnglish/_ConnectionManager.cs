using Networks;
public static class _ConnectionManager
{
	static public void Login_Enter(this ConnectionManager mgr, string p_username, string p_password, int userType, int p_nServerID, string p_deviceIdentifier, string p_deviceModel)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(0,0).Add(p_username).Add(p_password).Add(userType).Add(p_nServerID).Add(p_deviceIdentifier).Add(p_deviceModel).Send(mgr);
	}

	static public void Login_ResetEnter(this ConnectionManager mgr, string p_username, string p_password, int userType, int p_nServerID, string p_deviceIdentifier, string p_deviceModel, int code)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(0,3).Add(p_username).Add(p_password).Add(userType).Add(p_nServerID).Add(p_deviceIdentifier).Add(p_deviceModel).Add(code).Send(mgr);
	}

	static public void Login_Register(this ConnectionManager mgr, string p_username, string p_password, int userType, string tel, int code, string pass, int isMember, int groupID, string groupName, string area, string Province, string city, string mail)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(0,2).Add(p_username).Add(p_password).Add(userType).Add(tel).Add(code).Add(pass).Add(isMember).Add(groupID).Add(groupName).Add(area).Add(Province).Add(city).Add(mail).Send(mgr);
	}

	static public void CharacterImpl_RequestBaseInfo(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(5,0).Send(mgr);
	}

	static public void GroupImpl_Update(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(1,1).Send(mgr);
	}

	static public void GroupImpl_Create(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(1,0).Send(mgr);
	}

	static public void GroupImpl_Delete(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(1,2).Send(mgr);
	}

	static public void CenterImpl_UpdateGroup(this ConnectionManager mgr, int groupId, string area, string Province, string city, string name, string pass, int anthLevel)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,25).Add(groupId).Add(area).Add(Province).Add(city).Add(name).Add(pass).Add(anthLevel).Send(mgr);
	}

	static public void CenterImpl_GetGroup(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,17).Send(mgr);
	}

	static public void CenterImpl_AddMsg(this ConnectionManager mgr, int type, string title, string content, int isTop, long topTime, long showTime, string area, int groupId, string linkurl, string imgurl)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,24).Add(type).Add(title).Add(content).Add(isTop).Add(topTime).Add(showTime).Add(area).Add(groupId).Add(linkurl).Add(imgurl).Send(mgr);
	}

	static public void CenterImpl_GetGroupList(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,21).Send(mgr);
	}

	static public void CenterImpl_UnlockGate(this ConnectionManager mgr, int type, int gateId)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,3).Add(type).Add(gateId).Send(mgr);
	}

	static public void CenterImpl_unlockSmallGate(this ConnectionManager mgr, int gate, int smallgate)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,18).Add(gate).Add(smallgate).Send(mgr);
	}

	static public void CenterImpl_AddGroup(this ConnectionManager mgr, string area, string Province, string city, string name, string pass, int anthLevel, int parent)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,20).Add(area).Add(Province).Add(city).Add(name).Add(pass).Add(anthLevel).Add(parent).Send(mgr);
	}

	static public void CenterImpl_AboutUs(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,15).Send(mgr);
	}

	static public void CenterImpl_GetUserList(this ConnectionManager mgr, int page, int groupId, string area, string Province, string city, string tel)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,22).Add(page).Add(groupId).Add(area).Add(Province).Add(city).Add(tel).Send(mgr);
	}

	static public void CenterImpl_RemoveGroup(this ConnectionManager mgr, int id)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,23).Add(id).Send(mgr);
	}

	static public void CenterImpl_FullInfo(this ConnectionManager mgr, string mail, int groupId)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,5).Add(mail).Add(groupId).Send(mgr);
	}

	static public void CenterImpl_GetIcon(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,6).Send(mgr);
	}

	static public void CenterImpl_ExchangeCode(this ConnectionManager mgr, string code)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,7).Add(code).Send(mgr);
	}

	static public void CenterImpl_ShareAward(this ConnectionManager mgr, int canShare)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,8).Add(canShare).Send(mgr);
	}

	static public void CenterImpl_RetrievePass(this ConnectionManager mgr, string tel, int code, string pass)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,4).Add(tel).Add(code).Add(pass).Send(mgr);
	}

	static public void CenterImpl_GetAds(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,2).Send(mgr);
	}

	static public void CenterImpl_EvalAward(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,9).Send(mgr);
	}

	static public void CenterImpl_LoginAward(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,10).Send(mgr);
	}

	static public void CenterImpl_MsgList(this ConnectionManager mgr, int page, int type)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,11).Add(page).Add(type).Send(mgr);
	}

	static public void CenterImpl_QueryGroup(this ConnectionManager mgr, int groupId)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,12).Add(groupId).Send(mgr);
	}

	static public void CenterImpl_View(this ConnectionManager mgr, int type, string title, string msg)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,13).Add(type).Add(title).Add(msg).Send(mgr);
	}

	static public void CenterImpl_ModifyPass(this ConnectionManager mgr, string oldpass, string newpss)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,14).Add(oldpass).Add(newpss).Send(mgr);
	}

	static public void CenterImpl_AddUserMoney(this ConnectionManager mgr, long roleId, int money)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,27).Add(roleId).Add(money).Send(mgr);
	}

	static public void CenterImpl_AddUser(this ConnectionManager mgr, string tel, string mail, string area, string name, string pass, int money, int groupId, string groupName, string province, string city)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,26).Add(tel).Add(mail).Add(area).Add(name).Add(pass).Add(money).Add(groupId).Add(groupName).Add(province).Add(city).Send(mgr);
	}

	static public void CenterImpl_UpdateGroupField(this ConnectionManager mgr, int groupId, string Area, string Province, string City, string Tel, string Addr, string Name, string Password, int Number, int Income, int ChargeNum, int ChargeCount, int LostUserCount, int AddUserCount, int DownloadCount, int CardUsedCount1, int CardUsedCount2, int FreeUsedCount1, int Auth, int isSelf, int Parent)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,37).Add(groupId).Add(Area).Add(Province).Add(City).Add(Tel).Add(Addr).Add(Name).Add(Password).Add(Number).Add(Income).Add(ChargeNum).Add(ChargeCount).Add(LostUserCount).Add(AddUserCount).Add(DownloadCount).Add(CardUsedCount1).Add(CardUsedCount2).Add(FreeUsedCount1).Add(Auth).Add(isSelf).Add(Parent).Send(mgr);
	}

	static public void CenterImpl_ChangeAuth(this ConnectionManager mgr, string name)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,29).Add(name).Send(mgr);
	}

	static public void CenterImpl_ReadAuth(this ConnectionManager mgr, string params)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,30).Add(params).Send(mgr);
	}

	static public void CenterImpl_UpdateUser(this ConnectionManager mgr, long userId, string area, string Province, string city, string name, string mail, int isEbl, int groupId, string groupName, int age, string tel)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,31).Add(userId).Add(area).Add(Province).Add(city).Add(name).Add(mail).Add(isEbl).Add(groupId).Add(groupName).Add(age).Add(tel).Send(mgr);
	}

	static public void CenterImpl_FinishedGate(this ConnectionManager mgr, int gate, int smallgate)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,32).Add(gate).Add(smallgate).Send(mgr);
	}

	static public void CenterImpl_ModifyUser(this ConnectionManager mgr, string tel, string area, string Province, string city, string name, string mail, int isEbl, int groupId, string groupName, int age)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,38).Add(tel).Add(area).Add(Province).Add(city).Add(name).Add(mail).Add(isEbl).Add(groupId).Add(groupName).Add(age).Send(mgr);
	}

	static public void CenterImpl_UseCode(this ConnectionManager mgr, string code)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,34).Add(code).Send(mgr);
	}

	static public void CenterImpl_RemoveGlobalMsg(this ConnectionManager mgr, int gid)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,39).Add(gid).Send(mgr);
	}

	static public void CenterImpl_InitAuth(this ConnectionManager mgr, string content)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,28).Add(content).Send(mgr);
	}

	static public void CenterImpl_GetRecordByTime(this ConnectionManager mgr, int page, long begintime, long endtime)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,16).Add(page).Add(begintime).Add(endtime).Send(mgr);
	}

	static public void CenterImpl_TagReadMsg(this ConnectionManager mgr, int msgid)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,35).Add(msgid).Send(mgr);
	}

	static public void CenterImpl_LogOut(this ConnectionManager mgr)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,36).Send(mgr);
	}

	static public void CenterImpl_GeneralCode(this ConnectionManager mgr, int groupId, int type, int price, int count)
	{
		PacketBuffer.GetInstance().Clear().SetObjMethod(3,33).Add(groupId).Add(type).Add(price).Add(count).Send(mgr);
	}

}
