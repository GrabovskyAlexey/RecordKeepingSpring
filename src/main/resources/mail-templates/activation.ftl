<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<p>Добро пожаловать, ${username}</p>
<p>Для подтверждения адреса электронной почты необходимо пройти по
    <a href="https://localhost:8080/app/api/v1/activation?email=${email}&code=${activationCode}">ссылке</a></p>
</body>
</html>