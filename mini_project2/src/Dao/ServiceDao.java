package Dao;

import java.util.List;

import Dto.MemberDto;
import Dto.OpenDto;
import Dto.RegL;
import Dto.RegisterDto;
import Dto.SubjectDto;
import Interface.Service;

public class ServiceDao implements Service {
	MemberDao member;
	ProfessorDao professor;
	StaffDao staff;
	StudentDao student;
	
	public ServiceDao(){
		member = new MemberDao();
		professor = new ProfessorDao();
		staff = new StaffDao();
		student = new StudentDao();
	}
	
	@Override
	//
	public boolean addSub(String sd) {
		return staff.addSub(sd);
	}
	
	//
	@Override
	public List<SubjectDto> listSub() {
		return staff.listSub();
	}

	
	//
	@Override
	public boolean addOpen(OpenDto od) {
		return professor.addOpen(od);
	}


	//
	@Override
	public List<OpenDto> listOpen(int num) {
		return professor.listOpen(num);
	}

	@Override
	public boolean modOpen(OpenDto od) {
		return professor.modOpen(od);
	}
	//
	@Override
	public boolean delOpen(int num) {
		return professor.delOpen(num);
	}
	//
	@Override
	public int addReg(RegisterDto rd) {
		return student.addReg(rd);
	}
	//
	@Override
	public boolean delReg(int num) {
		return student.delReg(num);
	}

	//
	@Override
	public List<RegL> listReg(int num) {
		return student.listReg(num);
	}

	@Override
	public boolean join(MemberDto m) {
		return member.join(m);
	}

	@Override
	public boolean login(int num, String name, int m_type) {
		return member.login(num, name, m_type);
	}

	//
	public SubjectDto infoSub(int num){
		return staff.infoSub(num);
	}
	
	//
	public List<OpenDto> listOpenAll(int num){
		return student.listOpen(num);
	}
	

	//
	public OpenDto infoOpen(int num){
		return professor.infoOpen(num);
	}
	//
	public boolean delRegAll(int num){
		return student.delRegAll(num);
	}
	
	//
	public List<String> regList(int num){
		return student.regList(num);
	}
	//
	public boolean regCHK(int num, String day, String time) {
		return student.regCHK(num, day, time);
	}

	public MemberDto getMember(int num) {
		return member.select(num);
	}

}
