let api = 'http://127.0.0.1:8080/';

$(function () {
    let params = new URLSearchParams(window.location.search)

    if (!params.has('id')) {
        showSearchPage(params);
    } else {
        showDetailsPage(params);
    }

    console.log("Document Loaded!");
});

// View Functions -------------------------------------------------------------

function showSearchPage(params) {
    $('#view').load("./components/search.html", function () {
        if (params.has('search')) {
            loadResultsTable(params.get('search'), params.get('type'));
        }
    });
}

function showDetailsPage(params) {
    $('#view').load("./components/details.html", function () {
        let id = params.get('id');
        let type = params.get('type');
        $("#detailsForm input").prop("disabled", true);

        $.ajax({
            url: api + type + '/get/' + id,
            success: function (data) {
                console.log(data);
                $('#title').attr("placeholder", sentencify(data.name));
                $('#info-L').attr("placeholder", sentencify(data.type));
                $('#info-R').attr("placeholder", sentencify(data.directions));
            }
        });

    });
}


// Controller Functions --------------------------------------------------------

function loadResultsTable(search, type) {
    $('#results').load("./components/table.html", function () {

        let header = $('#results').find('thead').find('tr');
        header.html('<tr>Test</tr>')
        if (type === 'species') {
            header.html('<th>Name</th><th>Origin</th>'); // Removed <th>Tax Group</th> as it's not given in the API
        } else if (type === 'settlement') {
            header.html('<th>Name</th><th>Type</th><th>Base Tax</th>');
        }

        $.ajax({
            url: api + type + '/all',
            success: function (data) {
                console.log(data);
                let body = $('#results').find('tbody');


                data.forEach(element => {
                    let row = $('<tr>');
                    row.append($('<td>').text(sentencify(element.name)));
                    row.append($('<td>').text(sentencify(type === "species" ? element.origin.name : element.type)));
                    if (type === 'settlement') {
                        row.append($('<td>').text(element.taxModifier + '%'));
                    }

                    row.on('click', function () {
                        // Set the URL parameters
                        let url = new URL(window.location.origin + window.location.pathname);
                        console.log(url);
                        url.searchParams.set('id', element.id);
                        url.searchParams.set('type', type);

                        // Redirect to the new URL
                        window.location.href = url.toString();
                    });


                    body.append(row);
                });
            }
        });
    });

}

// Helper Functions ------------------------------------------------------------

function sentencify(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}