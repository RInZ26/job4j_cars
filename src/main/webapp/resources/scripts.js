function addAllAdverts() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_cars/advert.do',
    }).done(function (adverts) {
        for (var advert of adverts) {
            addAdvert(advert);
        }
    }).fail(function (err) {
        console.log(err);
    });
}

function addAdvert(advert) {
    let color = getColourByCarcaseType(advert.car.carcaseType);
    $('#advertsTableBody tr:last').after(`<tr class=${color}><th scope=\"row\">${advert.id}</th><td>${advert.car.carModel}</td><td>${advert.car.carcaseType}</td><td>${advert.sold}</td>
<td>${advert.description}</td><td>${advert.author.login}</td><td>${advert.publishDate}</td></tr>`)

}

function getColourByCarcaseType(carcaseType) {
    switch (carcaseType) {
        case "SEDAN":
            return "bg-warning";
        case "MINIVAN":
            return "bg-success";
        case "HATCHBACK" :
            return "bg-primary";
        default:
            return "";
    }
}
