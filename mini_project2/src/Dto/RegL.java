package Dto;

public class RegL {
	private int reg_num;
	private int sub_num;
	private String prof_name;
	private String room;
	private String sub_day;
	private String sub_time;
	private String sub_name;
	
	public RegL(int reg_num, int sub_num, String prof_name, String room, String sub_day, String sub_time,
			String sub_name) {
		this.reg_num = reg_num;
		this.sub_num = sub_num;
		this.prof_name = prof_name;
		this.room = room;
		this.sub_day = sub_day;
		this.sub_time = sub_time;
		this.sub_name = sub_name;
	}
	public int getReg_num() {
		return reg_num;
	}
	public void setReg_num(int reg_num) {
		this.reg_num = reg_num;
	}
	public int getSub_num() {
		return sub_num;
	}
	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getSub_day() {
		return sub_day;
	}
	public void setSub_day(String sub_day) {
		this.sub_day = sub_day;
	}
	public String getSub_time() {
		return sub_time;
	}
	public void setSub_time(String sub_time) {
		this.sub_time = sub_time;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	
}
