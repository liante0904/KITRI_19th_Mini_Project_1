package Interface;

import java.util.List;

import Dto.RegL;
import Dto.RegisterDto;

public interface Student {
	int addReg(RegisterDto rd);
	boolean delReg(int num);
	//register num������ �������
	List<RegL> listReg(int num);
	//student num������ ����Ʈ�̾ƿ���
	
}
