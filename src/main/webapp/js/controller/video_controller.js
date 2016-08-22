'use strict';

App.controller('VideoController', ['$scope', '$modal', 'VideoService',  function ($scope, $modal, VideoService) {

    var self = this;
    self.video = {id: null, title:null};
    self.videos = [];
    var widgetId;



    self.fetchAllVideos = function () {
        VideoService.fetchAllVideos()
            .then(
                function (d) {
                    self.videos = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Videos');
                }
            );
    };

    self.createVideo = function (video) {
        var v = grecaptcha.getResponse(widgetId);
        if (v.length != 0) {
            VideoService.createVideo(video)
                .then(
                    self.fetchAllVideos,
                    function (errResponse) {
                        console.error('Error while creating Video.');
                    }
                );
        }
        else {
            document.getElementById('captcha').innerHTML = "You can't leave Captcha Code empty";
        }
    };

    self.updateVideo = function (video, id) {
        var v = grecaptcha.getResponse(widgetId);
        if (v.length != 0) {
            VideoService.updateVideo(video, id)
                .then(
                    self.fetchAllVideos,
                    function (errResponse) {
                        console.error('Error while updating Video.');
                    }
                );
        }
        else {
            document.getElementById('captcha').innerHTML = "You can't leave Captcha Code empty";
        }
    };

    self.deleteVideo = function (id) {
        VideoService.deleteVideo(id)
            .then(
                self.fetchAllVideos,
                function (errResponse) {
                    console.error('Error while deleting Video.');
                }
            );
    };

    self.fetchAllVideos();

    self.submit = function () {
        if (self.video.id == null) {
            self.createVideo(self.video);
        } else {
            self.updateVideo(self.video, self.video.id);
        }

    };

    self.edit = function (id) {

        for (var i = 0; i < self.videos.length; i++) {
            if (self.videos[i].id == id) {
                self.video = angular.copy(self.videos[i]);
                break;
            }
        }
    };

    self.remove = function (id) {
        if (self.video.id === id) {
            self.reset();
        }
        self.deleteVideo(id);
    };



    self.reset = function () {
        self.video = {id: null, videoName: '', author: ''};
        $scope.myForm.$setPristine(); //reset Form
        grecaptcha.reset(widgetId);
    };

    $scope.open = function (video,remove) {
        $scope.modal={};
        if(remove){
            $scope.modal.templateUrl = 'removeContent.html';
        }
        else {
            $scope.modal.templateUrl = 'addContent.html';
        }
        $modal.open({
            templateUrl: $scope.modal.templateUrl,
            backdrop: true,
            windowClass: 'modal',
            controller: function ($scope, $modalInstance) {

                $scope.ctrl = {};
                $scope.ctrl.video = angular.copy(video);

                $scope.onloadCallback = function () {
                    widgetId = grecaptcha.render(document.getElementById('captcha'), {
                        'sitekey' : '6LfQbSITAAAAAC1J-H6dzkL-RWM91a06002Rdi1k'
                    });
                };


                $scope.submit = function (myForm) {
                    var v = grecaptcha.getResponse(widgetId);
                    if (v.length != 0) {
                        self.video = myForm.ctrl.video;
                        self.submit();
                        $modalInstance.dismiss('cancel');
                    }
                    else {
                        document.getElementById('captcha').innerHTML = "You can't leave Captcha Code empty";
                    }
                };

                $scope.cancel = function () {
                    $modalInstance.dismiss('cancel');
                };

                $scope.reset = function () {
                    $scope.ctrl.video = {id: null, videoName: '', author: ''};
                    grecaptcha.reset(widgetId);
                };

                $scope.remove = function (){
                    self.remove(video.id);
                    $modalInstance.dismiss('cancel');
                };

            }
        });
    };

}]);
