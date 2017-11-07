package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
*@author niuhao
*@version 0.0.1
*@create by en_mysql_to_class.py
*@time:Jul-08-17 15:26:15
**/
public class bs_code extends RoleDataBase
{
	public DBInt GID;//

	public DBInt GroupID;//

	public DBInt Type;//

	public DBInt Price;//

	public DBString Code;//

	public DBInt Used;//

	public void packData(SendMsgBuffer buffer){
		buffer.Add(GID.Get());
		buffer.Add(GroupID.Get());
		buffer.Add(Type.Get());
		buffer.Add(Price.Get());
		buffer.Add(Code.Get());
		buffer.Add(Used.Get());
	}
}
