-- phpMyAdmin SQL Dump
-- version 4.1.5
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Мар 18 2014 г., 13:47
-- Версия сервера: 5.6.15
-- Версия PHP: 5.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `exam_tickets`
--

-- --------------------------------------------------------

--
-- Структура таблицы `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
  `subj_id` int(10) NOT NULL AUTO_INCREMENT,
  `subj_name` varchar(1000) NOT NULL,
  PRIMARY KEY (`subj_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=118 ;

--
-- Дамп данных таблицы `subjects`
--

INSERT INTO `subjects` (`subj_id`, `subj_name`) VALUES
(113, 'Сетевое администрирование'),
(114, 'Физика');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fio` varchar(100) NOT NULL DEFAULT 'Вася Пупкин',
  `login` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `access_level` int(1) NOT NULL DEFAULT '5',
  `hash` varchar(64) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `subj_id` int(10) DEFAULT NULL,
  `ticket` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`login`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Таблица зарегистрированных пользователей' AUTO_INCREMENT=87 ;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `fio`, `login`, `password`, `email`, `access_level`, `hash`, `ip`, `subj_id`, `ticket`) VALUES
(73, 'Администратор', 'admin', 'e4a2ef3c97af5e252ad88f2cf9eeafac', 'krotmen@list.ru', 1, 'BwGWZk06ARzK4mXTt7b2NguMle5a8iqrofnHEQdFsVIjcp3hLYUPJC9yDv1SOx', '192.168.128.243', 0, ''),
(86, 'Петров Петр Петрович', 'petr', '2f0714f5365318775c8f50d720a307dc', 'petr@mail.ru', 5, 'VlaCJjg51WiDEcAmBe869SQHyIMPhvNRqFO7xdwZ3XtnG4TuYpUsf0br2LkzoK', '192.168.128.243', 117, '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
