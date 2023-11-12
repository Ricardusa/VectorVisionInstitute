INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Jan 1 ','New Year''s Day','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Oct 31 ','Halloween','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Nov 24 ','Thanksgiving Day','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Dec 25 ','Christmas','FESTIVAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Jan 17 ','Martin Luther King Jr. Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' July 4 ','Independence Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Sep 5 ','Labor Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`, `created_by`)
VALUES (' Nov 11 ','Veterans Day','FEDERAL',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
VALUES ('STUDENT',CURDATE(),'DBA');

INSERT INTO `person` (`name`,`email`,`mobile_number`,`pwd`,`role_id`,`created_at`, `created_by`)
VALUES ('Admin','admin@gmail.com','3443434343','$2a$10$XhU4UcSxDPb5G0I0fT/CZ.Lfj2VW2fkLkUP5cOEM.xM8EzyUQXaD2', 1 ,CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Eve','3159876421','eve@gmail.com','Job Inquiry','Interested in a job as a Full Stack Developer','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Bob','4087652398','bob@gmail.com','Job Application','Enquiry about job opportunities as a Game Developer','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Charlie','5103248796','charlie@gmail.com','Job Opportunity','Looking for job opportunities in the field of AI','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Diana','6129873456','diana@gmail.com','Job Inquiry','Interested in a job related to Design','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Frank','7198765432','frank@gmail.com','Job Application','Looking for job opportunities in Cybersecurity','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Grace','8234567890','grace@gmail.com','Job Inquiry','Inquiring about job opportunities as a Data Analyst','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Ivan','9876543210','ivan@gmail.com','Job Inquiry','Interested in a job as a Full Stack Developer','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Hannah','7654321098','hannah@gmail.com','Job Application','Enquiry about job opportunities as a Game Developer','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Jack','8765432109','jack@gmail.com','Job Opportunity','Looking for job opportunities in the field of AI','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Lily','6543210987','lily@gmail.com','Job Inquiry','Interested in a job related to Design','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Mike','8901234567','mike@gmail.com','Job Application','Looking for job opportunities in Cybersecurity','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Nina','0123456789','nina@gmail.com','Job Inquiry','Inquiring about job opportunities as a Data Analyst','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Oliver','2345678901','oliver@gmail.com','Job Inquiry','Interested in a job as a Full Stack Developer','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Penelope','3456789012','penelope@gmail.com','Job Application','Enquiry about job opportunities as a Game Developer','Open',CURDATE(),'DBA');

INSERT INTO `contact_msg` (`name`,`mobile_num`,`email`,`subject`,`message`,`status`,`created_at`, `created_by`)
VALUES ('Quincy','4567890123','quincy@gmail.com','Job Opportunity','Looking for job opportunities in the field of AI','Open',CURDATE(),'DBA');
