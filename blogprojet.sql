-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mar 18 Mars 2014 à 12:39
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `blogprojet`
--
CREATE DATABASE IF NOT EXISTS `blogprojet` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `blogprojet`;

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE IF NOT EXISTS `administrateur` (
  `identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `pseudonyme` varchar(50) NOT NULL,
  `motdepasse` varchar(50) NOT NULL,
  `nombreCommentaire` int(11) NOT NULL,
  PRIMARY KEY (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Table de stockage d''un administrateur' AUTO_INCREMENT=3 ;

--
-- Contenu de la table `administrateur`
--

INSERT INTO `administrateur` (`identifiant`, `pseudonyme`, `motdepasse`, `nombreCommentaire`) VALUES
(1, 'stephen', 'stephen', 0),
(2, 'guillaume', 'guillaume', 0);

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
  `identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(500) NOT NULL,
  `date` date NOT NULL,
  `contenu` varchar(10000) NOT NULL,
  `idAdministrateur` int(11) NOT NULL,
  PRIMARY KEY (`identifiant`),
  KEY `idAdministrateur` (`idAdministrateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Table de stockage d''un article du blog' AUTO_INCREMENT=27 ;

--
-- Contenu de la table `article`
--

INSERT INTO `article` (`identifiant`, `titre`, `date`, `contenu`, `idAdministrateur`) VALUES
(13, 'Victoire des irlandais', '2014-03-16', 'Dans un match au coudes à coudes, les irlandais de Joe Schmith remporte le tournoi des 6 nations en battant la France de deux longueurs 22 Ã?Â?Ã?Â  20. Ce titre est d', 1),
(24, 'Avion retrouvÃ©', '2014-03-18', 'Ils ont retrouvÃ© l''avion disparu de Malaisia Airlines.', 2),
(25, 'Reptilien au gouvernement franÃ§ais', '2014-03-18', 'Tout comme le 1er ministre nÃ©o-zÃ©landais, le ministre du redressement productif serait un reptile de type dangereux.', 2),
(26, 'Prix nobel de mastermind', '2014-03-18', 'Et oui Mr. Chambriard Arthur a Ã©tÃ© Ã©lu hier prix Nobel de Mastermind 2014, nous le fÃ©licitons.', 2);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `auteur` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `contenu` varchar(10000) NOT NULL,
  `idArticle` int(11) NOT NULL,
  PRIMARY KEY (`identifiant`),
  KEY `idArticle` (`idArticle`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Table de stockage d''un commentaire' AUTO_INCREMENT=7 ;

--
-- Contenu de la table `commentaire`
--

INSERT INTO `commentaire` (`identifiant`, `auteur`, `date`, `contenu`, `idArticle`) VALUES
(5, 'Jean raconte', '2014-03-16', 'Il faut aussi souligner le trÃ¨s bon match des franÃ§ais, qui ont jouaient avec dÃ©termination et discipline, malgrÃ© quelques imperfections en dÃ©fense ainsi que dans la conquÃªte, une bien belle Ã©quipe de France que nous avons vu !', 13),
(6, 'Pierre Tombale', '2014-03-18', 'J''ai adorÃ© ce match, un Ã©norme suspense', 13);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `relationAdminArticle` FOREIGN KEY (`idAdministrateur`) REFERENCES `administrateur` (`identifiant`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `relationArticleCommentaire` FOREIGN KEY (`idArticle`) REFERENCES `article` (`identifiant`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
