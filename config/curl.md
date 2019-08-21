### curl samples (application deployed in application context `topjava`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/topjava/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/topjava/rest/admin/users/100001 --user admin@gmail.com:admin`

#### get All Meals
`curl -s http://localhost:8080/topjava/rest/profile/meals --user user@yandex.ru:password`

#### get Meals 100003
`curl -s http://localhost:8080/topjava/rest/profile/meals/100003  --user user@yandex.ru:password`

#### filter Meals
`curl -s "http://localhost:8080/topjava/rest/profile/meals/filter?startDate=2015-05-30&startTime=07:00:00&endDate=2015-05-31&endTime=11:00:00" --user user@yandex.ru:password`

#### get Meals not found
`curl -s -v http://localhost:8080/topjava/rest/profile/meals/100008 --user user@yandex.ru:password`

#### delete Meals
`curl -s -X DELETE http://localhost:8080/topjava/rest/profile/meals/100002 --user user@yandex.ru:password`

#### create Meals
`curl -s -X POST -d '{"dateTime":"2015-06-01T12:00","description":"Created lunch","calories":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile/meals --user user@yandex.ru:password`

#### update Meals
`curl -s -X PUT -d '{"dateTime":"2015-05-30T07:00", "description":"Updated breakfast", "calories":200}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/profile/meals/100003 --user user@yandex.ru:password`

#### validate with Error
`curl -s -X POST -d '{}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/admin/users --user admin@gmail.com:admin`

#### update profile with existing email
`curl -s -X PUT -d '{"id":"100000","name":"lazha","email":"admin@gmail.com","password":"password","caloriesPerDay":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile --user user@yandex.ru:password`

#### create profile from anonymous
`curl -s -X POST -d '{"name":"lazha","email":"admin@gmail.com","password":"password","caloriesPerDay":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/profile/register`

#### update user with existing email from admin
`curl -s -X PUT -d '{"name":"lazha","email":"admin@gmail.com","password":"password","caloriesPerDay":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/admin/users/100000 --user admin@gmail.com:admin`

#### create user with existing email from admin
`curl -s -X POST -d '{"name":"lazha","email":"admin@gmail.com","password":"password","caloriesPerDay":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/admin/users --user admin@gmail.com:admin`

#### update meal with duplicated dateTime
`curl -s -X PUT -d '{"dateTime":"2015-05-30T10:00", "description":"update 100003 with dateTime from 100002", "calories":200}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/profile/meals/100003 --user user@yandex.ru:password`

#### create meal with duplicated dateTime
`curl -s -X POST -d '{"dateTime":"2015-05-30T10:00", "description":"create meal with dateTime from 100002", "calories":200}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/profile/meals --user user@yandex.ru:password`
