angular.module('todomvc')
    .controller('MapCtrl', ['$scope', '$http', function($scope, $http) {
        'use strict';
        //$scope.markers = store.todos;
        //$scope.infoBox = new google.maps.InfoWindow();
        var mapContainer = document.getElementById('map_container');
        var map;
        $scope.clickAddMarker = false;
        mapContainer.style.width = '95%';
        mapContainer.style.height = '650px';
        var infoWindow = new google.maps.InfoWindow();

        infoWindow.addListener('domready', function() {

            // Reference to the DIV that wraps the bottom of infowindow
            var iwOuter = $('.gm-style-iw');

            /* Since this div is in a position prior to .gm-div style-iw.
             * We use jQuery and create a iwBackground variable,
             * and took advantage of the existing reference .gm-style-iw for the previous div with .prev().
             */
            var iwBackground = iwOuter.prev();

            // Removes background shadow DIV
            iwBackground.children(':nth-child(2)').css({'display' : 'none'});

            // Removes white background DIV
            iwBackground.children(':nth-child(4)').css({'display' : 'none'});

            // Moves the infowindow 115px to the right.
            iwOuter.parent().parent().css({left: '115px'});

            // Moves the shadow of the arrow 76px to the left margin.
            iwBackground.children(':nth-child(1)').attr('style', function(i,s){ return s + 'left: 76px !important;'});

            // Moves the arrow 76px to the left margin.
            iwBackground.children(':nth-child(3)').attr('style', function(i,s){ return s + 'left: 76px !important;'});

            // Changes the desired tail shadow color.
            iwBackground.children(':nth-child(3)').find('div').children().css({'box-shadow': 'rgba(72, 181, 233, 0.6) 0px 1px 6px', 'z-index' : '1'});

            // Reference to the div that groups the close button elements.
            var iwCloseBtn = iwOuter.next();

            // Apply the desired effect to the close button
            iwCloseBtn.css({opacity: '1', right: '38px', top: '3px', border: '7px solid #48b5e9', 'border-radius': '13px', 'box-shadow': '0 0 5px #3990B9'});

            // If the content of infowindow not exceed the set maximum height, then the gradient is removed.
            if($('.iw-content').height() < 140){
                $('.iw-bottom-gradient').css({display: 'none'});
            }

            // The API automatically applies 0.7 opacity to the button after the mouseout event. This function reverses this event to the desired value.
            iwCloseBtn.mouseout(function(){
                $(this).css({opacity: '1'});
            });
        });

        $scope.initialize = function() {
            var latLng = new google.maps.LatLng(52.0899983, 23.6778754);
            var myOptions = {
                zoom: 4,
                center: latLng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(mapContainer, myOptions);

            //add markerCluster
            $http.get('/marker').success(function (data) {
                var markers = data.map(function (marker) {
                    return addMarker(marker);
                });
                // Add a marker clusterer to manage the markers.
                var markerCluster = new MarkerClusterer(map, markers,
                    {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
            });

            // Event that closes the Info Window with a click on the map
            map.addListener('click', function(e) {
                if($scope.clickAddMarker == true)
                    placeMarkerAndPanTo(e.latLng);
                infoWindow.close();
            });

            // Listen for clicks and add the location of the click to firebase.
/*            map.addListener('click', function(e) {
                data.latitude = e.latLng.lat();
                data.longitude = e.latLng.lng();
                data.mood = "test";
                addMarker(data);
            });*/

        };

        //test click listener
        function placeMarkerAndPanTo(latLng) {
            var marker = new google.maps.Marker({
                position: latLng,
                map: map
            });
            map.panTo(latLng);
            $scope.clickAddMarker = false;
        }

        // Adds a marker with info content to the map.
        function addMarker(marker) {
            var googleMarker = new google.maps.Marker({
                position: new google.maps.LatLng(marker.latitude, marker.longitude),
                //map: $scope.map,
                title: marker.mood
            });

            // InfoWindow content
            var content = '<div id="iw-container">' +
                '<div class="iw-title">Брестская крепость</div>' +
                '<div class="iw-content">' +
                '<div class="iw-subTitle">История</div>' +
                '<img src="http://allcastle.info/assets/images/foto/663-1.jpg" alt="Брестская крепость" height="115" width="83">' +
                '<p>Бре́стская кре́пость — крепость в черте города Бреста в Белоруссии, у впадения реки Мухавец в Западный Буг, а также Тереспольской гмины Польши. Крепость-герой.</p>' +
                '<div class="iw-subTitle">Контакты</div>' +
                '<p>VISTA ALEGRE ATLANTIS, SA<br>3830-292 Ílhavo - Portugal<br>'+
                '<br>Phone. +937 99 92<br>e-mail: email@vaa.pt<br>www: www.localhost.com</p>'+
                '</div>' +
                '<div class="iw-bottom-gradient"></div>' +
                '</div>';

            infoWindow.setContent(content);
            // This event expects a click on a marker
            // When this event is fired the Info Window is opened.
            googleMarker.addListener('click', function() {
                infoWindow.open(googleMarker.get('map_container'), googleMarker);

            });

            return googleMarker;
        }

        $scope.newMarker = function() {
            map.addListener('click', function(e) {
                placeMarkerAndPanTo(e.latLng, map);
            });
        };

/*        $scope.showMarker = function(marker) {
            var coordinates = new google.maps.LatLng(marker.latitude, marker.longitude);


            $scope.infoBox.setContent('<div id="content">' + marker.mood + '</div>');
            $scope.infoBox.setPosition(coordinates);
            $scope.infoBox.open($scope.map);
            //$scope.map.setCenter(coords);
        }*/


    }]);