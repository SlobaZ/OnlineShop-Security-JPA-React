CREATE USER IF NOT EXISTS jwduser IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS OnlineShopSecurityJPAReact;
CREATE DATABASE OnlineShopSecurityJPAReact DEFAULT CHARACTER SET utf8;

USE OnlineShopSecurityJPAReact;

GRANT ALL ON OnlineShopSecurityJPAReact.* TO 'jwduser'@'%';

FLUSH PRIVILEGES;

