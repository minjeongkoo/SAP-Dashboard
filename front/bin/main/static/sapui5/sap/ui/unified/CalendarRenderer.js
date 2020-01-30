/*!
 * OpenUI5
 * (c) Copyright 2009-2019 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define([],function(){"use strict";var C={};C.render=function(r,c){c._iMode=0;var I=c.getId();var t=c.getTooltip_AsString();var m=c.getAggregation("month");var w=c.getWidth();r.write("<div");r.writeControlData(c);r.addClass("sapUiCal");if(m.length>1){r.addClass("sapUiCalMulti");}var a=sap.ui.getCore().getLibraryResourceBundle("sap.ui.unified");var A={labelledby:{value:"",append:false}};if(c._bPoupupMode){A.role="dialog";A.modal=true;}r.writeAccessibilityState(c,A);if(t){r.writeAttributeEscaped('title',t);}if(w){r.addClass("sapUiCalWidth");r.addStyle("width",w);r.writeStyles();}if(c._getSecondaryCalendarType()){r.addClass("sapUiCalSecType");}if(this.addAttributes){this.addAttributes(r,c);}r.writeClasses();r.write(">");var h=c.getAggregation("header");r.renderControl(h);var M=m.length;r.write("<div id=\""+I+"-content\" class=\"sapUiCalContent\">");for(var i=0;i<M;i++){var o=m[i];r.renderControl(o);if(M===2&&i===0){r.renderControl(c.getAggregation("secondMonthHeader"));}}this.renderCalContentOverlay(r,c,I);if(!c._bNamesLengthChecked){var b=c.getAggregation("monthPicker");r.renderControl(b);}r.write("</div>");if(!c._bSkipCancelButtonRendering){r.write("<button id=\""+I+"-cancel\" class=\"sapUiCalCancel\" tabindex=\"-1\">");r.write(a.getText("CALENDAR_CANCEL"));r.write("</button>");}r.write("<div id=\""+I+"-end\" tabindex=\"0\" style=\"width:0;height:0;position:absolute;right:0;bottom:0;\"></div>");this.renderCalContentAndArrowsOverlay(r,c,I);r.write("</div>");};C.renderCalContentOverlay=function(r,c,i){r.write("<div id=\""+i+"-contentOver\" class=\"sapUiCalContentOver\" style=\"display:none;\"></div>");};C.renderCalContentAndArrowsOverlay=function(r,c,i){};return C;},true);