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
