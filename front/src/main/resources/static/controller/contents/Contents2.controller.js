sap.ui.define([
	"OpenUI5/controller/common/BaseController"
], function(BaseController) {
	"use strict";
		
	return BaseController.extend("OpenUI5.controller.common.BaseController", {
		onInit : function () {
			console.log("Contents2.js OnInit()");
			var that = this;
			setTimeout(() => {
				var container = document.getElementById(that.createId('map'));
				var options = {
						center: new kakao.maps.LatLng(37.5643260711243, 127.03187339939737),
						level: 9
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
				
				// 지도를 클릭한 위치에 표출할 마커입니다
				var marker = new kakao.maps.Marker({ 
				    // 지도 중심좌표에 마커를 생성합니다 
				    position: map.getCenter() 
				});
				
				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);
				
				// 지도에 클릭 이벤트를 등록합니다
				// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
				kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
					
				    // 클릭한 위도, 경도 정보를 가져옵니다 
				    var latlng = mouseEvent.latLng; 
				    
				    // 마커 위치를 클릭한 위치로 옮깁니다
				    marker.setPosition(latlng);
				    
				    console.log('클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, 경도는 ' + latlng.getLng() + ' 입니다');
				});
			}, 1000);
		}
	});
}, true);