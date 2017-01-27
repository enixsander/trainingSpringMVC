/*global Backbone */
var app = app || {};

(function () {
    'use strict';

    // Todo Model
    // ----------

    // Our basic **Todo** model has `name`, `order`, and `completed` attributes.
    app.Todo = Backbone.Model.extend({
        // Default attributes for the todo
        // and ensure that each todo created has `name` and `completed` keys.
        defaults: {
            name: '',
            country: 'Nope'
        },

        idAttribute: '_id'
    });
})();
