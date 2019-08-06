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
        $('#filterForm').find(":input").val("");
    });
});

function filter() {
    $.ajax({
        type: "POST",
        url: context.ajaxUrl + "filter",
        data: $('#filterForm').serialize(),
        success: function (data) {
            context.datatableApi.clear().rows.add(data).draw()
        }
    });
}

function updateTable() {
    filter();
}