-----------------------------

CREATE TABLE  board (
   bno NUMBER(6) PRIMARY KEY,
   title VARCHAR2(100) NOT NULL,
   content VARCHAR2(3000) NOT NULL,
   writer VARCHAR2(30) NOT NULL,
   regDate DATE DEFAULT SYSDATE,
   updateDate DATE DEFAULT SYSDATE,
   viewCnt NUMBER DEFAULT 0
)

select * from BOARD order by bno desc

insert into BOARD (bno, title, content, writer)
values (1, 'HELLO', 'HELLO IS ANNYEON', 'KIM')

select * from board where lower(writer)  like lower('%kim%')

--보드테이블 댓글개수 컬럼 추가
ALTER TABLE BOARD add replyCnt number(4) default 0
--이미 생성된 댓글의 개수 반영 업데이트
update BOARD set replyCnt = (select count(rno) from REPLY where bno= BOARD.BNO)
commit

--댓글 VO 테이블 생성

create table reply(
rno number primary key,
bno number not null,
replyText varchar2(1000) not null,
replyer varchar2(30) not null,
regDate date default sysdate,
updateDate date default sysdate,
constraint reply_fk_bno foreign key(bno) references board(bno)
)

select * from REPLY

--- 업로드 파일에 대한 테이블
create table attach(
id number(8) primary key,
fullName varchar2(200) not null,
bno number(6),
regdate date default sysdate,
constraint fk_attach_bno foreign key(bno) references board(bno)
ON DELETE CASCADE
)

select * from attach


------------------------------

-- 회원 정보 
create table member(
mnum number(6) primary key,
id varchar2(10) not null,
email varchar2(20) not null,
pw varchar2(15) not null,
name varchar2(12) not null,
phone number(15) not null,
address varchar2(300) not null,
regdate DATE DEFAULT SYSDATE,
updatedate DATE DEFAULT SYSDATE,
birth varchar2(10) not null,
point number(10) DEFAULT 0,
mtype number(10) DEFAULT 1,
memo varchar2(100)
)






