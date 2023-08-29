-- 사원 관리 프로그램 스프링 버전 테이블 생성


-- 부서 정보 테이블
/*
create table DepInfo(
	depNum int identity(1,1) not null, -- 부서 번호, pk로 설정
	depName varchar(30), -- 부서 이름
	primary key (depNum) -- 사원번호를 프라이머리 키로 설정
	) 

-- 테스트 데이터 넣기
insert into DepInfo (depName) values('대표');
insert into DepInfo (depName) values('경영지원');
insert into DepInfo (depName) values('영업');
insert into DepInfo (depName) values('CS1');
insert into DepInfo (depName) values('CS2');


-- 데이터 확인
select * from DepInfo;
*/

-- ##################################################



/*
-- 직급 정보 테이블
create table RankInfo(
	rankNum int identity(1,1) not null, -- 직급 번호, pk로 설정
	rankName varchar(30), -- 직급명
	primary key (rankNum) -- 직급 번호를 프라이머리 키로 설정
	) 

-- 테스트 데이터 넣기
insert into RankInfo (RankName) values('대표');
insert into RankInfo (RankName) values('팀장');
insert into RankInfo (RankName) values('부장');
insert into RankInfo (RankName) values('차장');
insert into RankInfo (RankName) values('과장');
insert into RankInfo (RankName) values('대리');
insert into RankInfo (RankName) values('주임');
insert into RankInfo (RankName) values('사원');

-- 데이터 확인
select * from RankInfo;
*/
-- ##################################################
/*
-- 직원 정보 테이블
create table EmpInfo(
	empNum int identity(1,1) not null, -- 사원 번호, pk로 설정
	empName varchar(20) not null, -- 이름 
	empSex char(1) not null, -- 0이면 남자 1이면 여자
	empEmail varchar(50), -- 이메일, 길이는 50로 제한
	depName varchar(30) -- 부서
	primary key (empNum) -- 사원번호를 프라이머리 키로 설정
	) 

insert into EmpInfo (empName, empSex,empEmail,depName) values  ('박대표','0','head@email.com','대표');

insert into EmpInfo (empName, empSex,empEmail,depName) values  ('박팀장','0','teamjang@email.com','경영지원');
insert into EmpInfo (empName, empSex,empEmail,depName) values  ('박부장','0','bujang@email.com','영업');

select * from empInfo;
*/

-- SELECT count(*) FROM EmpInfo;

SELECT b.*
		FROM(SELECT ROW_NUMBER AS rm, a.*
			FROM(SELECT * FROM EmpInfo
			ORDER BY empNum ASC)a)b
			WHERE b.rm>=1 AND b.rm<=3;