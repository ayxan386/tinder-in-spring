<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="static/css/style.css">
</head>
<body style="background-color: #f5f5f5;">
<div class="col-4 offset-1">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src=${photo_link! notfound} alt="" class="mx-auto rounded-circle img-fluid">
                    <h3 class="mb-0 text-truncated">${userName! 'notfound'}</h3>
                    <br>
                </div>
                <div class="col-12 col-lg-6">
                    <form action="/users" method="post">
                        <input name="id" style="display: none" value=${user_id}>
                        <input name="liked" style="display: none" value="false">
                        <button class="btn btn-outline-danger btn-block" type="submit"><span
                                    class="fa fa-times"></span>
                            Dislike
                        </button>
                    </form>
                </div>
                <div class="col-12 col-lg-6">
                    <form action="/users" method="post">
                        <input name="id" style="display: none" value=${user_id}>
                        <input name="liked" style="display: none" value="true">
                        <button class="btn btn-outline-success btn-block" type="submit"><span
                                    class="fa fa-heart"></span> Like
                        </button>
                    </form>
                </div>
                <!--/col-->
                <div class="row justify-content-center align-items-center"><a href="/liked">See whom you liked</a></div>
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>
</body>
</html>