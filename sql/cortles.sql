--=============================
-- cortles계정 생성 @관리자
--=============================
ALTER SYSTEM KILL SESSION 'sid, serial#';

alter session set "_oracle_script" = true;

create user cortles
identified by cortles
default tablespace users;

grant connect, resource to cortles;

alter user cortles quota unlimited on users;


SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;
--DROP TABLE "MEMBER" CASCADE CONSTRAINTS;
--DROP TABLE "BOARD" CASCADE CONSTRAINTS;
--DROP TABLE "ATTACHMENT" CASCADE CONSTRAINTS;
--DROP TABLE "BOARD_COMMENT" CASCADE CONSTRAINTS;
--DROP TABLE "CELEB" CASCADE CONSTRAINTS;
--DROP TABLE "PHOTO" CASCADE CONSTRAINTS;


--===============================
-- cortles 계정
--===============================



CREATE TABLE member (
   member_id           varchar2(50)   NOT NULL,
   favorite_genre_id   varchar2(100)   NOT NULL,
   favorite_movie_code varchar2(200),
   member_pw           varchar2(300)   NOT NULL,
   member_name         varchar2(30)   NOT NULL,
   email               varchar2(50),
   phone               varchar2(20)   NOT NULL,
   gender              char(1)   NOT NULL,
   member_role         char(1)   DEFAULT 'U',
   birthday            date   NOT NULL,
   enroll_date         date   DEFAULT sysdate
);

CREATE TABLE movie (
   movie_code    varchar2(200)   NOT NULL,
   title         varchar2(100)   NOT NULL,
   movie_grade   number   NOT NULL,
   story         varchar2(3000)   NOT NULL,
   open_date     date   NOT NULL,
   runtime       timestamp   NOT NULL,
   poster_id     varchar2(200)   NOT NULL,
   director_id   varchar2(50)   NOT NULL,
   actor_id      varchar2(50)   NOT NULL
);

CREATE TABLE actor (
   actor_id     varchar2(50)   NOT NULL,
   movie_code   varchar2(200)   NOT NULL,
   actor_name   varchar2(50)   NOT NULL
);

CREATE TABLE director (
   director_id     varchar2(50)   NOT NULL,
   movie_code      varchar2(200)   NOT NULL,
   director_name   varchar2(20)   NOT NULL
);

CREATE TABLE board (
   board_no       number   NOT NULL,
   writer_id      varchar2(50)   NOT NULL,
   title          varchar2(50)   NOT NULL,
   content        varchar2(3000)   NOT NULL,
   like_count     number   DEFAULT 0,
   read_count     number   DEFAULT 0,
   reg_date       date   DEFAULT sysdate
);

CREATE TABLE poster (
   poster_id     varchar2(50)   NOT NULL,
   movie_code    varchar2(200)   NOT NULL,
   poster_img    blob   NOT NULL
);

CREATE TABLE quit_member (
   member_id      varchar2(50)   NOT NULL,
   member_pw      varchar2(300)   NOT NULL,
   name           varchar2(30)   NOT NULL,
   email          varchar2(50)   NOT NULL,
   phone          varchar2(20)   NOT NULL,
   gender         char(1)   NOT NULL,
   member_role    char(1)   NOT NULL,
   birthday       date   NOT NULL,
   enroll_date    date   NOT NULL,
   quit_date      date   DEFAULT sysdate
);

CREATE TABLE genre (
   genre_id      varchar2(100)   NOT NULL,
   genre_name    varchar2(50)   NOT NULL
);

CREATE TABLE report_comment (
   comment_no         number   NOT NULL,
   report_count       number   DEFAULT 0,
   report_id          varchar2(50)   NOT NULL,
   report_content     varchar2(1000)   NOT NULL,
   report_date        date   DEFAULT sysdate
);

CREATE TABLE movie_comment (
   comment_no      number   NOT NULL,
   writer_id       varchar2(50)   NOT NULL,
   movie_code      varchar2(200)   NOT NULL,
   movie_content   varchar2(3000)   NOT NULL,
   reg_date        date   DEFAULT sysdate,
   star_grade      number   NOT NULL
);

CREATE TABLE actor_movie (
   movie_code   varchar2(200)   NOT NULL,
   actor_id     varchar2(50)   NOT NULL
);

CREATE TABLE member_report (
   comment_no   number   NOT NULL,
   member_id    varchar2(50)   NOT NULL
);

CREATE TABLE director_movie (
   movie_code      varchar2(200)   NOT NULL,
   director_id     varchar2(50)   NOT NULL
);

CREATE TABLE board_comment (
   comment_no      number   NOT NULL,
   board_no        number   NOT NULL,
   writer_id       varchar2(50)   NOT NULL,
   content         varchar2(3000)   NOT NULL,
   reg_date        date   DEFAULT sysdate
);

CREATE TABLE movie_genre (
   genre_id      varchar2(100)   NOT NULL,
   movie_code    varchar2(200)   NOT NULL
);

ALTER TABLE member ADD CONSTRAINT PK_MEMBER PRIMARY KEY (
   member_id
);

ALTER TABLE movie ADD CONSTRAINT PK_MOVIE PRIMARY KEY (
   movie_code
);

ALTER TABLE actor ADD CONSTRAINT PK_ACTOR PRIMARY KEY (
   actor_id
);

ALTER TABLE director ADD CONSTRAINT PK_DIRECTOR PRIMARY KEY (
   director_id
);

ALTER TABLE board ADD CONSTRAINT PK_BOARD PRIMARY KEY (
   board_no
);

ALTER TABLE poster ADD CONSTRAINT PK_POSTER PRIMARY KEY (
   poster_id,
   movie_code
);

ALTER TABLE quit_member ADD CONSTRAINT PK_QUIT_MEMBER PRIMARY KEY (
   member_id
);

ALTER TABLE genre ADD CONSTRAINT PK_GENRE PRIMARY KEY (
   genre_id
);

ALTER TABLE report_comment ADD CONSTRAINT PK_REPORT_COMMENT PRIMARY KEY (
   comment_no
);

ALTER TABLE movie_comment ADD CONSTRAINT PK_MOVIE_COMMENT PRIMARY KEY (
   comment_no,
   writer_id,
   movie_code
);

ALTER TABLE actor_movie ADD CONSTRAINT PK_ACTOR_MOVIE PRIMARY KEY (
   movie_code,
   actor_id
);

ALTER TABLE member_report ADD CONSTRAINT PK_MEMBER_REPORT PRIMARY KEY (
   comment_no,
   member_id
);

ALTER TABLE director_movie ADD CONSTRAINT PK_DIRECTOR_MOVIE PRIMARY KEY (
   movie_code,
   director_id
);

ALTER TABLE board_comment ADD CONSTRAINT PK_BOARD_COMMENT PRIMARY KEY (
   comment_no,
   board_no,
   writer_id
);

ALTER TABLE movie_genre ADD CONSTRAINT PK_MOVIE_GENRE PRIMARY KEY (
   genre_id,
   movie_code
);

ALTER TABLE board ADD CONSTRAINT FK_MEMBER_TO_BOARD FOREIGN KEY (
   writer_id
) REFERENCES member (
   member_id
);

ALTER TABLE poster ADD CONSTRAINT FK_MOVIE_TO_POSTER FOREIGN KEY (
   movie_code
) REFERENCES movie (
   movie_code
);

ALTER TABLE quit_member ADD CONSTRAINT FK_MEMBER_TO_QUIT_MEMBER FOREIGN KEY (
   member_id
) REFERENCES member (
   member_id
);

ALTER TABLE movie_comment ADD CONSTRAINT FK_MEMBER_TO_MOVIE_COMMENT FOREIGN KEY (
   writer_id
) REFERENCES member (
   member_id
);

ALTER TABLE movie_comment ADD CONSTRAINT FK_MOVIE_TO_MOVIE_COMMENT FOREIGN KEY (
   movie_code
) REFERENCES movie (
   movie_code
);

ALTER TABLE actor_movie ADD CONSTRAINT FK_MOVIE_TO_ACTOR_MOVIE FOREIGN KEY (
   movie_code
) REFERENCES movie (
   movie_code
);

ALTER TABLE actor_movie ADD CONSTRAINT FK_ACTOR_TO_ACTOR_MOVIE FOREIGN KEY (
   actor_id
) REFERENCES actor (
   actor_id
);

ALTER TABLE member_report ADD CONSTRAINT FK_REPORT_COMMENT_TO_MEMBER_REPORT FOREIGN KEY (
   comment_no
) REFERENCES report_comment (
   comment_no
);

ALTER TABLE member_report ADD CONSTRAINT FK_MEMBER_TO_MEMBER_REPORT FOREIGN KEY (
   member_id
) REFERENCES member (
   member_id
);

ALTER TABLE director_movie ADD CONSTRAINT FK_MOVIE_TO_DIRECTOR_MOVIE FOREIGN KEY (
   movie_code
) REFERENCES movie (
   movie_code
);

ALTER TABLE director_movie ADD CONSTRAINT FK_DIRECTOR_TO_DIRECTOR_MOVIE FOREIGN KEY (
   director_id
) REFERENCES director (
   director_id
);


ALTER TABLE board_comment ADD CONSTRAINT FK_BOARD_TO_BOARD_COMMENT FOREIGN KEY (
   board_no
) REFERENCES board (
   board_no
);

ALTER TABLE board_comment ADD CONSTRAINT FK_WRITER_TO_BOARD_COMMENT FOREIGN KEY (
   writer_id
) REFERENCES member (
   member_id
);

ALTER TABLE movie_genre ADD CONSTRAINT FK_GENRE_TO_MOVIE_GENRE FOREIGN KEY (
   genre_id
) REFERENCES genre (
   genre_id
);

ALTER TABLE movie_genre ADD CONSTRAINT FK_MOVIE_TO_MOVIE_GENRE FOREIGN KEY (
   movie_code
) REFERENCES movie (
   movie_code
);

