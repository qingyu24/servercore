package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
*@author niuhao
*@version 0.0.1
*@create by en_mysql_to_class.py
*@time:Jul-08-17 15:26:15
**/
public class logindata extends RoleDataBase
{
	public DBString UserName;//

	public DBLong UserID;//

	public DBString Password;//

	public DBDateTime LastLoginTime;//

	public DBInt Forbid;//

	public DBInt ServerID;//

	public DBInt ChannelID;//

	public void packData(SendMsgBuffer buffer){
		buffer.Add(UserName.Get());
		buffer.Add(UserID.Get());
		buffer.Add(Password.Get());
		buffer.Add(LastLoginTime.GetMillis());
		buffer.Add(Forbid.Get());
		buffer.Add(ServerID.Get());
		buffer.Add(ChannelID.Get());
	}
}
