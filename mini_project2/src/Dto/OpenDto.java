package Dto;

public class OpenDto {
	private int num; //ÅØ½ºÆ®Æû
	private int subject_num; //${num}
	private String room; //ÅØ½ºÆ®Æû
	private String sub_day; //ÅØ½ºÆ®Æû
	private String sub_time; //ÅØ½ºÆ®Æû
	private int prof_num; //¼¼¼ÇÀÇ num°ª
	private String subject_name;
	private String prof_name;
	public String getSubject_name() {
		return subject_name;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public OpenDto(int num, int subject_num, String room, String sub_day, String sub_time, int prof_num,
			String subject_name, String prof_name) {
		super();
		this.num = num;
		this.subject_num = subject_num;
		this.room = room;
		this.sub_day = sub_day;
		this.sub_time = sub_time;
		this.prof_num = prof_num;
		this.subject_name = subject_name;
		this.prof_name = prof_name;
	}
	public OpenDto(int num, int subject_num, String room, String sub_day, String sub_time, int prof_num,
			String subject_name) {
		this.num = num;
		this.subject_num = subject_num;
		this.room = room;
		this.sub_day = sub_day;
		this.sub_time = sub_time;
		this.prof_num = prof_num;
		this.subject_name = subject_name;
	}
	//${name}
	public OpenDto(){}
	public OpenDto(int num, int subject_num, String room, String sub_day, String sub_time, int prof_num) {
		this.num = num;
		this.subject_num = subject_num;
		this.room = room;
		this.sub_day = sub_day;
		this.sub_time = sub_time;
		this.prof_num = prof_num;
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
	public int getProf_num() {
		return prof_num;
	}
	public void setProf_num(int prof_num) {
		this.prof_num = prof_num;
	}
	@Override
	public String toString() {
		return "OpenDto [num=" + num + ", subject_num=" + subject_num + ", room=" + room + ", sub_day=" + sub_day
				+ ", sub_time=" + sub_time + ", prof_num=" + prof_num + "]";
	}
	
	
}
