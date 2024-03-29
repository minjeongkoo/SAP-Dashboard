sap.ui.define([
	"OpenUI5/controller/common/BaseController",
	'sap/ui/core/mvc/Controller',
    'sap/ui/model/BindingMode',
    'sap/ui/model/json/JSONModel',
    'sap/viz/ui5/format/ChartFormatter',
    'sap/viz/ui5/api/env/Format'
], function(BaseController,Controller ,BindingMode ,JSONModel ,ChartFormatter ,Format) {
    "use strict";
        
    return BaseController.extend("OpenUI5.controller.common.BaseController",
    {
        onInit : function ()
        {
        	window.contents1 = this;
            
        	this.localApi();
        },
        /*
         *  table list 데이터 가져오는 callback() 함수
         */
        callbackFunction : function(oModel)
        {
            //console.log(JSON.stringify(oModel, null, 2));
            var oData = oModel.getProperty("/result/list");
            
            this.byId("pm10_grade").setText(oData[0].pm10_grade);//미세먼지(pm10등급)
            this.byId("pm10_value").setText(oData[0].pm10_value);//미세먼지(pm10농도)
 
            //console.log("oData callbackFunction >>>> "+JSON.stringify(oData, null, 2));
            //oTabled의 아이디 (idTable)가져와서 변수에 넣는다.
            var oTable = this.byId("idTable");
            //oTable 변수에 setModel 한다.
            oTable.setModel(new JSONModel(oData));
            //oTable.setVisibleRowCount(oData.length);
            
            //callback() 끝나면 callbackFunctionAfter()를 호출한다.
            this.callbackFunctionAfter();
        },
        callbackFunctionAfter : function(){
        	// 차트 데이터를 가져오기 위해 charMeasureNameApi() 호출한다.
        	this.charMeasureNameApi();
        },
        //차트 데이터를 가져오기 위해 chartCallbackFunction()을 호출한다.
        chartCallbackFunction : function(oChartModel){
        	   	
        	Format.numericFormatter(ChartFormatter.getInstance());
            var formatPattern = ChartFormatter.DefaultPattern;

            var oVizFrame = this.oVizFrame = this.getView().byId("idVizFrame");
            oVizFrame.setVizProperties({
                plotArea: {
                    dataLabel: {
                        //formatString: formatPattern.SHORTFLOAT_MFD2,
                    	formatString: formatPattern,
                        visible: false
                    },
                    window: {
                        start: "firstDataPoint",
                        end: "lastDataPoint"
                    },
                },
                valueAxis: {
                    label: {
                        formatString: formatPattern.SHORTFLOAT
                    },
                    title: {
                        visible: false
                    }
                },
                categoryAxis: {
                    title: {
                        visible: false
                    }
                },
                title: {
                    visible: false,
                    text: '통합대기환경수치'
                },
                legend: {
                    title: {
                        visible: false
                    },
                    label: true,
                    isScrollable: false
                },
                legendGroup: {
                    layout: {
                        linesOfWrap: 3,
                        alignment: 'center',
                        position: 'bottom'
                    }
                }                
            });
            //console.log("chartModel>>>> "+JSON.stringify(oChartModel, null, 2));
            var oChartData = oChartModel.getProperty("/result");
            //console.log("chartModel22>>>> "+JSON.stringify(oChartData, null, 2));
            oVizFrame.setModel(new JSONModel(oChartData));
        },
              
        errorCallbackFunction : function()
        {
            console.log("error callback");
        },
        
        localApi : function()
        {
            var oParam = {
                url     : "http://192.168.1.138:9001/list/page?page=1&size=25&sort=asc",
                type    : "GET",
                data    : "",
                callback: "callbackFunction",
                error   : "errorCallbackFunction"
            };
            
            this.callAjax(oParam);
        },
        
//        callPublicApi : function()
//        {
//            var oParam = {
//                    url     : "http://192.168.1.138:8088/",
//                    type    : "GET"
//                };
//          
//            var serviceId   = "";
//            var operationNm = "";
//          
//                serviceId   = "ArpltnInforInqireSvc"    ;  // 대기오염정보 조회 서비스
//                operationNm = "getCtprvnRltmMesureDnsty";  // 시도별 실시간 측정정보 조회
//
//              //serviceId   = "MsrstnInfoInqireSvc"     ;  // 측정소정보 조회 서비스
//              //operationNm = "getNearbyMsrstnList"     ;  // 근접측정소 목록 조회
//              //operationNm = "getMsrstnList"           ;  // 측정소 목록 조회
//              
//            var serviceKey  = "bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D";
//            var numOfRows   = "100" ;
//            var pageNo      = "1"   ;
//            var sidoName    = "서울" ;
//            var version     = "1.3" ;
//            var returnType  = "json";
//          
//            oParam.url += "http://openapi.airkorea.or.kr/openapi/services/rest/"
//                       + serviceId
//                       + "/"
//                       + operationNm
//                       + "?" + "serviceKey="   + serviceKey
//                       + "&" + "numOfRows="    + numOfRows
//                       + "&" + "pageNo="       + pageNo
//                       + "&" + "sidoName="     + sidoName
//                       + "&" + "ver="          + version
//                       + "&" +  "_returnType=" + returnType;
//          
//            this.callAjax2(oParam);
//        },
        //측정소별 Chart데이터 호출한다.
        charMeasureNameApi : function()
        {
            var oParam = {
                url     : "http://192.168.1.138:9001/list/khai?sido_name=서울&mang_name=도시대기",
                type    : "GET",
                callback: "chartCallbackFunction",
                error   : "errorCallbackFunction"
            };
            
            this.callAjax(oParam);
        },
        
    });
}, true);
