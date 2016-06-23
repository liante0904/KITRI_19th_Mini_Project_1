package Interface;

import java.util.List;

import Dto.RegL;
import Dto.RegisterDto;

public interface Student {
	int addReg(RegisterDto rd);
	boolean delReg(int num);
	//register num가지고 수강취소
	List<RegL> listReg(int num);
	//student num가지고 리스트뽑아오기
	
}
