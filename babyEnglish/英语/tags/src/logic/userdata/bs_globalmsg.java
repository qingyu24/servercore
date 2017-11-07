package logic.userdata;
import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;
/**
*@author niuhao
*@version 0.0.1
*@create by en_mysql_to_class.py
*@time:Jul-08-17 15:26:15
**/
public class bs_globalmsg extends RoleDataBase
{
	public DBInt GID;//

	public DBString Title;//

	public DBString Content;//

	public DBInt Type;//

	public DBDateTime DateTime;//

	public DBInt IsRead;//

	public DBInt IsTop;//是否置顶

	public DBDateTime TopTime;//置顶的结束时间

	public DBDateTime ShowTime;//发送时间

	public DBString Area;//地区

	public DBInt GroupId;//

	public DBString Linkurl;//

	public DBString Imgurl;//

	public void packData(SendMsgBuffer buffer){
		buffer.Add(GID.Get());
		buffer.Add(Title.Get());
		buffer.Add(Content.Get());
		buffer.Add(Type.Get());
		buffer.Add(DateTime.GetMillis());
		buffer.Add(IsRead.Get());
		buffer.Add(IsTop.Get());
		buffer.Add(TopTime.GetMillis());
		buffer.Add(ShowTime.GetMillis());
		buffer.Add(Area.Get());
		buffer.Add(GroupId.Get());
		buffer.Add(Linkurl.Get());
		buffer.Add(Imgurl.Get());
	}
}
