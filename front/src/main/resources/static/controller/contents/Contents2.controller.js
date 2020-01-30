sap.ui.define([
	"OpenUI5/controller/common/BaseController"
], function(BaseController) {
	"use strict";
		
	return BaseController.extend("OpenUI5.controller.common.BaseController", {
		sido_info: [],
		station_info: [],
		sido_latlng: [{sido_name: "서울", station_name: "서울", mang_name: "도시대기", position: new kakao.maps.LatLng(37.56722060794568, 126.97847103982909)}],
		station_latlng: [
			{sido_name: "서울", station_name: "강남구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.500945292576965, 127.04523641759724)},
			{sido_name: "서울", station_name: "은평구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
			{sido_name: "서울", station_name: "성북구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.58945792968436, 127.01666669585296)},
			{sido_name: "서울", station_name: "강동구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.55303825648873, 127.15499719380928)},
			{sido_name: "서울", station_name: "마포구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.56579185489066, 126.91017321366402)},
			{sido_name: "서울", station_name: "용산구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.53728117683401, 126.98298285689891)},
			{sido_name: "서울", station_name: "동작구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.49719122215534, 126.94137653875208)},
			{sido_name: "서울", station_name: "강서구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.551457667327156, 126.84898956564062)},
			{sido_name: "서울", station_name: "성동구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.563585804728596, 127.03685325322374)},
			{sido_name: "서울", station_name: "광진구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.53883951378862, 127.0822587728297)}
			
//			{sido_name: "서울", station_name: "양천구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "구로구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "중구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "관악구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "서초구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "강북구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "노원구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "송파구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "동대문구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "중랑구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "종로구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "영등포구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "서대문구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "도봉구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)},
//			{sido_name: "서울", station_name: "금천구", mang_name: "도시대기", position: new kakao.maps.LatLng(37.61049118551127, 126.92461633598255)}
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
					if(map.getLevel() > 8){
						
					}else{
						
					}
				});
				
				kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
				    console.log(mouseEvent.latLng.getLat() + ', ' + mouseEvent.latLng.getLng());
				});
				
				that.map = map;
				that.stationApi();
				that.sidoApi();
			}, 1000);
		},
		sidoApi : function() {
            var oParam = {
                url     : "http://localhost:9001/list/all?sido_name=서울",
                type    : "GET",
                data    : "",
                callback: "sidoCallbackFunction",
                error   : "errorCallbackFunction"
            };
            
            this.callAjax(oParam);
        },
        sidoCallbackFunction : function(oModel) {
            var oData = oModel.getProperty("/result/list");
            this.setModel(new sap.ui.model.json.JSONModel(oData), "sido");
            
            var infowindow = new kakao.maps.InfoWindow({
			    position : oData.position, 
			    content : '<div class="mapInfoWindow" style="padding:5px;">'+oData.station_name+'<br><div style="color:skyblue;">'+oData.khai_value+'</div></div>'
			});
			  
//			infowindow.open(this.map);
			this.sido_info.push(infowindow);
        },
		stationApi : function() {
            var oParam = {
                url     : "http://localhost:9001/list",
                type    : "GET",
                data    : "",
                callback: "stationCallbackFunction",
                error   : "errorCallbackFunction"
            };
            
            this.callAjax(oParam);
        },
        stationCallbackFunction : function(oModel) {
            var oData = oModel.getProperty("/result/list");
            for (var i = 0; i < oData.length; i++) {
            	for (var j = 0; j < this.station_latlng.length; j++) {
					if(oData[i].station_name == this.station_latlng[j].station_name){
						oData[i].position = this.station_latlng[j].position;
						this.openInfoWindow(oData[i]);
						break;
					}
				}// for j
			}// for i
            this.setModel(new sap.ui.model.json.JSONModel(oData), "station");
        },
        openInfoWindow : function(oData) {
        	var infowindow = new kakao.maps.InfoWindow({
			    position : oData.position, 
			    content : '<div class="mapInfoWindow" style="padding:5px;">'+oData.station_name+'<br><div style="color:skyblue;">'+oData.khai_value+'</div></div>'
			});
			  
			infowindow.open(this.map);
			this.station_info.push(infowindow);
        }
	});
}, true);