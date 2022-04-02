--Составить первый JOIN-запрос, чтобы получить информацию обо всех студентах
-- (достаточно получить только имя и возраст студента) школы Хогвартс вместе с названиями факультетов.

SELECT student.name, student.age, faculty.name

FROM faculty
         INNER JOIN student
                    ON faculty.id = student.faculty_id;

--Составить второй JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки.
SELECT student.name
FROM avatar
         INNER JOIN student on avatar.student_id = student.id;