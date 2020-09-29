drop table if exists Player;
drop table if exists Pit;


create table Player(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
    ,status VARCHAR(100) NOT NULL
);

create table Pit(
    pitId INT AUTO_INCREMENT PRIMARY KEY,
    posIdx INT NOT NULL,
    stones INT not null,
    ownerId INT not null,
    isKalaha BIT not null,
    stonesKalaha INT,
    foreign key (ownerId) references Player(id)
);

