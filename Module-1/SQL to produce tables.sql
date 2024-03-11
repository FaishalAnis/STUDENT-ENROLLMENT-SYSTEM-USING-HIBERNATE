CREATE TABLE `enrolledStudent`(
    `studentID [pk]` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `courseID [fk]` VARCHAR(255) NOT NULL,
    `courseName` BIGINT NOT NULL,
    `enrollmentDate` DATETIME NOT NULL
);
CREATE TABLE `appliedStudent`(
    `studentID [pk]` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `address` VARCHAR(255) NOT NULL,
    `phoneNum` BIGINT NOT NULL,
    `name` CHAR(255) NOT NULL,
    `DOB` DATE NOT NULL,
    `courseID` BIGINT NOT NULL,
    `status` TINYINT(1) NOT NULL
);
CREATE TABLE `subject`(
    `subCode [pk]` VARCHAR(255) NOT NULL,
    `subName` CHAR(255) NOT NULL,
    `courseID [fk]` BIGINT NOT NULL,
    `lecturerID [fk]` BIGINT NOT NULL,
    PRIMARY KEY(`subCode [pk]`)
);
CREATE TABLE `lecturer_copy`(
    `lecturerID [pk]` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `lecturerName` CHAR(255) NOT NULL,
    `courseID` BIGINT NOT NULL,
    `subjectCode` VARCHAR(255) NOT NULL
);
CREATE TABLE `course`(
    `courseID [pk]` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `courseName` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `enrolledStudent` ADD CONSTRAINT `enrolledstudent_courseid [fk]_foreign` FOREIGN KEY(`courseID [fk]`) REFERENCES `course`(`courseID [pk]`);
ALTER TABLE
    `lecturer_copy` ADD CONSTRAINT `lecturer_copy_courseid_foreign` FOREIGN KEY(`courseID`) REFERENCES `course`(`courseID [pk]`);
ALTER TABLE
    `enrolledStudent` ADD CONSTRAINT `enrolledstudent_studentid [pk]_foreign` FOREIGN KEY(`studentID [pk]`) REFERENCES `appliedStudent`(`studentID [pk]`);
ALTER TABLE
    `subject` ADD CONSTRAINT `subject_lecturerid [fk]_foreign` FOREIGN KEY(`lecturerID [fk]`) REFERENCES `lecturer_copy`(`lecturerID [pk]`);
ALTER TABLE
    `subject` ADD CONSTRAINT `subject_courseid [fk]_foreign` FOREIGN KEY(`courseID [fk]`) REFERENCES `course`(`courseID [pk]`);