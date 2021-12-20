function performLogin(email, password) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/job4j_cars/auth.do',
        data: {
            "email": email,
            "password": password
        },
        dataType: 'json'
    }).done(function (jsonResponse) {
        if (jsonResponse.errorMsg != null) {
            alert(jsonResponse.errorMsg)
        } else {
            window.location = jsonResponse.url;
        }
    }).fail(function (err) {
        console.log(err);
    });
}

function performRegistration(email, login, name, patronymic, surname, firstPassword, secondPassword) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/job4j_cars/reg.do',
        data: {
            "email": email,
            "login": login,
            "name": name,
            "patronymic": patronymic,
            "surname": surname,
            "firstPassword": firstPassword,
            "secondPassword": secondPassword
        },
        dataType: 'json'
    }).done(function (jsonResponse) {
        if (jsonResponse.errorMsg != null) {
            alert(jsonResponse.errorMsg)
        } else {
            changeLocation(jsonResponse.url);
        }
    }).fail(function (err) {
        console.log(err);
    });
}

function changeLocation(url) {
    window.location = url;
}