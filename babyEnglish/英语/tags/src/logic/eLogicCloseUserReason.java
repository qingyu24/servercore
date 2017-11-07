/**
 * eLogicCloseUserReason.java 2013-3-14下午2:15:27
 */
package logic;

/**
 * @author ddoq
 * @version 1.0.0
 *
 */
public enum eLogicCloseUserReason
{
	DFWZ_BATTLE_CHECK_FAIL,
	ECHO_NOT_RECEIVE,
	GM_IDENTITY_CHECK_FAIL,
	LISTROLE_EMPTY_DATA,
	UPDATE_LOGINDATA_FAIL,
	CREATEROLE_CHECK,
	UPDATE_LOGINDATA_CREATEROLE_NOT_FIND,
	UPDATE_LOGINDATA_CREATEROLE_CHANGED,
	UPDATE_LOGINDATA_CREATEROLE_FINISH_NOT_FIND,
	UPDATE_LOGINDATA_CREATEROLE_FINISH_CHANGED,
	UPDATE_SET_LOGINDATA_FAIL,
	LOGIN_KILL_OPPOSITE,
	LOGIN_RETURN_FAILID,
	USER_TIRED,
	USER_FORBID,
	GM_KICK,
	PVP_ADDCHANNEL,
	NONE_PVP_DATA,
	PVP_LOADING_TOO_LONG,
	PVP_BATTLE_ERROR_SCENE,
	PVP_BATTLE_FIND_DATA,
	FACTIONVS_FIND_DATA,
	;
	
	/**
	 * @return must more than 0
	 */
	public int ID()
	{
		switch (this)
		{
		case DFWZ_BATTLE_CHECK_FAIL:	return 1;
		case ECHO_NOT_RECEIVE:			return 2;
		case GM_IDENTITY_CHECK_FAIL:	return 3;
		case LISTROLE_EMPTY_DATA:		return 4;
		case UPDATE_LOGINDATA_FAIL:		return 5;
		case CREATEROLE_CHECK:			return 6;
		case UPDATE_LOGINDATA_CREATEROLE_NOT_FIND:	return 7;
		case UPDATE_LOGINDATA_CREATEROLE_CHANGED:	return 8;
		case UPDATE_LOGINDATA_CREATEROLE_FINISH_NOT_FIND:	return 9;
		case UPDATE_LOGINDATA_CREATEROLE_FINISH_CHANGED:	return 10;
		case UPDATE_SET_LOGINDATA_FAIL:	return 11;
		case LOGIN_KILL_OPPOSITE:		return 12;
		case LOGIN_RETURN_FAILID:		return 13;
		case USER_TIRED:				return 14;
		case USER_FORBID:				return 15;
		case GM_KICK:					return 16;
		case PVP_ADDCHANNEL:			return 17;
		case NONE_PVP_DATA:				return 18;
		case PVP_LOADING_TOO_LONG:		return 19;
		case PVP_BATTLE_ERROR_SCENE:	return 20;
		case PVP_BATTLE_FIND_DATA:		return 21;
		}
		return 0;
	}
}
