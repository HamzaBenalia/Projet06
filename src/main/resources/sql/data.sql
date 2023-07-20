--
-- Dumping data for table `bank_acount`
--

LOCK TABLES `bank_acount` WRITE;
/*!40000 ALTER TABLE `bank_acount` DISABLE KEYS */;
INSERT INTO `bank_acount` VALUES (1,4,20.00,'Fr0000 0000 0000 0000'),(2,1,40.00,'Fr777 7777 7777 7777'),(3,3,0.00,'FR4444 4444 4444 4444'),(4,5,0.00,'FR 8888 8888 8888 8888');
/*!40000 ALTER TABLE `bank_acount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `relations`
--

LOCK TABLES `relations` WRITE;
/*!40000 ALTER TABLE `relations` DISABLE KEYS */;
INSERT INTO `relations` VALUES (4,1),(1,3),(1,4);
/*!40000 ALTER TABLE `relations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,1,3,40.00,'2023-06-28 13:50:30',0.20),(2,1,3,2.00,'2023-07-06 20:57:40',0.01),(3,4,1,80.00,'2023-07-08 20:30:25',0.40);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Hamza','Benalia','hamzabenalia93@gmail.com',67.79,'$2a$10$OjTuczpKQw0eEKGwTd3IHOCDXB2lWouzDIfks8qz4sCVj2tL6tzmG'),(2,'eric ','eric','eric@gmail.com',0.00,'$2a$10$sF3f1918fyCIRmVLb.0dYeYyeJOlhfStLjNhyTVD68lzVokaK9IQW'),(3,'sara','hms','sara@gmail.com',62.00,'$2a$10$ISDrAywMiAQueV4CEYTyZOxUtc6q6jz0SEMsMjS9Je2djrOjCo4vS'),(4,'toto','toto','toto@gmail.com',19.60,'$2a$10$0tEln6BXpFexwN/Hg5iKt.giV/zT7Qa1XJWS5vlYle0xnNLKAd57i'),(5,'tata','tata','tata@gmail.com',720.00,'$2a$10$4WTmize4cfYHduIo/JDk3e9oQz1VLjtY806.RCjMPW0.0fPBXJaNG');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;