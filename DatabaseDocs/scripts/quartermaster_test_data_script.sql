-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 27, 2014 at 03:13 PM
-- Server version: 5.5.40-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.4

SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `quartermaster_test`
--
drop database if exists `quartermaster_test`;
CREATE DATABASE IF NOT EXISTS `quartermaster_test` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci;
USE `quartermaster_test`;
-- --------------------------------------------------------

--
-- Table structure for table `assets`
--

CREATE TABLE IF NOT EXISTS `assets` (
  `asset_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_type_id` int(11) NOT NULL,
  `serial_number` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `in_stock` bit(1) NOT NULL DEFAULT b'1',
  `damage_loss_theft` varchar(20) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`asset_id`),
  KEY `asset_type_id` (`asset_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=26 ;

--
-- Dumping data for table `assets`
--

INSERT INTO `assets` (`asset_id`, `asset_type_id`, `serial_number`, `in_stock`, `damage_loss_theft`) VALUES
(1, 1, '1111', b'1', '\\N'),
(2, 1, '2222', b'1', '\\N'),
(3, 2, '11112', b'1', '\\N'),
(4, 2, '11113', b'1', '\\N'),
(5, 2, '11114', b'1', '\\N'),
(6, 3, '33331', b'1', '\\N'),
(7, 3, '33332', b'1', '\\N'),
(8, 3, '33333', b'1', '\\N'),
(9, 4, '4441', b'1', '\\N'),
(10, 4, '4442', b'1', '\\N'),
(11, 4, '4443', b'1', '\\N'),
(12, 5, '551', b'1', '\\N'),
(13, 5, '552', b'1', '\\N'),
(14, 5, '553', b'1', '\\N'),
(15, 5, '554', b'1', '\\N'),
(16, 6, '61', b'1', '\\N'),
(17, 6, '62', b'1', '\\N'),
(18, 6, '63', b'1', '\\N'),
(19, 7, '7', b'1', '\\N'),
(20, 8, '81', b'1', '\\N'),
(21, 8, '82', b'1', '\\N'),
(22, 8, '83', b'1', '\\N'),
(23, 8, '84', b'1', '\\N'),
(24, 8, '85', b'1', '\\N'),
(25, 8, '86', b'1', '\\N');

-- --------------------------------------------------------

--
-- Table structure for table `assets_events`
--

CREATE TABLE IF NOT EXISTS `assets_events` (
  `asset_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `return_date` date DEFAULT NULL,
  `damage_loss_theft` varchar(20) COLLATE latin1_general_ci NULL,
  PRIMARY KEY (`asset_id`,`event_id`),
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `assets_events`
--
DELETE FROM `assets_events`;
INSERT INTO `assets_events` (`asset_id`, `event_id`, `return_date`, `damage_loss_theft`) VALUES
(1, 1, NULL, 'none'),
(9, 3, NULL, 'one'),
(10, 4, '2014-10-29', 'none'),
(11, 6, NULL, 'two'),
(13, 5, NULL, 'three'),
(15, 6, NULL, 'four'),
(17, 4, NULL, 'five');

-- --------------------------------------------------------

--
-- Table structure for table `asset_notes`
--

CREATE TABLE IF NOT EXISTS `asset_notes` (
  `asset_note_id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_id` int(11) NOT NULL,
  `note_detail` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `note_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `note_category` varchar(20) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`asset_note_id`),
  KEY `asset_id` (`asset_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=16 ;

--
-- Dumping data for table `asset_notes`
--
DELETE FROM `asset_notes`;
INSERT INTO `asset_notes` (`asset_note_id`, `asset_id`, `note_detail`, `note_date`, `note_category`) VALUES
(1, 1, 'asset id 1 note', '0000-00-00 00:00:00', ''),
(2, 2, 'asset id 2 note', '0000-00-00 00:00:00', ''),
(3, 3, 'asset id 3 note', '0000-00-00 00:00:00', ''),
(4, 4, 'asset id 4 note', '0000-00-00 00:00:00', ''),
(5, 5, 'asset id 5 note', '0000-00-00 00:00:00', ''),
(6, 6, 'asset id 6 note', '0000-00-00 00:00:00', ''),
(7, 7, 'asset id 7 note', '0000-00-00 00:00:00', ''),
(8, 1, 'asset id 1 note 2', '0000-00-00 00:00:00', ''),
(9, 2, 'asset id 2 note2', '0000-00-00 00:00:00', ''),
(10, 3, 'asset id 3 note2', '0000-00-00 00:00:00', ''),
(11, 4, 'asset id 4 note 2', '0000-00-00 00:00:00', ''),
(12, 5, 'asset id 5 note 2', '0000-00-00 00:00:00', ''),
(13, 6, 'asset id 6 note 2', '0000-00-00 00:00:00', ''),
(14, 7, 'asset id 7 note 2', '0000-00-00 00:00:00', ''),
(15, 8, 'asset id 8 note 2', '0000-00-00 00:00:00', '');

-- --------------------------------------------------------

--
-- Table structure for table `asset_types`
--

CREATE TABLE IF NOT EXISTS `asset_types` (
  `asset_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `category_id` int(11) NOT NULL,
  `image_path` varchar(35) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`asset_type_id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `asset_types`
--
DELETE FROM `asset_types`;
INSERT INTO `asset_types` (`asset_type_id`, `name`, `category_id`, `image_path`) VALUES
(1, 'tent', 2, ''),
(2, 'basketballs', 6, ''),
(3, 'sleeping bags', 8, ''),
(4, 'large tarp', 2, ''),
(5, 'small tarp', 2, ''),
(6, 'Stakes(20 pack)', 2, ''),
(7, 'large dutch oven', 1, ''),
(8, 'small dutch oven', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(40) COLLATE latin1_general_ci NOT NULL,
  `authority` varchar(40) COLLATE latin1_general_ci NOT NULL,
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `categories`
--
DELETE FROM `categories`;
INSERT INTO `categories` (`category_id`, `category_name`) VALUES
(1, 'cookware'),
(2, 'shelter'),
(3, 'lighting'),
(4, 'watersports'),
(5, 'games'),
(6, 'sports equipment'),
(7, 'winter sports'),
(8, 'bedding'),
(9, 'tables'),
(10, 'chairs');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--
CREATE TABLE IF NOT EXISTS `events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `event_name` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `check_out_date` date NOT NULL,
  `due_date` date NOT NULL,
  `is_open` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`event_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `events`
--
DELETE FROM `events`;
INSERT INTO `events` (`event_id`, `user_id`, `event_name`, `check_out_date`, `due_date`, `is_open`) VALUES
(1, 3, 'Boy Scout Summer Campout', '2014-10-25', '2014-10-25', b'1'),
(2, 4, 'Men''s Softball Canoe trip', '2014-10-25', '2014-10-25', b'1'),
(3, 5, 'Ymca summer adventure', '2014-10-25', '2014-10-25', b'1'),
(4, 6, 'Charity Church Bible Camp', '2014-10-25', '2014-10-25', b'1'),
(5, 7, 'Girl scouts camping trip', '2014-10-24', '2014-10-25', b'1'),
(6, 8, 'Software Craftsmanship Guild W', '2014-10-24', '2014-10-25', b'1'),
(7, 8, 'Software Craftsmanship Guild c', '2014-10-24', '2014-10-25', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `event_notes`
--

CREATE TABLE IF NOT EXISTS `event_notes` (
  `event_note_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `note_detail` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `note_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_note_id`),
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `event_notes`
--
DELETE FROM `event_notes`;
INSERT INTO `event_notes` (`event_note_id`, `event_id`, `note_detail`, `note_date`) VALUES
(1, 1, 'Boy Scout Campout note', '0000-00-00 00:00:00'),
(2, 2, 'Canoe Trip note', '0000-00-00 00:00:00'),
(3, 3, 'YMCA Summer Adventure Note', '0000-00-00 00:00:00'),
(4, 4, 'Bible Camp Note', '0000-00-00 00:00:00'),
(5, 5, 'Camping trip', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `password` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `good_standing` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=15 ;

--
-- Dumping data for table `users`
--
DELETE FROM `users`;
INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`, `name`, `good_standing`) VALUES
(3, 'bill', '', b'1', '1', b'1'),
(4, 'stuart', '', b'1', '1', b'1'),
(5, 'colin', '', b'1', '1', b'1'),
(6, 'jennifer', '', b'1', '1', b'1'),
(7, 'eugene', '', b'1', '1', b'1'),
(8, 'rachel', '', b'1', '1', b'1'),
(9, 'kevin', '', b'1', '0', b'1'),
(10, 'natalie', '', b'1', '0', b'1'),
(11, 'brian', '', b'1', '0', b'1'),
(12, 'jakob', '', b'1', '0', b'1'),
(13, 'josephine', '', b'1', '0', b'1'),
(14, 'shawn', '', b'1', '0', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `user_notes`
--

CREATE TABLE IF NOT EXISTS `user_notes` (
  `user_note_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `note_detail` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `note_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_note_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=13 ;

--
-- Dumping data for table `user_notes`
--
DELETE FROM `user_notes`;
INSERT INTO `user_notes` (`user_note_id`, `user_id`, `note_detail`, `note_date`) VALUES
(1, 5, 'User ID 5 note', '0000-00-00 00:00:00'),
(2, 3, 'User ID 3 note', '0000-00-00 00:00:00'),
(3, 3, 'User ID 3 note', '0000-00-00 00:00:00'),
(4, 4, 'User ID 4 note', '0000-00-00 00:00:00'),
(5, 5, 'User ID 5 note', '0000-00-00 00:00:00'),
(6, 6, 'User ID 6 note', '0000-00-00 00:00:00'),
(7, 7, 'User ID 7 note', '0000-00-00 00:00:00'),
(8, 8, 'User ID 8 note', '0000-00-00 00:00:00'),
(9, 9, 'User ID 9 note', '0000-00-00 00:00:00'),
(10, 10, 'User ID 1 note', '0000-00-00 00:00:00');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `assets`
--
ALTER TABLE `assets`
  ADD CONSTRAINT `asset_type_idfk` FOREIGN KEY (`asset_type_id`) REFERENCES `asset_types` (`asset_type_id`);

--
-- Constraints for table `assets_events`
--
ALTER TABLE `assets_events`
  ADD CONSTRAINT `asset_idfk` FOREIGN KEY (`asset_id`) REFERENCES `assets` (`asset_id`),
  ADD CONSTRAINT `event_idfk` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`);

--
-- Constraints for table `asset_notes`
--
ALTER TABLE `asset_notes`
  ADD CONSTRAINT `asset_notes_ibfk_1` FOREIGN KEY (`asset_id`) REFERENCES `assets` (`asset_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `asset_types`
--
ALTER TABLE `asset_types`
  ADD CONSTRAINT `category_ibfk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `username_authorityfk` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `user_idfk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `event_notes`
--
ALTER TABLE `event_notes`
  ADD CONSTRAINT `event_id_notefk` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_notes`
--
ALTER TABLE `user_notes`
  ADD CONSTRAINT `user_id_notefk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
