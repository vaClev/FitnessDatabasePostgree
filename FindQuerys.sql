--поиск Member по id
SELECT Members.id, Members.name, Members.secondname, Members.age, genders.gender,
Members.Wallet, Members.VisitedList, Members.TicketDate 
FROM Members 
JOIN Genders ON Members.gender = genders.Id
WHERE Members.id ='qwert';

