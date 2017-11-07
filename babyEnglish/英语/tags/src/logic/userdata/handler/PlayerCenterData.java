package logic.userdata.handler;

import java.util.ArrayList;
import java.util.Iterator;

import utility.TimeMethod;

import logic.MyUser;
import logic.eUserType;
import logic.config.PlayerInitIni;
import logic.loader.GlobalLoader;
import logic.loader.GroupLoader;
import logic.loader.RecordLoader;
import logic.loader.UserLoader;
import logic.sqlrun.MySQLRun;
import logic.userdata.bs_auth;
import logic.userdata.bs_cardrecord;
import logic.userdata.bs_global;
import logic.userdata.bs_group;
import logic.userdata.bs_message;
import logic.userdata.bs_user;
import manager.ConfigManager;
import manager.LoaderManager;
import core.DBMgr;
import core.SQLRun;
import core.UserData;
import core.db.RoleIDUniqueID;
import core.detail.impl.OnlineUserSelector;
import core.detail.impl.socket.SendMsgBuffer;
import core.remote.PI;
import core.remote.PL;
import core.remote.PS;
import utility.TimeMethod;;
public class PlayerCenterData implements UserData {

	private boolean m_dataReady;
	private MyUser m_user;
	private bs_user m_users;
	private ArrayList<bs_message> m_msgs = new ArrayList<bs_message>();
	//private ArrayList<bs_user> m_allusers = new ArrayList<bs_user>();
	private bs_group m_group;
	private static final String m_MaxRoleData = "SELECT * FROM bs_user ORDER BY ROLEID DESC LIMIT 1";
	private static final String m_user_create = "insert into bs_user(RoleID, Tel, Password, isEb, GroupID, Money, GroupName, Area, Province, city) values (%d, '%s', '%s', %d, %d, %d, '%s', '%s', '%s', '%s')";//
	private static final String m_initAuth = "update bs_auth set AuthContent='%s' where AuthLevel=1";
	private static final String m_readAuth = "select AuthContent form bs_auth where AuthLevel=1";
	
	private static final String m_MaxGroupData = "SELECT * FROM bs_group ORDER BY ROLEID DESC LIMIT 1";
	private static final String m_group_create = "insert into bs_group(RoleID, Name, Tel, Password, Area, Province, City, Auth, Parent) values (%d, '%s', '%s', '%s', '%s', '%s', '%s', %d, %d)";//
	private static final String m_group_remove = "delete from bs_group where GID=%d";//

	private String[] gates1 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates2 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates3 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates4 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates5 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates6 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates7 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates8 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates9 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private String[] gates10 = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
	private ArrayList<String[]> gates = new ArrayList<String[]>();
	public PlayerCenterData(MyUser user){
		this.m_user = user;
	}
	
	private int m_gid;
	public int getGid(){
		int baseId = m_user.getBaseRoleID(m_gid);
		m_gid ++;
		return baseId;
	}
	
	public int getGroupId(){
		return this.m_users.GroupID.Get();
	}
	
	public void packData(SendMsgBuffer buffer){
		if(this.m_user.getUserType() == eUserType.USER_PLAYER.ID()){
			this.m_users.packData(buffer,true);
		}
		
		if(this.m_user.getUserType() == eUserType.USER_ADMIN.ID()){
			buffer.Add(1L);
		}
		if(this.m_user.getUserType() == eUserType.USER_GROUP.ID()){
			this.m_group.packData(buffer);
		}
		
	}
	
	public void packMessage(SendMsgBuffer buffer){
		buffer.Add((short)m_msgs.size());
		Iterator<bs_message> it = m_msgs.iterator();
		while(it.hasNext()){
			bs_message obj = it.next();
			obj.packData(buffer);
		}
	}
	
	public void addPersonalMsg(String title, String content){
		bs_message msg = new bs_message();
		msg = DBMgr.CreateRoleData(this.m_user.GetRoleGID(), msg);
		//msg.GID.Set(getGid());
		msg.DateTime.Set(System.currentTimeMillis());
		msg.Title.Set(title);
		msg.Content.Set(content);
		m_msgs.add(msg);
		
		
	}
	
	public boolean queryCode(String code){
		GlobalLoader loader = (GlobalLoader) LoaderManager.getInstance().getLoader(LoaderManager.Global);
		return loader.queryCode(code);
	}
	
	public String getAboutUs(){
		GlobalLoader loader = (GlobalLoader) LoaderManager.getInstance().getLoader(LoaderManager.Global);
		
		return loader.getContact();
	}
	
	public void updateUserInfo(String mail){
		this.m_users.Mail.Set(mail);
	}
	
	public String getPass(){
		return this.m_users.Password.Get();
	}
	
	public void changePass(String pass){
		this.m_users.Password.Set(pass);
	}
	
	public int getCoin(){
		return this.m_users.Money.Get();
	}
	
	public String getGate(){
		return this.m_users.Gate.Get();
	}
	
	public int changeCoin(int val){
		int curr = this.m_users.Money.Get();
		curr += val;
		this.m_users.Money.Set(curr);
		return curr;
	}
	
	public int getCoin(long userId){
		UserLoader loader = (UserLoader)LoaderManager.getInstance().getLoader(LoaderManager.Users);
		bs_user it = loader.getUser(userId);
		if(null != it){
			return it.Money.Get();
		}
		return 0;
	}
	
	public boolean hasShare(){
		return TimeMethod.IsToday(this.m_users.ShareTime.GetMillis());
	}
	
	public int updateShareTime(){
		int old = this.m_users.Money.Get();
		if(!this.hasShare()){
			this.m_users.ShareTime.Set(System.currentTimeMillis());
			this.m_users.Money.Set(this.m_users.Money.Get() + 20);
		}
		return this.m_users.Money.Get() - old;
		
	}
	
	public void changeCardUsedCount(int type, int count){
		GroupLoader loader = (GroupLoader) LoaderManager.getInstance().getLoader(LoaderManager.Group);
		RecordLoader rloader = (RecordLoader) LoaderManager.getInstance().getLoader(LoaderManager.Record);
		bs_cardrecord rc = rloader.createRecord(this.m_user);
		
		bs_group group = loader.getGroup(m_users.GroupID.Get());
		switch(type){
		case 1:
			this.m_users.CardUsedCount1.Set(this.m_users.CardUsedCount1.Get() + count);
			if(null != group){
				group.CardUsedCount1.Set(this.m_users.CardUsedCount1.Get() + count);
			}
			rc.CardUsedCount1.Set(1);
		break;
		case 2:
			this.m_users.CardUsedCount2.Set(this.m_users.CardUsedCount2.Get() + count);
			if(null != group){
				group.CardUsedCount2.Set(this.m_users.CardUsedCount2.Get() + count);
			}
			rc.CardUsedCount2.Set(1);
		break;
		case 3:
			this.m_users.FreeUsedCount1.Set(this.m_users.FreeUsedCount1.Get() + count);
			if(null != group){
				group.FreeUsedCount1.Set(this.m_users.FreeUsedCount1.Get() + count);
			}
			rc.FreeUsedCount1.Set(1);
		break;
		}
		
		loader.updateIncome(m_users.GroupID.Get());
		
	}
	
	public String unlockSmallGate(int gate, int smallgate){
		String g = this.m_users.Gate.Get();
		String ret = null;
		if(g == null || g == ""){
			g = str2;
			
		}
		int price = 120;
		if(gate == 2){
			price = 240;
		}
		if(price > this.m_users.Money.Get()){
			return null;
		}
		if(gates.get(gate)[smallgate] == "2"){
			return null;
		}
		gates.get(gate)[smallgate] = "1";
		
		String[] l = g.split("[|]");
		if(l.length > gate){
			String[] subs = l[gate].split("[,]");
			subs[smallgate] = "1";
			String substr = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", subs);
			l[gate] = substr;
			ret = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s", l);
			this.m_users.Gate.Set(ret);
		}
		return ret;
	}
	
	public String finishedSmallGate(int gate, int smallgate){
		String g = this.m_users.Gate.Get();
		String ret = null;
		gates.get(gate)[smallgate] = "2";
		if(null == g || "" == g){
			g = str2;
		}
		String[] l = g.split("[|]");
		if(l.length > gate){
			String[] subs = l[gate].split("[,]");
			subs[smallgate] = "2";
			String substr = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", subs);
			l[gate] = substr;
			ret = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s", l);
			this.m_users.Gate.Set(ret);
		}
		this.m_users.Money.Set(this.m_users.Money.Get() + 5);
		return ret;
	}
	
	private static String str = 
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1|" +
			"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1";
	
	private static String str2 = 
			"1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0|" +
			"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
	private static String str3 = 
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s|" +
			"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s";
			
	public int unlockGate(int gate){
		PlayerInitIni ini = (PlayerInitIni)ConfigManager.getInstance().getConfig(ConfigManager.PlayerInitIni);
		int ret = 0;
		if(gate == -1){ //解锁所有的关卡;
			int total = ini.getTotalCost();
			if(total > this.m_users.Money.Get()){
				ret = 0;
			}else{
				this.changeCoin(-total);
				ret = total;
				String str = "";
				for(int i = 0; i < 10; ++ i){
					this.checkFinishGate(i);
					str += this.sezGate(i) + "|";
				}
				
				this.m_users.Gate.Set(str.substring(0, str.length() - 1));
			}
		}else{//解锁某一个大关;
			int price = ini.getCostByGate(gate);
			if(price > this.m_users.Money.Get()){
				ret = 0;
			}else{
				ret = price;
				this.changeCoin(-price);
				String g = this.m_users.Gate.Get();
				if(g == null || g == ""){
					g = str2;
				}
				this.checkFinishGate(gate);
				
				String[] l = g.split("[|]");
				if(l.length > gate){
					l[gate] = this.sezGate(gate);
				}
				String r = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s", l);
				this.m_users.Gate.Set(r.substring(0, r.length()));
			}
		}
		
		return ret;
	}
	
	public String sezGate(int gate){
		String ret = "";
		for(int i = 0; i < this.gates.get(gate).length; ++ i){
			ret += this.gates.get(gate)[i].toString() + ",";
		}
		return ret.substring(0, ret.length() - 1);
	}
	
	public void checkFinishGate(int gate){
		for(int i = 0; i < this.gates.get(gate).length; ++ i){
			if(this.gates.get(gate)[i].equals("0")){
				this.gates.get(gate)[i] = "1";
			}
		}
	}
	
	public int addGroup(String area, String p, String city, String name, String pass, int authLevel, int parent){
		RoleIDUniqueID build = DBMgr.GetCreateRoleUniqueID();
		bs_group[] maxrd = DBMgr.ReadSQL(new bs_group(), m_MaxGroupData);
		if (maxrd.length == 0)
		{
			build.SetBaseValue(0);
		}
		else
		{
			build.SetBaseValue(maxrd[0].RoleID.Get());
		}
		long userID = build.Get();
		
		boolean ret = DBMgr.ExecuteSQL(String.format(m_group_create, userID, name, "", pass, area, p, city, authLevel, parent));
		if(ret){
			GroupLoader loader1 = (GroupLoader)LoaderManager.getInstance().getLoader(LoaderManager.Group);
			bs_group[] user = DBMgr.ReadRoleIDData(userID, new bs_group());
			loader1.addGroup(user[0]);
			return user[0].GID.Get();
		}
		return 0;
	}
	
	public void removeGroup(int gid){
		GroupLoader loader = (GroupLoader)LoaderManager.getInstance().getLoader(LoaderManager.Group);
		boolean ret = DBMgr.ExecuteSQL(String.format(m_group_remove, gid));
		if(ret){
			loader.removeGroup(gid);
		}
		
	}
	
	//更新bs_group表里面的指定的字段;
	public void updateGroupFieldByName(int gid, String fieldname, String value){
		GroupLoader loader = (GroupLoader)LoaderManager.getInstance().getLoader(LoaderManager.Group);
		bs_group it = loader.getGroup(gid);
		
		switch(fieldname){
		case "Area":
			it.Area.Set(value);
			break;
		case "Province":
			it.Province.Set(value);
			break;
		case "City":
			it.City.Set(value);
			break;
		case "Tel":
			it.Tel.Set(value);
			break;
		case "Addr":
			it.Addr.Set(value);
			break;
		case "Name":
			it.Name.Set(value);
			break;
		case "Password":
			it.Password.Set(value);
			break;
		case "Number":
			it.Number.Set(Integer.getInteger(value));
			break;
		case "Income":
			it.Income.Set(Integer.getInteger(value));
			break;
		case "ChargeNum":
			it.ChargeNum.Set(Integer.getInteger(value));
			break;
		case "ChargeCount":
			it.ChargeCount.Set(Integer.getInteger(value));
			break;
		case "LostUserCount":
			it.LostUserCount.Set(Integer.getInteger(value));
			break;
		case "AddUserCount":
			it.AddUserCount.Set(Integer.getInteger(value));
			break;
		case "DownloadCount":
			it.DownloadCount.Set(Integer.getInteger(value));
			break;
		case "CardUsedCount1":
			it.CardUsedCount1.Set(Integer.getInteger(value));
			break;
		case "CardUsedCount2":
			it.CardUsedCount2.Set(Integer.getInteger(value));
			break;
		case "FreeUsedCount1":
			it.FreeUsedCount1.Set(Integer.getInteger(value));
			break;
		case "Auth":
			it.Auth.Set(Integer.getInteger(value));
			break;
		case "isSelf":
			it.isSelf.Set(Integer.getInteger(value));
			break;
		case "Parent":
			it.Parent.Set(Integer.getInteger(value));
			break;
		}
	}
	
	public void updateGroupFields(int gid, 
	String Area,
	String Province,
	String City,
	String Tel,
	String Addr,
	String Name,
	String Password,
	int Number,
	int Income,
	int ChargeNum,
	int ChargeCount,
	int LostUserCount,
	int AddUserCount,
	int DownloadCount,
	int CardUsedCount1,
	int CardUsedCount2,
	int FreeUsedCount1,
	int Auth,
	int isSelf,
	int Parent){
		GroupLoader loader = (GroupLoader)LoaderManager.getInstance().getLoader(LoaderManager.Group);
		bs_group it = loader.getGroup(gid);
		if(null != it){
			it.Area.Set(Area);
			it.Province.Set(Province);
			it.City.Set(City);
			it.Tel.Set(Tel);
			it.Addr.Set(Addr);
			it.Name.Set(Name);
			it.Password.Set(Password);
			it.Number.Set(Number);
			it.Income.Set(Income);
			it.ChargeNum.Set(ChargeNum);
			it.ChargeCount.Set(ChargeCount);
			it.LostUserCount.Set(LostUserCount);
			it.AddUserCount.Set(AddUserCount);
			it.DownloadCount.Set(DownloadCount);
			it.CardUsedCount1.Set(CardUsedCount1);
			it.CardUsedCount2.Set(CardUsedCount2);
			it.FreeUsedCount1.Set(FreeUsedCount1);
			it.Auth.Set(Auth);
			it.isSelf.Set(isSelf);
			it.Parent.Set(Parent);
		}
		
	}
	
	public void updateGroup(int gid, String area,
			String Province, String city, String name,
			String pass, int anthLevel){
		GroupLoader loader = (GroupLoader)LoaderManager.getInstance().getLoader(LoaderManager.Group);
		bs_group it = loader.getGroup(gid);
		it.Area.Set(area);
		it.Province.Set(Province);
		it.City.Set(city);
		it.Name.Set(name);
		it.Password.Set(pass);
		it.Auth.Set(anthLevel);
		
	}
	
	public boolean modifyUser(long userId, String area,
			String Province, String city, @PS String name,
			String mail, int isEbl, int groupId,
			String groupName, int age){
		
		UserLoader loader = (UserLoader)LoaderManager.getInstance().getLoader(LoaderManager.Users);
		bs_user it = loader.getUser(userId);
		if(null != it){
			it.Area.Set(area);
			it.Province.Set(Province);
			it.City.Set(city);
			it.UserName.Set(name);
			it.Mail.Set(mail);
			it.GroupID.Set(groupId);
			it.GroupName.Set(groupName);
			it.IsEb.Set(isEbl);
			it.Age.Set(age);
			return true;
		}
		return false;
		
	}
	
	public int updateUser(long userId, String area,
			String Province, String city, @PS String name,
			String mail, int isEbl, int groupId,
			String groupName, int age){
		
		UserLoader loader = (UserLoader)LoaderManager.getInstance().getLoader(LoaderManager.Users);
		bs_user it = loader.getUser(userId);
		boolean ret1 = false;
		boolean ret2 = false;
		int oldGroupId = -1;
		if(null != it){
			oldGroupId = it.GroupID.Get();
			ret1 = //it.Area.Get().isEmpty() || 
					//it.Province.Get().isEmpty() || 
					//it.City.Get().isEmpty() || 
					it.UserName.Get().isEmpty() || 
					it.Mail.Get().isEmpty() ||
					/*it.GroupID.Get() == 0 ||*/
					//it.GroupName.Get().isEmpty() ||
					it.Age.Get() == 0;
			it.Area.Set(area);
			it.Province.Set(Province);
			it.City.Set(city);
			it.UserName.Set(name);
			it.Mail.Set(mail);
			it.GroupID.Set(groupId);
			it.GroupName.Set(groupName);
			it.IsEb.Set(isEbl);
			it.Age.Set(age);
		}
		if(this.m_users.RoleID.Get() == userId){
			ret1 = //m_users.Area.Get().isEmpty() || 
					//m_users.Province.Get().isEmpty() || 
					//m_users.City.Get().isEmpty() || 
					m_users.UserName.Get().isEmpty() || 
					m_users.Mail.Get().isEmpty() ||
					//m_users.GroupID.Get() == 0 ||
					//m_users.GroupName.Get().isEmpty() ||
					m_users.Age.Get() == 0;
			oldGroupId = m_users.GroupID.Get();
			m_users.Area.Set(area);
			m_users.Province.Set(Province);
			m_users.City.Set(city);
			m_users.UserName.Set(name);
			m_users.Mail.Set(mail);
			m_users.GroupID.Set(groupId);
			m_users.GroupName.Set(groupName);
			m_users.IsEb.Set(isEbl);
			m_users.Age.Set(age);
			ret2 = //m_users.Area.Get().isEmpty() || 
					////m_users.Province.Get().isEmpty() || 
					//m_users.City.Get().isEmpty() || 
					m_users.UserName.Get().isEmpty() || 
					m_users.Mail.Get().isEmpty() ||
					//m_users.GroupID.Get() == 0 ||
					//m_users.GroupName.Get().isEmpty() ||
					m_users.Age.Get() == 0;
		}
		if(null != it){
			ret2 = //it.Area.Get().isEmpty() || 
					//it.Province.Get().isEmpty() || 
					//it.City.Get().isEmpty() || 
					it.UserName.Get().isEmpty() || 
					it.Mail.Get().isEmpty() ||
					//it.GroupID.Get() == 0 ||
					//it.GroupName.Get().isEmpty() ||
					it.Age.Get() == 0;
		}
		
		if(ret1 && !ret2){
			this.m_users.Money.Set(this.m_users.Money.Get() + 10);
		}
		return oldGroupId;
	}
	
	
	
//	public void packUserList(SendMsgBuffer buffer, int page, int groupId){
//		ArrayList<bs_user> list = new ArrayList<bs_user>();
//		UserLoader loader = (UserLoader)LoaderManager.getInstance().getLoader(LoaderManager.Users);
//		loader.packData(buffer)
//		for(int i = 0; i < this.m_allusers.size(); ++ i){
//			if(this.m_allusers.get(i).GroupID.Get() == groupId){
//				list.add(this.m_allusers.get(i));
//			}
//			
//		}
//		
//		float pageCount = 10f;
//		int count = 10;
//		int maxPage = (int)(list.size() / pageCount);
//		float temp = list.size() / pageCount;
//	
//		if(temp > maxPage){
//			maxPage += 1;
//		}
//		if(page == maxPage - 1){
//			count = (int) (list.size() % pageCount);
//		}
//		//buffer.Add((short)count);
//		buffer.Add((short)1);
//		//if(page < maxPage)
//		{
//			for(int i = 0; i < list.size(); ++ i){
//				list.get(i).packData(buffer);
//				
//			}
//		}
//		
//	}
	
	public long addUser(String tel, String mail,
			String area, String name, String pass, int money,
			int groupId, String groupName, String province, String city){
		
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
		
		boolean ret = DBMgr.ExecuteSQL(String.format(m_user_create, userID, tel, pass, 0, groupId, money,groupName, area, province, city));
		if(ret){
			UserLoader loader = (UserLoader)LoaderManager.getInstance().getLoader(LoaderManager.Users);
			bs_user[] user = DBMgr.ReadRoleData(userID, new bs_user());
			loader.addUser(user[0]);
			
		}else{
			return 0L;
		}
		return userID;
	}
	
	public void changeUserMoney(long uid, int money){
		//首先判断他有没有权限;
		UserLoader loader = (UserLoader)LoaderManager.getInstance().getLoader(LoaderManager.Users);
		bs_user user = loader.getUser(uid);
		if(null != user){
			user.Money.Set(user.Money.Get() + money);
		}
	}
	
	public boolean initAuth(String content){
		boolean ret = DBMgr.ExecuteSQL(String.format(m_initAuth, content));
		return ret;
	}
	
	public boolean updateAuth(String content){
		return initAuth(content);
	}
	
	public String readAuth(){
		bs_auth[] ret = DBMgr.ReadAllData(new bs_auth());
		return ret[0].AuthContent.Get();
	}
	
	@Override 
	public boolean DataReady() throws Exception {
		// TODO Auto-generated method stub
		return this.m_dataReady;
	}

	@Override
	public SQLRun GetSQLRun() throws Exception {
		// TODO Auto-generated method stub
		return new PlayerSqlRun();
	}

	@Override
	public void SaveToDB() throws Exception {
		// TODO Auto-generated method stub
		if(this.m_users != null){
			DBMgr.UpdateRoleData(this.m_users);
		}
		Iterator<bs_message> it = this.m_msgs.iterator();
		while(it.hasNext()){
			bs_message msg = it.next();
			DBMgr.UpdateRoleData(msg);
		}
		
		
	}
	
	
	public class PlayerSqlRun extends MySQLRun
	{
		@Override
		public void Execute(MyUser p_User) throws Exception {
			// TODO Auto-generated method stub
			m_dataReady = false;
			if(p_User.getUserType() == eUserType.USER_PLAYER.ID()){
				//如果是普通用户登录;
				long gid = p_User.GetRoleGID();
				bs_user[] sign = DBMgr.ReadRoleIDData(gid, new bs_user());
				m_gid = sign.length;
				if(sign.length == 0){
					
				}else{
					m_users = sign[0];
					
					if(m_users.RegTime.GetMillis() == 0){
						m_users.RegTime.Set(System.currentTimeMillis());
						m_users.LoginTime.Set(System.currentTimeMillis());
						m_users.LoginDays.Set(m_users.LoginDays.Get() + 1);
						m_users.Money.Set(125);
						GroupLoader loader = (GroupLoader) LoaderManager.getInstance().getLoader(LoaderManager.Group);
						loader.updateUserCount(m_users.GroupID.Get(), 1);
						m_users.isGetAward.Set(System.currentTimeMillis());
						RecordLoader rloader = (RecordLoader) LoaderManager.getInstance().getLoader(LoaderManager.Record);
						bs_cardrecord cr = rloader.createRecord(p_User);
						cr.Register.Set(1);
						cr.DateTime.Set(System.currentTimeMillis());
						//给注册的奖励;
						UserLoader uloader = (UserLoader)LoaderManager.getInstance().getLoader(LoaderManager.Users);
						uloader.addUser(m_users);
					}else{
						if(!TimeMethod.IsToday(m_users.isGetAward.GetMillis())){
							m_users.Money.Set(m_users.Money.Get() + 5);
							m_users.isGetAward.Set(System.currentTimeMillis());
						}
						if(!TimeMethod.IsToday(m_users.LoginTime.GetMillis())){
							m_users.LoginDays.Set(m_users.LoginDays.Get() + 1);
							m_users.LoginTime.Set(System.currentTimeMillis());
						}
					}
					//给登录的奖励
					
					String g = m_users.Gate.Get();
					if(g != null && g != ""){
						String[] l = g.split("[|]");
						for(int i = 0; i < l.length; ++i){
							gates.add(l[i].split("[,]"));
						}
					}else{
						for(int i = 0; i < 10; ++ i){
							gates.add(gates1);
						}
					}
					//

				}
			}
			if(p_User.getUserType() == eUserType.USER_ADMIN.ID()){
				//如果是管理员登录;
				long gid = p_User.GetRoleGID();
				bs_group[] groups = DBMgr.ReadAllData(new bs_group());
				m_gid = groups.length;
				
				//读取所有的用户的数据;
				/*
				bs_user[] users = DBMgr.ReadAllData(new bs_user());
				for(int i = 0; i < users.length; ++ i){
					m_allusers.add(users[i]);
				}
				*/
				
				
			}
			if(p_User.getUserType() == eUserType.USER_GROUP.ID()){
				//如果是盟商登录;
				long gid = p_User.GetRoleGID();
				bs_group[] g = DBMgr.ReadRoleIDData(gid, new bs_group());
				if(g.length > 0){
					m_group = g[0];
				}
				/*
				bs_user[] users = DBMgr.ReadAllData(new bs_user());
				for(int i = 0; i < users.length; ++ i){
					//if(users[i].GroupID.Get() == m_group.GID.Get())
					{
						m_allusers.add(users[i]);
					}
					
				}
				*/
			}
			
//			bs_message[] msgs = DBMgr.ReadRoleIDData(gid, new bs_message());
//			m_gid = msgs.length;
			m_dataReady = true;
		}
	}

}
