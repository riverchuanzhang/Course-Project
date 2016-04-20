create database mypersonal
use personal
create table Password(
	Employee_no Varchar(20) primary key not null,
	foreign key(Employee_no) references Employee(Employee_no),
	Employee_password Varchar(20),

)
create table Employee(
	Employee_no Varchar(20) primary key not null,
	Employee_name Varchar(20) null,
	Employee_sex Varchar(20)check(Employee_sex = '男' or Employee_sex = '女'),
	Employee_birth datetime null,
	Employee_work_date datetime null,
	Employee_phone_no Varchar(20),
	Salary_degree_no Varchar(20),
	Department_no Varchar(20),
	Occupation_no Varchar(20),
	Work_type_no Varchar(20),
	foreign key(Salary_degree_no) references Salary(Salary_degree_no),
	foreign key(Department_no) references Department(Department_no),
	foreign key(Occupation_no) references Occupation(Occupation_no),
	foreign key(Work_type_no) references Work(Work_type_no),
)
create table Department(
	Department_no Varchar(20) primary key not null,
	Department_name Varchar(20) null,
	Department_totality Int,
	
)
create table Occupation(
	Occupation_no Varchar(20) primary key not null,
	Occupation_name Varchar(20) not null,
	Occupation_totality Int,
	Occupation_degree Varchar(20),
	Department_no Varchar(20),
	foreign key(Department_no) references Department(Department_no),
	
)
create table Salary(
	Salary_degree_no Varchar(20) primary key not null,
	Elementary_salary float check(Elementary_salary > 0 and Elementary_salary <15000),
	Extra_work_salary float check(Extra_work_salary > 0 and Extra_work_salary <1500),
	Cut_payment float check(Cut_payment > 0 and  Cut_payment <1500),
	Bonus_percentage Varchar(20),
)
create table Welfare(
	Endowment_insurance float,
	Unemployment_insurance float,
	Birth_insurance float,
	Medical_insurance float,
	Accident_insurance float,
	House_found float,
	Employee_no Varchar(20),
	foreign key(Employee_no) references Employee(Employee_no),
)
create table Work(
	Work_time int check(Work_time >0 and Work_time < 24),
	Work_type_no Varchar(20) primary key not null,
	Work_type Varchar(20), 
)
create table Rework(
	Reword_punish_no Varchar(20) primary key not null,
	Rework_punish_name Varchar(20),
	Reword_punish_found float check(Reword_punish_found > 0 and Reword_punish_found <10000)
)
create table Select_Course(
	Select_time datetime,
	Grade float check(Grade > 0 and Grade < 100),
	Employee_no Varchar(20),
	Course_no Varchar(20),
	primary key(Employee_no,Course_no),
	foreign key(Employee_no) references Employee(Employee_no),
	foreign key(Course_no) references Course(Course_no),
)
create table Accept_Rework(
	Time datetime,
	Employee_no Varchar(20),
	Reword_punish_no Varchar(20),
	primary key(Employee_no,Reword_punish_no),
	foreign key(Employee_no) references Employee(Employee_no),
	foreign key(Reword_punish_no) references Rework(Reword_punish_no),
)
create table Course(
	Course_no Varchar(20) primary key not null,
	Course_name Varchar(20),
	Course_time int,
	Course_book Varchar(20),	
)


insert into Salary values('01',7000,'300','400','0.2')
insert into Salary values('02',650,'300','400','0.2')
insert into Salary values('03',5000,'300','400','0.2')
insert into Salary values('04',400,'300','400','0.2')

insert into Department values('01','人事部','5')
insert into Department values('02','仓库部','5')
insert into Department values('03','市场部','5')
insert into Department values('04','生产部','5')

insert into Occupation values('01','人事部主管',1,'1','01')
insert into Occupation values('04','人事部员工',1,'1','01')
insert into Occupation values('02','仓库部主管',1,'1','01')
insert into Occupation values('05','仓库部员工',1,'1','02')
insert into Occupation values('03','市场部主管',1,'1','01')
insert into Occupation values('06','市场部员工',1,'1','03')

update Occupation set Department_no = '02' where Occupation_no = '02'
update Employee set Department_no = '01' where Employee_no = '01'

select *
from Occupation



delete from Work

insert into Work values(8,'01','白班')
insert into Work values(8,'02','中班')
insert into Work values(8,'03','夜班')

select *
from Work

drop table Work
drop table Employee

select Occupation_no, Occupation.Department_no from Department,Occupation where Occupation.Department_no = Department.Department_no and Department_name = '质量部'


insert into Employee values('001','张大川','男','1991-12-06','2000-01-01','3328752','01','01','01','01')
insert into Employee values('002','郑泽林','男','1991-12-05','2000-01-01','3328888','02','02','02','02')
insert into Employee values('003','钟大康','女','1991-12-09','2000-01-01','3329999','02','01','01','01')
insert into Employee values('004','张溶东','男','1991-12-09','2000-01-01','3329999','01','03','01','01')
insert into Employee values('005','张三','男','1991-12-04','2000-01-01','33244444','02','03','02','01','005')
insert into Employee values('006','张四','女','1991-12-09','2000-01-01','33243333','02','04','03','02','006')



select *
from Employee


insert into Password values('001','001')
insert into Password values('002','002')
insert into Password values('004','004')

drop table Password


insert into Course values('01','数学',8,'高等数学同济大学版')
insert into Course values('02','英语',9,'牛津英语')
insert into Course values('03','计算机程序语言',9,'C语言')
insert into Course values('04','数据库',9,'数据库概论')
insert into Course values('05','计算机组成原理',9,'清华版计算机组成')
insert into Course values('06','语文',9,'大学语文')


insert into Welfare values(100,100,100,100,100,100,'001')
insert into Welfare values(100,100,100,100,100,100,'002')
insert into Welfare values(100,100,100,100,100,100,'003')

insert into Select_Course values(2010-02-02,85,'001','01')
insert into Select_Course values(2010-02-03,95,'001','02')
insert into Select_Course values(2010-02-06,95,'002','02')

insert into Rework values('01','科技创新奖','2000')--添加的数字不是float类型
insert into Rework values('02','人为损失','1000')
insert into Rework values('03','人为损失',900)
insert into Rework values('04','发明奖',4000)
insert into Rework values('05','最高科学技术奖',8000)
drop table Rework

insert into Accept_Rework values('2011-02-23','001','01')
insert into Accept_Rework values('2011-02-23','001','02')
insert into Accept_Rework values('2011-02-23','002','02')

insert into Accept_Rework values('2011-02-23','002','03')

insert into Accept_Rework values('2011-02-23','002','04')



select * from Password,Department,Employee where 
				Password.Employee_no = Employee.Employee_no and 
				Employee.Department_no = Department.Department_no 
				and Department.Department_name = '人事部'
				
select Employee_password from Password,Department,Employee where 
				Password.Employee_no = Employee.Employee_no and 
				Employee.Department_no = Department.Department_no 
				and Department.Department_name = '人事部' and 
			    Password.Employee_no = '001'
			    
alter table Employee add Employee_password 	Varchar(20)	
update Employee set Employee_password = '004' where Employee_no = '004'	
insert into Employee (Employee_password) values('001')    		

select *
from Employee

select Employee_no,Employee_name,Employee_sex,Department_name,Occupation_name
				Employee_work_date,Employee_birth,Employee_phone_no from Employee,Department,Occupation 
				where Employee.Department_no = Department.Department_no and Employee.Occupation_no = 
				Occupation.Occupation_no 

select Employee.Employee_no, Employee_name, Rework_punish_name, Reword_punish_found ,time
from Employee, Accept_Rework ,Rework where Employee.Employee_no = Accept_Rework.Employee_no 
and Rework.Reword_punish_no = Accept_Rework.Reword_punish_no and Employee.Employee_no = '002' 			

select Employee.Employee_no, Employee_name, Rework_punish_name, Reword_punish_found ,time from Employee, Accept_Rework ,Rework where Employee.Employee_no = Accept_Rework.Employee_no and Rework.Reword_punish_no = Accept_Rework.Reword_punish_no and Employee.Employee_no = 002 

select Employee.Employee_no, Employee_name, Work_type, Work_time from Employee, Work where Employee.Work_type_no = Work.Work_type_no and Employee_no = 002

update Employee set Employee_password = '0001' where 

update Employee set Employee_password = '0001'+ 
				"where Employee_no = ' " + "'" + employeeNo + "'";		
				
update Employee set Employee_password = '002' where Employee_no = '002'		

select Employee.Employee_no, Employee_name, Employee_sex, Course_name, Course_book, Course_time, Select_time, Grade from Employee, Course, Select_Course where Employee.Employee_no = Select_Course.Employee_no and Course.Course_no = Select_Course.Course_no and Employee.Employee_no = '002'		


select *
from Select_Course	

select Employee_no,Employee_name,Employee_sex,Department_name,Occupation_name,Employee_work_date,Employee_birth,Employee_phone_no from Employee,Department,Occupation where Employee.Department_no = Department.Department_no and Employee.Occupation_no = Occupation.Occupation_no and Employee.Employee_no ='002'			

select Department_name from Department

select Occupation_name from Occupation ,Department where Occupation.Department_no = Department.Department_no and Occupation_name = '人事部'


select Occupation_name from Occupation ,Department where Occupation.Department_no = Department.Department_no and DepartmentName = '人事部'

select Employee_name, Salary.Salary_degree_no, Course_name, Elementary_salary from Employee, Salary, Course, Select_Course where Employee.salary_degree_no = Salary.salary_degree_no and Employee.Employee_no = Select_Course.Employee_no and Select_Course.Course_no = Course.Course_no

select Employee_name, Salary.Salary_degree_no, Course_name, Elementary_salary from Employee, Salary, Course, Select_Course, Department, Occupation where Employee.Occupation_no = Occupation.Occupation_no and Employee.Department_no = Department.Department_no and Employee.Salary_degree_no = Salary.Salary_degree_no and Employee.Employee_no = Select_Course.Employee_no and Select_Course.Course_no = Course.Course_no and Department_name = '人事部' and Occupation_name = '人事部主管'

select Employee_name, Salary.Salary_degree_no, Course_name, Elementary_salary from Employee, Salary, Course, Select_Course where Employee.salary_degree_no = Salary.salary_degree_no and Employee.Employee_no = Select_Course.Employee_no and Select_Course.Course_no = Course.Course_no

select Employee_name, Rework_punish_name, Reword_punish_found from Employee, Accept_Rework, Department, Work, Rework where Employee.Employee_no = Accept_Rework.Employee_no and Accept_Rework.Reword_punish_no = Rework.Reword_punish_no and Employee.Work_type_no = Work.Work_type_no and Employee.Department_no = Department.Department_no  and Department_name = '仓库部' and Work_type = '白'

select Department_name, Department.Department_no from Department

select Department_name, Department.Department_no from Department

select Rework.Reword_punish_no ,Rework_punish_name,Reword_punish_found from Rework

select Employee.Employee_no,Employee_name,Employee_sex,Employee_birth,Employee_work_date,Employee_phone_no,Department_name,Occupation_name from Employee,Department,Occupation where Employee.Department_no = Department.Department_no and Occupation.Department_no = Department.Department_no

select Employee_name, Course_name, Course_time, Course_book,Grade from Course,Select_Course,Employee where Select_Course.Course_no = Course.Course_no and Select_Course.Employee_no = Employee.Employee_no

select Occupation_no ,Occupation_name, Occupation_totality, Occupation_degree, Department_name from Department ,Occupation where Department.Department_no = Occupation.Department_no

select Employee_name, Salary.Salary_degree_no, Course_name, Elementary_salary from Employee, Salary, Course, Select_Course, Department, Occupation where Employee.Occupation_no = Occupation.Occupation_no and Employee.Department_no = Department.Department_no and Employee.Salary_degree_no = Salary.Salary_degree_no and Employee.Employee_no = Select_Course.Employee_no and Select_Course.Course_no = Course.Course_no and Department_name = '市场部' and Occupation_name = '市场部员工'

select *
from Department

select *
from Occupation

select*
from Employee

select *
from Course

select Employee_no from Employee

select Employee_no ,Course_name, Course.Course_no from Select_Course, Course where Course.Course_no = Select_Course.Course_no

delete from Select_Course,Course where Course.Course_no = Select_Course.Course_no and Course_name ='数学' and Employee_no ='001'

select *
from Select_Course

delete from Select_Course,Course where Course.Course_no = Select_Course.Course_no and Course_name ='01' and Employee_no ='001'

select *
from Rework
insert into Occupation Values('07','企业人事调度人员',3,'04','01')

select Occupation_no ,Department_name from Department,Occupation where Occupation.Department_no = Department.Department_no and Occupation.Department_no = '人事部'

select Departemt.Department_no from Department, Employee where Department.Department_no = Employee.Department_no and Department_name = '仓库部'

alter database personal
add file
(
	name=personalData,
	filename='C:\Program Files\Microsoft SQL Server\MSSQL.2\MSSQL\Data\personalData.ndf',
	size=3mb,
	maxsize=20mb,
	filegrowth=10mb
)

alter database student
add log file
(
	name=personalLog,
	filename='C:\Program Files\Microsoft SQL Server\MSSQL.2\MSSQL\Data\personalLog.ldf',
	size=2mb,
	maxsize=10mb,
	filegrowth=1mb
)