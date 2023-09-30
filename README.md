# Airport Management

## PLEASE NOTE: IN PROGRESS

## Description

This is my basic portfolio project for intern or junior java developer positions. Please note that I am a beginner and a huge part of code in this repo needs refactor and could be done better / differently.

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
