/**
 * LoginSQLRun.java 2012-6-24上午10:47:42
 */
package logic.sqlrun;

import utility.UniqueID;
import core.*;
import core.db.RoleDataBase;
import core.db.RoleIDUniqueID;
//import core.detail.impl.log.*;
import logic.*;
import logic.module.log.*;
import logic.module.log.sql.*;
import logic.module.login.*;
import logic.userdata.bs_group;
import logic.userdata.bs_user;
import logic.userdata.logindata;

/**
 * @author ddoq
 * @version 1.0.0
 *
 */
public class LoginSQLRun extends MySQLRun
{
	private static logindata m_LoginData = new logindata();	///<这个只当成模板用,没实际效果
	private String m_sUserName;
	private String m_sPassword;
	private String m_sToken;		// 客户端段提交的accesstoken
	private String m_sServerToken;	// 服务器生成的accesstoken
	private boolean m_bForbid;
	private int m_nServerID;
	private int m_nChannelID;
	private String m_sDeviceIdentifier;
	private String m_sDeviceModel;
	private boolean m_bCreate;
	
	private int m_usertype;
	private String m_tel;
	private int m_code;
	private String m_pass;
	private int m_isMember;
	private int m_groupID;
	private String m_groupName;
	private String m_area;
	private String m_province;
	private String m_city;
	private String m_mail;
//	private long stm = 0;
	private static UniqueID m_IDBuild = new UniqueID();
	private static final String m_CreateUserData = "INSERT INTO LoginData (`UserName`,`UserID`, `Password`, `ServerID`, `ChannelID`) VALUES ('%s', %d, '%s', %d, %d)";
	private static final String m_SelectUserData = "SELECT * FROM LoginData WHERE UserName = '%s' AND ServerID = %d";
	private static final String m_group_query = "select * from bs_group where Name = '%s'";
	private static final String m_user_query = "select * from bs_user where Tel = '%s'";
	private static final String m_group_create = "insert into bs_group(RoleID, ID, Area, Province, City,Name, Password,Auth) values (%d, %d,'%s','%s','%s','%s','%s', %d)";//
	private static final String m_user_create = "insert into bs_user(RoleID, UserName, tel, Password, isEb, GroupID, GroupName, Area, Province, City, DriveInfo, Mail) values (%d,'%s', '%s', '%s', %d, %d, '%s', '%s', '%s', '%s','%s','%s')";//
	private static final String m_MaxRoleData = "SELECT * FROM bs_user ORDER BY ROLEID DESC LIMIT 1";
	
	
	//
	public LoginSQLRun(MyUser p_User,String p_sUserName,String p_sPassword, boolean forbid, int serverid)
	{
		m_sUserName = p_sUserName;
		m_sPassword = p_sPassword;
		m_sToken = "";
		m_sServerToken = "";
		m_bForbid = forbid;
		m_nServerID = serverid;
		m_nChannelID = 0;
		m_sDeviceIdentifier = "";
		m_bCreate = false;
	}
	

	
	public LoginSQLRun(MyUser p_user, 
			String p_username,
			String p_password, 
			int userType, 
			String tel,
			int code, 
			String pass, 
			int isMember, 
			int groupID,
			String groupName,
			String Area,
			String Province,
			String city,
			String mail)
	{
		m_sUserName = p_username;
		m_sPassword = p_password;
		m_usertype = userType;
		m_tel = tel;
		m_code = code;
		m_pass = pass;
		m_isMember = isMember;
		m_groupID = groupID;
		m_nChannelID = 0;
		m_groupName = groupName;
		m_area = Area;
		m_province = Province;
		m_city = city;
		m_mail = mail;
		m_bCreate = true;
		
	}
	
	public LoginSQLRun(MyUser p_User,String p_sUserName,String p_sPassword, int userType, int serverid, String deviceIdentifier, String deviceModel, int code, boolean isCreate)
	{
		m_tel = m_sUserName = p_sUserName;
		m_sPassword = p_sPassword;
		m_sToken = "";
		m_sServerToken = "";
		m_usertype = userType;
		m_nServerID = serverid;
		m_nChannelID = 0;
		m_sDeviceIdentifier = deviceIdentifier.substring(0, Math.min(deviceIdentifier.length(), 60));
		m_sDeviceModel = deviceModel.substring(0, Math.min(deviceModel.length(), 60));
		m_bCreate = isCreate;
		m_code = code;
	}
	
	public LoginSQLRun(MyUser p_user, String p_sUserName, String p_sToken, String p_sServerToken, boolean forbid, int serverid, int channelID, String deviceIdentifier, String deviceModel, boolean isCreate)
	{
		m_sUserName = p_sUserName;
		m_sPassword = "";
		m_sToken = p_sToken;
		m_sServerToken = p_sServerToken;
		m_bForbid = forbid;
		m_nServerID = serverid;
		m_nChannelID = channelID;
		m_sDeviceIdentifier = deviceIdentifier.substring(0, Math.min(deviceIdentifier.length(), 60));
		m_sDeviceModel = deviceModel.substring(0, Math.min(deviceModel.length(), 60));
		m_bCreate = isCreate;
	}
	
	/* 
	 * 登录数据查询
	 */
	@Override
	public void Execute(MyUser p_User) throws Exception
	{
		
		if(this.m_usertype == eUserType.USER_ADMIN.ID()){
			_ExecuteAdmin(p_User);
		}else if(this.m_usertype == eUserType.USER_GROUP.ID()){
			_ExecuteGroup(p_User);
		}else if(this.m_usertype == eUserType.USER_PLAYER.ID()){
			_ExecuteUser(p_User);
		}
		
	}
	
	private void _ExecuteUser(MyUser p_User) throws Exception
	{
		bs_user[] ds = DBMgr.ReadSQL(new bs_user(), String.format(m_user_query, m_tel));
		bs_user d = null;
		if (ds.length > 1)
		{
			//读取到多条数据,程序不处理,让玩家找GM协助
			eLoginDebugLogType.LOGINSQL_ERR_CREATE.Log(p_User);
			p_User.setLoginData(null, eLoginErrorCode.UNKNOW);
			return;
		}
		
		else if (ds.length == 1)
		{
			d = ds[0];
		}
		
		_CheckLoginUserByPassword(d, p_User);
		
	}
	
	private void _ExecuteGroup(MyUser p_User) throws Exception
	{
		bs_group[] ds = DBMgr.ReadSQL(new bs_group(), String.format(this.m_group_query, m_sUserName));
		bs_group d = null;
		if (ds.length > 1)
		{
			//读取到多条数据,程序不处理,让玩家找GM协助
			eLoginDebugLogType.LOGINSQL_ERR_CREATE.Log(p_User);
			p_User.setLoginData(null, eLoginErrorCode.UNKNOW);
			return;
		}
		
		else if (ds.length == 1)
		{
			d = ds[0];
		}
		
		_CheckLoginUserByPassword(d, p_User);
	}
	
	private void _ExecuteAdmin(MyUser p_User) throws Exception
	{
		logindata[] ds = DBMgr.ReadSQL(m_LoginData, String.format(m_SelectUserData, m_sUserName, m_nServerID));
		logindata d = null;
		if (ds.length > 1)
		{
			//读取到多条数据,程序不处理,让玩家找GM协助
			eLoginDebugLogType.LOGINSQL_ERR_CREATE.Log(p_User);
			p_User.setLoginData(null, eLoginErrorCode.UNKNOW);
			return;
		}
		
		else if (ds.length == 1)
		{
			d = ds[0];
		}
		
		_CheckLoginUserByPassword(d, p_User);
	}
	
	private void _CheckLoginUserByPassword(RoleDataBase d, MyUser p_User)
	{
		if(d == null)
		{
			// 没有找到用户的数据，用户名尚未注册
			if(!m_bCreate)
			{
				// 没有请求创建新用户数据，则返回用户不存在
				System.out.println("* 用户不存在");
				eLoginDebugLogType.LOGIN_FAIL_USER_NOT_EXIST.Log(p_User);
				p_User.setLoginData(null, eLoginErrorCode.USERNAME_NOTEXIST);
				return;
			}
			else
			{
				// 需要创建用户
				d = _CreateUser(p_User);
				if(d == null)
					return;
			}
		}
		else
		{
			// 找到用户的数据，用户名已经注册
			if(m_bCreate)
			{
				// 如果请求创建新用户数据，则返回用户已被注册
				System.out.println("* 用户名已被注册");
				eLoginDebugLogType.REGISTER_FAIL_USERNAME_EXIST.Log(p_User);
				p_User.setLoginData(null, eLoginErrorCode.USERNAME_ALREADYEXIST);
				return;
			}
			else
			{
				//验证密码
				if(!_CheckPassword(d, p_User)){
					
				/*	 _KillOpposite((MyUser) Root.GetInstance().GetUserByUserName(p_username));*/
					/* if (p_User != null) {
							PackBuffer.GetInstance().Clear().AddID(Reg.LOGIN, 0).Add(eLoginErrorCode.OPPO_KILLED.ID()).Send(p_User);
							p_User.Close(eLogicCloseUserReason.LOGIN_KILL_OPPOSITE.ID(), 0);
						}*/
					
					return;
					}
			}
		}
		// 验证是否封禁
		//if(!_CheckForbid(d, p_User))
		//	return;
		// 一切OK
		//d.LastLoginTime.Set(System.currentTimeMillis());
		if(m_bCreate){
			p_User.setLoginData(null, eLoginErrorCode.REGISTER_OK);
			//_Log(p_User, d, eLoginErrorCode.REGISTER_OK);
		}else{
			p_User.setLoginData(null, eLoginErrorCode.LOGIN_OK);
			//_Log(p_User, d, eLoginErrorCode.LOGIN_OK);
		}

	}
	
	
	private RoleDataBase _CreateUser(MyUser p_User)
	{
		System.out.println("* 没有登陆数据,创建登陆数据");
		boolean c = false;
		RoleIDUniqueID build = DBMgr.GetCreateRoleUniqueID();
		bs_user[] maxrd = DBMgr.ReadSQL(new bs_user(), m_MaxRoleData);
		if (maxrd.length == 0)
		{
			build.SetBaseValue(0);
		}
		else
		{
			build.SetBaseValue(maxrd[0].RoleID.Get());
		}
		long userID = build.Get();
		String nickName = "";
		if(this.m_usertype == eUserType.USER_ADMIN.ID()){
			//如果是管理员注册;
			c = DBMgr.ExecuteSQL(String.format(m_CreateUserData, m_sUserName, userID, m_sPassword, m_nServerID, m_nChannelID));
			nickName = m_sUserName;
		}else if(this.m_usertype == eUserType.USER_GROUP.ID()){
			//如果是盟商注册;
			//todo;先保留出来吧，目前是没有盟商注册的;
			
			
		}else if(this.m_usertype == eUserType.USER_PLAYER.ID()){
			//TODO;如果是会员注册；
			c = DBMgr.ExecuteSQL(String.format(m_user_create, userID, m_sUserName, m_tel, m_sPassword, m_isMember, m_groupID, m_groupName, m_area, m_province, m_city, this.m_sDeviceIdentifier, this.m_mail));
			nickName = m_tel;
		}
		
	
		RoleDataBase d = null;
		if ( c )
			d = _GetLoginData();
		
		if ( !c || d == null )
		{
			System.out.println("* 创建登陆数据失败");
			eLoginDebugLogType.LOGINSQL_ERR_CREATE.Log(p_User);
			p_User.setLoginData(null, eLoginErrorCode.UNKNOW);
		}
		else
		{
			p_User.SetRoleGID(userID);
			p_User.SetNick(nickName);
			Login.GetInstance().SetLoginSuccess(p_User);
			p_User.setUserType(m_usertype);
			System.out.println("* 获取角色,进入世界:" + p_User.GetRoleGID());
			//Root.GetInstance().AttachUser(p_User);
			PackBuffer.GetInstance().Clear().AddID(Reg.LOGIN,2).
			Add(userID).
			Add(nickName).
			Send(p_User);
			
			System.out.println("* 创建登陆数据成功");
			p_User.Log(eLogicSQLLogType.LOGIC_SQL_REGISTER, 
					RootConfig.GetInstance().ServerUniqueID,
					eLogicSQLLogType.GetCurrTime(),
					userID,
					m_sUserName,
					p_User.GetLink().GetIP(),
					m_sDeviceIdentifier,
					m_sDeviceModel,
					1);
		}
		return d;
	}
	
	
	private boolean _CheckPassword(RoleDataBase data, MyUser p_User)
	{
		String pass = "";
		if(data instanceof bs_user){
			bs_user p2 = (bs_user)data;
			pass = p2.Password.Get();
			
		}else if(data instanceof logindata){
			logindata p = (logindata)data;
			pass = p.Password.Get();
			
		}else if(data instanceof bs_group){
			bs_group bg = (bs_group)data;
			pass = bg.Password.Get();
		}
		//todo;
		
		if ( !pass.equals(m_sPassword) && this.m_code == 0 )
		{
			System.out.println("* 密码错误");
			eLoginDebugLogType.LOGINSQL_PASSWORD_CHECK.Log(p_User);
			p_User.setLoginData(null, eLoginErrorCode.PASSWORD_ERR);
			//_Log(p_User, d, eLoginErrorCode.PASSWORD_ERR);
			return false;
		}else{
			//
/*			Login.Check(p_User, p_User.GetUserName(), pass);*/
			System.out.println("* 密码正确(重置)");
			eLoginDebugLogType.LOGINSQL_PASSWORD_OK.Log(p_User);
			long roleId = 0;
			String roleName = "test";
			
			if(data instanceof bs_user){
				bs_user p2 = (bs_user)data;
				p_User.SetRoleGID(p2.RoleID.Get());
				p_User.SetNick(p2.Tel.Get());
				roleName = p2.Tel.Get();
				//Root.GetInstance().AttachUser(p_User);
				roleId = p2.RoleID.Get();
				p_User.setUserType(eUserType.USER_PLAYER.ID());
				if(this.m_code != 0){
					p2.Password.Set(this.m_sPassword);
					DBMgr.UpdateRoleData(p2);
				}
			}
			else if(data instanceof logindata){
				logindata p = (logindata)data;
				p_User.SetRoleGID(p.UserID.Get());
				p_User.SetNick(p.UserName.Get());
				//Root.GetInstance().AttachUser(p_User);
				roleName = p.UserName.Get();
				roleId = p.UserID.Get();
				p_User.setUserType(eUserType.USER_ADMIN.ID());
				if(this.m_code != 0){
					p.Password.Set(this.m_sPassword);
					DBMgr.UpdateRoleData(p);
				}
			}else if( data instanceof bs_group){
				bs_group p = (bs_group)data;
				p_User.SetRoleGID(p.RoleID.Get());
				p_User.SetNick(p.Name.Get());
				//Root.GetInstance().AttachUser(p_User);
				roleName = p.Name.Get();
				roleId = p.RoleID.Get();
				p_User.setUserType(eUserType.USER_GROUP.ID());
			}
			
			System.out.println("* 获取角色,进入世界:" + p_User.GetRoleGID());
			PackBuffer.GetInstance().Clear().AddID(Reg.LOGIN,2).
			Add(roleId).
			Add(roleName).
			Send(p_User);
			return true;
		}
		
	}
	
	
	
	private boolean _CheckForbid(logindata d, MyUser p_User)
	{
		if (m_bForbid)
		{
			d.Forbid.Set(1);
			DBMgr.UpdateUserNameData(d);
//			Log.out.Log(eSystemInfoLogType.SYSTEM_INFO_SQL_STATEMENT, System.currentTimeMillis() - stm, "_CheckForbid DBMgr.UpdateUserNameData");
			eLoginDebugLogType.LOGIN_FORBID_FAIL.Log(p_User);
			p_User.setLoginData(null, eLoginErrorCode.LOGIN_FORBID);
			return false;
		}
		if (d.Forbid.Get() == 0 )
		{
			eLoginDebugLogType.LOGIN_FORBID_OK.Log(p_User);
//			p_User.GetLoginUserData().SetLoginData(d, LoginUserData.eLoginRes.OK);
			return true;
		}
		else
		{
			eLoginDebugLogType.LOGIN_FORBID_FAIL.Log(p_User);
			p_User.setLoginData(null, eLoginErrorCode.LOGIN_FORBID);
			return false;
		}
//		Log.out.Log(eSystemInfoLogType.SYSTEM_INFO_SQL_STATEMENT, System.currentTimeMillis() - stm, "_CheckForbid Finish");
	}
	
	private RoleDataBase _GetLoginData()
	{
		
		if(this.m_usertype == eUserType.USER_ADMIN.ID()){
			logindata[] ds = DBMgr.ReadSQL(m_LoginData, String.format(m_SelectUserData, m_sUserName, m_nServerID));
			if ( ds != null && ds.length == 1 )
			{
				return ds[0];
			}
			else
			{
				return null;
			}
		}else if(this.m_usertype == eUserType.USER_GROUP.ID()){
			
		}else if(this.m_usertype == eUserType.USER_PLAYER.ID()){
			bs_user[] ds = DBMgr.ReadSQL(new bs_user(), String.format(this.m_user_query, this.m_tel));
			if ( ds != null && ds.length == 1 )
			{
				return ds[0];
			}
			else
			{
				return null;
			}
		}
		return null;
		
	}

	private void _Log(MyUser p_User, logindata d, eLoginErrorCode c)
	{
		p_User.Log(eLogicSQLLogType.LOGIC_SQL_LOGIN, 
				RootConfig.GetInstance().ServerUniqueID,
				eLogicSQLLogType.GetCurrTime(),
				d != null ? d.UserID.Get() : 0,
				d != null ? d.UserName.Get() : "",
				p_User.GetLink().GetIP(),
				m_sDeviceIdentifier,
				m_sDeviceModel,
				c.ID());
	}
}
