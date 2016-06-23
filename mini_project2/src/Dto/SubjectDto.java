package Dto;

public class SubjectDto {
	private int num;
	private String name;
	private int flag; //개설 여부
	public SubjectDto(){}
	public SubjectDto(int num, String name, int flag) {
		this.num = num;
		this.name = name;
		this.flag = flag;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "SubjectDto [num=" + num + ", name=" + name + ", flag=" + flag + "]";
	}
	
	
}
