package bit.com.a.model;

/*
DROP TABLE VOTER
CASCADE CONSTRAINTS;

DROP SEQUENCE VOTER_SEQ;

CREATE TABLE VOTER (
	VOTERID NUMBER NOT NULL,
	POLLID NUMBER NOT NULL,
	POLLSUBID NUMBER NOT NULL,
	ID VARCHAR2(50) NOT NULL,
	REGDATE DATE NOT NULL,
	CONSTRAINT VOTER_PK PRIMARY KEY (VOTERID)
);

CREATE SEQUENCE VOTER_SEQ
START WITH 1
INCREMENT BY 1;


ALTER TABLE VOTER ADD CONSTRAINT VOTER_FK
FOREIGN KEY(POLLID)
REFERENCES POLL(POLLID);

ALTER TABLE VOTER ADD CONSTRAINT VOTER_FK2
FOREIGN KEY(POLLSUBID)
REFERENCES POLLSUB(POLLSUBID);

ALTER TABLE VOTER ADD CONSTRAINT VOTER_FK3
FOREIGN KEY(ID)
REFERENCES MEMBER(ID);
*/

import java.io.Serializable;
import java.util.Date;

public class Voter implements Serializable{
	private int voterid;		// sequence
	private int pollid;			// 질문에 대한 seq
	private int pollsubid;		// 보기에 대한 seq
	private String id;			// 투표자의 id
	private Date regdate;		// 언제
	
	public Voter() {
		// TODO Auto-generated constructor stub
	}

	public Voter(int voterid, int pollid, int pollsubid, String id, Date regdate) {
		super();
		this.voterid = voterid;
		this.pollid = pollid;
		this.pollsubid = pollsubid;
		this.id = id;
		this.regdate = regdate;
	}

	public Voter(int pollid, int pollsubid, String id) {
		super();
		this.pollid = pollid;
		this.pollsubid = pollsubid;
		this.id = id;
	}

	public int getVoterid() {
		return voterid;
	}

	public void setVoterid(int voterid) {
		this.voterid = voterid;
	}

	public int getPollid() {
		return pollid;
	}

	public void setPollid(int pollid) {
		this.pollid = pollid;
	}

	public int getPollsubid() {
		return pollsubid;
	}

	public void setPollsubid(int pollsubid) {
		this.pollsubid = pollsubid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Voter [voterid=" + voterid + ", pollid=" + pollid + ", pollsubid=" + pollsubid + ", id=" + id
				+ ", regdate=" + regdate + "]";
	}
	
}
