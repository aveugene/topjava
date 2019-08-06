// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: "ajax/profile/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]
                ]
            })
        }
    );

    $("#clear").click(function () {
        $('#filterForm')[0].reset();
        updateTable();
    });
});

function filter() {
    $.ajax({
        type: "POST",
        url: context.ajaxUrl + "filter",
        data: $('#filterForm').serialize(),
        success: function (data) {
            redrawTable(data)
        }
    });
}

function updateTable() {
    filter();
}