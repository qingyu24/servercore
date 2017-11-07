package test.robot;

public class RFCFn
{
	public static void Login_Enter(Robot r, String p_username, String p_password, int userType, int p_nServerID, String p_deviceIdentifier, String p_deviceModel)
	{
		r.GetSendBuffer().Clear().AddID(0,0).Add(p_username).Add(p_password).Add(userType).Add(p_nServerID).Add(p_deviceIdentifier).Add(p_deviceModel).Send(r.GetLink());
	}

	public static void Login_ResetEnter(Robot r, String p_username, String p_password, int userType, int p_nServerID, String p_deviceIdentifier, String p_deviceModel, int code)
	{
		r.GetSendBuffer().Clear().AddID(0,3).Add(p_username).Add(p_password).Add(userType).Add(p_nServerID).Add(p_deviceIdentifier).Add(p_deviceModel).Add(code).Send(r.GetLink());
	}

	public static void Login_Register(Robot r, String p_username, String p_password, int userType, String tel, int code, String pass, int isMember, int groupID, String groupName, String area, String Province, String city, String mail)
	{
		r.GetSendBuffer().Clear().AddID(0,2).Add(p_username).Add(p_password).Add(userType).Add(tel).Add(code).Add(pass).Add(isMember).Add(groupID).Add(groupName).Add(area).Add(Province).Add(city).Add(mail).Send(r.GetLink());
	}

	public static void CharacterImpl_RequestBaseInfo(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(5,0).Send(r.GetLink());
	}

	public static void GroupImpl_Update(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(1,1).Send(r.GetLink());
	}

	public static void GroupImpl_Create(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(1,0).Send(r.GetLink());
	}

	public static void GroupImpl_Delete(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(1,2).Send(r.GetLink());
	}

	public static void CenterImpl_GetGroupList(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,21).Send(r.GetLink());
	}

	public static void CenterImpl_GetIcon(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,6).Send(r.GetLink());
	}

	public static void CenterImpl_QueryGroup(Robot r, int groupId)
	{
		r.GetSendBuffer().Clear().AddID(3,12).Add(groupId).Send(r.GetLink());
	}

	public static void CenterImpl_GetUserList(Robot r, int page, int groupId, String area, String Province, String city, String tel)
	{
		r.GetSendBuffer().Clear().AddID(3,22).Add(page).Add(groupId).Add(area).Add(Province).Add(city).Add(tel).Send(r.GetLink());
	}

	public static void CenterImpl_ExchangeCode(Robot r, String code)
	{
		r.GetSendBuffer().Clear().AddID(3,7).Add(code).Send(r.GetLink());
	}

	public static void CenterImpl_ShareAward(Robot r, int canShare)
	{
		r.GetSendBuffer().Clear().AddID(3,8).Add(canShare).Send(r.GetLink());
	}

	public static void CenterImpl_FullInfo(Robot r, String mail, int groupId)
	{
		r.GetSendBuffer().Clear().AddID(3,5).Add(mail).Add(groupId).Send(r.GetLink());
	}

	public static void CenterImpl_ModifyPass(Robot r, String oldpass, String newpss)
	{
		r.GetSendBuffer().Clear().AddID(3,14).Add(oldpass).Add(newpss).Send(r.GetLink());
	}

	public static void CenterImpl_GetAds(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,2).Send(r.GetLink());
	}

	public static void CenterImpl_LoginAward(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,10).Send(r.GetLink());
	}

	public static void CenterImpl_AboutUs(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,15).Send(r.GetLink());
	}

	public static void CenterImpl_RetrievePass(Robot r, String tel, int code, String pass)
	{
		r.GetSendBuffer().Clear().AddID(3,4).Add(tel).Add(code).Add(pass).Send(r.GetLink());
	}

	public static void CenterImpl_EvalAward(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,9).Send(r.GetLink());
	}

	public static void CenterImpl_MsgList(Robot r, int page, int type)
	{
		r.GetSendBuffer().Clear().AddID(3,11).Add(page).Add(type).Send(r.GetLink());
	}

	public static void CenterImpl_UnlockGate(Robot r, int type, int gateId)
	{
		r.GetSendBuffer().Clear().AddID(3,3).Add(type).Add(gateId).Send(r.GetLink());
	}

	public static void CenterImpl_GetGroup(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,17).Send(r.GetLink());
	}

	public static void CenterImpl_AddGroup(Robot r, String area, String Province, String city, String name, String pass, int anthLevel, int parent)
	{
		r.GetSendBuffer().Clear().AddID(3,20).Add(area).Add(Province).Add(city).Add(name).Add(pass).Add(anthLevel).Add(parent).Send(r.GetLink());
	}

	public static void CenterImpl_View(Robot r, int type, String title, String msg)
	{
		r.GetSendBuffer().Clear().AddID(3,13).Add(type).Add(title).Add(msg).Send(r.GetLink());
	}

	public static void CenterImpl_ReadAuth(Robot r, String params)
	{
		r.GetSendBuffer().Clear().AddID(3,30).Add(params).Send(r.GetLink());
	}

	public static void CenterImpl_ModifyUser(Robot r, String tel, String area, String Province, String city, String name, String mail, int isEbl, int groupId, String groupName, int age)
	{
		r.GetSendBuffer().Clear().AddID(3,38).Add(tel).Add(area).Add(Province).Add(city).Add(name).Add(mail).Add(isEbl).Add(groupId).Add(groupName).Add(age).Send(r.GetLink());
	}

	public static void CenterImpl_AddUser(Robot r, String tel, String mail, String area, String name, String pass, int money, int groupId, String groupName, String province, String city)
	{
		r.GetSendBuffer().Clear().AddID(3,26).Add(tel).Add(mail).Add(area).Add(name).Add(pass).Add(money).Add(groupId).Add(groupName).Add(province).Add(city).Send(r.GetLink());
	}

	public static void CenterImpl_RemoveGroup(Robot r, int id)
	{
		r.GetSendBuffer().Clear().AddID(3,23).Add(id).Send(r.GetLink());
	}

	public static void CenterImpl_ChangeAuth(Robot r, String name)
	{
		r.GetSendBuffer().Clear().AddID(3,29).Add(name).Send(r.GetLink());
	}

	public static void CenterImpl_GeneralCode(Robot r, int groupId, int type, int price, int count)
	{
		r.GetSendBuffer().Clear().AddID(3,33).Add(groupId).Add(type).Add(price).Add(count).Send(r.GetLink());
	}

	public static void CenterImpl_LogOut(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,36).Send(r.GetLink());
	}

	public static void CenterImpl_InitAuth(Robot r, String content)
	{
		r.GetSendBuffer().Clear().AddID(3,28).Add(content).Send(r.GetLink());
	}

	public static void CenterImpl_TagReadMsg(Robot r, int msgid)
	{
		r.GetSendBuffer().Clear().AddID(3,35).Add(msgid).Send(r.GetLink());
	}

	public static void CenterImpl_UseCode(Robot r, String code)
	{
		r.GetSendBuffer().Clear().AddID(3,34).Add(code).Send(r.GetLink());
	}

	public static void CenterImpl_FinishedGate(Robot r, int gate, int smallgate)
	{
		r.GetSendBuffer().Clear().AddID(3,32).Add(gate).Add(smallgate).Send(r.GetLink());
	}

	public static void CenterImpl_UpdateGroup(Robot r, int groupId, String area, String Province, String city, String name, String pass, int anthLevel)
	{
		r.GetSendBuffer().Clear().AddID(3,25).Add(groupId).Add(area).Add(Province).Add(city).Add(name).Add(pass).Add(anthLevel).Send(r.GetLink());
	}

	public static void CenterImpl_AddUserMoney(Robot r, long roleId, int money)
	{
		r.GetSendBuffer().Clear().AddID(3,27).Add(roleId).Add(money).Send(r.GetLink());
	}

	public static void CenterImpl_UpdateUser(Robot r, long userId, String area, String Province, String city, String name, String mail, int isEbl, int groupId, String groupName, int age)
	{
		r.GetSendBuffer().Clear().AddID(3,31).Add(userId).Add(area).Add(Province).Add(city).Add(name).Add(mail).Add(isEbl).Add(groupId).Add(groupName).Add(age).Send(r.GetLink());
	}

	public static void CenterImpl_AddMsg(Robot r, int type, String title, String content, int isTop, long topTime, long showTime, String area, int groupId, String linkurl, String imgurl)
	{
		r.GetSendBuffer().Clear().AddID(3,24).Add(type).Add(title).Add(content).Add(isTop).Add(topTime).Add(showTime).Add(area).Add(groupId).Add(linkurl).Add(imgurl).Send(r.GetLink());
	}

	public static void CenterImpl_GetRecordByTime(Robot r, int page, long begintime, long endtime)
	{
		r.GetSendBuffer().Clear().AddID(3,16).Add(page).Add(begintime).Add(endtime).Send(r.GetLink());
	}

	public static void CenterImpl_UpdateGroupField(Robot r, int groupId, String Area, String Province, String City, String Tel, String Addr, String Name, String Password, int Number, int Income, int ChargeNum, int ChargeCount, int LostUserCount, int AddUserCount, int DownloadCount, int CardUsedCount1, int CardUsedCount2, int FreeUsedCount1, int Auth, int isSelf, int Parent)
	{
		r.GetSendBuffer().Clear().AddID(3,37).Add(groupId).Add(Area).Add(Province).Add(City).Add(Tel).Add(Addr).Add(Name).Add(Password).Add(Number).Add(Income).Add(ChargeNum).Add(ChargeCount).Add(LostUserCount).Add(AddUserCount).Add(DownloadCount).Add(CardUsedCount1).Add(CardUsedCount2).Add(FreeUsedCount1).Add(Auth).Add(isSelf).Add(Parent).Send(r.GetLink());
	}

	public static void CenterImpl_unlockSmallGate(Robot r, int gate, int smallgate)
	{
		r.GetSendBuffer().Clear().AddID(3,18).Add(gate).Add(smallgate).Send(r.GetLink());
	}

	public static void CenterImpl_RemoveGlobalMsg(Robot r, int gid)
	{
		r.GetSendBuffer().Clear().AddID(3,39).Add(gid).Send(r.GetLink());
	}

}
