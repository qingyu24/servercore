package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
 *@author niuhao
 *@version 0.0.1
 *@create by en_mysql_to_class.py
 *@time:Jul-08-17 15:26:15
 **/
public class bs_group extends RoleDataBase
{
	public DBInt GID;//编号

	public DBLong RoleID;//

	public DBString Area;//地区

	public DBString Province;//省份

	public DBString City;//城市

	public DBString Tel;//

	public DBString Addr;//

	public DBString Name;//名字

	public DBString Password;//密码

	public DBInt Number;//用户数量

	public DBInt Income;//收入

	public DBInt ChargeNum;//付费人数

	public DBInt ChargeCount;//付费次数

	public DBInt LostUserCount;//流失用户数

	public DBInt AddUserCount;//新增用户数

	public DBInt DownloadCount;//下载数

	public DBInt CardUsedCount1;//大额兑换卡使用次数

	public DBInt CardUsedCount2;//小额兑换卡使用次数

	public DBInt FreeUsedCount1;//大额免费卡使用次数

	public DBInt Auth;//权限级别

	public DBInt FreeUsedCount2;//小额免费卡使用次数

	public DBInt isSelf;//是否是直营店

	public DBInt Parent;//

	public void packData(SendMsgBuffer buffer){
		buffer.Add(GID.Get());
		buffer.Add(RoleID.Get());
		buffer.Add(Area.Get());
		buffer.Add(Province.Get());
		buffer.Add(City.Get());
		buffer.Add(Tel.Get());
		buffer.Add(Addr.Get());
		buffer.Add(Name.Get());
		buffer.Add(Password.Get());
		buffer.Add(Number.Get());
		buffer.Add(Income.Get());
		buffer.Add(ChargeNum.Get());
		buffer.Add(ChargeCount.Get());
		buffer.Add(LostUserCount.Get());
		buffer.Add(AddUserCount.Get());
		buffer.Add(DownloadCount.Get());
		buffer.Add(CardUsedCount1.Get());
		buffer.Add(CardUsedCount2.Get());
		buffer.Add(FreeUsedCount1.Get());
		buffer.Add(Auth.Get());
		buffer.Add(FreeUsedCount2.Get());
		buffer.Add(isSelf.Get());
		buffer.Add(Parent.Get());
	}

	
}
