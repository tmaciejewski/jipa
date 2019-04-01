DROP TABLE IF EXISTS train;
CREATE TABLE train(
    train_id INTEGER NOT NULL AUTO_INCREMENT,
    train_name VARCHAR(100) UNIQUE,
    PRIMARY KEY(train_id)
);

DROP TABLE IF EXISTS schedule;
CREATE TABLE schedule(
    schedule_id INTEGER NOT NULL,
    schedule_date DATE NOT NULL,
    train_id INTEGER NOT NULL,
    active INTEGER NOT NULL,
    PRIMARY KEY(schedule_id)
);

DROP TABLE IF EXISTS station;
CREATE TABLE station(
    station_id INTEGER NOT NULL AUTO_INCREMENT,
    station_name VARCHAR(100) UNIQUE,
    PRIMARY KEY (station_id)
);

DROP TABLE IF EXISTS schedule_info;
CREATE TABLE schedule_info(
    schedule_id INTEGER NOT NULL,
    stop_number INTEGER NOT NULL,
    station_id INTEGER NOT NULL,
    arrival_time DATETIME DEFAULT NULL,
    arrival_delay INTEGER DEFAULT NULL,
    departure_time DATETIME DEFAULT NULL,
    departure_delay INTEGER DEFAULT NULL,
    PRIMARY KEY(schedule_id, stop_number)
);
