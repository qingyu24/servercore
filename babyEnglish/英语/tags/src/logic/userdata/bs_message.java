package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
*@author niuhao
*@version 0.0.1
*@create by en_mysql_to_class.py
*@time:Jul-08-17 15:26:15
**/
public class bs_message extends RoleDataBase
{
	public DBInt GID;//

	public DBLong RoleID;//

	public DBString Title;//

	public DBString Content;//

	public DBInt Type;//

	public DBDateTime DateTime;//消息的创建时间

	public void packData(SendMsgBuffer buffer){
		buffer.Add(GID.Get());
		buffer.Add(RoleID.Get());
		buffer.Add(Title.Get());
		buffer.Add(Content.Get());
		buffer.Add(Type.Get());
		buffer.Add(DateTime.GetMillis());
	}
}
