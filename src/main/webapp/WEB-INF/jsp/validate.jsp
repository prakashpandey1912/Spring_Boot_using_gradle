<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
</head>
<form action='/validate' method='POST'>
<label for="inputUserName">User Name</label><input id="userName" type="text" name="username" placeholder="Enter user name" required /><span color='red'>${validationError.username}</span></div>
<button type="submit">confirm</button>
</form>
</body>
</html>