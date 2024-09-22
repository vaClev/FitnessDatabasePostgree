-- сброс всей базы
DROP TABLE IF EXISTS publicprogramms;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS genders;

-- Таблица пол для тренировки
CREATE TABLE Genders
(
    Id SMALLINT PRIMARY KEY,
    Gender VARCHAR(1) NOT NULL
);
INSERT INTO genders (Id, Gender) 
VALUES (0, 'F'), (1, 'M');

--Члены клуба
CREATE TABLE Members
(
    Id VARCHAR(5) PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    SecondName VARCHAR(30) NOT NULL,
    Age SMALLINT NOT NULL,
    Gender SMALLINT NOT NULL,
    FOREIGN KEY (Gender) REFERENCES Genders(id) ON DELETE CASCADE,
    Wallet INT,
    VisitedList SMALLINT[],
    TicketDate DATE
);

INSERT INTO members (id, name, secondname, age, gender, wallet, visitedlist, ticketdate)
VALUES 
('qwert', 'Oleg', 'Vasilev', 32, 1, 1000,null, null),
('asdfg', 'Ivan', 'Ivanov', 22, 1, 1800,null, null);

--выборка членов клуба
SELECT Members.id, Members.name, Members.secondname, Members.age, genders.gender,
Members.Wallet, Members.VisitedList, Members.TicketDate 
FROM Members 
JOIN Genders ON Members.gender = genders.Id;

--Сотрудники
CREATE TABLE positions
(
    Id SMALLINT PRIMARY KEY,
    positionName VARCHAR(30) NOT NULL
);
INSERT INTO positions (id, positionName)
VALUES 
(0, 'Trainer'),
(1, 'Administrator'),
(2, 'Director');

CREATE TABLE Employees
(
    Id VARCHAR(5) PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    SecondName VARCHAR(30) NOT NULL,
    Age SMALLINT NOT NULL,
    status SMALLINT NOT NULL,
    FOREIGN KEY (status) REFERENCES positions(id) ON DELETE CASCADE
);

INSERT INTO employees (id, name, secondname, age, status)
VALUES 
('zxcvb', 'Petr', 'Petrov', 42, 2), --Директор 2
('gfdsa', 'Anna', 'Grey', 22, 1), --Админ 1
('dfghj', 'Arnold', 'Shrartz', 50, 0), --Тренер 0
('rewt1', 'Alla', 'Blue', 25, 0); --Тренер 0

--выборка сотрдников с должностями
SELECT Employees.id, Employees.name, Employees.secondname, Employees.age, Employees.status,
Positions.positionName
FROM Employees 
JOIN Positions ON Employees.status = Positions.Id;

--далее программы тренировок
CREATE TABLE PublicProgramms
(
    Id SMALLINT PRIMARY KEY,
    ProgrammName VARCHAR(30) NOT NULL,
    TrainerId VARCHAR(30),
	FOREIGN KEY (TrainerId) REFERENCES Employees(id) ON DELETE CASCADE,
    Duration SMALLINT,
    MaxMembers SMALLINT
);

INSERT INTO PublicProgramms(id, ProgrammName, trainerId, duration, maxMembers)
VALUES 
(1, 'Pilates','dfghj', 50, 20),
(2, 'Croosfit','rewt1', 120, 30); 

--выборка списка программ
SELECT PublicProgramms.id, PublicProgramms.ProgrammName, Employees.name, Employees.secondname,
PublicProgramms.duration, PublicProgramms.maxMembers
FROM PublicProgramms 
JOIN Employees ON PublicProgramms.trainerid = Employees.Id;