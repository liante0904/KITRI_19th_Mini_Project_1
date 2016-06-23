package Interface;

import Dto.MemberDto;

public interface Member {
	boolean join(MemberDto m);
	boolean modify(MemberDto m);
	boolean login(int num, String name, int type);
}
