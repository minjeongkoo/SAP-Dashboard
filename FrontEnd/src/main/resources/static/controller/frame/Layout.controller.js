sap.ui.define([
	"OpenUI5/controller/common/BaseController"
], function(BaseController) {
    "use strict";
		
	return BaseController.extend("OpenUI5.controller.common.BaseController", {
		onInit : function () {
			console.log("Layout.js OnInit()");
		},
		
		onAfterRendering : function () {
			console.log("Layout.js onAfterRendering()");
			
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("contents1");
		}
	});
}, true);