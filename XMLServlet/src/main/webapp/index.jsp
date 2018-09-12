<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>XML</title>
</head>
<body>
	<form action = 'Controller'>
		<input type = 'hidden' name = 'command' value = 'forward'>
		<label>
			Choose medicine number:
		</label>
		<input name = 'number' type='number' min = '1' max = '16'>
		<button type = 'submit'>Send</button>
	</form>
</body>
</html>
