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











