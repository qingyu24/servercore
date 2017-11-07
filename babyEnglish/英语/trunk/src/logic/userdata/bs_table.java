package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
*@author niuhao
*@version 0.0.1
*@create by en_mysql_to_class.py
*@time:Jul-08-17 15:26:15
**/
public class bs_table extends RoleDataBase
{
	public DBString Table;//

	public void packData(SendMsgBuffer buffer){
		buffer.Add(Table.Get());
	}
}
