sap.ui.define([
    "OpenUI5/controller/common/BaseController",
    "sap/ui/model/json/JSONModel"
], function(BaseController, JSONModel) {
    "use strict";
        
    return BaseController.extend("OpenUI5.controller.common.BaseController",
    {
        onInit : function ()
        {
            console.log("Contents1333.js OnInit()..");
            
            //this.callPublicApi();
            //this.localApi();
            this.local1api();
            
            console.log("Contents2221.js OnInit() Afteraa..");
        },
        
        callbackFunction : function(oModel)
        {
            console.log("Content1.controller.js callbackFunction()");
            
            console.log(JSON.stringify(oModel, null, 2));
            
            var oData = oModel.getProperty("/result/fineDustList");
            
            console.log("oData >>>> "+JSON.stringify(oData, null, 2));
            
            var oTable = this.byId("idTable");
            
            oTable.setModel(new JSONModel(oData));
            oTable.setVisibleRowCount(oData.length);
        },
        callbackFunction1 : function(oModel)
        {
            console.log("Content1_test.controller.js callbackFunction111()");
            
            console.log(JSON.stringify(oModel, null, 2));
            
            var oData = oModel.getProperty("/result/frcstdustlist");
            
            console.log("oData >>>> "+JSON.stringify(oData, null, 2));
            
            var oTable = this.byId("idTable");
            
            oTable.setModel(new JSONModel(oData));
            oTable.setVisibleRowCount(oData.length);
        },
        
        errorCallbackFunction : function()
        {
            console.log("error callback");
        },
        
        localApi : function()
        {
            var oParam = {
                url     : "http://localhost:8081/list",
                type    : "GET",
                data    : "",
                callback: "callbackFunction",
                error   : "errorCallbackFunction"
            };
            
            this.callAjax(oParam);
        },
        
        callPublicApi : function()
        {
            var oParam = {
                    url     : "http://localhost:8088/",
                    type    : "GET"
                };
          
            var serviceId   = "";
            var operationNm = "";
          
                serviceId   = "ArpltnInforInqireSvc"    ;  // 대기오염정보 조회 서비스
                operationNm = "getCtprvnRltmMesureDnsty";  // 시도별 실시간 측정정보 조회

              //serviceId   = "MsrstnInfoInqireSvc"     ;  // 측정소정보 조회 서비스
              //operationNm = "getNearbyMsrstnList"     ;  // 근접측정소 목록 조회
              //operationNm = "getMsrstnList"           ;  // 측정소 목록 조회
              
            var serviceKey  = "bg9choiwFZX5JYcIIF76jFiVYe0VwiWdxdpCUldbALWxzJLNZA4Ipq2Z1SVqkZyWSW88og%2Bt8EiOCX9J%2BB3ZUw%3D%3D";
            var numOfRows   = "100" ;
            var pageNo      = "1"   ;
            var sidoName    = "서울" ;
            var version     = "1.3" ;
            var returnType  = "json";
          
            oParam.url += "http://openapi.airkorea.or.kr/openapi/services/rest/"
                       + serviceId
                       + "/"
                       + operationNm
                       + "?" + "serviceKey="   + serviceKey
                       + "&" + "numOfRows="    + numOfRows
                       + "&" + "pageNo="       + pageNo
                       + "&" + "sidoName="     + sidoName
                       + "&" + "ver="          + version
                       + "&" +  "_returnType=" + returnType;
          
            this.callAjax2(oParam);
        },
        local1api : function()
        {
            var oParam = {
                url     : "http://localhost:8081/list",
                type    : "GET",
                data    : "",
                callback: "callbackFunction1",
                error   : "errorCallbackFunction"
            };
            
            this.callAjax(oParam);
        },
    });
}, true);
