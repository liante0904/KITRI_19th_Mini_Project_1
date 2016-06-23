package Interface;

import java.util.List;

import Dto.MemberDto;
import Dto.OpenDto;
import Dto.RegL;
import Dto.RegisterDto;
import Dto.SubjectDto;

public interface Service {
	//교직원
	List<SubjectDto> listSub(); //등록된 과목 목록 출력
	
	//교수
	boolean addOpen(OpenDto od); //과목 개설
	List<OpenDto> listOpen(int num); //교수가 개설한 과목 목록 출력(Open테이블에서 교수num인 것)
	boolean modOpen(OpenDto od); //교수가 개설한 과목 수정
	boolean delOpen(int num); //교수가 개설한 과목 삭제
	
	//학생
	int addReg(RegisterDto rd); //수강 신청(리턴 0:실패, 1:성공, 2:인원초과)
	boolean delReg(int num); //수강 취소(Register의 num)
	List<RegL> listReg(int num); //학생이 수강신청한 목록(Register테이블에서 학생의 num가지고 검색)
	
	//로그인 관련
	boolean join(MemberDto m); //회원가입
	boolean login(int num, String name, int type); //로그인
	boolean addSub(String sd);
}
