let api = 'localhost:8080/';

$(function () {
    let params = new URLSearchParams(window.location.search)

    if (params.has('id')) {
        $('#view').load("./components/search.html", function () {
            if (params.has('search')) {
                loadResultsTable(params.get('search'), params.get('type'));
            }
        });
    } else {

        $('#view').load("./components/details.html", function () {
            let id = params.get('id');
            let type = params.get('type');


        });
    }

    console.log("Document Loaded!");
});


function loadResultsTable(search, type) {
    console.log("Search: " + search + " | Type: " + type);
    $('#results').load("./components/table.html", function () {

        let header = $('#results').find('thead').find('tr');
        header.html('<tr>Test</tr>')
        if (type === 'species') {
            header.html('<th>Name</th><th>Origin</th><th>Tax Group</th>');
        } else if (type === 'settlement') {
            header.html('<th>Name</th><th>Type</th><th>Base Tax</th>');
        }

        $.ajax({
            url: api + '/' + type + '/all',
            success: function (data) {
                $('#view').load("./components/table.html", function () {
                    let tableBody = $('#table').find('tbody');


                    data.forEach(element => {
                        let row = $('<tr>');
                        row.append($('<td>').text(element.name));
                        row.append($('<td>').text(element.origin));
                        row.append($('<td>').text(element.taxGroup));

                        tableBody.append(row);
                    });
                });
            }
        });
    });

}
