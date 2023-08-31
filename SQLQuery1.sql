/*
-- drop table depTable;
-- drop table empTable;

-- �μ� ���̺� ����
create table depTable2(
	depNum int not null primary key, -- �μ� ��ȣ,, �����̸Ӹ� Ű�� ����
	department varchar(20) -- �μ� �̸�

)

-- depNum ������ ����
create sequence depSeq2 as int start with 1 increment by 1;


-- ���� ���̺� ����
create table empTable2(
	empNum int , -- ��� ��ȣ, �����̸Ӹ� Ű�� ����
	name varchar(20), -- �̸� 
	email varchar (45), -- �̸���, ���̴� 45�� ����
	password varchar(30), -- ��й�ȣ
	hireDate varchar(30), -- ä����
	depNum int fOREIGN KEY REFERENCES depTable2(depNum),
    CONSTRAINT emp_pk PRIMARY KEY (empNum) -- �μ� ��ȣ �ܷ�Ű�� ����
	
)

-- empNum ������ ����
create sequence empSeq2 as int start with 1 increment by 1;

------------------------------------------------
-- �μ� ������ ���� �� Ȯ��
insert into depTable2 values(next value for depSeq2, '����');
insert into depTable2 values(next value for depSeq2, '�濵����');
insert into depTable2 values(next value for depSeq2, '����1');
insert into depTable2 values(next value for depSeq2, '����2');

select * from depTable2;

-- ���� ���̺� ���� �� Ȯ��

insert into empTable2 values(next value for empSeq2, '�ڿ���', 'sale1@ablecom.co.kr','password','2020-08-17','1');
insert into empTable2 values(next value for empSeq2, '�ڿ���', 'sale2@ablecom.co.kr','password','2021-04-17','1');
insert into empTable2 values(next value for empSeq2, '�̰���', 'manage1@ablecom.co.kr','password','2007-07-07','2');
insert into empTable2 values(next value for empSeq2, '�谳��', 'dev1@ablecom.co.kr','password','2020-08-17','3');
insert into empTable2 values(next value for empSeq2, '������', 'hyewon97@ablecom.co.kr','password','2023-08-01','4');
insert into empTable2 values(next value for empSeq2, 'ȫ����', 'hogn@ablecom.co.kr','password','2022.02.22','4');

-- GETDATE


select * from empTable2;
*/
----------------------------------------------------------
/*
create table empTest(
	empNum varchar(10) , -- ��� ��ȣ, �����̸Ӹ� Ű�� ����
	name varchar(20), -- �̸� 
	email varchar (50), -- �̸���, ���̴� 45�� ����
	password varchar(30), -- ��й�ȣ
	hireDate varchar(30), -- ä����
)

insert into empTest values('1234', '�ڿ���', 'sale1@ablecom.co.kr','password','2020-08-17');

select * from empTest;


create table empTest2(
	empNum int , -- ��� ��ȣ, 
	name varchar(20), -- �̸� 
	email varchar (50), -- �̸���
	password varchar(30), -- ��й�ȣ
	hireDate varchar(30), -- ä����
)

insert into empTest2 values( 1234, '�ڿ���', 'sale1@ablecom.co.kr','password','2020-08-17');

select * from empTest2;
*/

-- int empNum
-- string name
-- string email
-- string department

/*create table EmsUsers(
	empNum int identity(1,1) not null, -- ��� ��ȣ
	name varchar(20), -- �̸� 
	email varchar (50), -- �̸���, ���̴� 50�� ����
	department varchar(30) -- �μ�
	primary key (empNum) -- �����ȣ�� �����̸Ӹ� Ű�� ����
	) */
	


	
	insert into EmsUsers (name, email, department) values('��ö��', 'kim@naver.com', '����');
	insert into EmsUsers (name, email, department) values('�迵��', 'kim@2naver.com', '�濵');

	--select empNum, name, email, department from EmsUsers where empNum=1;

	-- delete from EmsUsers where empNum=2;

	-- update EmsUsers set name = '�����',email= 'stone@email.om', department ='ȸ��' where empNum = 3;
	


select * from EmsUsers;


--insert into EmsUsers (name, email, department) values('�迵��', 'kim@2naver.com', '�濵');