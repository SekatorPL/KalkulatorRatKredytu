<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kredyt</title>
</head>
<body>

	<h1>Generator rat kredytu</h1>

	<form action="hello" method="post">
		<table>
			<tr>
				<td>Kwota kredytu: <input type="number" name="kwotaKredytu" min="100"/></td>
				<td>Ilość rat: <input type="number" name="iloscRat" min="2"/></td>
			</tr>
			<tr>
				
			</tr>
			<tr>
				<td>Oprocentowanie: <input type="number" name="oprocentowanie" min="1"/></td>
			</tr>
				<td>Rodzaj rat:
					<input type="radio" name="rodzajRat" value="stala" checked="checked"/> stała
					<input type="radio" name="rodzajRat" value="malejaca"/> malejąca
				</td>
			<tr>
			<tr>
				<td>
					<input type="submit" value="Wyświetl raty" name="przycisk"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>