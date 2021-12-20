<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="resources/security.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>AJAX</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<body>

<section class="w-auto p-3" style="background-color: #9A616D;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block">
                            <img
                                    src="images/elya.jpg"
                                    alt="reg form"
                                    class="img-fluid" style="border-radius: 1rem 0 0 1rem;"
                            />
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">

                                <form>
                                    <div class="d-flex align-items-center mb-3 pb-1">
                                        <i class="fa fa-cubes" aria-hidden="true"></i>
                                        <span class="h1 fw-bold mb-0">JOIN TO JOB4J CARS</span>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="email" id="email" required class="form-control form-control-lg"/>
                                        <label class="form-label" for="email">Email address</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input id="login" required class="form-control form-control-lg"/>
                                        <label class="form-label" for="login">Login</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input id="name" required class="form-control form-control-lg"/>
                                        <label class="form-label" for="name">Name</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input id="patronymic" required class="form-control form-control-lg"/>
                                        <label class="form-label" for="patronymic">Patronymic</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input id="surname" required class="form-control form-control-lg"/>
                                        <label class="form-label" for="surname">Surname</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" id="passwordFirst" required
                                               class="form-control form-control-lg"/>
                                        <label class="form-label" for="passwordFirst">Password</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" id="passwordSecond" required
                                               class="form-control form-control-lg"/>
                                        <label class="form-label" for="passwordSecond">Password</label>
                                    </div>

                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-dark btn-lg btn-block"
                                                onclick="return performRegistration($('#email').val(), $('#login').val(),
                                                  $('#name').val(), $('#patronymic').val(),  $('#surname').val(), $('#passwordFirst').val(),  $('#passwordSecond').val(),)"
                                                type="button">Registr me now!!!
                                        </button>
                                    </div>

                                    <a class="small text-muted" href="#!">Forgot password?</a>
                                    <p class="mb-5 pb-lg-2"
                                       onclick='return changeLocation("<%=request.getContextPath()%>/auth.do")'
                                       style="color: #393f81;">Already in team? <a href="#!"
                                                                                   style="color: #393f81;">Back to
                                        login</a></p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>