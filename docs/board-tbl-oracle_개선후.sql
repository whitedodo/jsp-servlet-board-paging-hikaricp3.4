-- Oracle 11 - 자동번호 생성 테이블 정의
-- Table 생성 (BOARD)
-- NEW.ID (Table의 id를 가리킴)
CREATE TABLE board
(
    id NUMBER PRIMARY KEY,
    name VARCHAR2(30),
    subject VARCHAR2(30),
    memo NCLOB,
    count NUMBER,
    regidate DATE
);

-- Sequence 정의
CREATE SEQUENCE board_sequence
START WITH 1
INCREMENT BY 1;

-- Trigger 생성
-- BEFORE INSERT on '테이블명'
CREATE OR REPLACE TRIGGER board_trigger
BEFORE INSERT
    ON board
REFERENCING NEW AS NEW
FOR EACH ROW
BEGIN
SELECT board_sequence.nextval INTO :NEW.ID FROM dual;
END;


/* 데이터 추가 */
INSERT INTO board (name, subject, memo, count, regidate) VALUES ('홍길동', '안녕하세요.', '메모메모', '0', '2020-09-29 11:11:00');

/* 데이터 등록 후 커밋할 것(대량 정보 처리 후) */
COMMIT;

-- 싱글 쿼리 (페이징)
SELECT * FROM ( 
SELECT /*+ INDEX_DESC(Z OP_SAMPLE_PK) */ ROWNUM AS RNUM, Z.* FROM ( 
SELECT * from board order by id desc 
) Z WHERE ROWNUM <= 10
) WHERE RNUM >= 1

-- 특정 싱글 쿼리 SQL
SELECT * FROM ( 
SELECT /*+ INDEX_DESC(Z OP_SAMPLE_PK) */ ROWNUM AS RNUM, Z.* FROM ( 
SELECT * from board where subject like '%야해해%' order by id desc 
) Z WHERE ROWNUM <= 10
) WHERE RNUM >= 1
