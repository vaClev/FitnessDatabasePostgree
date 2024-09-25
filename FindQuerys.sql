--Члены клуба
--поиск Member по id
SELECT Members.id, Members.name, Members.secondname, Members.age, genders.gender,
Members.Wallet, Members.VisitedList, Members.TicketDate 
FROM Members 
JOIN Genders ON Members.gender = genders.Id
WHERE Members.id ='qwert';

--выборка всех членов клуба с JOIN символ пол м/ж
SELECT Members.id, Members.name, Members.secondname, Members.age, genders.gender,
Members.Wallet, Members.VisitedList, Members.TicketDate 
FROM Members 
JOIN Genders ON Members.gender = genders.Id;


-------------------------------------------------------------------------------------------
--Сотрудники
--поиск Employee по id
SELECT Employees.id, Employees.name, Employees.secondname, Employees.age, Employees.status,
Positions.positionName
FROM Employees
JOIN Positions ON Employees.status = Positions.Id
WHERE Employees.id ='dfghj';

--выборка всех сотрдников с JOIN должностями
SELECT Employees.id, Employees.name, Employees.secondname, Employees.age, Employees.status,
Positions.positionName
FROM Employees 
JOIN Positions ON Employees.status = Positions.Id;

-------------------------------------------------------------------------------------------
--Программы тренировок
--выборка списка программ
SELECT PublicProgramms.id, PublicProgramms.ProgrammName, Employees.name, Employees.secondname,
PublicProgramms.duration, PublicProgramms.maxMembers
FROM PublicProgramms 
JOIN Employees ON PublicProgramms.trainerid = Employees.Id;