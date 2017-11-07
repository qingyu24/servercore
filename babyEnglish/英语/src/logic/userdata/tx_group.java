package logic.userdata;

import core.detail.impl.socket.SendMsgBuffer;

public class tx_group {

	public int GID;

    public long RoleID;//

    public String Area;//地区

    public String Province;//省份

    public String City;//城市

    public String Tel;//电话

    public String Addr;//地址

    public String Name;//名字

    public String Password;//密码

    public int Number;//用户数量

    public float Income;//收入

    public int ChargeNum;//付费人数

    public int ChargeCount;//付费次数   

    public int LostUserCount;//流失用户数

    public int AddUserCount;//新增用户数

    public int DownloadCount;//下载数

    public int CardUsedCount1;//大额兑换卡使用次数

    public int CardUsedCount2;//小额兑换卡使用次数

    public int FreeUsedCount1;//免费卡使用次数

    public int Auth;//权限级别

    public int FreeUsedCount2;

    public int isSelf;

    public int parent;


	public void packData(SendMsgBuffer buffer) {
		buffer.Add(GID);
		buffer.Add(RoleID);
		buffer.Add(Area);
		buffer.Add(Province);
		buffer.Add(City);
		buffer.Add(Tel);
		buffer.Add(Addr);
		buffer.Add(Name);
		buffer.Add(Password);
		buffer.Add(Number);
		buffer.Add(Income);
		buffer.Add(ChargeNum);
		buffer.Add(ChargeCount);
		buffer.Add(LostUserCount);
		buffer.Add(AddUserCount);
		buffer.Add(DownloadCount);
		buffer.Add(CardUsedCount1);
		buffer.Add(CardUsedCount2);
		buffer.Add(FreeUsedCount1);
		buffer.Add(Auth);
		buffer.Add(FreeUsedCount2);
		buffer.Add(isSelf);
		buffer.Add(parent);
	}

	public tx_group(bs_group group) {
		GID = group.GID.Get();
		RoleID = group.RoleID.Get();
		Area = group.Area.Get();
		Province = group.Province.Get();
		City = group.City.Get();
		Tel = group.Tel.Get();
		Addr = group.Addr.Get();
		Name = group.Name.Get();
		Password = group.Password.Get();
		Number = group.Number.Get();
		Income = group.Income.Get();
		ChargeNum = group.ChargeNum.Get();
		ChargeCount = group.ChargeCount.Get();
		LostUserCount = group.LostUserCount.Get();
		AddUserCount = group.AddUserCount.Get();
		DownloadCount = group.DownloadCount.Get();
		CardUsedCount1 = group.CardUsedCount1.Get();
		CardUsedCount2 = group.CardUsedCount2.Get();
		FreeUsedCount1 = group.FreeUsedCount1.Get();
		Auth = group.Auth.Get();
		FreeUsedCount2 = group.FreeUsedCount2.Get();
		isSelf = group.isSelf.Get();
		parent = group.Parent.Get();
	}

	@Override
	public String toString() {
		return "tx_group [GID=" + GID + ", RoleID=" + RoleID + ", Area=" + Area + ", Province=" + Province + ", City="
				+ City + ", Tel=" + Tel + ", Addr=" + Addr + ", Name=" + Name + ", Password=" + Password + ", Number="
				+ Number + ", Income=" + Income + ", ChargeNum=" + ChargeNum + ", ChargeCount=" + ChargeCount
				+ ", LostUserCount=" + LostUserCount + ", AddUserCount=" + AddUserCount + ", DownloadCount="
				+ DownloadCount + ", CardUsedCount1=" + CardUsedCount1 + ", CardUsedCount2=" + CardUsedCount2
				+ ", FreeUsedCount1=" + FreeUsedCount1 + ", Auth=" + Auth + ", FreeUsedCount2=" + FreeUsedCount2
				+ ", isSelf=" + isSelf + ", parent=" + parent + "]";
	}
	
	
}
