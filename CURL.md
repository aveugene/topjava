## CURL requests

- ### Get all users

#### _`curl -s http://localhost:8080/topjava/rest/admin/users`_

<pre>[{"id":100001,"name":"Admin","email":"admin@gmail.com","password":"admin","enabled":true,"registered":"2019-07-29T12:16:33.858+0000","roles":["ROLE_USER","ROLE_ADMIN"],"caloriesPerDay":2000,"meals":null},{"id":100000,"name":"User","email":"user@yandex.ru","password":"password","enabled":true,"registered":"2019-07-29T12:16:33.858+0000","roles":["ROLE_USER"],"caloriesPerDay":2000,"meals":null}]</pre> 

- ### Get User 

#### _`curl -s http://localhost:8080/topjava/rest/admin/users/100000`_

<pre>{"id":100000,"name":"User","email":"user@yandex.ru","password":"password","enabled":true,"registered":"2019-07-29T12:16:33.858+0000","roles":["ROLE_USER"],"caloriesPerDay":2000,"meals":null}</pre>

- ### Get Admin 

#### _`curl -s http://localhost:8080/topjava/rest/admin/users/100001`_

<pre>{"id":100001,"name":"Admin","email":"admin@gmail.com","password":"admin","enabled":true,"registered":"2019-07-29T12:16:33.858+0000","roles":["ROLE_USER","ROLE_ADMIN"],"caloriesPerDay":2000,"meals":null}</pre>

- ### Get all meals

#### _`curl -s http://localhost:8080/topjava/rest/profile/meals`_

<pre>[{"id":100007,"dateTime":"2015-05-31T20:00:00","description":"Ужин","calories":510,"excess":true},{"id":100006,"dateTime":"2015-05-31T13:00:00","description":"Обед","calories":1000,"excess":true},{"id":100005,"dateTime":"2015-05-31T10:00:00","description":"Завтрак","calories":500,"excess":true},{"id":100004,"dateTime":"2015-05-30T20:00:00","description":"Ужин","calories":500,"excess":false},{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"excess":false},{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"excess":false}]</pre>

- ### Get MEAL1

#### _`curl -s http://localhost:8080/topjava/rest/profile/meals/100002`_

<pre>{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"user":null}</pre>

- ### Get MEAL2
#### _`curl -s http://localhost:8080/topjava/rest/profile/meals/100003`_
<pre>{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"user":null}</pre>

- ### Get MEAL3
#### _`curl -s http://localhost:8080/topjava/rest/profile/meals/100004`_
<pre>{"id":100004,"dateTime":"2015-05-30T20:00:00","description":"Ужин","calories":500,"user":null}</pre>

- ### Get MEAL4
#### _`curl -s http://localhost:8080/topjava/rest/profile/meals/100005`_
<pre>{"id":100005,"dateTime":"2015-05-31T10:00:00","description":"Завтрак","calories":500,"user":null}</pre>

- ### Get MEAL5
#### _`curl -s http://localhost:8080/topjava/rest/profile/meals/100006`_
<pre>{"id":100006,"dateTime":"2015-05-31T13:00:00","description":"Обед","calories":1000,"user":null}</pre>

- ### Get MEAL6
#### _`curl -s http://localhost:8080/topjava/rest/profile/meals/100007`_
<pre>{"id":100007,"dateTime":"2015-05-31T20:00:00","description":"Ужин","calories":510,"user":null}</pre>

- ### Get 100008 meal - not exist
#### _`curl -s http://localhost:8080/topjava/rest/profile/meals/100008`_
<pre>HTTP Status 500 – Internal Server Error
<b>Type</b> Exception Report
<b>Message</b> Request processing failed; 
    nested exception is ru.javawebinar.topjava.util.exception.NotFoundException: 
    Not found entity with id=100008
<b>Description</b> The server encountered an unexpected condition that prevented it from fulfilling the request.
<b>Exception</b> org.springframework.web.util.NestedServletException: 
    Request processing failed;
    nested exception is ru.javawebinar.topjava.util.exception.NotFoundException: Not found entity with id=100008</pre>

- ### Get Meals between
#### _`curl -s "http://localhost:8080/topjava/rest/profile/meals/between?startDate=2015-05-30&startTime=00:00:00&endDate=2015-05-30&endTime=13:00:00"`_
<pre>[{"id":100003,"dateTime":"2015-05-30T13:00:00","description":"Обед","calories":1000,"excess":false},{"id":100002,"dateTime":"2015-05-30T10:00:00","description":"Завтрак","calories":500,"excess":false}]</pre>

- ### Create new Meal
#### _`curl -s -X POST -d '{"dateTime":"2015-05-30T06:00","description":"Early breakfast","calories":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile/meals`_
<pre>{"id":100010,"dateTime":"2015-05-30T06:00:00","description":"Early breakfast","calories":300,"user":null}</pre>

- ### Update Meal
#### _`curl -s -X PUT -d '{"dateTime":"2015-05-30T06:10", "description":"Early breakfast updated", "calories":200}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/profile/meals/100010`_
<pre>       </pre>
<pre>$ curl -s http://localhost:8080/topjava/rest/profile/meals/100010
{"id":100010,"dateTime":"2015-05-30T06:10:00","description":"Early breakfast updated","calories":200,"user":null}</pre>


- ### Delete Meal1
#### _`curl -s -X DELETE http://localhost:8080/topjava/rest/profile/meals/100010`_
<pre>       </pre>
<pre>$ curl -s http://localhost:8080/topjava/rest/profile/meals/100010
HTTP Status 500 – Internal Server Error
<b>Type</b> Exception Report
<b>Message</b> Request processing failed; 
    nested exception is ru.javawebinar.topjava.util.exception.NotFoundException: 
    Not found entity with id=100010
<b>Description</b> The server encountered an unexpected condition that prevented it from fulfilling the request.
<b>Exception</b> org.springframework.web.util.NestedServletException: 
    Request processing failed;
    nested exception is ru.javawebinar.topjava.util.exception.NotFoundException: Not found entity with id=100010</pre>