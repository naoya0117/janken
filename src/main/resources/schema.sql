CREATE TABLE users (
   id IDENTITY,
   name VARCHAR NOT NULL
);
CREATE TABLE matchinfo(
  id        IDENTITY,
  user1     INT,
  user2     INT,
  user1HAND VARCHAR NOT NULL,
  isActive  BOOLEAN NOT NULL
);
CREATE TABLE matches (
     id IDENTITY,
     user1 INT,
     user2 INT,
     user1HAND VARCHAR NOT NULL,
     user2HAND VARCHAR NOT NULL,
     isActive BOOLEAN NOT NULL
);
