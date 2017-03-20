angular.module('todomvc')
    .controller('oauthCtrl',
        function($scope, $resource, $http, $httpParamSerializer, $cookies) {

            $scope.$on('login()', function(event, token) {
                $http.defaults.headers.common.Authorization= 'Bearer ' + token.access_token;
            });

            $scope.data = {
                grant_type:"password",
                username: "",
                password: "",
                client_id: "my-trusted-client"
            };
            $scope.encoded = btoa("my-trusted-client:secret");

            $scope.login = function() {
                var req = {
                    method: 'POST',
                    url: "http://localhost:8088/oauth/token",
                    headers: {
                        "Authorization": "Basic " + $scope.encoded,
                        "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                    },
                    data: $httpParamSerializer($scope.data)
                };
                $http(req).then(function(data){
                    $http.defaults.headers.common.Authorization =
                        'Bearer ' + data.data.access_token;
                    $cookies.put("access_token", data.data.access_token);
                    console.log($cookies.get("access_token"));
                    //window.location.href="index";
                });

                if($cookies.get("access_token")){
                    $http.defaults.headers.common.Authorization =
                        'Bearer ' + $cookies.get("access_token");
                } else{
                    window.location.href = "login";
                }
            }
        });