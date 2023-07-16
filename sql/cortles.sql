------------- 삭제할 테이블 조회 -------------
--SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;
--DROP TABLE "MEMBER" CASCADE CONSTRAINTS;
--DROP TABLE "MOVIE" CASCADE CONSTRAINTS;
--DROP TABLE "BOARD" CASCADE CONSTRAINTS;
--DROP TABLE "QUIT_MEMBER" CASCADE CONSTRAINTS;
--DROP TABLE "REPORT_COMMENT" CASCADE CONSTRAINTS;
--DROP TABLE "MOVIE_COMMENT" CASCADE CONSTRAINTS;
--DROP TABLE "MEMBER_REPORT" CASCADE CONSTRAINTS;
--DROP TABLE "BOARD_COMMENT" CASCADE CONSTRAINTS;
--DROP TABLE "ATTACHMENT" CASCADE CONSTRAINTS;
--DROP TABLE "FAVORITE" CASCADE CONSTRAINTS;
 
 
------------- 삭제할 테이블 조회 -------------
-- SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;
--DROP TABLE "MEMBER" CASCADE CONSTRAINTS;
--DROP TABLE "MOVIE" CASCADE CONSTRAINTS;
--DROP TABLE "BOARD" CASCADE CONSTRAINTS;
--DROP TABLE "QUIT_MEMBER" CASCADE CONSTRAINTS;
--DROP TABLE "REPORT_COMMENT" CASCADE CONSTRAINTS;
--DROP TABLE "MOVIE_COMMENT" CASCADE CONSTRAINTS;
--DROP TABLE "MEMBER_REPORT" CASCADE CONSTRAINTS;
--DROP TABLE "BOARD_COMMENT" CASCADE CONSTRAINTS;
--DROP TABLE "ATTACHMENT" CASCADE CONSTRAINTS;
--DROP TABLE "FAVORITE" CASCADE CONSTRAINTS;

--------------- 테이블 조회 -----------------
select * from board;
select * from movie;
select * from member;
select * from attachment;
select * from quit_member;
update member set favorite_movie_code = favorite_movie_code || ',' || + '1' where member_id = 'sejong';
update member set favorite_movie_code = favorite_movie_code || '1' where member_id = 'sejong';
------------- 테이블 행 삭제 ----------------
-- delete from member where member_id = 'sejong';

---------------- 시퀀스 생성 -----------------
create sequence seq_board_no;
create sequence seq_attachment_no;
create sequence seq_quit_member_no;
create sequence seq_board_comment_no;

---------------시퀀스 삭제 ------------------
--drop sequence seq_board_no;
--drop sequence seq_attachment_no;
--drop sequence seq_quit_member_no;
--drop sequence seq_board_comment_no;

---------------쿼리문 확인용 ------------------
--insert into movie values ('qwe123', 'qwe', 'qwe', default, '호러, 로멘스', '이것은 내용입니다', '1999-09-09', '123', 'www.naver.com', '홍길동', '세종대왕', 'ㅁㄶㅍㄻ널머ㅏㄴㄹ만ㄹ휴ㅣ며ㅗㄴㄹ');
--insert into movie values (?, ?, ?, default, ?, ?, ?, ?, ?, ?, ?, ?)

CREATE TABLE member (
	member_id	varchar2(50)	NOT NULL,
	favorite_genre_name	varchar2(30),
	favorite_movie_code	varchar2(200),
	member_pw	varchar2(300)	NOT NULL,
	member_name	varchar2(30)	NOT NULL,
	email	varchar2(50),
	phone	varchar2(20)	NOT NULL,
	gender	char(1)	NOT NULL,
	member_role	char(1)	DEFAULT 'U',
	birthday	date	NOT NULL,
	enroll_date	date	DEFAULT sysdate
);

CREATE TABLE movie (
	movie_code	varchar2(200)	NOT NULL,
	title	varchar2(200)	NOT NULL,
	title_eng	varchar2(200)	NOT NULL,
	movie_grade	number	DEFAULT 0,
	genre	varchar2(100)	NOT NULL,
	story	varchar2(3000)	NOT NULL,
	open_date	date	NOT NULL,
	runtime	varchar2(100)	NOT NULL,
	poster_url	 varchar2(500)	NOT NULL,
	director	varchar2(500)	NOT NULL,
	actors	varchar2(500)	NOT NULL,
	vod	varchar2(500)	NOT NULL
);

CREATE TABLE board (
	board_no	number	NOT NULL,
	writer_id	varchar2(50)	NOT NULL,
	title	varchar2(50)	NOT NULL,
	content	varchar2(3000)	NOT NULL,
	like_count	number,
	read_count	number,
	reg_date	date DEFAULT sysdate
);

CREATE TABLE quit_member (
	no	number	NOT NULL,
	member_id	varchar2(50)	NOT NULL,
	member_pw	varchar2(300)	NOT NULL,
	member_name	varchar2(30)	NOT NULL,
	email	varchar2(50)	NOT NULL,
	phone	varchar2(20)	NOT NULL,
	gender	char(1)	NOT NULL,
	member_role	char(1)	NOT NULL,
	birthday	date	NOT NULL,
	enroll_date	date	NOT NULL,
	quit_date	date DEFAULT sysdate
);

CREATE TABLE report_comment (
	comment_no	number	NOT NULL,
	report_count	number DEFAULT 0,
	report_id	varchar2(50)	NOT NULL,
	report_content	varchar2(1000)	NOT NULL,
	report_date	date DEFAULT sysdate
);

CREATE TABLE movie_comment (
	comment_no	number	NOT NULL,
	writer_id	varchar2(50)	NOT NULL,
	movie_code	varchar2(200)	NOT NULL,
	movie_content	varchar2(3000)	NOT NULL,
	reg_date	date DEFAULT sysdate,
	star_grade	number	NOT NULL
);

CREATE TABLE member_report (
	comment_no	number	NOT NULL,
	member_id	varchar2(50)	NOT NULL
);

CREATE TABLE board_comment (
	comment_no	number	NOT NULL,
	board_no	number	NOT NULL,
	writer_id	varchar2(50)	NOT NULL,
	content	varchar2(3000)	NOT NULL,
	reg_date	date DEFAULT sysdate
);

CREATE TABLE attachment (
	attachment_no	number	NOT NULL,
	board_no	number	NOT NULL,
	original_filename	varchar2(100)	NOT NULL,
	renamed_filename	varchar2(100)	NOT NULL,
	reg_date	date DEFAULT sysdate
);

CREATE TABLE favorite (
	movie_code	varchar2(200)	NOT NULL,
	member_id	varchar2(50)	NOT NULL
);

ALTER TABLE member ADD CONSTRAINT PK_MEMBER PRIMARY KEY (
	member_id
);

ALTER TABLE movie ADD CONSTRAINT PK_MOVIE PRIMARY KEY (
	movie_code
);

ALTER TABLE board ADD CONSTRAINT PK_BOARD PRIMARY KEY (
	board_no
);

ALTER TABLE quit_member ADD CONSTRAINT PK_QUIT_MEMBER PRIMARY KEY (
	no
);

ALTER TABLE report_comment ADD CONSTRAINT PK_REPORT_COMMENT PRIMARY KEY (
	comment_no
);

ALTER TABLE movie_comment ADD CONSTRAINT PK_MOVIE_COMMENT PRIMARY KEY (
	comment_no,
	writer_id,
	movie_code
);

ALTER TABLE member_report ADD CONSTRAINT PK_MEMBER_REPORT PRIMARY KEY (
	comment_no,
	member_id
);

ALTER TABLE board_comment ADD CONSTRAINT PK_BOARD_COMMENT PRIMARY KEY (
	comment_no,
	board_no,
	writer_id
);

ALTER TABLE attachment ADD CONSTRAINT PK_ATTACHMENT PRIMARY KEY (
	attachment_no,
	board_no
);

ALTER TABLE favorite ADD CONSTRAINT PK_FAVORITE PRIMARY KEY (
	movie_code,
	member_id
);

ALTER TABLE movie_comment ADD CONSTRAINT FK_member_TO_movie_comment_1 FOREIGN KEY (
	writer_id
)
REFERENCES member (
	member_id
);

ALTER TABLE movie_comment ADD CONSTRAINT FK_movie_TO_movie_comment_1 FOREIGN KEY (
	movie_code
)
REFERENCES movie (
	movie_code
);

ALTER TABLE member_report ADD CONSTRAINT FK_report_comment_TO_member_report_1 FOREIGN KEY (
	comment_no
)
REFERENCES report_comment (
	comment_no
);

ALTER TABLE member_report ADD CONSTRAINT FK_member_TO_member_report_1 FOREIGN KEY (
	member_id
)
REFERENCES member (
	member_id
);

ALTER TABLE board_comment ADD CONSTRAINT FK_board_TO_board_comment_1 FOREIGN KEY (
	board_no
)
REFERENCES board (
	board_no
);

ALTER TABLE board_comment ADD CONSTRAINT FK_member_TO_board_comment_1 FOREIGN KEY (
	writer_id
)
REFERENCES member (
	member_id
);

ALTER TABLE attachment ADD CONSTRAINT FK_board_TO_attachment_1 FOREIGN KEY (
	board_no
)
REFERENCES board (
	board_no
);

ALTER TABLE favorite ADD CONSTRAINT FK_member_TO_favorite_1 FOREIGN KEY (
	member_id
)
REFERENCES member (
	member_id
);
----------- 트리거 생성 ------------
create or replace trigger trig_user_quit
    before
    delete on member
    for each row 
begin
    insert into 
        quit_member
    values(
        seq_quit_member_no.nextval,
        :old.member_id, 
        :old.member_pw,
        :old.member_name,
        :old.email,
        :old.phone,
        :old.gender,
        :old.member_role,
        :old.birthday,
        :old.enroll_date,
        default
    );
end;
/
---------------------------------

------------------------- 데이터 예시 -------------------------
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);insert into board values (
--   seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--   seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdf1','sadfsadfwef',default,default,sysdate
--);
--insert into board values (
--    seq_board_no.nextval, 'asdf1', 'asdasdfsadfadsfsdf1','sadfsadfwef',default,default,sysdate
--);
--select seq_board_no.currval from dual;


