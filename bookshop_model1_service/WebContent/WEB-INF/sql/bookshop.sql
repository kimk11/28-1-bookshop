-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.32 - MySQL Community Server (GPL)
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- bookshop 의 데이터베이스 구조 덤핑
CREATE DATABASE IF NOT EXISTS `bookshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bookshop`;


-- 테이블 bookshop의 구조를 덤프합니다. admin
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_no` int(10) NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(50) NOT NULL,
  `admin_pw` varchar(50) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  `admin_date` datetime NOT NULL,
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.admin: ~2 rows (대략적)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`admin_no`, `admin_id`, `admin_pw`, `admin_name`, `admin_date`) VALUES
	(1, 'id100', 'pw100', '김진우', '2018-07-24 15:08:59'),
	(2, 'id200', 'pw200', '박성환', '2018-07-24 15:09:17');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. book
CREATE TABLE IF NOT EXISTS `book` (
  `book_no` int(10) NOT NULL AUTO_INCREMENT,
  `bookcode_no` int(10) NOT NULL,
  `publisher_no` int(10) NOT NULL,
  `book_name` varchar(50) NOT NULL,
  `book_author` varchar(50) NOT NULL,
  `book_price` int(10) NOT NULL,
  `book_point` int(10) NOT NULL,
  `book_amount` int(10) NOT NULL,
  `book_out` varchar(50) NOT NULL,
  `book_date` datetime NOT NULL,
  PRIMARY KEY (`book_no`),
  KEY `FK_book_bookcode` (`bookcode_no`),
  KEY `FK_book_publisher` (`publisher_no`),
  CONSTRAINT `FK_book_bookcode` FOREIGN KEY (`bookcode_no`) REFERENCES `bookcode` (`bookcode_no`),
  CONSTRAINT `FK_book_publisher` FOREIGN KEY (`publisher_no`) REFERENCES `publisher` (`publisher_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.book: ~17 rows (대략적)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`book_no`, `bookcode_no`, `publisher_no`, `book_name`, `book_author`, `book_price`, `book_point`, `book_amount`, `book_out`, `book_date`) VALUES
	(1, 1, 1, '한국단편소설 40', '계용묵,김동인,김유정 등', 15000, 800, 38, '있음', '2018-07-24 14:31:55'),
	(2, 1, 1, '세계단편소설 40', '오 헨리', 15000, 800, 18, '있음', '2018-07-24 14:34:29'),
	(3, 2, 3, '그대를 듣는다', '정재찬', 12600, 700, 6, '있음', '2018-07-24 14:36:21'),
	(4, 2, 4, '시와 에세이 14', '국제문학바탕문인협회', 9000, 500, 53, '있음', '2018-07-24 14:38:09'),
	(5, 3, 5, '경영.경제.인생 강좌 45편', '윤석철', 9000, 500, 3, '있음', '2018-07-24 14:38:56'),
	(6, 3, 6, '주식투자 무작정 따라하기', '윤재수', 15000, 800, 22, '있음', '2018-07-24 14:39:50'),
	(7, 4, 5, '배려', '한설', 9000, 500, 35, '있음', '2018-07-24 14:41:27'),
	(8, 5, 7, '태평양전쟁사 1', '오일환, 이연식', 26000, 1400, 25, '있음', '2018-07-24 14:56:10'),
	(9, 5, 8, '베트남', '유인선', 18000, 1000, 1, '있음', '2018-07-24 14:56:58'),
	(10, 6, 9, '다이어터 1 : 식이조절 편 ', '네온비', 10000, 600, 3, '있음', '2018-07-24 14:58:01'),
	(11, 8, 10, '왜 종교는 과학이 되려 하는가', '리처드 토킨스', 6700, 300, 5, '있음', '2018-07-24 14:59:28'),
	(12, 8, 11, '복음이 나에게 물었다', '에르메스 론키', 11000, 600, 6, '있음', '2018-07-24 15:00:32'),
	(13, 8, 12, '이것이 복음이다', '톰 라이트', 11000, 600, 23, '있음', '2018-07-24 15:01:13'),
	(14, 9, 13, '아무튼, 외국어', '조지영', 8000, 400, 2, '있음', '2018-07-24 15:02:14'),
	(15, 9, 14, 'EBSi 강의교재 수능완성 제2외국어&한문영역 아랍어 1 (2018년)', '윤은경', 4500, 0, 3, '있음', '2018-07-24 15:02:56'),
	(16, 10, 15, '[세트] 모모맘의 어린이 간식 + 유아 식판식 - 전2권', '정현미', 23400, 1300, 0, '없음', '2018-07-24 15:03:58'),
	(17, 11, 16, '오투 중등 과학 3-2 (2018년)', '비상교육 편집부', 15000, 800, 0, '없음', '2018-07-24 15:04:50');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. bookcode
CREATE TABLE IF NOT EXISTS `bookcode` (
  `bookcode_no` int(10) NOT NULL AUTO_INCREMENT,
  `bookcode_name` varchar(50) NOT NULL,
  PRIMARY KEY (`bookcode_no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.bookcode: ~11 rows (대략적)
/*!40000 ALTER TABLE `bookcode` DISABLE KEYS */;
INSERT INTO `bookcode` (`bookcode_no`, `bookcode_name`) VALUES
	(1, '소설'),
	(2, '에세이/시'),
	(3, '경영/경제'),
	(4, '자기계발'),
	(5, '인문/사회/역사'),
	(6, '건강/다이어트'),
	(7, '가정/생활'),
	(8, '종교'),
	(9, '외국어'),
	(10, '어린이/유아'),
	(11, '과학');
/*!40000 ALTER TABLE `bookcode` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. bookintro
CREATE TABLE IF NOT EXISTS `bookintro` (
  `bookintro_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_no` int(10) NOT NULL,
  `bookintro_content` text NOT NULL,
  `bookintro_writer` varchar(50) NOT NULL,
  PRIMARY KEY (`bookintro_no`),
  KEY `FK__book` (`book_no`),
  CONSTRAINT `FK__book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.bookintro: ~0 rows (대략적)
/*!40000 ALTER TABLE `bookintro` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookintro` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. bookreview
CREATE TABLE IF NOT EXISTS `bookreview` (
  `bookreview_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_no` int(10) NOT NULL,
  `member_no` int(10) NOT NULL,
  `bookreview_content` text NOT NULL,
  PRIMARY KEY (`bookreview_no`),
  KEY `FK_bookreview_book` (`book_no`),
  KEY `FK_bookreview_member` (`member_no`),
  CONSTRAINT `FK_bookreview_book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `FK_bookreview_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.bookreview: ~0 rows (대략적)
/*!40000 ALTER TABLE `bookreview` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookreview` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. member
CREATE TABLE IF NOT EXISTS `member` (
  `member_no` int(10) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) NOT NULL,
  `member_pw` varchar(50) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `member_addr` varchar(50) NOT NULL,
  `member_point` int(11) NOT NULL DEFAULT '0',
  `member_date` datetime NOT NULL,
  PRIMARY KEY (`member_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.member: ~5 rows (대략적)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_no`, `member_id`, `member_pw`, `member_name`, `member_addr`, `member_point`, `member_date`) VALUES
	(1, 'id001', 'pw001', '송원민', '1', 0, '2018-07-18 15:42:44'),
	(2, 'id002', 'pw002', '탁재은', '2', 0, '2018-07-18 15:47:27'),
	(3, 'id003', 'pw003', '최윤석', '3', 0, '2018-07-24 15:08:08'),
	(4, 'id004', 'pw004', '현희문', '4', 0, '2018-07-24 15:08:22'),
	(5, 'id005', 'pw005', '송유빈', '5', 0, '2018-07-24 15:08:40');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. memberinter
CREATE TABLE IF NOT EXISTS `memberinter` (
  `memberinter_no` int(10) NOT NULL AUTO_INCREMENT,
  `member_no` int(10) NOT NULL,
  `bookcode_no` int(10) NOT NULL,
  PRIMARY KEY (`memberinter_no`),
  KEY `FK_memberinter_member` (`member_no`),
  KEY `FK_memberinter_bookcode` (`bookcode_no`),
  CONSTRAINT `FK_memberinter_bookcode` FOREIGN KEY (`bookcode_no`) REFERENCES `bookcode` (`bookcode_no`),
  CONSTRAINT `FK_memberinter_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.memberinter: ~0 rows (대략적)
/*!40000 ALTER TABLE `memberinter` DISABLE KEYS */;
/*!40000 ALTER TABLE `memberinter` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. orders
CREATE TABLE IF NOT EXISTS `orders` (
  `orders_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_no` int(10) NOT NULL,
  `member_no` int(10) NOT NULL,
  `orders_amount` int(10) NOT NULL,
  `orders_price` int(10) NOT NULL,
  `orders_date` datetime NOT NULL,
  `orders_addr` varchar(50) NOT NULL,
  `orders_state` varchar(50) NOT NULL,
  PRIMARY KEY (`orders_no`),
  KEY `FK_orders_book` (`book_no`),
  KEY `FK_orders_member` (`member_no`),
  CONSTRAINT `FK_orders_book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `FK_orders_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.orders: ~0 rows (대략적)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. publisher
CREATE TABLE IF NOT EXISTS `publisher` (
  `publisher_no` int(10) NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(50) NOT NULL,
  `publisher_website` varchar(50) NOT NULL,
  PRIMARY KEY (`publisher_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.publisher: ~15 rows (대략적)
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` (`publisher_no`, `publisher_name`, `publisher_website`) VALUES
	(1, '리베르', 'http://www.liber.site/'),
	(3, '휴머니스트', 'http://www.humanistbooks.com/main/main.aspx'),
	(4, '문학바탕', 'http://www.munhakvatang.co.kr/'),
	(5, '위즈덤하우스', 'http://www.wisdomhouse.co.kr/'),
	(6, '길벗', 'http://www.gilbut.co.kr/main/main.aspx'),
	(7, '채륜', 'http://www.chaeryun.com/xe/'),
	(8, '세창 출판사', 'http://www.sechangpub.co.kr/'),
	(9, '중앙books', 'http://jbooks.joins.com/'),
	(10, '바다출판사', 'http://www.badabooks.co.kr/'),
	(11, '바오로딸', 'http://contents.pauline.or.kr/'),
	(12, 'IVP', 'http://www.ivp.co.kr/'),
	(13, '위고', 'https://www.facebook.com/hugobooks'),
	(14, '한국교육방송공사(EBSi)', 'http://www.ebsi.co.kr/ '),
	(15, '미호', 'https://post.naver.com/my.nhn?memberNo=15188734'),
	(16, '비상교육', 'http://www.visang.com/pageLoad?page_id=kr/main');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. qna
CREATE TABLE IF NOT EXISTS `qna` (
  `qna_no` int(10) NOT NULL AUTO_INCREMENT,
  `member_no` int(11) NOT NULL,
  `qna_title` varchar(50) NOT NULL,
  `qna_content` varchar(50) NOT NULL,
  `qna_date` datetime NOT NULL,
  PRIMARY KEY (`qna_no`),
  KEY `FK_qna_member` (`member_no`),
  CONSTRAINT `FK_qna_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.qna: ~0 rows (대략적)
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. qna_comment
CREATE TABLE IF NOT EXISTS `qna_comment` (
  `qna_comment_no` int(10) NOT NULL AUTO_INCREMENT,
  `qna_no` int(10) NOT NULL,
  `admin_no` int(10) NOT NULL,
  `comment_content` varchar(50) NOT NULL,
  `comment_date` varchar(50) NOT NULL,
  PRIMARY KEY (`qna_comment_no`),
  KEY `FK_qna_comment_qna` (`qna_no`),
  KEY `FK_qna_comment_admin` (`admin_no`),
  CONSTRAINT `FK_qna_comment_admin` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`),
  CONSTRAINT `FK_qna_comment_qna` FOREIGN KEY (`qna_no`) REFERENCES `qna` (`qna_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.qna_comment: ~0 rows (대략적)
/*!40000 ALTER TABLE `qna_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna_comment` ENABLE KEYS */;


-- 테이블 bookshop의 구조를 덤프합니다. shoppingcart
CREATE TABLE IF NOT EXISTS `shoppingcart` (
  `shoppingcart_no` int(10) NOT NULL AUTO_INCREMENT,
  `book_no` int(10) NOT NULL,
  `member_no` int(10) NOT NULL,
  `shoppingcart_amount` int(10) NOT NULL,
  `shoppingcart_price` int(10) NOT NULL,
  `shoppingcart_date` datetime NOT NULL,
  PRIMARY KEY (`shoppingcart_no`),
  KEY `FK_shoppingcart_book` (`book_no`),
  KEY `FK_shoppingcart_member` (`member_no`),
  CONSTRAINT `FK_shoppingcart_book` FOREIGN KEY (`book_no`) REFERENCES `book` (`book_no`),
  CONSTRAINT `FK_shoppingcart_member` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bookshop.shoppingcart: ~6 rows (대략적)
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
INSERT INTO `shoppingcart` (`shoppingcart_no`, `book_no`, `member_no`, `shoppingcart_amount`, `shoppingcart_price`, `shoppingcart_date`) VALUES
	(1, 15, 1, 1, 4500, '2018-07-24 15:12:33'),
	(2, 1, 1, 2, 30000, '2018-07-24 15:14:01'),
	(3, 2, 1, 2, 30000, '2018-07-24 15:14:07'),
	(4, 3, 1, 1, 12600, '2018-07-24 15:14:16'),
	(5, 4, 1, 1, 9000, '2018-07-24 15:14:25'),
	(6, 5, 1, 3, 27000, '2018-07-24 15:14:34');
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
