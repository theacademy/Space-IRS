let url = new URL(window.location.origin + window.location.pathname);
let api = 'http://localhost:8080/';

$(function () {
    let params = new URLSearchParams(window.location.search)

    if (!params.has('id')) {
        $('#homeButton').hide();
        showSearchPage(params);
        searchViewButtons();
    } else {
        $('#homeButton').show();
        showDetailsPage(params);
    }

    console.log("Document Loaded!");
});

// View Functions -------------------------------------------------------------

function showSearchPage(params) {
    $('#view').load("./components/search.html", function () {
        $('#wildcard').on('click', function () {
            $.ajax({
                url: api + `${$('#type').val()}/all`,
                success: data => loadObjectData('#results', data, $('#type').val())
            });
        });

        if (params.has('search')) {
            $.ajax({
                url: api + `${params.get('type')}/search/${params.get('search')}`,
                success: data => loadObjectData('#results', data, params.get('type')),
            });
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

                $.ajax({
                    url: api + type + '/subtable/' + id,
                    success: data =>
                        loadObjectData('#subtable', data, type),
                });
            }
        });

    });
}

// Button Functions ------------------------------------------------------------

function searchViewButtons() {
    let species = ['👽', '👾', '🤖', '😀', '🥶', '😺', '👻', '🐶', '🦁', '🦊', '🐸', '🐼', '🐨', '🐻', '🐗', '🕷', '🦚', '🦀', '🦐', '🦑', '🐙', '🐧', '🐌', '🐠', '🐲'];
    $('#upperButton').find('h2').text(species[Math.floor(Math.random() * species.length)]);
    $('#upperButton').prop('title', 'Create Species')
    $('#upperButton').on('click', function () {
        console.log('Upper Button Clicked');
    });

    let settlements = ['🏘', '🏙', '⚙', '🖨', '💾', '💿', '🧶', '🌍', '🌎', '🌏', '🌐', '🕍', '🏛', '🕋', '🏭', '🌋', '🏔', '🌁', '🧳', '🌔', '☀', '🌀', '🚠', '🚀', '🛰', '🌌', '☄'];
    $('#lowerButton').find('h2').text(settlements[Math.floor(Math.random() * settlements.length)]);
    $('#lowerButton').prop('title', 'Create Settlement')
    $('#lowerButton').on('click', function () {
        console.log('Lower Button Clicked');
    });
}

// Controller Functions --------------------------------------------------------

function createTable(id, headings, rows, type) {
    $(id).load("./components/table.html", function () {
        let header = $('#results').find('thead').find('tr');
        let body = $('#results').find('tbody');
        header.html(headings);

        Object.entries(rows).forEach(([id, rowHTML]) => {
            let row = $('<tr>').addClass('cursor-pointer hover:bg-slate-200 hover:text-slate-600');
            row.html(rowHTML);

            row.on('click', function () {
                // Set the URL parameters
                url.searchParams.set('id', id);
                url.searchParams.set('type', type);

                // Redirect to the new URL
                window.location.href = url.toString();
            });

            body.append(row);
        });

        $(id).find('th').each(function () {
            $(this).addClass('px-3 py-1 whitespace-nowrap');
        });

        $(id).find('tr').each(function () {
            $(this).find('td').each(function () {
                $(this).addClass('px-3 py-1 whitespace-nowrap');
            });
        });

    });
}

function loadObjectData(tableID, data, type) {
    var headings;
    var rows;
    if (type === 'species') {
        headings = '<th>Name</th><th>Origin</th>';
        rows = data.reduce((dict, species) => {
            let origin = species.origin ? species.origin.name : 'Unknown';
            dict[species.id] = `<td>${sentencify(species.name)}</td><td>${sentencify(origin)}</td>`;
            return dict;
        }, {});
    } else {
        headings = '<th>Name</th><th>Type</th><th>Base Tax</th>';
        rows = data.reduce((dict, settlement) => {
            dict[settlement.id] = `<td>${sentencify(settlement.name)}</td><td>${sentencify(settlement.type)}</td><td>${settlement.taxModifier + '%'}</td>`;
            return dict;
        }, {});
    }
    createTable(tableID, headings, rows, type);
}

// Helper Functions ------------------------------------------------------------

function sentencify(string) {
    if (string === undefined) return string;
    return string.charAt(0).toUpperCase() + string.slice(1);
}
