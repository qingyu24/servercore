package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
*@author niuhao
*@version 0.0.1
*@create by en_mysql_to_class.py
*@time:Jul-08-17 15:26:14
**/
public class bs_auth extends RoleDataBase
{
	public DBInt AuthLevel;//

	public DBString AuthContent;//

	public void packData(SendMsgBuffer buffer){
		buffer.Add(AuthLevel.Get());
		buffer.Add(AuthContent.Get());
	}
}
