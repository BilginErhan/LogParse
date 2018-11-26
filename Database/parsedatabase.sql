-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1:3306
-- Üretim Zamanı: 04 Kas 2018, 20:13:55
-- Sunucu sürümü: 5.7.23
-- PHP Sürümü: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `parsedatabase`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `bitrate_tbl`
--

DROP TABLE IF EXISTS `bitrate_tbl`;
CREATE TABLE IF NOT EXISTS `bitrate_tbl` (
  `file_name` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `bitrate_type` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `bitrate` int(11) NOT NULL,
  `bitrate_rate` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`file_name`,`bitrate_type`,`bitrate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `bitrate_tbl`
--

INSERT INTO `bitrate_tbl` (`file_name`, `bitrate_type`, `bitrate`, `bitrate_rate`) VALUES
('deneme.access.log', 'audio', 90000, '51.73913043478261'),
('deneme.access.log', 'video', 400000, '2.1739130434782608'),
('deneme.access.log', 'video', 1400000, '43.69565217391304'),
('deneme.access.log', 'video', 800000, '2.391304347826087'),
('nginx.access.log', 'audio', 90000, '46.9075478498679'),
('nginx.access.log', 'audio', 1400000, '3.715773085169235E-5'),
('nginx.access.log', 'video', 300000, '0.09122222924090471'),
('nginx.access.log', 'video', 400000, '5.433054774211048'),
('nginx.access.log', 'video', 700000, '7.43154617033847E-5'),
('nginx.access.log', 'video', 1300000, '2.023164129412945'),
('nginx.access.log', 'video', 800000, '4.953831519416772'),
('nginx.access.log', 'video', 1400000, '40.59106802465787');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `browser_tbl`
--

DROP TABLE IF EXISTS `browser_tbl`;
CREATE TABLE IF NOT EXISTS `browser_tbl` (
  `file_name` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `browser_name` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `browser_rate` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`file_name`,`browser_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `browser_tbl`
--

INSERT INTO `browser_tbl` (`file_name`, `browser_name`, `browser_rate`) VALUES
('deneme.access.log', 'Firefox', '78.26086956521739'),
('deneme.access.log', 'Chrome', '16.304347826086957'),
('nginx.access.log', 'Firefox', '76.89636337288155'),
('deneme.access.log', 'Mozilla', '5.434782608695652'),
('nginx.access.log', 'Dalvik', '1.1147319255507705E-4'),
('nginx.access.log', 'SonyC6603 Build', '3.715773085169235E-5'),
('nginx.access.log', 'SonyD6503 Build', '4.0873503936861587E-4'),
('nginx.access.log', 'NSPlayer', '4.830505010720005E-4'),
('nginx.access.log', 'AppleCoreMedia', '0.048936731531678816'),
('nginx.access.log', 'Chrome', '17.911586895211485'),
('nginx.access.log', 'stagefright', '0.013413940837460938'),
('nginx.access.log', 'Mozilla', '5.128398538958023'),
('nginx.access.log', 'QuickTime', '2.6010411596184645E-4');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `http_status_tbl`
--

DROP TABLE IF EXISTS `http_status_tbl`;
CREATE TABLE IF NOT EXISTS `http_status_tbl` (
  `file_name` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `status_type` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `status_rate` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`file_name`,`status_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `http_status_tbl`
--

INSERT INTO `http_status_tbl` (`file_name`, `status_type`, `status_rate`) VALUES
('deneme.access.log', '200', '100.0'),
('nginx.access.log', '504', '0.22439553661337008'),
('nginx.access.log', '404', '2.972618468135388E-4'),
('nginx.access.log', '206', '0.0018950442734363096'),
('nginx.access.log', '304', '0.10314986084429796'),
('nginx.access.log', '403', '0.2812468648164594'),
('nginx.access.log', '502', '0.3573458976007253'),
('nginx.access.log', '499', '0.026865039405773564'),
('nginx.access.log', '400', '0.10545364015710289'),
('nginx.access.log', '200', '98.89935085444202');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `parse_tbl`
--

DROP TABLE IF EXISTS `parse_tbl`;
CREATE TABLE IF NOT EXISTS `parse_tbl` (
  `file_name` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  `unique_ip` int(11) NOT NULL,
  `unique_content` int(11) NOT NULL,
  `total_data` double NOT NULL,
  `most_user_ip` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`file_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `parse_tbl`
--

INSERT INTO `parse_tbl` (`file_name`, `unique_ip`, `unique_content`, `total_data`, `most_user_ip`) VALUES
('deneme.access.log', 76, 38, 0, '168.1.75.29'),
('nginx.access.log', 420, 207, 861, '192.240.109.106');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
