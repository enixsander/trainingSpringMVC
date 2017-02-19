/*global Backbone */
var app = app || {};

(function () {
    'use strict';

    // Todo Model
    // ----------

    // Our basic **Todo** model has `firstName`, `order`, and `completed` attributes.
    app.Todo = Backbone.Model.extend({
        // Default attributes for the todo
        // and ensure that each todo created has `firstName` and `completed` keys.
        defaults: {
            firstName: '',
            lastName: 'Nope'
        },

        idAttribute: '_id'
    });
})();
