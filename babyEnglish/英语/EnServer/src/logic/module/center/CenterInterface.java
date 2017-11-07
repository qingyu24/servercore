package logic.module.center;

import core.remote.PI;
import core.remote.PL;
import core.remote.PS;
import core.remote.PU;
import core.remote.RCC;
import core.remote.RFC;
import logic.MyUser;
import logic.Reg;

@RCC (ID = Reg.CENTER)
public interface CenterInterface {
	
	
	static final int MID_REGISTER = 1; // 注册;
	static final int MID_GETADS = 2; // 获得广告图片地址;
	static final int MID_UNLOCKGATE = 3; // 解锁关卡;(四种方式)
	static final int MID_RETRIEVEPASS = 4; //密码重置;
	static final int MID_FULLINFO = 5; //完善信息;(金币奖励)
	static final int MID_GETCOIN = 6; //获得金币数;
	static final int MID_EXCHANGECODE = 7; //兑换码换取金币;
	static final int MID_SHAREAWARD = 8; //分享奖励金币;
	static final int MID_EVALAWARD = 9; //appstore评价奖励;
	static final int MID_LOGINAWARD = 10; //登录奖励;
	static final int MID_MSGLIST = 11; //消息列表;
	static final int MID_QUERYGROUP = 12; //盟商信息查询;
	static final int MID_VIEW = 13; //留言;
	static final int MID_MODIFYPASS = 14; //修改密码;
	static final int MID_ABOUTUS = 15; //关于我们
/*	static final int MID_GETMSG = 16; // 收到消息;
*/	static final int MID_GETGROUP = 17; //获得盟商的列表;
	static final int MID_UNLOCKSMALLGATE = 18; // 解锁小关;
	static final int MID_ONLINEMESSAGE = 19; // 获得在线的消息;
	
	static final int MID_ADD_GROUP = 20; // 添加盟商列表;
	static final int MID_GET_GROUPLIST = 21; // 获得盟商的列表；
	static final int MID_GET_USERLIST = 22; // 获得用户的列表;
	
	static final int MID_REMOVE_GROUP = 23; // 删除盟商;
	static final int MID_ADD_MSG = 24; // 添加消息;
	
	static final int MID_UPDATE_GROUP = 25; // 更新盟商数据;
	
	static final int MID_ADD_USER = 26; // 添加用户;
	
	static final int MID_ADD_USER_SCORE = 27; //添加用户积分；
	
	static final int MID_INIT_AUTH = 28; //初始化权限列表；
	
	static final int MID_CHANGE_AUTH = 29; //修改权限;
	
	static final int MID_READ_AUTH = 30; // 读取权限;
	
	static final int MID_UPDATE_USER = 31; // 更新用户数据;
	
	static final int MID_FINISHED_GATE = 32; //完成关卡;
	
	static final int MID_GENERAL_CODE = 33; //生成激活码;

	static final int MID_USE_CODE = 34; // 使用激活码;
	
	static final int MID_TAG_MSG_READ = 35; //标记消息已经读取;
	
	static final int MID_LOGOUT = 36; //离开的请求;
	
	static final int MID_UPDATE_GROUPFIELD = 37; //更新盟商的数据
	
	static final int MID_MODIFY_USER = 38; //修改用户数据;
	
	static final int MID_REMOVE_GLOBALMSG = 39; //修改用户数据;
	
	static final int MID_GET_RECORD_BY_TIME = 16; //按时更新用户
	
	//@RFC (ID = MID_REGISTER)
	//void Register(@PU MyUser p_user, @PS String tel, @PI int code, @PS String pass, @PI int isMember, @PI int groupID);
	
	@RFC (ID = MID_GETADS)
	void GetAds(@PU MyUser p_user);
	
	@RFC (ID = MID_UNLOCKGATE)
	void UnlockGate(@PU MyUser p_user, @PI int type, @PI int gateId);
	
	@RFC (ID = MID_RETRIEVEPASS)
	void RetrievePass(@PU MyUser p_user, @PS String tel, @PI int code, @PS String pass);
	
	@RFC (ID = MID_FULLINFO)
	void FullInfo(@PU MyUser p_user, @PS String mail, @PI int groupId);
	
	@RFC (ID = MID_GETCOIN)
	void GetIcon(@PU MyUser p_user);
	
	@RFC (ID = MID_EXCHANGECODE)
	void ExchangeCode(@PU MyUser p_user, @PS String code);
	
	@RFC (ID = MID_SHAREAWARD)
	void ShareAward(@PU MyUser p_user, @PI int ret);
	
	@RFC (ID = MID_EVALAWARD)
	void EvalAward(@PU MyUser p_user);
	
	@RFC (ID = MID_LOGINAWARD)
	void LoginAward(@PU MyUser p_user);
	
	@RFC (ID = MID_MSGLIST)
	void MsgList(@PU MyUser p_user, @PI int page, @PI int type);
	
	@RFC (ID = MID_QUERYGROUP)
	void QueryGroup(@PU MyUser p_user, @PI int groupId);
	
	@RFC (ID = MID_VIEW)
	void View(@PU MyUser p_user, @PI int type, @PS String title, @PS String msg);
	
	@RFC (ID = MID_MODIFYPASS)
	void ModifyPass(@PU MyUser p_user, @PS String oldpass, @PS String newpss);
	
	@RFC (ID = MID_ABOUTUS)
	void AboutUs(@PU MyUser p_user);
	
	@RFC (ID = MID_GETGROUP)
	void GetGroup(@PU MyUser p_user);
	
	@RFC (ID = MID_UNLOCKSMALLGATE)
	void unlockSmallGate(@PU MyUser p_user, @PI int gate, @PI int smallgate);
	
	
	//
	@RFC (ID = MID_ADD_GROUP)
	void AddGroup(@PU MyUser p_user,@PS String area, @PS String Province,@PS String city, 
			@PS String name, @PS String pass, @PI int anthLevel,@PI int parent);
	
	@RFC (ID = MID_GET_GROUPLIST)
	void GetGroupList(@PU MyUser p_user);
	
	@RFC (ID = MID_GET_USERLIST)
	void GetUserList(@PU MyUser p_user, @PI int page, @PI int groupId, @PS String area, @PS String Province, @PS String city, @PS String tel);
	
	@RFC (ID = MID_REMOVE_GROUP)
	void RemoveGroup(@PU MyUser p_user, @PI int id);
	
	@RFC (ID = MID_ADD_MSG)
	void AddMsg(@PU MyUser p_user, @PI int type, @PS String title, 
			@PS String content, @PI int isTop, @PL long topTime,
			@PL long showTime, @PS String area, @PI int groupId,
			@PS String linkurl, @PS String imgurl);
	
	@RFC (ID = MID_UPDATE_GROUP)
	void UpdateGroup(@PU MyUser p_user, @PI int groupId, @PS String area, @PS String Province,@PS String city, @PS String name, @PS String pass, @PI int anthLevel);
	
	@RFC (ID = MID_ADD_USER)
	void AddUser(@PU MyUser p_user, @PS String tel, @PS String mail,@PS String area, @PS String name, @PS String pass, @PI int money, @PI int groupId, @PS String groupName, @PS String province, @PS String city);
	
	@RFC (ID = MID_ADD_USER_SCORE)
	void AddUserMoney(@PU MyUser p_user, @PL long roleId, @PI int money);
	
	@RFC (ID = MID_INIT_AUTH)
	void InitAuth(@PU MyUser p_user, @PS String content);
	
	@RFC (ID = MID_CHANGE_AUTH)
	void ChangeAuth(@PU MyUser p_user, @PS String name);
	
	@RFC (ID = MID_READ_AUTH)
	void ReadAuth(@PU MyUser p_user, @PS String authparams);
	
	@RFC (ID = MID_UPDATE_USER)
	void UpdateUser(@PU MyUser p_user, @PL long userId, 
			@PS String area, @PS String Province,@PS String city, 
			@PS String name, @PS String mail, @PI int isEbl, 
			@PI int groupId, @PS String groupName, @PI int age);
	
	
	@RFC (ID = MID_FINISHED_GATE)
	void FinishedGate(@PU MyUser p_user, @PI int gate, @PI int smallgate);
	
	@RFC (ID = MID_GENERAL_CODE)
	void GeneralCode(@PU MyUser p_user, @PI int groupId,@PI int type, @PI int price, @PI int count);
	
	@RFC (ID = MID_USE_CODE)
	void UseCode(@PU MyUser p_user, @PS String code);
	
	@RFC (ID = MID_TAG_MSG_READ)
	void TagReadMsg(@PU MyUser p_user, @PI int msgid);
	
	@RFC (ID = MID_LOGOUT)
	void LogOut(@PU MyUser p_user);
	
	@RFC (ID = MID_UPDATE_GROUPFIELD)
	void UpdateGroupField(@PU MyUser p_user,
			@PI int groupId, 
			@PS String Area,
			@PS String Province,
			@PS String City,
			@PS String Tel,
			@PS String Addr,
			@PS String Name,
			@PS String Password,
			@PI int Number,
			@PI int Income,
			@PI int ChargeNum,
			@PI int ChargeCount,
			@PI int LostUserCount,
			@PI int AddUserCount,
			@PI int DownloadCount,
			@PI int CardUsedCount1,
			@PI int CardUsedCount2,
			@PI int FreeUsedCount1,
			@PI int Auth,
			@PI int isSelf,
			@PI int Parent);
	
	
	@RFC (ID = MID_MODIFY_USER)
	void ModifyUser(@PU MyUser p_user, @PS String tel, @PS String area,
			@PS String Province, @PS String city, @PS String name,
			@PS String mail, @PI int isEbl, @PI int groupId,
			@PS String groupName, @PI int age);
	
	@RFC (ID = MID_REMOVE_GLOBALMSG)
	void RemoveGlobalMsg(@PU MyUser p_user, @PI int gid);

	@RFC (ID = MID_GET_RECORD_BY_TIME)
	void  GetRecordByTime(@PU MyUser p_user, @PI int userId,@PL long begintime ,@PL long endtime );

	
}
