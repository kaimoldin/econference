# eConference

### Getting Started

The eConference application is designed to manage the conference room reservation schedule.
Like a typical booking system it exposes a set of REST methods to customers.

#### Registration

```console
curl -X POST 'http://localhost:8080/api/v1/signup' -H "Accept: application/json" \
--data-raw '{"name": "John Doe", "email": "johndoe@example.com", "password": "qwerty"}'
```

```json
{
  "id": 777,
  "name": "John Doe",
  "email": "johndoe@example.com",
  "roles": [
    "participant"
  ]
}
```

#### Authentication

To be able to make any other requests a REST client can use the basic HTTP authentication scheme using credentials
specified for registration. For example:

```console
curl -X GET 'http://localhost:8080/api/v1/rooms/123' --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
```

#### Users

###### Add new user

```console
curl -X POST 'http://localhost:8080/api/v1/users' -H "Accept: application/json" \
--data-raw '{"name": "John Doe", "email": "johndoe@example.com", "password": "qwerty"}'
```

```json
{
  "id": 777,
  "name": "John Doe",
  "email": "johndoe@example.com",
  "roles": [
    "participant"
  ],
  "enabled": true
}
```

###### Update existing user (except roles)

```console
curl -X PUT 'http://localhost:8080/api/v1/users/777' -H "Accept: application/json" \
--data-raw '{"name": "John Doe Jr"}'
```

```json
{
  "id": 777,
  "name": "John Doe Jr",
  "email": "johndoe@example.com",
  "roles": [
    "participant"
  ],
  "enabled": true
}
```

###### Get user info

```console
curl -X GET 'http://localhost:8080/api/v1/users/777' -H "Accept: application/json" 
```

```json
{
  "id": 777,
  "name": "John Doe Jr",
  "email": "johndoe@example.com",
  "roles": [
    "participant"
  ],
  "enabled": true
}
```

#### Roles

- `admin` - can create rooms, get a list of users, change user roles and status
- `manager` - can create/cancel a conference and check a conference room availability
- `participant` - can get a list of available conferences, register for a conference, cancel his registration

###### Get list of users

```console
curl -X GET 'http://localhost:8080/api/v1/users?page=0' -H "Accept: application/json"
```

```json
[
  {
    "id": 777,
    "name": "John Doe Jr",
    "email": "johndoe@example.com",
    "roles": [
      "manager",
      "participant"
    ],
    "enabled": true
  }
]
```

###### Get user roles

```console
curl -X GET 'http://localhost:8080/api/v1/users/777/roles' -H "Accept: application/json"
```

```json
[
  "manager",
  "participant"
]
```

###### Update user roles

```console
curl -X PUT 'http://localhost:8080/api/v1/users/777/roles' -H "Accept: application/json" \
--data-raw '["manager", "participant"]'
```

```json
[
  "manager",
  "participant"
]
```

#### Conference rooms

###### Add new room

```console
curl -X POST 'http://localhost:8080/api/v1/rooms' -H "Accept: application/json" \
--data-raw '{"name": "Room #1", "seatCount": 25}'
```

```json
{
  "id": 333,
  "name": "Room #1",
  "seatCount": 25,
  "enabled": true
}
```

###### Update existing room

```console
curl -X PUT 'http://localhost:8080/api/v1/rooms/333' -H "Accept: application/json" \
--data-raw '{"name": "Room #1a"}'
```

```json
{
  "id": 333,
  "name": "Room #1a",
  "seatCount": 25,
  "enabled": true
}
```

###### Get room info

```console
curl -X GET 'http://localhost:8080/api/v1/rooms/333' -H "Accept: application/json" 
```

```json
{
  "id": 333,
  "name": "Room #1",
  "seatCount": 25,
  "enabled": true
}
```

#### Conferences

###### Add new conference

```console
curl -X POST 'http://localhost:8080/api/v1/conferences' -H "Accept: application/json" \
--data-raw '{"name": "Summer conference", "expectedParticipantCount": 20, "roomId": 777}'
```

```json
{
  "id": 111,
  "name": "Summer conference",
  "expectedParticipantCount": 20,
  "roomId": 777,
  "enabled": true
}
```

###### Update existing conference

```console
curl -X PUT 'http://localhost:8080/api/v1/conferences/111' -H "Accept: application/json" \
--data-raw '{"expectedParticipantCount": 25}'
```

```json
{
  "id": 111,
  "name": "Summer conference",
  "expectedParticipantCount": 25,
  "roomId": 777,
  "enabled": true
}
```

###### Get conference info

```console
curl -X GET 'http://localhost:8080/api/v1/conferences/111' -H "Accept: application/json" 
```

```json
{
  "id": 111,
  "name": "Summer conference",
  "expectedParticipantCount": 20,
  "roomId": 777,
  "enabled": true
}
```

###### Get conference statistics

```console
curl -X GET 'http://localhost:8080/api/v1/conferences/111/statistics' -H "Accept: application/json" 
```

```json
{
  "expectedParticipantCount": 20,
  "actualParticipantCount": 10,
  "availableSeatCount": 10
}
```

###### Get conference participants

```console
curl -X GET 'http://localhost:8080/api/v1/conferences/111/participants' -H "Accept: application/json" 
```

```json
[
  {
    "id": 777,
    "name": "John Doe Jr",
    "email": "johndoe@example.com",
    "roles": [
      "participant"
    ],
    "enabled": true
  }
]
```

#### Participants

###### Get list of available conferences

```console
curl -X GET 'http://localhost:8080/api/v1/users/777/available-conferences' -H "Accept: application/json" 
```

```json
[
  {
    "id": 111,
    "name": "Summer conference",
    "roomId": 777
  }
]
```

###### Register for a conference

```console
curl -X POST 'http://localhost:8080/api/v1/users/777/conferences' -H "Accept: application/json" \
--data-raw '{"conferenceId": 111}'
```

```json
{
  "id": 111,
  "name": "Summer conference",
  "roomId": 777
}
```

###### Cancel registration

```console
curl -X DELETE 'http://localhost:8080/api/v1/users/777/conferences/111' -H "Accept: application/json" \
--data-raw '{"conferenceId": 111}'
```

```json
{
  "id": 111,
  "name": "Summer conference",
  "roomId": 777
}
```

###### Get list of scheduled conferences

```console
curl -X GET 'http://localhost:8080/api/v1/users/777/scheduled-conferences' -H "Accept: application/json" 
```

```json
[
  {
    "id": 111,
    "name": "Summer conference",
    "roomId": 777
  }
]
```

#### Error messages

```json
{
  "requestId": "f959753d-192a-48c3-a229-c368a519196c",
  "message": "Too many participants"
}
```
