-- ��� ���� ���α׷� ������ ���� ���̺� ����


-- �μ� ���� ���̺�
/*
create table DepInfo(
	depNum int identity(1,1) not null, -- �μ� ��ȣ, pk�� ����
	depName varchar(30), -- �μ� �̸�
	primary key (depNum) -- �����ȣ�� �����̸Ӹ� Ű�� ����
	) 

-- �׽�Ʈ ������ �ֱ�
insert into DepInfo (depName) values('��ǥ');
insert into DepInfo (depName) values('�濵����');
insert into DepInfo (depName) values('����');
insert into DepInfo (depName) values('CS1');
insert into DepInfo (depName) values('CS2');


-- ������ Ȯ��
select * from DepInfo;
*/

-- ##################################################



/*
-- ���� ���� ���̺�
create table RankInfo(
	rankNum int identity(1,1) not null, -- ���� ��ȣ, pk�� ����
	rankName varchar(30), -- ���޸�
	primary key (rankNum) -- ���� ��ȣ�� �����̸Ӹ� Ű�� ����
	) 

-- �׽�Ʈ ������ �ֱ�
insert into RankInfo (RankName) values('��ǥ');
insert into RankInfo (RankName) values('����');
insert into RankInfo (RankName) values('����');
insert into RankInfo (RankName) values('����');
insert into RankInfo (RankName) values('����');
insert into RankInfo (RankName) values('�븮');
insert into RankInfo (RankName) values('����');
insert into RankInfo (RankName) values('���');

-- ������ Ȯ��
select * from RankInfo;
*/
-- ##################################################
/*
-- ���� ���� ���̺�
create table EmpInfo(
	empNum int identity(1,1) not null, -- ��� ��ȣ, pk�� ����
	empName varchar(20) not null, -- �̸� 
	empSex char(1) not null, -- 0�̸� ���� 1�̸� ����
	empEmail varchar(50), -- �̸���, ���̴� 50�� ����
	depName varchar(30) -- �μ�
	primary key (empNum) -- �����ȣ�� �����̸Ӹ� Ű�� ����
	) 

insert into EmpInfo (empName, empSex,empEmail,depName) values  ('�ڴ�ǥ','0','head@email.com','��ǥ');

insert into EmpInfo (empName, empSex,empEmail,depName) values  ('������','0','teamjang@email.com','�濵����');
insert into EmpInfo (empName, empSex,empEmail,depName) values  ('�ں���','0','bujang@email.com','����');

select * from empInfo;
*/

-- SELECT count(*) FROM EmpInfo;

SELECT b.*
		FROM(SELECT ROW_NUMBER AS rm, a.*
			FROM(SELECT * FROM EmpInfo
			ORDER BY empNum ASC)a)b
			WHERE b.rm>=1 AND b.rm<=3;