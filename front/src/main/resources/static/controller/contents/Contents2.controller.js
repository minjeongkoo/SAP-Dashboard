sap.ui.define([
	"OpenUI5/controller/common/BaseController"
], function(BaseController) {
	"use strict";
		
	return BaseController.extend("OpenUI5.controller.common.BaseController", {
		info: [],
		latlng: [
			{sido_name: "서울", station_name: "강남구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.500945292576965, 127.04523641759724)},
			{sido_name: "서울", station_name: "은평구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "성북구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.5789423437988, 127.02264178204969)},
			
			{sido_name: "서울", station_name: "강동구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.55303825648873, 127.15499719380928)},
			
			{sido_name: "서울", station_name: "마포구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.56579185489066, 126.91017321366402)},
			
			{sido_name: "서울", station_name: "용산구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.53728117683401, 126.98298285689891)},
			{sido_name: "서울", station_name: "동작구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.50295219450119, 126.93123880729159)},
			{sido_name: "서울", station_name: "강서구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "성동구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "광진구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "양천구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "구로구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "중구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "관악구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "서초구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "강북구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "노원구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "송파구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "동대문구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "중랑구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "종로구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "영등포구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "서대문구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "도봉구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "금천구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)}
		],
		onInit : function () {
			console.log("Contents2.js OnInit()");
			window.kMap = this;
			var that = this;
			setTimeout(() => {
				var container = document.getElementById(that.createId('map'));
				var options = {
						center: new kakao.maps.LatLng(37.571587695469695, 127.03332542526158),
						level: 8
				};
				
				var map = new kakao.maps.Map(container, options);
				
				// 지도에 확대 축소 컨트롤을 생성한다
				var zoomControl = new kakao.maps.ZoomControl();

				// 지도의 우측에 확대 축소 컨트롤을 추가한다
				map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
				
				// 지도 확대 레벨 변화 이벤트를 등록한다
				kakao.maps.event.addListener(map, 'zoom_changed', function () {
					console.log('지도의 현재 확대레벨은 ' + map.getLevel() +'레벨 입니다.');
				});
				
				kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
				    console.log(mouseEvent.latLng.getLat() + ', ' + mouseEvent.latLng.getLng());
				});
				
				that.map = map;
				that.localApi();
			}, 1000);
		},
		localApi : function() {
            var oParam = {
                url     : "http://localhost:9001/list/all?sido_name=서울&mang_name=도시대기",
                type    : "GET",
                data    : "",
                callback: "callbackFunction",
                error   : "errorCallbackFunction"
            };
            
            this.callAjax(oParam);
        },
        callbackFunction : function(oModel) {
            var oData = oModel.getProperty("/result/list");
            for (var i = 0; i < oData.length; i++) {
            	for (var j = 0; j < this.latlng.length; j++) {
					if(oData[i].station_name == this.latlng[j].station_name){
						oData[i].position = this.latlng[j].position;
						
						var infowindow = new kakao.maps.InfoWindow({
						    position : this.latlng[j].position, 
						    content : '<div class="mapInfoWindow" style="padding:5px;">'+oData[i].station_name+'<br><div style="color:skyblue;">'+oData[i].khai_value+'</div></div>'
						});
						  
						infowindow.open(this.map);
						this.info.push(infowindow);
						break;
					}
				}// for j
			}// for i
            this.setModel(new sap.ui.model.json.JSONModel(oData));
            
//            $(".mapInfoWindow");
        },
	});
}, true);