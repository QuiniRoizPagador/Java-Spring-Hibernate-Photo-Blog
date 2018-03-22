// clase Evento
function Event(id, name, location, startDate, endDate) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.startDate = startDate;
    this.endDate = endDate;
}

function editEvent(event) {
    $('#event-modal input[name="event-index"]').val(event ? event.id : '');
    $('#event-modal input[name="event-name"]').val(event ? event.name : '');
    $('#event-modal input[name="event-location"]').val(event ? event.location : '');
    $('#event-modal input[name="event-start-date"]').datepicker('update', event ? event.startDate : '');
    $('#event-modal input[name="event-end-date"]').datepicker('update', event ? event.endDate : '');
    $('#event-modal').modal();
}

function deleteEvent(event) {
    var dataSource = $('#calendar').data('calendar').getDataSource();
    for (var i in dataSource) {
        if (dataSource[i].id == event.id) {
            $.ajax({
                type: 'POST',
                url: "removeEvent",
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                data: JSON.stringify(event.id),
                success: function (string) {
                    if (string) {
                        dataSource.splice(i, 1);
                        $('#calendar').data('calendar').setDataSource(dataSource);
                    }
                },
                error: function (string) {
                    $.notify("Error!", "error");
                }
            });
            break;
        }
    }


}
function generateUUID() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
                .toString(16)
                .substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
            s4() + '-' + s4() + s4() + s4();
}

function saveEvent() {
    //new Evento(0, 'Google I/O', 'San Francisco, CA', new Date(currentYear, 4, 28), new Date(currentYear, 4, 29)),
    var event = new Event($('#event-modal input[name="event-index"]').val(), $('#event-modal input[name="event-name"]').val(),
            $('#event-modal input[name="event-location"]').val(), $('#event-modal input[name="event-start-date"]').datepicker('getDate'),
            $('#event-modal input[name="event-end-date"]').datepicker('getDate'));


    var dataSource = $('#calendar').data('calendar').getDataSource();
    if (event.id) {
        for (var i in dataSource) {
            dataSource.push(event);
            dataSource[i].name = event.name;
            dataSource[i].location = event.location;
            dataSource[i].startDate = event.startDate;
            dataSource[i].endDate = event.endDate;
            $('#event-modal').modal('hide');
            if (dataSource[i].id == event.id) {
                $.ajax({
                    type: 'POST',
                    url: "updateEvent",
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: JSON.stringify(event),
                    success: function (string) {

                    },
                    error: function (string) {
                        $.notify("Error!", "error");
                    }
                });
                break;
            }
        }
    } else
    {
        event.id = generateUUID();
        //dataSource.push(event);
        $.ajax({
            type: 'POST',
            url: "saveEvent",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(event),
            success: function (string) {
                dataSource.push(event);
                $('#calendar').data('calendar').setDataSource(dataSource);
                $('#event-modal').modal('hide');
            },
            error: function (string) {
                $.notify("Error!", "error");
            }
        });
    }

    /*$('#calendar').data('calendar').setDataSource(dataSource);
     $('#event-modal').modal('hide');*/
}

$(function () {
    $.ajax({
        type: 'POST',
        url: "listEvents",
        data: JSON.stringify(event.id),
        success: function (string) {

            $('#calendar').calendar({
                enableContextMenu: true,
                enableRangeSelection: true,
                language: "es",
                contextMenuItems: [
                    {
                        text: 'Actualizar',
                        click: editEvent
                    },
                    {
                        text: 'Eliminar',
                        click: deleteEvent
                    }
                ],
                selectRange: function (e) {
                    editEvent({startDate: e.startDate, endDate: e.endDate});
                },
                mouseOnDay: function (e) {
                    if (e.events.length > 0) {
                        var content = '';
                        for (var i in e.events) {
                            content += '<div class="event-tooltip-content">'
                                    + '<div class="event-name" style="color:' + e.events[i].color + '">' + e.events[i].name + '</div>'
                                    + '<div class="event-location">' + e.events[i].location + '</div>'
                                    + '</div>';
                        }

                        $(e.element).popover({
                            trigger: 'manual',
                            container: 'body',
                            html: true,
                            content: content
                        });
                        $(e.element).popover('show');
                    }
                },
                mouseOutDay: function (e) {
                    if (e.events.length > 0) {
                        $(e.element).popover('hide');
                    }
                },
                dayContextMenu: function (e) {
                    $(e.element).popover('hide');
                },
                dataSource: string
            });
            $('#save-event').click(function () {
                saveEvent();
            });
        },
        error: function (string) {

        }
    });

});