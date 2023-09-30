# Airport Management

## PLEASE NOTE: IN PROGRESS

## Description

Basic portfolio project for intern or junior java developer positions. 
This application is a simulation of airport management. Below you can find the list of endpoints for all features of this app.

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

## TODO List
- [ ] Add changing airplane status
- [ ] Add changing airplane carrier
- [ ] Add role list to user preview and list
- [ ] Add storing admin endpoint
- [ ] Add timestamps to airplanes
- [ ] Add timestamps to users
- [ ] Add active column to users
- [ ] Add more detailed feature description
- [ ] Add feature tests !!!
- [ ] Add response codes description
