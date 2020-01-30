/*!
 * SAPUI5

(c) Copyright 2009-2019 SAP SE. All rights reserved
 */
sap.ui.define(['sap/viz/ui5/controls/common/utils/Constants','sap/ui/model/Sorter',"sap/ui/thirdparty/jquery","sap/base/util/deepEqual"],function(C,S,q,c){"use strict";function e(i){return Object.prototype.toString.call(i)==='[object Array]';}function f(a,m,b,n,F){var r={dataset:null,context:null};if(!b||b.length===0){return r;}var A=[],o=[],M=[],p=[],s=[];q.each(a,function(i,l){if(l.getAxis()===1){A.push({def:l,adapter:l._getAdapter()});}else if(l.getAxis()===2){o.push({def:l,adapter:l._getAdapter()});}else{throw new Error("currently, only axis 1 and 2 are supported");}});q.each(m,function(i,l){M.push({def:l,adapter:l._getAdapter()});p.push([]);});function t(D,E,G){var l=D.length,V,i;if(l===0){return 0;}V=[];for(i=0;i<l;i++){V.push(D[i].adapter(G));}for(i=0,l=E.length;i<l;i++){if(c(E[i],V)){return i;}}E.push(V);return E.length-1;}var u=[];var v=[];q.each(b,function(I,l){var D=t(A,u,l);var E=t(o,v,l);for(var i=0;i<M.length;i++){var G=M[i].adapter(l);if(A.length===0&&o.length===0){if(p[i][0]===undefined){p[i][0]=[];}p[i][0].push(G);}else{(p[i][E]=(p[i][E]||[]))[D]=G;}}if(A.length===0&&o.length===0){D=I;}(s[E]||(s[E]=[]))[D]=l;});var L=u.length;var w=Math.max(v.length,1);for(var j=0;j<w;j++){for(var i=0;i<M.length;i++){var d=p[i][j];if(!d){d=p[i][j]=[];}if(d.length<L){d[L-1]=undefined;}}if(!s[j]){s[j]=[];}if(!s[j].length<L){s[j][L]=undefined;}}var x={};if(A.length>0){if(x.analysisAxis===undefined){x.analysisAxis=[];}var y={index:1,data:[]};for(var i=0;i<A.length;i++){var z=[];for(var j=0;j<u.length;j++){z[j]=u[j][i];}y.data.push({name:A[i].def.getName(),values:z});}x.analysisAxis.push(y);}if(o.length>0){if(x.analysisAxis===undefined){x.analysisAxis=[];}var y={index:2,data:[]};for(var i=0;i<o.length;i++){var z=[];for(var j=0;j<v.length;j++){z[j]=v[j][i];}y.data.push({name:o[i].def.getName(),values:z});}x.analysisAxis.push(y);}if(M.length>0){x.measureValuesGroup=[];for(var i=0;i<M.length;i++){if(!x.measureValuesGroup[M[i].def.getGroup()-1]){x.measureValuesGroup[M[i].def.getGroup()-1]={index:M[i].def.getGroup(),data:[]};}x.measureValuesGroup[M[i].def.getGroup()-1].data.push({name:M[i].def.getName(),values:p[i]});}for(var i=0,B=x.measureValuesGroup.length;i<B;i++){if(x.measureValuesGroup[i]===undefined){throw new Error("Measure Group "+(i+1)+" is missing.");}}}r.dataset=F?new sap.viz.data.CrosstableDataset():new sap.viz.api.data.CrosstableDataset();r.dataset.data(x);if(n){r.dataset.info(n);}r.context=s;return r;}function g(v,d){var a=(v&&v.value)||v,b=(d&&d.value)||d;return S.defaultComparator(a,b);}function h(d,m,a,b,p,l){var r={dataset:null,context:null};var n=['_context_row_number'];if(a){if(!e(a)){a=[a];}for(var i=0;i<a.length;++i){var o=a[i];var s=!(o.showInTooltip===false);if(o.id){o=o.id;}n.push({id:o,showInTooltip:s});}}var A=[],M=[],t=[],u={'metadata':{'fields':[]},'context':n,'data':[]},v=[];q.each(d,function(i,j){A.push({def:j,vAdapter:j._getAdapter(),dAdapter:j._getDisplayValueAdapter()});var w=j.getDataType();var x=j.getSorter();var y={'id':j.getIdentity()||j.getName(),'name':j.getName()||j.getIdentity(),'semanticType':'Dimension','dataType':w,'inResult':j._getInResult(),'timeUnitType':j._getTimeUnit()};if(x&&typeof x==="object"){var z={bDescending:x.bDescending};z.fnComparator=function(B,D){var E={value:B&&B.v||B,displayValue:B&&B.d},F={value:D&&D.v||D,displayValue:D&&D.d};var G=(x.fnComparator&&typeof x.fnComparator==="function")?x.fnComparator:g;return G(E,F);};y["sorter"]=z;}u.metadata.fields.push(y);});q.each(m,function(i,j){M.push({def:j,adapter:j._getAdapter()});var w={'id':j.getIdentity()||j.getName(),'name':j.getName()||j.getIdentity(),'semanticType':'Measure'};w.formatString=j.getFormat();if(j.getUnit()){w.unit=j.getUnit();}w.unitBinding=j._getUnitBinding();var R=j.getRange();if(R&&R.length){w.min=R[0];w.max=R[1];}u.metadata.fields.push(w);});if(p){if(p.bEnabled){u.metadata.options={pagination:{mode:p.sMode,ratio:p.thumbRatio}};}}if(!b||b.length===0){r.dataset=new sap.viz.api.data.FlatTableDataset(u);return r;}if(p&&(!l||p.sMode==="reset")){l=[];}q.each(b,function(I,w){if(!u.data[I]){u.data[I]=[];}for(var i=0;i<A.length;i++){var x=A[i].vAdapter(w);if(x instanceof Date){x=x.getTime();}var V=A[i].dAdapter(w);u.data[I].push(V.enableDisplayValue?{v:x,d:V.value}:x);}for(var j=0;j<M.length;j++){var x=M[j].adapter(w);u.data[I].push(x);var U=M[j].def._getUnitBinding();if(U){var y=w.getProperty(U);if(t[j]){if(t[j]==="*"||t[j]!==y||(p&&l[j]!==y)){throw C.ERROR_MESSAGE.MULTIPLEUNITS;}}else{t[j]=y;if(p&&!l[j]){l[j]=y;}}}}v[I]=w;});r.context=v;u.metadata.fields.filter(function(j){return(j.semanticType==="Measure");}).forEach(function(j,w){if(j.unitBinding&&t[w]){j.unit=t[w];delete j.unitBinding;}});r.dataset=new sap.viz.api.data.FlatTableDataset(u);return r;}var k=function(){this._oCVOMDataset=null;this._aContext=null;this._sDatasetType=null;this._pagingUnit=null;};k.prototype.getDataset=function(o){if(this._sDatasetType===o.type&&this._oCVOMDataset){return this._oCVOMDataset;}this.invalidate();this._sDatasetType=o.type;var r=null;if(this._sDatasetType===C.DATASET_TYPES.FLATTABLEDATASET){r=h(o.dimensions,o.measures,o.contexts,o.dataContexts,o.pagingOption,this._pagingUnit);}else{r=f(o.dimensions,o.measures,o.dataContexts,o.additionalInfo,this._sDatasetType==C.DATASET_TYPES.CROSSTABLEDATASET?false:true);}this._oCVOMDataset=r.dataset;this._aContext=r.context;return this._oCVOMDataset;};k.prototype.findContext=function(o){if(this._sDatasetType===C.DATASET_TYPES.FLATTABLEDATASET){if(this._aContext&&typeof o==='object'&&o._context_row_number!==undefined){return this._aContext[o._context_row_number];}}else{if(this._aContext&&typeof o==='object'){return this._aContext[o.dii_a2]&&this._aContext[o.dii_a2][o.dii_a1];}}return null;};k.prototype.invalidate=function(){this._oCVOMDataset=null;this._aContext=null;};return k;},true);
