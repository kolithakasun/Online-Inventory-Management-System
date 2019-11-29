<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=8; IE=EDGE">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="blue.css" rel="stylesheet" type="text/css">
<title>Staff Login</title>
</head>
<body bagrounf>

        <div class="view-container">
            <form class="prompt" action="EmployeeLoginController" method="post" name="f" autocomplete="off">
                <div class="content with-header">
                    <div class="header">
                        <f-icon class="ftnt-fortinet-grid icon-xl"></f-icon>
                        <div>Staff Login</div>
                    </div>
                    <div class="sub-content">
                        <div class="wide-inputs">



		Enter username :<input type="text" name="username" placeholder="Name"> <br>
		Enter password :<input type="password" name="password" placeholder="Password"><br>
		<input type="submit" value="Login">
	</div>
                    </div>
                </div>
            </form>
        </div>

</body>
</html>