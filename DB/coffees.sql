-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2014 at 12:05 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dreamhome`
--

-- --------------------------------------------------------

--
-- Table structure for table `coffees`
--

CREATE TABLE IF NOT EXISTS `coffees` (
`ID` int(11) NOT NULL,
  `SALES` int(11) NOT NULL,
  `COF_NAME` varchar(255) NOT NULL,
  `TOTAL` int(11) NOT NULL,
  `PRICE` float NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `coffees`
--

INSERT INTO `coffees` (`ID`, `SALES`, `COF_NAME`, `TOTAL`, `PRICE`) VALUES
(1, 15, 'Coffe1', 40, 10),
(2, 22, 'Coffe2', 82, 15),
(3, 55, 'Coffe3', 55, 20),
(4, 10, 'Coffe4', 55, 37.5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coffees`
--
ALTER TABLE `coffees`
 ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `coffees`
--
ALTER TABLE `coffees`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
