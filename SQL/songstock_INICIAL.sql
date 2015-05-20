-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 23-02-2015 a las 00:03:23
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `songstock`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion`
--

CREATE TABLE IF NOT EXISTS `cancion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `interprete` int(11) NOT NULL,
  `anhio` varchar(4) NOT NULL,
  `album` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cancion_interprete_idx` (`interprete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE IF NOT EXISTS `compra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comprador` bigint(20) NOT NULL,
  `vendedor` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `precio` bigint(20) NOT NULL,
  `disco` int(11) NOT NULL,
  `calificacion` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_compra_disco_idx` (`disco`),
  KEY `fk_compra_vendedor_idx` (`comprador`),
  KEY `fk_compra_comprador_idx` (`vendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disco`
--

CREATE TABLE IF NOT EXISTS `disco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `genero` smallint(6) NOT NULL,
  `imagen` varchar(200) NOT NULL,
  `interprete` int(11) NOT NULL,
  `precio` bigint(20) NOT NULL,
  `stock` int(11) NOT NULL,
  `anhio` year(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_disco_interprete_idx` (`interprete`),
  KEY `fk_disco_genero_idx` (`genero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `discocancion`
--

CREATE TABLE IF NOT EXISTS `discocancion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disco` int(11) NOT NULL,
  `cancion` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dc_disco_idx` (`disco`),
  KEY `fk_dc_cancion_idx` (`cancion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE IF NOT EXISTS `genero` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=38 ;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`id`, `nombre`) VALUES
(1, 'balada'),
(2, 'vallenato'),
(3, 'pop'),
(4, 'rock'),
(5, 'salsa'),
(6, 'tropical'),
(7, 'popular'),
(8, 'ranchera'),
(9, 'techno'),
(10, 'regge'),
(11, 'reggeton'),
(12, 'musica clasica'),
(13, 'latina'),
(14, 'electonica'),
(15, 'jazz'),
(16, 'infantil'),
(17, 'rap'),
(18, 'tango'),
(19, 'ska'),
(20, 'flamenco'),
(21, 'folk'),
(22, 'pcicoldelica'),
(23, 'punk'),
(24, 'celta'),
(25, 'country'),
(26, 'afroamericana'),
(27, 'vals'),
(28, 'navideña'),
(29, 'carranga'),
(30, 'instrumental'),
(31, 'recuerdo'),
(32, 'despecho'),
(33, 'metal'),
(34, 'black-metal'),
(35, 'taoista'),
(36, 'pachanga'),
(37, 'gospel');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `interprete`
--

CREATE TABLE IF NOT EXISTS `interprete` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mp3`
--

CREATE TABLE IF NOT EXISTS `mp3` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cancion` bigint(20) NOT NULL,
  `peso` varchar(7) NOT NULL,
  `calidad` varchar(3) NOT NULL,
  `duracion` time NOT NULL,
  `precio` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mp_cancion_idx` (`cancion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comprador` bigint(20) NOT NULL,
  `proveedor` bigint(20) NOT NULL,
  `fechapedido` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enviado` bit(1) NOT NULL DEFAULT b'0',
  `rechazado` bit(1) NOT NULL DEFAULT b'0',
  `precio` bigint(20) NOT NULL,
  `disco` int(11) NOT NULL,
  `comentario` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pedido_proveedor_idx` (`proveedor`),
  KEY `fk_pedido_comprador_idx` (`comprador`),
  KEY `fk_pedido_disco_idx` (`disco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE IF NOT EXISTS `persona` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `nacimiento` date NOT NULL,
  `correo` varchar(100) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `rol` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_persona_rol_idx` (`rol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombres`, `apellidos`, `nacimiento`, `correo`, `clave`, `rol`) VALUES
(1, 'Administrador', 'Administrador', '1990-07-01', 'admon@songstock.com', 'clave', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recocancion`
--

CREATE TABLE IF NOT EXISTS `recocancion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recoplicacion` int(11) NOT NULL,
  `cancion` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rc_recopilacion_idx` (`recoplicacion`),
  KEY `fk_rc_cancion_idx` (`cancion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recopilacion`
--

CREATE TABLE IF NOT EXISTS `recopilacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `propietario` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `publica` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `fk_recopilacion_persona_idx` (`propietario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `nombre`) VALUES
(1, 'comprador'),
(2, 'vendedor'),
(3, 'administrador');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD CONSTRAINT `fk_cancion_interprete` FOREIGN KEY (`interprete`) REFERENCES `interprete` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_compra_comprador` FOREIGN KEY (`vendedor`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_compra_vendedor` FOREIGN KEY (`comprador`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_compra_disco` FOREIGN KEY (`disco`) REFERENCES `disco` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `disco`
--
ALTER TABLE `disco`
  ADD CONSTRAINT `fk_disco_interprete` FOREIGN KEY (`interprete`) REFERENCES `interprete` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_disco_genero` FOREIGN KEY (`genero`) REFERENCES `genero` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `discocancion`
--
ALTER TABLE `discocancion`
  ADD CONSTRAINT `fk_dc_disco` FOREIGN KEY (`disco`) REFERENCES `disco` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_dc_cancion` FOREIGN KEY (`cancion`) REFERENCES `cancion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `mp3`
--
ALTER TABLE `mp3`
  ADD CONSTRAINT `fk_mp_cancion` FOREIGN KEY (`cancion`) REFERENCES `cancion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_pedido_proveedor` FOREIGN KEY (`proveedor`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pedido_comprador` FOREIGN KEY (`comprador`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pedido_disco` FOREIGN KEY (`disco`) REFERENCES `disco` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `fk_persona_rol` FOREIGN KEY (`rol`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `recocancion`
--
ALTER TABLE `recocancion`
  ADD CONSTRAINT `fk_rc_recopilacion` FOREIGN KEY (`recoplicacion`) REFERENCES `recopilacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_rc_cancion` FOREIGN KEY (`cancion`) REFERENCES `cancion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `recopilacion`
--
ALTER TABLE `recopilacion`
  ADD CONSTRAINT `fk_recopilacion_persona` FOREIGN KEY (`propietario`) REFERENCES `persona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
