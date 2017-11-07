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
	/**
	 * public class GroupMessage
	{
		public long RoleID;//

		public int ID;//编号

		public string Area;//地区

		public string Province;//省份

		public string City;//城市
        public string Tel;//电话
        public string Addr;//地址
		public string Name;//名字

		public int Number;//用户数量

		public int Income;//收入

		public int ChargeNum;//付费人数

		public int ChargeCount;//付费次数

		public int LostUserCount;//流失用户数

		public int AddUserCount;//新增用户数

		public int DownloadCount;//下载数

		public int CardUsedCount1;//大额兑换卡使用次数

		public int CardUsedCount2;//小额兑换卡使用次数

		public int FreeUsedCount;//免费卡使用次数

		public int Auth;//权限级别

        public int Parent;//父级GroupID
	}
	 * @param buffer
	 */

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
