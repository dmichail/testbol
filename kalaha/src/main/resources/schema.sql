DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Board;
DROP TABLE IF EXISTS Game;


CREATE TABLE Player (
  playerId INT AUTO_INCREMENT  PRIMARY KEY,
  playerName VARCHAR(250) NOT NULL
);

CREATE TABLE Board (
    boardId INT AUTO_INCREMENT PRIMARY KEY,
    p1Id INT,
    p2Id INT,
    k1 INT NOT NULL,
    k2 INT NOT NULL,
    pits1 VARCHAR(30) NOT NULL,
    pits2 VARCHAR(30) NOT NULL,
    FOREIGN KEY (p1Id) REFERENCES Player(playerId),
    FOREIGN KEY (p2Id) REFERENCES Player(playerId)
);

CREATE TABLE Game (
  gameId INT AUTO_INCREMENT PRIMARY KEY,
  boardId INT ,
  turnOfWithId INT NOT NULL,
  isOver BIT NOT NULL,
  winner VARCHAR(MAX),
  FOREIGN KEY (boardId) REFERENCES Board(boardId)
);