package com.bitcamp.board;

public class BoardVO {
	private int no;
	private String subject;
	private String content;
	private String userid;
	private int hit;
	private String writedate;
	private String ip;
	
	public BoardVO() {}
	
	public BoardVO(String subject, String content){
		this.subject = subject;
		this.content = content;
	}
	
	public BoardVO(int no, String subject, String userid, String writedate,int hit) {
		this.no = no;
		this.subject = subject;
		this.hit =hit;
		this.userid= userid;
		this.writedate= writedate;
	}
	
	public BoardVO(int no, String subject,String content, int hit, String userid, String writedate) {
		this.no = no;
		this.subject = subject;
		this.content = content;
		this.hit =hit;
		this.userid= userid;
		this.writedate= writedate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
