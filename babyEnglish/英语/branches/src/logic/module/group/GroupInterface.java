package logic.module.group;

import logic.MyUser;
import logic.Reg;
import core.remote.PI;
import core.remote.PS;
import core.remote.PU;
import core.remote.RCC;
import core.remote.RFC;

@RCC (ID = Reg.GROUP)
public interface GroupInterface
{
	static final int MID_CREATE = 0;
	static final int MID_UPDATE = 1;
	static final int MID_DELETE = 2;
	
	@RFC (ID = MID_CREATE)
	void Create(@PU MyUser p_user);
	
	@RFC (ID = MID_UPDATE)
	void Update(@PU MyUser p_user);
	
	@RFC (ID = MID_DELETE)
	void Delete(@PU MyUser p_user);
	
	
	
}