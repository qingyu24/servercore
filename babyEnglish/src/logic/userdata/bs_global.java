package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
*@author niuhao
*@version 0.0.1
*@create by en_mysql_to_class.py
*@time:Jul-08-17 15:26:15
**/
public class bs_global extends RoleDataBase
{
	public DBString AboutUs;//

	public DBString Code;//

	public DBString FileUrl;//

	public DBString AdsUrl;//

	public DBString CoverUrl;//

	public DBString LinkUrl;//

	public void packData(SendMsgBuffer buffer){
		buffer.Add(AboutUs.Get());
		buffer.Add(Code.Get());
		buffer.Add(FileUrl.Get());
		buffer.Add(AdsUrl.Get());
		buffer.Add(CoverUrl.Get());
		buffer.Add(LinkUrl.Get());
	}
}
