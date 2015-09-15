-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 15. Sep 2015 um 13:26
-- Server Version: 5.6.21
-- PHP-Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `agb_tool_db`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `agb_advice`
--

CREATE TABLE IF NOT EXISTS `agb_advice` (
`agb_advice_id` int(11) NOT NULL,
  `link` varchar(200) NOT NULL,
  `submit_date` varchar(20) NOT NULL,
  `state` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `agb_favorite`
--

CREATE TABLE IF NOT EXISTS `agb_favorite` (
`agb_favorite_id` int(11) NOT NULL,
  `agb_source_id` int(11) NOT NULL,
  `counter` int(8) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `agb_source`
--

CREATE TABLE IF NOT EXISTS `agb_source` (
`agb_source_id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `link` varchar(400) NOT NULL,
  `xpath` varchar(80) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `agb_version`
--

CREATE TABLE IF NOT EXISTS `agb_version` (
`agb_version_id` int(11) NOT NULL,
  `agb_source_id` int(11) NOT NULL,
  `text` longtext NOT NULL,
  `version` int(11) NOT NULL,
  `published_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `agb_advice`
--
ALTER TABLE `agb_advice`
 ADD PRIMARY KEY (`agb_advice_id`), ADD UNIQUE KEY `link` (`link`);

--
-- Indizes für die Tabelle `agb_favorite`
--
ALTER TABLE `agb_favorite`
 ADD PRIMARY KEY (`agb_favorite_id`);

--
-- Indizes für die Tabelle `agb_source`
--
ALTER TABLE `agb_source`
 ADD PRIMARY KEY (`agb_source_id`);

--
-- Indizes für die Tabelle `agb_version`
--
ALTER TABLE `agb_version`
 ADD PRIMARY KEY (`agb_version_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `agb_advice`
--
ALTER TABLE `agb_advice`
MODIFY `agb_advice_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `agb_favorite`
--
ALTER TABLE `agb_favorite`
MODIFY `agb_favorite_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT für Tabelle `agb_source`
--
ALTER TABLE `agb_source`
MODIFY `agb_source_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=103;
--
-- AUTO_INCREMENT für Tabelle `agb_version`
--
ALTER TABLE `agb_version`
MODIFY `agb_version_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=155;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
