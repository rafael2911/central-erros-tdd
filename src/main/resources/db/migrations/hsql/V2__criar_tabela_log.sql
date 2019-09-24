CREATE TABLE log (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  level VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  details VARCHAR(255) NOT NULL,
  environment VARCHAR(255) NOT NULL,
  source VARCHAR(255) NOT NULL,
  ddd VARCHAR(2) NOT NULL,
  numero VARCHAR(9) NOT NULL,
  frequence BIGINT NOT NULL,
  created_at timestamp,
  id_user BIGINT NOT NULL,
  FOREIGN KEY (id_user) REFERENCES user(id)
);