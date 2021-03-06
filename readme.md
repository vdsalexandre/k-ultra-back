# Ultra Coding Test

### Project versions
* Kotlin version : **1.6.21**
* Java version : **17**
* Springboot version : **2.6.7**
* Application version : **0.0.1-SNAPSHOT**
* Tests version : **junit-5**

### Install and use
`git clone https://github.com/vdsalexandre/k-ultra-back.git`

`gradle build`

`gradle bootRun`

### Description
Little backend REST in kotlin which aims to serve the games data.  
Application exposes REST api providing CRUD operations to fetch one or several games.

### Application endpoints

| VERB   | URL                                 | PARAMETER(S) |
|--------|-------------------------------------|--------------|
| GET    | http://localhost:9090/game/find/    | **id**       |
| GET    | http://localhost:9090/game/find/all |              |
| POST   | http://localhost:9090/game/add      | body         |
| DELETE | http://localhost:9090/game/del/     | **id**       |
| PUT    | http://localhost:9090/game/update/  | body         |