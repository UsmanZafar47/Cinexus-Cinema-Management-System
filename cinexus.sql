CREATE DATABASE IF NOT EXISTS Cinexus
use Cinexus

DROP TABLE users;
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    cnic VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role ENUM('Customer', 'Admin', 'Cinema Manager', 'Event Organizer') NOT NULL
);
INSERT into users (username, password, name, cnic, email, role) VALUES ("Usman", "321", "Usman", "12", "usman@gmail.com", "Customer"); 
select * from users;


DROP TABLE movies;
CREATE TABLE movies (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    rating VARCHAR(50)
);
INSERT INTO movies (title, duration, rating) VALUES ('Avengers', 110, '4');
INSERT INTO movies (title, duration, rating) VALUES ('Avengers EndGame', 100, '3');
INSERT INTO movies (title, duration, rating) VALUES ('Justice League', 90, '1');
select * from movies;
SELECT m.title, c.name AS cinema_name, GROUP_CONCAT(s.showtime SEPARATOR ', ') AS showtimes
                    FROM movies m 
                    JOIN showtimes s ON m.movie_id = s.movie_id
                    JOIN cinema c ON s.cinema_id = c.cinema_id ;
                    
                    SELECT m.title, c.name AS cinema_name, GROUP_CONCAT(s.showtime SEPARATOR ', ') AS showtimes
FROM movies m 
JOIN showtimes s ON m.movie_id = s.movie_id
JOIN cinema c ON s.cinema_id = c.cinema_id  
GROUP BY m.title, c.name;



CREATE TABLE cinema (
    cinema_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
	manager_id int,
    manager_name VARCHAR(50)
);
insert into cinema (name, location, manager_name) values ("Cinepex","Islamabad","John Aik");
insert into cinema (name, location, manager_name) values ("Megapex","Islamabad","John Doe");
select * from cinema;



CREATE TABLE showtimes (
    showtime_id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT,
    cinema_id INT,
    showtime DATETIME NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (cinema_id) REFERENCES cinema(cinema_id)
);
INSERT INTO showtimes (movie_id, cinema_id, showtime) VALUES (1, 1, '2023-12-10 18:00:00');
insert into showtimes (movie_id, cinema_id, showtime) values (1, 2, '2023-12-10 19:00:00');
INSERT INTO showtimes (movie_id, cinema_id, showtime) VALUES (2, 1, '2023-12-10 20:00:00');
insert into showtimes (movie_id, cinema_id, showtime) values (2, 2, '2023-12-10 21:00:00');
INSERT INTO showtimes (movie_id, cinema_id, showtime) VALUES (3, 1, '2023-12-10 22:00:00');
insert into showtimes (movie_id, cinema_id, showtime) values (3, 2, '2023-12-10 23:00:00');


DROP Table tickets;
CREATE TABLE tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    showtime_id INT,
    seat_number INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status ENUM('booked', 'cancelled') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (showtime_id) REFERENCES showtimes(showtime_id)
);


CREATE TABLE events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    cinema_id INT,
    FOREIGN KEY (cinema_id) REFERENCES cinemas(cinema_id)
);

CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    status ENUM('pending', 'completed', 'failed') NOT NULL,
    payment_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);