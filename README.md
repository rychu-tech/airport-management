# Airport Management

## PLEASE NOTE: IN PROGRESS

## Description

Basic portfolio project for intern or junior java developer positions. 
This application is a simulation of airport management. Below you can find the list of endpoints for all features of this app.

## Setup

The setup process is very easy. You need to navigate to project folder. Inside, run command:
```
mvn install
```
or
```
mvn package "-Dmaven.test.skip"
```
```
docker-compose up --build
```

Basic api port is 8080 and database port is 3306.

## Carriers

| TYPE | URI | BODY | EFFECT |
|---|---|---|---|
| POST | /carriers | name: string | Adds carrier |
| DEL | /carriers/{id} |  | Changes the carrier to inactive |
| PUT | /carriers/{id} | name: string | Changes the given carrier's properties |
| GET | /carriers |  | Returns the list of carriers |
| PATCH | /carriers/{id} |  | Changes the carrier back to active |

## Airplanes

| TYPE | URI | BODY | EFFECT |
|---|---|---|---|
| GET | /airplane_statuses | | Returns the list of airplane statuses |
| POST | /airplanes | name: string, seats_number: integer, carrier_id: integer, airplane_status_id: integer | Adds airplane |
| GET | /airplanes | | Returns the list of airplanes |

## Users

| TYPE | URI | BODY | EFFECT |
|---|---|---|---|
| POST | /users/register | first_name: string, last_name: string, email: string, password: string | Adds user of type client |
| POST | /users/login | email: string, password: string | Returns bearer token which can be used for authentication |

## Destinations

| TYPE | URI | BODY | EFFECT |
|---|---|---|---|
| POST | /destinations | name: string | Adds new destination |
| PUT | /destinations/{id} | name: string | Edits given destination with provided name |
| PATCH | /destinations/{id} | | Restores the entity |
| DELETE | /destinations/{id} | | Removes the entity |
| GET | /destinations | | Returns the list of destinations |

## Gates
| TYPE | URI | BODY | EFFECT |
|---|---|---|---|
| POST | /gates | name: string | Adds new gate |
| GET | /gates | | Returns the list of gates |
| GET | /gates/{id} | | Returns the gate with given id |
| PATCH | /gates/{id} | active: boolean, comment: string | Adds comment to gate and saves it to history |

## Flights
| TYPE | URI | BODY | EFFECT |
|---|---|---|---|
| POST | /flights | code: string, flight_status_id: int, airplane_id: int, destination_id: int, gate_id: int, departure_time: string (format YYYY-MM-DDTH:i:s) | Adds flight |
| GET | /flights | | Returns flights list |
| GET | /flights/{id} | | Returns flight with given id |


## TODO List
- [ ] Add changing airplane status
- [ ] Add changing airplane carrier
- [ ] Add role list to user preview and list
- [ ] Add storing admin endpoint
- [ ] Add timestamps to airplanes
- [ ] Add timestamps to users
- [ ] Add active column to users
- [ ] Add more detailed feature description
- [X] Add tests for carrier and airplanes 
- [ ] Add tests for user authentication
- [ ] Add response codes description
- [ ] Add test for gates
- [X] Add API description for gates
- [ ] Add tests for flights
- [ ] Add changing flight status
- [ ] Add disabling flight
- [ ] Add integration with currency exchange API
- [ ] Add money depositing
- [ ] Add buying tickets 

## BUGS TO FIX
- [X] There is a bug in tests when I try to add test to throw Exceptions in addAirplane I get response - Expected {exception_name} to be thrown, but nothing was thrown. Found and fixed the issue. It turned out that I had to init mocks before each tests and I had to add one more @Mock.
- [ ] If user not found in DB, return error on login.
- [ ] Some cases were not tested and do not have predicted statuses
