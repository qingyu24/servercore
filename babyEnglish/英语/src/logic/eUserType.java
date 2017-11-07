package logic;

public enum eUserType {
	USER_ADMIN,
	USER_GROUP,
	USER_PLAYER;
	
	public int ID()
	{
		switch(this)
		{
		case USER_ADMIN: return 1;
		case USER_GROUP: return 2;
		case USER_PLAYER: return 3;
		
		}
		return -1;
	}
}
