var url = new URL(window.location.origin + window.location.pathname);
var api = 'http://localhost:8080/';
var params = new URLSearchParams(window.location.search)


$(function () {

    if (!params.has('id')) {
        $('#homeButton').hide();
        showSearchPage();
        searchViewButtons();
    } else {
        $('#homeButton').show();
        detailsViewButtons();
        showDetailsPage();
    }

    console.log("Document Loaded!");
});

// View Functions -------------------------------------------------------------

function showSearchPage() {
    $('#view').load("./components/search.html", function () {
        $('#wildcard').on('click', function () {
            $.ajax({
                url: api + `${$('#type').val()}/all`,
                success: data => loadSearchTable(data, $('#type').val())
            });
        });

        if (params.has('search')) {
            $.ajax({
                url: api + `${params.get('type')}/search/${params.get('search')}`,
                success: data => loadSearchTable(data, params.get('type')),
            });
        }
    });
}

function showDetailsPage() {
    $('#view').load("./components/details.html", function () {
        let id = params.get('id');
        let type = params.get('type');
        $("#detailsForm input").prop("disabled", true);

        $.ajax({
            url: api + type + '/get/' + id,
            type: 'GET',
            success: function (data) {
                console.log(data);
                if (type === 'species') {
                    $('#title span').text('Species :');
                    $('#title input').attr("placeholder", sentencify(data.name));
                    $('#info-L span').text('Species Origin');
                    $('#info-L input').attr("placeholder", sentencify(data.origin ? data.origin.name : 'Unknown'));
                    $('#info-R span').text('Tax Group');
                    $('#info-R input').attr("placeholder", data.taxGroup);
                    $('#populationsTable h3').text('Settlements');
                    $('#maths').hide();
                } else {
                    $('#title span').text('Settlement :');
                    $('#title input').attr("placeholder", sentencify(data.name));
                    $('#info-L span').text('Settlement Type');
                    $('#info-L input').attr("placeholder", sentencify(data.type));
                    $('#info-R span').text('Directions to Settlement');
                    $('#info-R input').attr("placeholder", sentencify(data.directions));
                    $('#populationsTable h3').text('Inhabitants');
                    $('#maths').show();
                    $('#maths input').attr("placeholder", data.taxModifier);
                }

                $.ajax({
                    url: api + 'populations/' + type + '/' + id,
                    type: 'GET',
                    success: data =>
                        loadPopulationsTable(data, type),
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
        url.searchParams.set('id', "");
        url.searchParams.set('type', 'species');

        // Redirect to the new URL
        window.location.href = url.toString();

    });

    let settlements = ['🏘', '🏙', '⚙', '🖨', '💾', '💿', '🧶', '🌍', '🌎', '🌏', '🌐', '🕍', '🏛', '🕋', '🏭', '🌋', '🏔', '🌁', '🧳', '🌔', '☀', '🌀', '🚠', '🚀', '🛰', '🌌', '☄'];
    $('#lowerButton').find('h2').text(settlements[Math.floor(Math.random() * settlements.length)]);
    $('#lowerButton').prop('title', 'Create Settlement')
    $('#lowerButton').on('click', function () {
        url.searchParams.set('id', "");
        url.searchParams.set('type', 'settlement');

        // Redirect to the new URL
        window.location.href = url.toString();
    });
}

function detailsViewButtons() {
    $('#upperButton').find('h2').text('🗑');
    $('#upperButton').prop('title', 'Delete')
    $('#upperButton').on('click', function () {
        console.log('Deleting...');
        deleteRecord();
        window.location.href = url.toString();
    });

    $('#lowerButton').find('h2').text('🖊');
    $('#lowerButton').prop('title', 'Edit')
    $('#lowerButton').on('click', flipEditButton);
}

function flipEditButton() {
    if ($('#lowerButton').find('h2').text() === '🖊') {
        $('#lowerButton').find('h2').text('💾');
        $('#lowerButton').prop('title', 'Save');
        $("#detailsForm input").prop("disabled", false);
    } else {
        $('#lowerButton').find('h2').text('🖊');
        $('#lowerButton').prop('title', 'Edit');
        $("#detailsForm input").prop("disabled", true);
        saveRecord();
        location.reload();
    }
}


// Table Functions --------------------------------------------------------

function createTable(divId, headings, rows, type) {
    $(divId).load("./components/table.html", function () {
        let header = $(divId).find('thead').find('tr');
        let body = $(divId).find('tbody');
        header.html(headings);

        Object.entries(rows).forEach(([params, rowHTML]) => {
            let row = $('<tr>').addClass('cursor-pointer hover:bg-slate-200 hover:text-slate-600');
            row.html(rowHTML);

            row.on('click', function () {
                // Set the URL parameters
                let split = params.split(',')
                url.searchParams.set('id', split[0]);
                url.searchParams.set('type', split[1]);

                // Redirect to the new URL
                window.location.href = url.toString();
            });

            body.append(row);
        });

        $(divId).find('th').each(function () {
            $(this).addClass('px-3 py-1 whitespace-nowrap');
        });

        $(divId).find('tr').each(function () {
            $(this).find('td').each(function () {
                $(this).addClass('px-3 py-1 whitespace-nowrap');
            });
        });

    });
}

function loadSearchTable(data, type) {
    console.log('Loading Search Table...', data);
    let tableId = '#resultsTable';
    let headings;
    let rows;

    if (type === 'species') {
        headings = '<th>Name</th><th>Origin</th><th>Tax Group</th>';
        rows = data.reduce((dict, species) => {
            let origin = species.origin ? species.origin.name : 'Unknown';
            dict[[species.id, type]] = `<td>${sentencify(species.name)}</td><td>${sentencify(origin)}</td><td>${species.taxGroup}</td>`;
            return dict;
        }, {});
    } else {
        headings = '<th>Name</th><th>Type</th><th>Base Tax</th>';
        rows = data.reduce((dict, settlement) => {
            dict[[settlement.id, type]] = `<td>${sentencify(settlement.name)}</td><td>${sentencify(settlement.type)}</td><td>${settlement.taxModifier + '%'}</td>`;
            return dict;
        }, {});
    }
    createTable(tableId, headings, rows, type);
}

async function loadPopulationsTable(data, type) {
    console.log('Loading Population Table...', data);
    let tableId = '#populationsTable div';
    let headings;
    let rows = {};

    if (type === 'settlement') {
        headings = '<th>Name</th><th>Population</th><th>Calculated Tax Rate</th>';
        for (const element of data) {
            rows[[element.species.id, 'species']] = `<td>${sentencify(element.species.name)}</td><td>${element.population}</td><td>${await calcTaxRate(element.settlement.id, element.species.id)}%</td>`;
        }
    } else {
        headings = '<th>Name</th><th>Population<th>Calculated Tax Rate</th>';
        for (const element of data) {
            rows[[element.settlement.id, 'settlement']] = `<td>${sentencify(element.settlement.name)}</td><td>${element.population}</td><td>${await calcTaxRate(element.settlement.id, element.species.id)}%</td>`;
        }
    }
    createTable(tableId, headings, rows, type);
}
// API Functions ------------------------------------------------------------

function saveRecord() {
    console.log('Saving...');
    let id = params.get('id');
    let type = params.get('type');

    let title = $("#detailsForm").find('input[name="title"]').val() || $("#detailsForm").find('input[name="title"]').attr("placeholder");
    let infoL = $("#detailsForm").find('input[name="input-L"]').val() || $("#detailsForm").find('input[name="input-L"]').attr("placeholder");
    let infoR = $("#detailsForm").find('input[name="input-R"]').val() || $("#detailsForm").find('input[name="input-R"]').attr("placeholder");
    let math = $("#detailsForm").find('input[name="mathResult"]').val() || $("#detailsForm").find('input[name="mathResult"]').attr("placeholder");
    let output;
    if (type === 'species') {
        output = {
            name: title,
            origin: infoL,
            taxGroup: infoR,
        };
    } else {
        output = {
            name: title,
            type: infoL,
            directions: infoR,
            taxModifier: math
        };
    }
    if (id) output.id = id;

    console.log(output);

    $.ajax({
        url: api + type + (id ? '/update/' + id : '/add'),
        type: id ? 'PUT' : 'POST',
        data: JSON.stringify(output),
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
            url.searchParams.set('id', "");
        }
    });
}

function deleteRecord() {
    let id = params.get('id');
    let type = params.get('type');

    $.ajax({
        url: api + type + '/delete/' + id,
        type: 'DELETE',
        success: function (data) {
            console.log(data);
        }
    });
}

async function calcTaxRate(settlementId, speciesId) {
    console.log(api + 'settlement/tax/' + settlementId + '/' + speciesId);
    let output = null;
    await $.ajax({
        url: api + 'settlement/tax/' + settlementId + '/' + speciesId,
        type: 'GET',
        success: (data) => output = data,
    });
    return output;
}


// Helper Functions ------------------------------------------------------------

function sentencify(string) {
    if (!string) return 'Unknown';
    return string.charAt(0).toUpperCase() + string.slice(1);
}

