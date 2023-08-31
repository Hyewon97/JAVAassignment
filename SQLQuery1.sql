/*
-- drop table depTable;
-- drop table empTable;

-- 부서 테이블 생성
create table depTable2(
	depNum int not null primary key, -- 부서 번호,, 프라이머리 키로 설정
	department varchar(20) -- 부서 이름

)

-- depNum 시퀀스 생성
create sequence depSeq2 as int start with 1 increment by 1;


-- 직원 테이블 생성
create table empTable2(
	empNum int , -- 사원 번호, 프라이머리 키로 설정
	name varchar(20), -- 이름 
	email varchar (45), -- 이메일, 길이는 45로 제한
	password varchar(30), -- 비밀번호
	hireDate varchar(30), -- 채용일
	depNum int fOREIGN KEY REFERENCES depTable2(depNum),
    CONSTRAINT emp_pk PRIMARY KEY (empNum) -- 부서 번호 외래키로 설정
	
)

-- empNum 시퀀스 생성
create sequence empSeq2 as int start with 1 increment by 1;

------------------------------------------------
-- 부서 데이터 삽입 및 확인
insert into depTable2 values(next value for depSeq2, '영업');
insert into depTable2 values(next value for depSeq2, '경영지원');
insert into depTable2 values(next value for depSeq2, '개발1');
insert into depTable2 values(next value for depSeq2, '개발2');

select * from depTable2;

-- 직원 테이블 삽입 및 확인

insert into empTable2 values(next value for empSeq2, '박영일', 'sale1@ablecom.co.kr','password','2020-08-17','1');
insert into empTable2 values(next value for empSeq2, '박영이', 'sale2@ablecom.co.kr','password','2021-04-17','1');
insert into empTable2 values(next value for empSeq2, '이경일', 'manage1@ablecom.co.kr','password','2007-07-07','2');
insert into empTable2 values(next value for empSeq2, '김개발', 'dev1@ablecom.co.kr','password','2020-08-17','3');
insert into empTable2 values(next value for empSeq2, '박혜원', 'hyewon97@ablecom.co.kr','password','2023-08-01','4');
insert into empTable2 values(next value for empSeq2, '홍개발', 'hogn@ablecom.co.kr','password','2022.02.22','4');

-- GETDATE


select * from empTable2;
*/
----------------------------------------------------------
/*
create table empTest(
	empNum varchar(10) , -- 사원 번호, 프라이머리 키로 설정
	name varchar(20), -- 이름 
	email varchar (50), -- 이메일, 길이는 45로 제한
	password varchar(30), -- 비밀번호
	hireDate varchar(30), -- 채용일
)

insert into empTest values('1234', '박영일', 'sale1@ablecom.co.kr','password','2020-08-17');

select * from empTest;


create table empTest2(
	empNum int , -- 사원 번호, 
	name varchar(20), -- 이름 
	email varchar (50), -- 이메일
	password varchar(30), -- 비밀번호
	hireDate varchar(30), -- 채용일
)

insert into empTest2 values( 1234, '박영일', 'sale1@ablecom.co.kr','password','2020-08-17');

select * from empTest2;
*/

-- int empNum
-- string name
-- string email
-- string department

/*create table EmsUsers(
	empNum int identity(1,1) not null, -- 사원 번호
	name varchar(20), -- 이름 
	email varchar (50), -- 이메일, 길이는 50로 제한
	department varchar(30) -- 부서
	primary key (empNum) -- 사원번호를 프라이머리 키로 설정
	) */
	


	
	insert into EmsUsers (name, email, department) values('김철수', 'kim@naver.com', '개발');
	insert into EmsUsers (name, email, department) values('김영희', 'kim@2naver.com', '경영');

	--select empNum, name, email, department from EmsUsers where empNum=1;

	-- delete from EmsUsers where empNum=2;

	-- update EmsUsers set name = '김수정',email= 'stone@email.om', department ='회계' where empNum = 3;
	


select * from EmsUsers;


--insert into EmsUsers (name, email, department) values('김영희', 'kim@2naver.com', '경영');