/*!
 * SAPUI5

(c) Copyright 2009-2019 SAP SE. All rights reserved
 */
sap.ui.define(['sap/ui/core/format/NumberFormat','sap/ui/core/format/DateFormat','sap/ui/core/format/FileSizeFormat'],function(N,D,F){"use strict";var s=":";var f=["MediumYear","MediumMonth","MediumDay","MediumHour","MediumMinute","MediumSecond","YearMonthDay","Quarter","Week","hh:mm","hh:mm:ss","MMM d","YearQuarter","YearMonth"];var C=function(){if(this.caller!=this.getInstance){throw new Error("This object cannot be instantiated");}this.formatFunctions=[];this._updatePatternTable();this._locale=sap.ui.getCore().getConfiguration().getLanguage();this.formatFunctions["hh:00"]=this._getCVOMHourZeroFormatter.bind(this);this.formatFunctions["ss''"]=this._getCVOMSecondFormatter.bind(this);this.formatFunctions["MMM"]=this._getCVOMMonthFormatter.bind(this);this.formatFunctions["d"]=this._getCVOMDayFormater.bind(this);};C.DefaultPattern={SHORTINTEGER:"ShortInteger",STANDARDINTEGER:"StandardInteger",SHORTFLOAT:"ShortFloat",SHORTFLOAT_MFD2:"ShortFloat_MFD2",STANDARDFLOAT:"StandardFloat",LONGFLOAT:"LongFloat",STANDARDPERCENT:"StandardPercent",STANDARDPERCENT_MFD2:"StandardPercent_MFD2",PERCENT:"Percent",STANDARDCURRENCY:"StandardCurrency",CURRENCY:"Currency",MEDIUMYEAR:"MediumYear",MEDIUMQUARTER:"MediumQuarter",QUARTER:"Quarter",MEDIUMMONTH:"MediumMonth",MEDIUMWEEK:"MediumWeek",WEEK:"Week",MEDIUMDAY:"MediumDay",MEDIUMHOUR:"MediumHour",MEDIUMMINUTE:"MediumMinute",MEDIUMSECOND:"MediumSecond",MEDIUMYEARMONTHDAY:"MediumYearMonthDay",YEARMONTHDAY:"YearMonthDay",BINARYFILESIZE:"BinaryFileSize",DECIMALFILESIZE:"DecimalFileSize"};C.prototype._updatePatternTable=function(){this.formatFunctions["ShortInteger"]=this._getShortIntegerFormatter();this.formatFunctions["StandardInteger"]=this._getStandardIntegerFormatter();this.formatFunctions["ShortFloat"]=this._getShortFloatFormatter();this.formatFunctions["ShortFloat_MFD2"]=this._getShortFloatMFD2Formatter();this.formatFunctions["StandardFloat"]=this._getStandardFloatFormatter();this.formatFunctions["LongFloat"]=this._getLongFloatFormatter();this.formatFunctions["StandardPercent"]=this._getStandardPercentFormatter();this.formatFunctions["StandardPercent_MFD2"]=this._getStandardPercentMFD2Formatter();this.formatFunctions["Percent"]=this._getPercentFormatter();this.formatFunctions["StandardCurrency"]=this._getStandardCurrencyFormatter();this.formatFunctions["Currency"]=this._getCurrencyFormatter();this.formatFunctions["MediumYear"]=this._getMediumYearFormatter();this.formatFunctions["MediumMonth"]=this._getMediumMonthFormatter();this.formatFunctions["MediumDay"]=this._getMediumDayFormatter();this.formatFunctions["MediumHour"]=this._getMediumHourFormatter();this.formatFunctions["MediumMinute"]=this._getMediumMinuteFormatter();this.formatFunctions["MediumSecond"]=this._getMediumSecondFormatter();this.formatFunctions["YearMonthDay"]=this._getYearMonthDayFormatter();this.formatFunctions["BinaryFileSize"]=this._getBinaryFileSizeFormatter();this.formatFunctions["DecimalFileSize"]=this._getDecimalFileSizeFormatter();this.formatFunctions["Quarter"]=this._getQuarterFormatter();this.formatFunctions["Week"]=this._getWeekFormatter();this.formatFunctions["hh:mm"]=this._getHourMinuteFormatter();this.formatFunctions["hh:mm:ss"]=this._getHourMinuteSecondFormatter();this.formatFunctions["MMM d"]=this._getMonthDayFormatter();this.formatFunctions["YearQuarter"]=this._getYearQuarterFormatter();this.formatFunctions["YearMonth"]=this._getYearMonthFormatter();};C.getInstance=function(){if(!this.instance){this.instance=new C();}return this.instance;};C.prototype.format=function(v,p,i){if(p){var c=sap.ui.getCore().getConfiguration().getLanguage();if(this._locale!==c){this._locale=c;this._updatePatternTable();}if(!Array.isArray(v)&&isNaN(v)){return v;}if(this.formatFunctions[p]){if(f.indexOf(p)>-1){return this.formatFunctions[p](v,i);}return this.formatFunctions[p](v);}else{return"use_default_formatter";}}return v;};C.prototype._getShortIntegerFormatter=function(){var n=N.getIntegerInstance({style:'short'});return n.format.bind(n);};C.prototype._getStandardIntegerFormatter=function(){var n=N.getIntegerInstance({style:'standard'});return n.format.bind(n);};C.prototype._getShortFloatFormatter=function(){var n=N.getFloatInstance({style:'short'});return n.format.bind(n);};C.prototype._getShortFloatMFD2Formatter=function(){var n=N.getFloatInstance({style:'short',maxFractionDigits:2});return n.format.bind(n);};C.prototype._getStandardFloatFormatter=function(){var n=N.getFloatInstance({style:'standard'});return n.format.bind(n);};C.prototype._getLongFloatFormatter=function(){var n=N.getFloatInstance({style:'long'});return n.format.bind(n);};C.prototype._getPercentFormatter=function(){var n=N.getPercentInstance({style:'standard'});return n.format.bind(n);};C.prototype._getStandardPercentFormatter=function(){var n=N.getPercentInstance({style:'standard'});return n.format.bind(n);};C.prototype._getStandardPercentMFD2Formatter=function(){var n=N.getPercentInstance({style:'standard',maxFractionDigits:2});return n.format.bind(n);};C.prototype._getCurrencyFormatter=function(){var n=N.getCurrencyInstance({style:'standard'});return n.format.bind(n);};C.prototype._getStandardCurrencyFormatter=function(){var n=N.getCurrencyInstance({style:'standard'});return n.format.bind(n);};C.prototype._getMediumYearFormatter=function(){var d=D.getDateTimeInstance({pattern:'yyyy'});return d.format.bind(d);};C.prototype._getMediumMonthFormatter=function(){var d=D.getDateTimeInstance({pattern:'MMM'});return d.format.bind(d);};C.prototype._getMediumDayFormatter=function(){var d=D.getDateTimeInstance({pattern:'dd'});return d.format.bind(d);};C.prototype._getMediumHourFormatter=function(){var d=D.getDateTimeInstance({pattern:'HH'});return d.format.bind(d);};C.prototype._getMediumMinuteFormatter=function(){var d=D.getDateTimeInstance({pattern:'mm'});return d.format.bind(d);};C.prototype._getQuarterFormatter=function(){var d=D.getDateInstance({pattern:"qqq"});return d.format.bind(d);};C.prototype._getWeekFormatter=function(){var d=D.getDateInstance({pattern:"www"});return d.format.bind(d);};C.prototype._getHourMinuteFormatter=function(){var d=D.getDateTimeInstance({format:"HHmm"});return d.format.bind(d);};C.prototype._getCVOMHourZeroFormatter=function(v){return this.formatFunctions["MediumHour"](v)+s+"00";};C.prototype._getHourMinuteSecondFormatter=function(){var d=D.getDateTimeInstance({format:"HHmmss"});return d.format.bind(d);};C.prototype._getYearQuarterFormatter=function(){var d=D.getDateTimeInstance({format:"yQ"});return d.format.bind(d);};C.prototype._getYearMonthFormatter=function(){var d=D.getDateTimeInstance({format:"yMMM"});return d.format.bind(d);};C.prototype._getCVOMSecondFormatter=function(v){return this.formatFunctions["MediumSecond"](v)+"''";};C.prototype._getCVOMMonthFormatter=function(v){return this.formatFunctions["MediumMonth"](v);};C.prototype._getCVOMDayFormater=function(v){return this.formatFunctions["MediumDay"](v);};C.prototype._getMonthDayFormatter=function(){var d=D.getDateTimeInstance({format:"MMMdd"});return d.format.bind(d);};C.prototype._getMediumSecondFormatter=function(){var d=D.getDateTimeInstance({pattern:'ss'});return d.format.bind(d);};C.prototype._getYearMonthDayFormatter=function(){var d=D.getDateTimeInstance({format:"yMMMdd"});return d.format.bind(d);};C.prototype._getBinaryFileSizeFormatter=function(){var a=F.getInstance({binaryFilesize:true});return a.format.bind(a);};C.prototype._getDecimalFileSizeFormatter=function(){var a=F.getInstance({binaryFilesize:false});return a.format.bind(a);};C.prototype.registerCustomFormatter=function(p,a){this.formatFunctions[p]=a;};return C;},true);
