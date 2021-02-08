create table member(
userid varchar2(6) primary key,
userpw varchar2(6) not null,
username varchar2(18) not null,
email varchar2(100),
regdate date default sysdate,
updatedate date default sysdate
)

select * from member
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

select * from BOARD

insert into BOARD (bno, title, content, writer)
values (1, 'HELLO', 'HELLO IS ANNYEON', 'KIM')
