package Dto;

import java.util.Date;

public class RegisterDto {
	private int num;
	private int subject_num;
	private int stud_num;
	private String sub_year;
	private int quarter;
	private String sub_name;
	public RegisterDto(){}
	
	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public RegisterDto(int num, int subject_num, int stud_num, String sub_year, int quarter, String sub_name) {
		this.num = num;
		this.subject_num = subject_num;
		this.stud_num = stud_num;
		this.sub_year = sub_year;
		this.quarter = quarter;
		this.sub_name = sub_name;
	}

	public RegisterDto(int num, int subject_num, int stud_num, String sub_year, int quarter) {
		this.num = num;
		this.subject_num = subject_num;
		this.stud_num = stud_num;
		this.sub_year = sub_year;
		this.quarter = quarter;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSubject_num() {
		return subject_num;
	}
	public void setSubject_num(int subject_num) {
		this.subject_num = subject_num;
	}
	public int getStud_num() {
		return stud_num;
	}
	public void setStud_num(int stud_num) {
		this.stud_num = stud_num;
	}
	public String getSub_year() {
		return sub_year;
	}
	public void setSub_year(String sub_year) {
		this.sub_year = sub_year;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	@Override
	public String toString() {
		return "RegisterDto [num=" + num + ", subject_num=" + subject_num + ", stud_num=" + stud_num + ", sub_year="
				+ sub_year + ", quarter=" + quarter + "]";
	}
	
	
}
