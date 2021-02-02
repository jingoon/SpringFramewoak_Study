create table member(
userid varchar2(6) primary key,
userpw varchar2(6) not null,
username varchar2(18) not null,
email varchar2(100),
regdate date default sysdate,
updatedate date default sysdate
)

select * from member