package Interface;

import java.util.List;

import Dto.OpenDto;

public interface Professor {
	boolean addOpen(OpenDto od);
	List<OpenDto> listOpen(int num);
	//교수번호로 개설한 과목 목록 출력
	boolean modOpen(OpenDto od);
	boolean delOpen(int num);
	//개설과목번호로 개설 과목 삭제(Open)
}
