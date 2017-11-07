package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
*@author niuhao
*@version 0.0.1
*@create by en_mysql_to_class.py
*@time:Jul-08-17 15:26:14
**/
public class bs_cardrecord extends RoleDataBase
{
	public DBInt GID;//

	public DBInt GroupID;//

	public DBLong RoleID;//

	public DBInt Register;
	
	public DBString Code;//

	public DBInt ChargeNum;

	public DBInt ChargeCount;
	
	public DBInt LostUserCount;
	
	public DBInt AddUserCount;
	
	public DBInt DownloadCount;
	
	public DBInt CardUsedCount1;
	
	public DBInt CardUsedCount2;
	
	public DBInt FreeUsedCount1;
	
	public DBInt FreeUsedCount2;
	

	public DBDateTime DateTime;//

	public void packData(SendMsgBuffer buffer){
		buffer.Add(GID.Get());
		buffer.Add(GroupID.Get());
		buffer.Add(RoleID.Get());
		buffer.Add(Register.Get());
		buffer.Add(Code.Get());
		buffer.Add(Code.Get());
		buffer.Add(ChargeNum.Get());
		buffer.Add(ChargeCount.Get());
		buffer.Add(LostUserCount.Get());
		buffer.Add(AddUserCount.Get());
		buffer.Add(DownloadCount.Get());
		buffer.Add(CardUsedCount1.Get());
		buffer.Add(CardUsedCount2.Get());
		buffer.Add(FreeUsedCount1.Get());
		buffer.Add(FreeUsedCount2.Get());
		buffer.Add(DateTime.GetMillis());
	}
}
