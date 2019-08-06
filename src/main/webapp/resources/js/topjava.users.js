// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: "ajax/admin/users/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
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
                        "asc"
                    ]
                ]
            })
        }
    );
});

function updateTable() {
    $.get(context.ajaxUrl, function (data) {
        redrawTable(data);
    });
}

function enable(checkbox, id) {
    var enabled = checkbox.is(":checked");
    $.ajax({
        url: context.ajaxUrl + id,
        type: "POST",
        data: "enabled=" + enabled,
    }).done(function (data) {
        checkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "enabled" : "disabled")
    }).fail(function (jqXHR, textStatus) {
        checkbox.prop("checked", !enabled);
    });
}