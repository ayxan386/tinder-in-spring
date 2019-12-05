<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <meta content="" name="description">
    <meta content="" name="author">
    <link href="img/favicon.ico" rel="icon">

    <title>Login Page</title>

    <!-- Bootstrap core CSS -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/style.css" rel="stylesheet">
</head>

<body class="text-center">
<form METHOD="post" class="form-signin">
    <img alt="" class="mb-4" height="72" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" width="72">
    <h1 class="h3 mb-3 font-weight-normal">Please register</h1>

    <label class="sr-only" for="inputEmail">Email address</label>
    <input autofocus class="form-control" id="inputEmail" name="email" placeholder="Email address" required
           type="email">
    <label class="sr-only" for="inputName">Name</label>
    <input autofocus class="form-control" id="inputName" name="name" placeholder="Name" required
           type="text">


    <label class="sr-only" for="inputPassword">Password</label>
    <input class="form-control" id="inputPassword" name="password" placeholder="Password" required type="password">

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <div class="row align-items-center justify-content-center">Already have an account. <a href="/login">Login here</a>
    </div>
    <p class="mt-5 mb-3 text-muted">&copy; Tinder 2018</p>
</form>
</body>
</html>