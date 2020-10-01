drop table if exists Game;
drop table if exists Pit;
drop table if exists Board;
drop table if exists Player;

create table Player(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    status VARCHAR(100) NOT NULL
);

create table Board(
    id INT AUTO_INCREMENT PRIMARY KEY,
    p1Id INT not null,
    p2Id INT not null,
    foreign key (p1Id) references Player(id),
    foreign key (p2Id) references Player(id)
);

create table Pit(
    pitId INT AUTO_INCREMENT PRIMARY KEY,
    posIdx INT NOT NULL,
    stones INT not null,
    isKalaha BIT not null,
    stonesKalaha INT,
    boardId INT NOT NULL,
    foreign key (boardId) references Board(id)
);

create table Game(
    id INT AUTO_INCREMENT PRIMARY KEY,
    boardId INT NOT NULL,
    turnId INT not null,
    isOver BIT,
    winnerId INT,
    foreign key (boardId) references Board(id),
    foreign key (turnId) references Player(id),
    foreign key (winnerId) references Player(id)
);







