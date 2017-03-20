/*global angular */

/**
 * The main controller for the app. The controller:
 * - retrieves and persists the model via the todoStorage service
 * - exposes the model to the template and provides event handlers
 */
angular.module('todomvc')
	.controller('TodoCtrl', function TodoCtrl($scope, $routeParams, $filter, store) {
		'use strict';

		var todos = $scope.todos = store.todos;

		$scope.newTodo = '';
        $scope.newTodo2 = '';
        $scope.editedTodo = null;

		$scope.addTodo = function () {
			var newTodo = {
				mood: $scope.newTodo.trim(),
				date: $scope.newTodo2.trim()
			};

			if (!newTodo.mood) {
				return;
			}

			$scope.saving = true;
			store.insert(newTodo)
				.then(function success() {
					$scope.newTodo = '';
				})
				.finally(function () {
					$scope.saving = false;
				});
		};

		$scope.editTodo = function (todo) {
			$scope.editedTodo = todo;
			// Clone the original todo to restore it on demand.
			$scope.originalTodo = angular.extend({}, todo);
		};

		$scope.saveEdits = function (todo, event) {
			// Blur events are automatically triggered after the form submit event.
			// This does some unfortunate logic handling to prevent saving twice.
			if (event === 'blur' && $scope.saveEvent === 'submit') {
				$scope.saveEvent = null;
				return;
			}

			$scope.saveEvent = event;

			todo.mood = todo.mood.trim();

			if (todo.mood === $scope.originalTodo.mood) {
				$scope.editedTodo = null;
				return;
			}

			store[todo.mood ? 'put' : 'delete'](todo)
				.then(function success() {}, function error() {
					todo.mood = $scope.originalTodo.mood;
				})
				.finally(function () {
					$scope.editedTodo = null;
				});
		};

		$scope.removeTodo = function (todo) {
			store.delete(todo);
		};

		$scope.saveTodo = function (todo) {
			store.put(todo);
		};

	});
