CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at timestamp
);