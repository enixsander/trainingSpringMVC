/*global Backbone */
var app = app || {};

(function () {
    'use strict';

    // Todo Router
    // ----------
    var TodoRouter = Backbone.Router.extend({
        routes: {
            'read' : 'read'
        },
        //#read
        read: function() {
            console.log('это роут Read');   
        }

    });

    app.TodoRouter = new TodoRouter();
    Backbone.history.start();
})();
