package logic.userdata;

import core.detail.impl.socket.SendMsgBuffer;
import core.db.*;

/**
 * @author niuhao
 * @version 0.0.1
 * @create by en_mysql_to_class.py
 * @time:Jul-08-17 15:26:15
 **/
public class bs_user extends RoleDataBase {
	public DBLong RoleID;//

	public DBString Tel;// 电话

	public DBString Mail;//

	public DBInt Age;//

	public DBString UserName;// 孩子的名字

	public DBString Password;// 密码

	public DBString Area;//

	public DBString Province;// 省份

	public DBString City;// 城市

	public DBString Gate;// 关卡数据: 1,1,1,1,0,0,0

	public DBInt Money;// 剩余金额

	public DBInt LoginDays;// 登录天数

	public DBInt ChargeNum;// 充值金额

	public DBInt ChargeCount;// 付费的次数

	public DBInt IsEb;// 是否属于额北路

	public DBString DriveInfo;//

	public DBInt CardUsedCount1;//

	public DBString Code;// 领取的码

	public DBInt CardUsedCount2;//

	public DBInt GroupID;// 所属的盟商

	public DBString GroupName;//

	public DBDateTime isGetAward;// 是否已经领取今日的奖励;

	public DBInt FreeUsedCount1;//

	public DBInt FreeUsedCount2;//

	public DBDateTime RegTime;//

	public DBDateTime LoginTime; // 登录时间;

	public DBDateTime ShareTime; // 分享时间;

	public void packData(SendMsgBuffer buffer, boolean flag) {
		buffer.Add(RoleID.Get());
		buffer.Add(Tel.Get());
		buffer.Add(Mail.Get());
		buffer.Add(Age.Get());
		buffer.Add(UserName.Get());
		buffer.Add(Password.Get());
		buffer.Add(Area.Get());
		buffer.Add(Province.Get());
		buffer.Add(City.Get());
		if (flag) {
			buffer.Add(Gate.Get());
		}else{
			buffer.Add("");
		}
		buffer.Add(Money.Get());
		buffer.Add(LoginDays.Get());
		buffer.Add(ChargeNum.Get());
		buffer.Add(ChargeCount.Get());
		buffer.Add(IsEb.Get());
		buffer.Add(DriveInfo.Get());
		buffer.Add(CardUsedCount1.Get());
		buffer.Add(Code.Get());
		buffer.Add(CardUsedCount2.Get());
		buffer.Add(GroupID.Get());
		buffer.Add(GroupName.Get());
		buffer.Add(isGetAward.GetMillis());
		buffer.Add(FreeUsedCount1.Get());
		buffer.Add(FreeUsedCount2.Get());
		buffer.Add(RegTime.GetMillis());
		buffer.Add(LoginTime.GetMillis());
		buffer.Add(ShareTime.GetMillis());
	}
}
