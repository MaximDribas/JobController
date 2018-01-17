CREATE DATABASE IF NOT EXISTS `companiesdb`;
USE `companiesdb`;

CREATE TABLE IF NOT EXISTS `companies` (
  `company_id` int(5) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) NOT NULL,
  `company_url` varchar(100) NOT NULL,
  `company_mail` varchar(100) NOT NULL,
  `company_date` Date(100) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;