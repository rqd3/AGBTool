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

--
-- Daten für Tabelle `agb_source`
--

INSERT INTO `agb_source` (`agb_source_id`, `name`, `link`, `xpath`) VALUES
(39, 'Amazon', 'http://www.amazon.de/gp/help/customer/display.html/ref=footer_cou?ie=UTF8&nodeId=505048', '//*[contains(concat(" ", normalize-space(@class), " "), " help-content")]'),
(40, 'Facebook', 'https://www.facebook.com/legal/terms?locale=de_DE', '//html/body/div/div[2]/div[1]/div/div'),
(41, 'Ebay', 'http://pages.ebay.de/help/policies/user-agreement.html', '//*[contains(concat(" ", normalize-space(@class), " "), " gryBxMainBar")]'),
(42, 'Google', 'http://www.google.de/intl/de/policies/terms/regional.html', '//html/body/div[3]/div[2]'),
(43, 'Youtube', 'https://www.youtube.com/static?gl=DE&template=terms&hl=de', '//*[@id="yts-article"]'),
(44, 'Web.de', 'http://agb.web.de/WEB.DE/AGB/20120801/', '/html/body/center/table'),
(45, 'Bild.de', 'http://www.bild.de/corporate-site/agb/bild-de/artikel-agb-2952414.bild.html', '//html/body/div[2]/div/article/div/div[1]'),
(46, 'GMX', 'https://service.gmx.net/de/cgi/g.fcgi/products/mail/agb', '//html/body/div[3]/div[4]/div[2]/div/div'),
(47, 'Yahoo', 'https://policies.yahoo.com/ie/de/yahoo/terms/utos/index.htm', '//html/body/main/div[2]/div/div/div[2]/div[1]/div/div'),
(48, 'Spiegel', 'http://abo.spiegel.de/de/c/abo-service/spiegel-abo-agb', '//html/body/div[3]/div/div/div'),
(49, 'Chip', 'http://www.chip.de/s_specials/Nutzungshinweis-CHIP-Online_45829065.html', '//*[@id="agb"]'),
(50, 'mobile.de', 'http://cms.mobile.de/de/home/agb_oeb.html', '/html/body/div[1]/div[2]/div[1]'),
(51, 'Paypal', 'https://www.paypal.com/de/webapps/mpp/ua/useragreement-full', '//html/body/div/div/section/div/section/section[2]'),
(52, 'Focus', 'http://www.tomorrow-focus-media.de/unternehmen/agb/websites-agb/', '//html/body/div/div[2]/div[1]/div/div/div'),
(53, 'Live.com', 'http://windows.microsoft.com/en-us/windows/microsoft-services-agreement', '/html/body/div[2]/div/div/div[1]'),
(54, 'streamcloud', 'http://streamcloud.eu/tos.html', '/html/body/div[2]/div[2]'),
(55, 'Immobilienscout24', 'http://www.immobilienscout24.de/agb/nutzungsagb.html', '/html/body/div[1]/div/div[2]/div/article'),
(56, 'Gutefrage', 'http://www.gutefrage.net/agb', '/html/body/div[2]/div/div[3]/div[2]/div/div'),
(57, 'Twitter', 'https://twitter.com/tos?lang=de', '/html/body/div[2]/article/div/div/div/div/div/div/div/div/div'),
(58, 'Microsoft', 'http://www.microsoft.com/de-de/rechtliche-hinweise/nutzungsbedingungen.aspx', '/html/body/div[3]/div/div'),
(59, 'Idealo', 'http://www.idealo.de/preisvergleich/AGB.html', '/html/body/div[2]/div[1]/div[1]/div'),
(62, 'Bing', 'http://www.microsoft.com/en-us/servicesagreement/', '/html/body/div[2]/div/div/div[1]'),
(63, 'Kicker', 'http://www.kicker.de/home/350282/nutzungsbedingungen.html', '//*[@id="ovArtikel"]'),
(64, 'Otto', 'https://www.otto.de/shoppages/service/agb', '/html/body/div[2]/div/div[3]/div[1]'),
(65, 'Welt', 'http://www.welt.de/services/article122129231/Nutzungsbedingungen-DIE-WELT-Digital.html', '//*[@id="main"]/div[4]/div[1]'),
(66, 'Ask', 'http://sp.de.ask.com/de/a11/docs/legal/terms.shtml', '//*[@id="con"]'),
(67, 'Chefkoch', 'http://www.chefkoch.de/terms-of-use.php', '/html/body/div[1]/div/div[2]/div[2]/div/div/div/div'),
(68, 'Booking', 'http://www.booking.com/content/terms.html?sid=06aa9b6703bb0e5da035590ce105b1b4;dcid=1', '/html/body/div[1]/div/div[3]/div[3]'),
(69, 'Tumblr', 'https://www.tumblr.com/policy/en/terms-of-service', '//*[@id="left_column"]'),
(70, 'wetteronline', 'http://www.wetteronline.de/agb', '//*[contains(concat(" ", normalize-space(@class), " "), "infopage")]'),
(71, 'Xing', 'https://www.xing.com/terms', '//*[@id="maincontent"]/div/div/div/div'),
(72, 'MSN', 'http://www.microsoft.com/en-us/servicesagreement/', '/html/body/div[2]/div/div/div[1]'),
(73, 'AutoScout24', 'http://about.autoscout24.com/de-de/au-company/au-company-agb/au-company-agb-as24.aspx', '/html/body/div[3]/div[3]'),
(75, 'ImmobilienScout24', 'http://www.immobilienscout24.de/agb/nutzungsagb.html', '/html/body/div[1]/div/div[2]/div/article'),
(78, 'wordpress', 'https://de.wordpress.com/tos/', '/html/body/div[1]/div[1]/div'),
(79, 'computerbild', 'http://www.computerbild.de/AGB-919758.html', '/html/body/div[6]/div/div[3]/div[1]/div'),
(80, 'instagram', 'https://help.instagram.com/478745558852511', '/html/body/div/div[3]/div[2]/div'),
(81, 'linkedin', 'http://www.linkedin.com/legal/user-agreement', '/html/body/div[3]/div/div[2]/div'),
(82, 'bs.to', 'http://bs.to/regeln', '/html/body/div/section'),
(83, 'sport1', 'http://www.sport1.de/unternehmen/agb', '/html/body/div[1]/div/div[2]/article/div[2]'),
(84, 'zeit', 'http://www.zeit.de/administratives/agb-kommentare-artikel', '/html/body/div[2]/div[2]/div[2]/div[1]/div[1]'),
(85, 'twitch.tv', 'http://www.twitch.tv/user/legal?page=terms_of_service', '/html/body/div[4]/div/div/div/article/section/ol'),
(86, 'wetter.de', 'http://www.wetter.de/cms/nutzungsbedingungen-premium-wetter-1234567.html?c=caa6&i=51', '/html/body/div[1]/div[2]/div/div[5]/div[1]/div[2]/article'),
(87, 'n-tv', 'http://www.n-tv.de/ntvintern/n-tv-Webseiten-article495196.html', '/html/body/div[3]/div[1]/div[1]/div/article'),
(88, 'apple', 'http://www.apple.com/de/legal/terms/site.html', '//*[@id="content"]'),
(89, 'zalando', 'http://www.zalando.de/zalando-agb/', '//*[contains(concat(" ", normalize-space(@class), " "), "flatpage agb")]'),
(90, 'stern', 'http://www.stern.de/agb-allgemeine-geschaeftsbedingungen-4541846.html', '/html/body/div[2]/div[1]/main/article'),
(91, 'leo', 'http://dict.leo.org/pages/about/ende/termsOfUsage_de.html', '/html/body/div[4]/div[2]/div/div/div/div'),
(92, 'imgur', 'http://imgur.com/tos', '/html/body/div[9]'),
(93, 'transfermarkt', 'http://www.transfermarkt.de/intern/anb', '//*[@id="main"]/div[8]/div/div'),
(94, 'mediamarkt', 'http://www.mediamarkt.de/webapp/wcs/stores/servlet/MultiChannelCMSPrintLayer?storeId=19501&langId=-3&layer=terms', '/html/body/div[2]'),
(96, 'holidaycheck', 'http://www.holidaycheck.de/agb', '/html/body/section/div'),
(97, 'pinterest', 'https://about.pinterest.com/de/terms-service', '//*[@id="content"]/section/div[1]/div[1]/div/div[2]/div/div/div/table/tbody'),
(98, 'giga', 'http://www.giga.de/info/nutzungsbedingungen/', '/html/body/div[2]/div/div/section[1]/div'),
(99, 'aol', 'http://hilfe.aol.de/article-legal/agb/', '/html/body/div/div[2]/div[3]/div/div/div[2]/div'),
(100, 'immowelt', 'http://www.immowelt.de/immoweltag/agb/einzelanzeigen', '/html/body/div/div[1]/div[3]/div/div'),
(101, 'Das Örtliche', 'http://www3.dasoertliche.de/?cmd=cmd_nav_termsofuse&la=&buc=&page=0&context=0&action=18', '/html/body/div[3]/div[3]/div/div/div'),
(102, 'reddit', 'https://www.reddit.com/help/useragreement', '/html/body/div[2]/div[2]');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
