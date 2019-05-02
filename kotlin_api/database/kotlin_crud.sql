-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2019 at 08:00 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kotlin_crud`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_student`
--

CREATE TABLE `tb_student` (
  `nis` int(20) NOT NULL,
  `name` text,
  `address` text,
  `gender` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_student`
--

INSERT INTO `tb_student` (`nis`, `name`, `address`, `gender`) VALUES
(11603011, 'Abil Muhammad Iranian ', 'Jln Raya Puncak', 'Pria'),
(11605470, 'Farika Amanda', 'Jln. Raya Ciawi Timur', 'Wanita'),
(11605471, 'Fadlan Naufal', 'Jln. Raya Cibedug', 'Pria'),
(11605473, 'Fikri Ramadhan', 'Jln. Raya Sukabumi', 'Pria'),
(11605474, 'Fajar Subeki', 'Jln. Raya Puncak', 'Pria'),
(11605478, 'Fika Siti Aurelia', 'Jln. Raya Pajajaran', 'Wanita'),
(11605479, 'Fitri Nur Insani', 'Jln. Raya Tajur', 'Wanita');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_student`
--
ALTER TABLE `tb_student`
  ADD PRIMARY KEY (`nis`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
