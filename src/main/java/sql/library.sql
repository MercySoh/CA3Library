-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2024 at 08:28 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `ca3library`
--
CREATE DATABASE IF NOT EXISTS `ca3library` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ca3library`;

-- --------------------------------------------------------

--
-- Table structure for table `bookgenres`
--

DROP TABLE IF EXISTS `bookgenres`;
CREATE TABLE `bookgenres` (
                              `bookID` int(11) NOT NULL,
                              `genreID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bookgenres`
--

INSERT INTO `bookgenres` (`bookID`, `genreID`) VALUES
                                                   (1, 1),
                                                   (1, 2),
                                                   (2, 1),
                                                   (2, 2),
                                                   (3, 2),
                                                   (4, 3),
                                                   (4, 1),
                                                   (5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
                         `bookID` int(11) NOT NULL,
                         `bookName` varchar(50) NOT NULL,
                         `author` varchar(50) NOT NULL,
                         `description` varchar(120) DEFAULT NULL,
                         `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`bookID`, `bookName`, `author`, `description`, `quantity`) VALUES
                                                                                    (1, 'one piece', 'oda', 'it is a manga of pirates', 1000),
                                                                                    (2, 'gintama', 'Hideaki Sorachi', 'it is a manga of gintama', 22),
                                                                                    (3, 'naruto', 'Masashi Kishimoto', 'it is a manga of ninjas', 5),
                                                                                    (4, 'Mieruko-chan', 'Tomoki Izumi', 'horror manga with ability to see ghost', 4),
                                                                                    (5, 'blue lock', 'yusuke nomura', 'hardcore soccer', 66);

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
CREATE TABLE `genres` (
                          `genreID` int(11) NOT NULL,
                          `genreName` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`genreID`, `genreName`) VALUES
                                                  (2, 'action'),
                                                  (1, 'comedy'),
                                                  (3, 'horror'),
                                                  (4, 'romance'),
                                                  (5, 'sci-fi');

-- --------------------------------------------------------

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
CREATE TABLE `loans` (
                         `loanID` int(11) NOT NULL,
                         `userID` int(11) NOT NULL,
                         `bookID` int(11) NOT NULL,
                         `borrowDate` date NOT NULL,
                         `dueDate` date NOT NULL,
                         `returnedDate` date DEFAULT NULL,
                         `fees` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loans`
--

INSERT INTO `loans` (`loanID`, `userID`, `bookID`, `borrowDate`, `dueDate`, `returnedDate`, `fees`) VALUES
                                                                                                        (9, 1, 2, '2023-12-20', '2023-12-21', '2023-12-27', '6.00'),
                                                                                                        (10, 1, 1, '2023-12-20', '2024-01-03', '2023-12-20', NULL),
                                                                                                        (11, 1, 3, '2023-12-20', '2024-01-03', '2023-12-20', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `userID` int(11) NOT NULL,
                         `userName` varchar(50) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `password` varchar(80) NOT NULL,
                         `address` varchar(80) DEFAULT NULL,
                         `phone` varchar(20) DEFAULT NULL,
                         `userType` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `userName`, `email`, `password`, `address`, `phone`, `userType`) VALUES
                                                                                                    (1, 'jerry', 'jerry@gmail.com', '$2a$10$K.uvVBVFs1HRMO83Y6Er0.Qx6CTy40VJf38TgkA7csG1.ecyKctUC', 'address', '231030213', 0),
                                                                                                    (2, 'admin', 'admin@gmail.com', '$2a$10$KLMZQ1aqt95iVgI/ir1qWOmE.Docxd5VucQRSIE62IkAVhvPVbXk2', 'address', '0231030213', 1),
                                                                                                    (3, 'bob', 'bob@gmail.com', '$2a$10$HdnetGtgXFa5DNGu2pWLcO2bv/VOH29b4GoEUrKRyIOLbUfbrck6G', 'asdsad', '213213213231', 0),
                                                                                                    (4, 'saa', 'sadsadasdsad', '$2a$10$FTZf4szQpabJX10n0JmN/eVGdlLb1ovw9CnsGSnfMBHn1bLrQfnvO', 'sadsad', 'asdsadsad', 0),
                                                                                                    (5, 'asd', 'asdsadss', '$2a$10$pS6WX8LEKqAqGRfswI/JRe6N7XmNXA78/DuoFcsO.lmxDhghPhDo6', 'aa', 'aa', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookgenres`
--
ALTER TABLE `bookgenres`
    ADD KEY `bookID` (`bookID`),
  ADD KEY `genreID` (`genreID`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
    ADD PRIMARY KEY (`bookID`);

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
    ADD PRIMARY KEY (`genreID`),
  ADD UNIQUE KEY `genreName` (`genreName`);

--
-- Indexes for table `loans`
--
ALTER TABLE `loans`
    ADD PRIMARY KEY (`loanID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `bookID` (`bookID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `userName` (`userName`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
    MODIFY `bookID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
    MODIFY `genreID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `loans`
--
ALTER TABLE `loans`
    MODIFY `loanID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookgenres`
--
ALTER TABLE `bookgenres`
    ADD CONSTRAINT `bookgenres_ibfk_1` FOREIGN KEY (`bookID`) REFERENCES `books` (`bookID`),
  ADD CONSTRAINT `bookgenres_ibfk_2` FOREIGN KEY (`genreID`) REFERENCES `genres` (`genreID`);

--
-- Constraints for table `loans`
--
ALTER TABLE `loans`
    ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  ADD CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`bookID`) REFERENCES `books` (`bookID`);
COMMIT;
