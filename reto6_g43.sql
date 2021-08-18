-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-08-2021 a las 02:36:21
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reto6_g43`
--
CREATE DATABASE IF NOT EXISTS `reto6_g43` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `reto6_g43`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_doctor`
--

CREATE TABLE `tb_doctor` (
  `id` int(11) NOT NULL,
  `doctor` varchar(30) NOT NULL,
  `document_type` varchar(15) NOT NULL,
  `document` int(11) NOT NULL,
  `id_hospital` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_doctor`
--

INSERT INTO `tb_doctor` (`id`, `doctor`, `document_type`, `document`, `id_hospital`) VALUES
(1, 'Dr. Felipe', 'C.C.', 10538181, 1),
(2, 'Dr. Alejandro', 'C.C.', 10539292, 2),
(3, 'Dr. Esteban', 'C.C.', 10537373, 1),
(4, 'Federico', 'Pasaporte', 10536464, 2),
(5, 'Humberto Henao', 'Pasaporte', 190021992, 1),
(6, 'Dr. Camilo ', 'Pasaporte', 221332322, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_hospital`
--

CREATE TABLE `tb_hospital` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_hospital`
--

INSERT INTO `tb_hospital` (`id`, `name`, `address`) VALUES
(1, 'San Miguel', 'Calle 5A'),
(2, 'Mascoticas', 'Calle 6A'),
(3, 'San Canino', 'Calle 45');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_pet`
--

CREATE TABLE `tb_pet` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `pet_type` varchar(30) NOT NULL,
  `id_owner_pet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_pet`
--

INSERT INTO `tb_pet` (`id`, `name`, `pet_type`, `id_owner_pet`) VALUES
(1, 'Juano', 'Perro', 1),
(2, 'Poli', 'Gato', 2),
(3, 'Dante', 'Perro', 3),
(4, 'Choko', 'Gato', 4),
(5, 'Hades', 'Gato', 1),
(6, 'keiko', 'Perro', 1),
(7, 'Nyu', 'Gato', 4),
(8, 'Lupita', 'Gato', 3),
(9, 'Loco', 'Perro', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_pet_hospital`
--

CREATE TABLE `tb_pet_hospital` (
  `id` int(11) NOT NULL,
  `id_pet` int(11) NOT NULL,
  `id_hospital` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_pet_hospital`
--

INSERT INTO `tb_pet_hospital` (`id`, `id_pet`, `id_hospital`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 1),
(4, 4, 2),
(5, 5, 2),
(6, 3, 2),
(7, 5, 1),
(8, 6, 1),
(10, 9, 3),
(11, 7, 3),
(12, 8, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_pet_owners`
--

CREATE TABLE `tb_pet_owners` (
  `id` int(11) NOT NULL,
  `owner` varchar(30) NOT NULL,
  `document_type` varchar(15) NOT NULL,
  `document` int(11) NOT NULL,
  `contact` int(11) NOT NULL,
  `gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tb_pet_owners`
--

INSERT INTO `tb_pet_owners` (`id`, `owner`, `document_type`, `document`, `contact`, `gender`) VALUES
(1, 'Juan', 'C.C.', 10538181, 2147483647, 'Masculino'),
(2, 'Manuel', 'C.C.', 10539292, 2147483647, 'Masculino'),
(3, 'Valeria', 'C.E.', 10537373, 2147483647, 'Femenino'),
(4, 'Federico', 'Pasaporte', 10536464, 2147483647, 'Masculino');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_doctor`
--
ALTER TABLE `tb_doctor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_hospital` (`id_hospital`);

--
-- Indices de la tabla `tb_hospital`
--
ALTER TABLE `tb_hospital`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tb_pet`
--
ALTER TABLE `tb_pet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_owner_pet` (`id_owner_pet`);

--
-- Indices de la tabla `tb_pet_hospital`
--
ALTER TABLE `tb_pet_hospital`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pet` (`id_pet`),
  ADD KEY `id_hospital` (`id_hospital`);

--
-- Indices de la tabla `tb_pet_owners`
--
ALTER TABLE `tb_pet_owners`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_doctor`
--
ALTER TABLE `tb_doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tb_hospital`
--
ALTER TABLE `tb_hospital`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tb_pet`
--
ALTER TABLE `tb_pet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `tb_pet_hospital`
--
ALTER TABLE `tb_pet_hospital`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `tb_pet_owners`
--
ALTER TABLE `tb_pet_owners`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_doctor`
--
ALTER TABLE `tb_doctor`
  ADD CONSTRAINT `tb_doctor_ibfk_1` FOREIGN KEY (`id_hospital`) REFERENCES `tb_hospital` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_pet`
--
ALTER TABLE `tb_pet`
  ADD CONSTRAINT `tb_pet_ibfk_1` FOREIGN KEY (`id_owner_pet`) REFERENCES `tb_pet_owners` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_pet_hospital`
--
ALTER TABLE `tb_pet_hospital`
  ADD CONSTRAINT `tb_pet_hospital_ibfk_1` FOREIGN KEY (`id_pet`) REFERENCES `tb_pet` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_pet_hospital_ibfk_2` FOREIGN KEY (`id_hospital`) REFERENCES `tb_hospital` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
