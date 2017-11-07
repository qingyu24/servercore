package logic.userdata;

import core.detail.impl.socket.SendMsgBuffer;

public class tx_cardrecord {

	public int GID;//

	public int GroupID;//

	public long RoleID;//

	public int Register;

	public String Code;//

	public int ChargeNum;

	public int ChargeCount;

	public int LostUserCount;

	public int AddUserCount;

	public int DownloadCount;

	public int CardUsedCount1;

	public int CardUsedCount2;

	public int FreeUsedCount1;

	public int FreeUsedCount2;

	public long DateTime;//

	public void packData(SendMsgBuffer buffer) {
		buffer.Add(GID);
		buffer.Add(GroupID);
		buffer.Add(RoleID);
		buffer.Add(Register);
		buffer.Add(Code);
		buffer.Add(ChargeNum);
		buffer.Add(ChargeCount);
		buffer.Add(LostUserCount);
		buffer.Add(AddUserCount);
		buffer.Add(DownloadCount);
		buffer.Add(CardUsedCount1);
		buffer.Add(CardUsedCount2);
		buffer.Add(FreeUsedCount1);
		buffer.Add(FreeUsedCount2);
		buffer.Add(DateTime);
	}

	public tx_cardrecord() {

	}

	public tx_cardrecord(bs_cardrecord card) {

		GID = card.GID.Get();

		GroupID = card.GroupID.Get();

		RoleID = card.RoleID.Get();

		Register = card.Register.Get();

		String Code = card.Code.Get();

		ChargeNum = card.ChargeNum.Get();

		ChargeCount = card.ChargeCount.Get();

		LostUserCount = card.LostUserCount.Get();

		AddUserCount = card.AddUserCount.Get();

		DownloadCount = card.DownloadCount.Get();

		CardUsedCount1 = card.CardUsedCount1.Get();

		CardUsedCount2 = card.CardUsedCount2.Get();

		FreeUsedCount1 = card.FreeUsedCount1.Get();

		FreeUsedCount2 = card.FreeUsedCount2.Get();

		DateTime = card.DateTime.GetMillis();

	}

}
