package Interface;

import java.util.List;

import Dto.OpenDto;
import Dto.SubjectDto;

public interface Staff {
	List<SubjectDto> listSub();
	boolean addSub(String name);
	
}
