/*!
 * OpenUI5
 * (c) Copyright 2009-2019 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['sap/ui/core/Element','sap/ui/core/library','sap/ui/core/Popup','sap/ui/model/Filter','sap/ui/model/FilterOperator','sap/ui/model/FilterType','sap/ui/model/Sorter','sap/ui/model/Type','sap/ui/model/type/String','./TableUtils','./library','./ColumnMenu','sap/base/util/ObjectPath',"sap/base/util/JSTokenizer","sap/base/Log","sap/ui/thirdparty/jquery"],function(E,c,P,F,a,b,S,T,d,f,g,C,O,J,L,q){"use strict";var H=c.HorizontalAlign,h=g.SortOrder,V=c.ValueState;var j={Standard:"Standard",Creation:"Creation"};var k=new window.WeakMap();var m=E.extend("sap.ui.table.Column",{metadata:{library:"sap.ui.table",properties:{width:{type:"sap.ui.core.CSSSize",group:"Dimension",defaultValue:null},minWidth:{type:"int",group:"Dimension",defaultValue:0},flexible:{type:"boolean",group:"Behavior",defaultValue:true,deprecated:true},resizable:{type:"boolean",group:"Behavior",defaultValue:true},hAlign:{type:"sap.ui.core.HorizontalAlign",group:"Appearance",defaultValue:H.Begin},sorted:{type:"boolean",group:"Appearance",defaultValue:false},sortOrder:{type:"sap.ui.table.SortOrder",group:"Appearance",defaultValue:h.Ascending},sortProperty:{type:"string",group:"Behavior",defaultValue:null},filtered:{type:"boolean",group:"Appearance",defaultValue:false},filterProperty:{type:"string",group:"Behavior",defaultValue:null},filterValue:{type:"string",group:"Behavior",defaultValue:null},filterOperator:{type:"string",group:"Behavior",defaultValue:null},defaultFilterOperator:{type:"string",group:"Behavior",defaultValue:null},filterType:{type:"any",group:"Misc",defaultValue:null},grouped:{type:"boolean",group:"Appearance",defaultValue:false},visible:{type:"boolean",group:"Appearance",defaultValue:true},name:{type:"string",group:"Appearance",defaultValue:null},showFilterMenuEntry:{type:"boolean",group:"Appearance",defaultValue:true},showSortMenuEntry:{type:"boolean",group:"Appearance",defaultValue:true},headerSpan:{type:"any",group:"Behavior",defaultValue:1},autoResizable:{type:"boolean",group:"Behavior",defaultValue:false}},defaultAggregation:"label",aggregations:{label:{type:"sap.ui.core.Control",altTypes:["string"],multiple:false},multiLabels:{type:"sap.ui.core.Control",multiple:true,singularName:"multiLabel"},template:{type:"sap.ui.core.Control",altTypes:["string"],multiple:false},creationTemplate:{type:"sap.ui.core.Control",multiple:false,visibility:"hidden"},menu:{type:"sap.ui.unified.Menu",multiple:false}},events:{columnMenuOpen:{allowPreventDefault:true,parameters:{menu:{type:"sap.ui.unified.Menu"}}}}}});m._DEFAULT_FILTER_TYPE=new d();m.prototype.init=function(){this.mSkipPropagation={template:true,creationTemplate:true};this._oSorter=null;this._initTemplateClonePool();};m.prototype._initTemplateClonePool=function(){this._mTemplateClones=Object.keys(j).reduce(function(t,s){t[s]=[];return t;},{});};m.prototype.exit=function(){this._destroyTemplateClones();C._destroyColumnVisibilityMenuItem(this.oParent);};m.prototype.setParent=function(p,A,s){C._destroyColumnVisibilityMenuItem(this.oParent);var r=E.prototype.setParent.apply(this,arguments);var M=this.getAggregation("menu");if(M&&typeof M._updateReferences==="function"){M._updateReferences(this);}return r;};m.prototype.invalidate=function(o){if(o!==this.getTemplate()&&o!==this.getCreationTemplate()&&!f.isA(o,"sap.ui.table.ColumnMenu")){E.prototype.invalidate.apply(this,arguments);}};m.prototype.setLabel=function(l){var o=l;if(typeof(l)==="string"){o=g.TableHelper.createLabel({text:l});}this.setAggregation("label",o);return this;};m.prototype.setTemplate=function(t){var o=t;var e=this._getTable();var i=this.getTemplate();if(typeof t==="string"){o=g.TableHelper.createTextView().bindProperty("text",t);}this.setAggregation("template",o,true);if(this.getVisible()){this.invalidate();}this._destroyTemplateClones("Standard");if(e&&this.getVisible()){if(o){e.invalidateRowsAggregation();}if(!i||!o){var l=e.getCreationRow();if(l){l._update();}}}return this;};m.prototype.destroyTemplate=function(){this.destroyAggregation("template");this._destroyTemplateClones("Standard");var t=this._getTable();var o=t?t.getCreationRow():null;if(o){o._update();}return this;};m.prototype.setCreationTemplate=function(o){var t=this._getTable();this.setAggregation("creationTemplate",o,true);this._destroyTemplateClones("Creation");if(o&&t&&this.getVisible()){var e=t.getCreationRow();if(e){e._update();}}return this;};m.prototype.getCreationTemplate=function(){return this.getAggregation("creationTemplate");};m.prototype.destroyCreationTemplate=function(){this.destroyAggregation("creationTemplate",true);this._destroyTemplateClones("Creation");return this;};m.prototype.getMenu=function(){var M=this.getAggregation("menu");if(!M){M=this._createMenu();this.setMenu(M);}return M;};m.prototype.invalidateMenu=function(){var M=this.getAggregation("menu");if(this._bMenuIsColumnMenu){M._invalidate();}};m.prototype._menuHasItems=function(){var M=this.getAggregation("menu");var t=this.getParent();var e=function(){return(this.isSortableByMenu()||this.isFilterableByMenu()||this.isGroupable()||(t&&t.getEnableColumnFreeze())||(t&&t.getShowColumnVisibilityMenu()));}.bind(this);return!!((M&&M.getItems().length>0)||e());};m.prototype.isFilterableByMenu=function(){return!!(this.getFilterProperty()&&this.getShowFilterMenuEntry());};m.prototype.isSortableByMenu=function(){return!!(this.getSortProperty()&&this.getShowSortMenuEntry());};m.prototype.isGroupable=function(){var t=this.getParent();return!!(t&&t.getEnableGrouping&&t.getEnableGrouping()&&this.getSortProperty());};m.prototype.setMenu=function(M){this.setAggregation("menu",M,true);this._bMenuIsColumnMenu=f.isA(M,"sap.ui.table.ColumnMenu");return this;};m.prototype._createMenu=function(){if(!this._defaultMenu){this._defaultMenu=new C(this.getId()+"-menu",{ariaLabelledBy:this});}return this._defaultMenu;};m.prototype.setSortProperty=function(v){this.setProperty("sortProperty",v);this.invalidateMenu();return this;};m.prototype.setSorted=function(e){this.setProperty("sorted",e,true);this._updateIcons();return this;};m.prototype.setSortOrder=function(t){this.setProperty("sortOrder",t,true);this._updateIcons();return this;};m.prototype.setFilterProperty=function(v){this.invalidateMenu();return this.setProperty("filterProperty",v);};m.prototype.setFiltered=function(e){this.setProperty("filtered",e,true);this._updateIcons();return this;};m.prototype.setFilterValue=function(v){this.setProperty("filterValue",v,true);var M=this.getMenu();if(this._bMenuIsColumnMenu){M._setFilterValue(v);}return this;};m.prototype.setFilterOperator=function(v){return this.setProperty("filterOperator",v,true);};m.prototype._openMenu=function(D,w){var M=this.getMenu();var e=this.fireColumnMenuOpen({menu:M});if(e){var i=P.Dock;var o=D;if(!D){D=this.getDomRef();o=this.getFocusDomRef();}M.open(!!w,o,i.BeginTop,i.BeginBottom,D);}};m.prototype.toggleSort=function(){this.sort(this.getSorted()&&this.getSortOrder()===h.Ascending);};m.prototype.sort=function(D,A){var t=this.getParent();if(t){t.pushSortedColumn(this,A);var N=D?h.Descending:h.Ascending;var e=t.fireSort({column:this,sortOrder:N,columnAdded:A});if(e){var s=t.getSortedColumns();var o=t.getColumns();for(var i=0,l=o.length;i<l;i++){if(s.indexOf(o[i])<0){o[i].setProperty("sorted",false,true);o[i].setProperty("sortOrder",h.Ascending,true);o[i]._updateIcons(true);delete o[i]._oSorter;}}this.setProperty("sorted",true,true);this.setProperty("sortOrder",N,true);this._oSorter=new S(this.getSortProperty(),this.getSortOrder()===h.Descending);var p=[];for(var i=0,l=s.length;i<l;i++){s[i]._updateIcons(true);p.push(s[i]._oSorter);}t._resetColumnHeaderHeights();t._updateRowHeights(t._collectRowHeights(true),true);var B=t.getBinding("rows");if(B){if(this._updateTableAnalyticalInfo){this._updateTableAnalyticalInfo(true);}B.sort(p);}else{L.warning("Sorting not performed because no binding present",this);}}}return this;};m.prototype._updateIcons=function(s){var t=this.getParent(),e=this.getSorted(),i=this.getFiltered();if(!t||!t.getDomRef()){return;}this.$().parents(".sapUiTableCHT").find('td[data-sap-ui-colindex="'+this.getIndex()+'"]:not([colspan]):not(.sapUiTableHidden):first').toggleClass("sapUiTableColFiltered",i).toggleClass("sapUiTableColSorted",e).toggleClass("sapUiTableColSortedD",e&&this.getSortOrder()===h.Descending);t._getAccExtension().updateAriaStateOfColumn(this);if(!s){t._resetColumnHeaderHeights();t._updateRowHeights(t._collectRowHeights(true),true);}};m.prototype._renderSortIcon=function(){this._updateIcons();};m.prototype._getFilter=function(){var o,p=this.getFilterProperty(),v=this.getFilterValue(),s=this.getFilterOperator(),e,i,t=this.getFilterType()||m._DEFAULT_FILTER_TYPE,I=t instanceof d,B;if(v){if(!s){B=v.match(/(.*)\s*\.\.\s*(.*)/);if(v.indexOf("=")==0){s=a.EQ;e=v.substr(1);}else if(v.indexOf("!=")==0){s=a.NE;e=v.substr(2);}else if(v.indexOf("<=")==0){s=a.LE;e=v.substr(2);}else if(v.indexOf("<")==0){s=a.LT;e=v.substr(1);}else if(v.indexOf(">=")==0){s=a.GE;e=v.substr(2);}else if(v.indexOf(">")==0){s=a.GT;e=v.substr(1);}else if(B){if(B[1]&&B[2]){s=a.BT;e=B[1];i=B[2];}else if(B[1]&&!B[2]){s=a.GE;e=B[1];}else{s=a.LE;e=B[2];}}else if(I&&v.indexOf("*")==0&&v.lastIndexOf("*")==v.length-1){s=a.Contains;e=v.substr(1,v.length-2);}else if(I&&v.indexOf("*")==0){s=a.EndsWith;e=v.substr(1);}else if(I&&v.lastIndexOf("*")==v.length-1){s=a.StartsWith;e=v.substr(0,v.length-1);}else{if(this.getDefaultFilterOperator()){s=this.getDefaultFilterOperator();}else{if(I){s=a.Contains;}else{s=a.EQ;}}e=v.substr(0);}if(!i){o=new F(p,s,this._parseFilterValue(e));}else{o=new F(p,s,this._parseFilterValue(e),this._parseFilterValue(i));}}else{o=new F(p,s,this._parseFilterValue(v));}}return o;};m.prototype.filter=function(v){var t=this.getParent();if(t&&t.isBound("rows")){var o=t.fireFilter({column:this,value:v});if(o){this.setProperty("filtered",!!v,true);this.setProperty("filterValue",v,true);var M=this.getMenu();if(this._bMenuIsColumnMenu){M._setFilterValue(v);}var p=[];var r=t.getColumns();for(var i=0,l=r.length;i<l;i++){var s=r[i],u;M=s.getMenu();try{u=s._getFilter();if(s._bMenuIsColumnMenu){M._setFilterState(V.None);}}catch(e){if(s._bMenuIsColumnMenu){M._setFilterState(V.Error);}continue;}if(u){p.push(u);}}t.getBinding("rows").filter(p,b.Control);this._updateIcons();}}return this;};m.prototype._parseFilterValue=function(v){var o=this.getFilterType();if(o){if(q.isFunction(o)){v=o(v);}else{v=o.parseValue(v,"string");}}return v;};m.prototype.shouldRender=function(){return this.getVisible()&&!this.getGrouped()&&this.getTemplate()!=null;};m.prototype.setProperty=function(N,v){var t=this._getTable();var e=t&&N==="visible"&&this.getProperty(N)!=v;var r=E.prototype.setProperty.apply(this,arguments);if(e){t.invalidateRowsAggregation();var o=t.getCreationRow();if(o){o._update();}}return r;};m.prototype.setFilterType=function(t){var o=t;if(typeof(t)==="string"){try{var e=J.parseJS(t);if(typeof(e.type)==="string"){var i=O.get(e.type);o=i&&new i(e.formatOptions,e.constraints);}}catch(l){var i=O.get(t);o=i&&new i();}if(!(o instanceof T)){L.error("The filter type is not an instance of sap.ui.model.Type! Ignoring the filter type!");o=undefined;}}this.setProperty("filterType",o,true);return this;};m.prototype.getIndex=function(){var t=this.getParent();if(t){return t.indexOfColumn(this);}else{return-1;}};m.prototype._getFreeTemplateClone=function(t){var e=this._mTemplateClones[t];var o=null;if(!e){return null;}for(var i=0;i<e.length;i++){if(!e[i]||e[i].bIsDestroyed){e.splice(i,1);i--;}else if(!o&&!e[i].getParent()){o=e[i];}}return o;};m.prototype.getTemplateClone=function(i,p){if(typeof i!=="number"||this.getTemplate()==null){return null;}var t=p==null?"Standard":p;var o=this._getFreeTemplateClone(t);if(!o&&j.hasOwnProperty(t)){var G=this["get"+(t==="Standard"?"":t)+"Template"];var e=G.call(this);if(e){o=e.clone();this._mTemplateClones[t].push(o);}}if(o){k.set(o,this);var l=this.getParent();if(l){l._getAccExtension().addColumnHeaderLabel(this,o);}}return o;};function n(e){for(var i=0;i<e.length;i++){if(e[i]!=null&&!e[i].bIsDestroyed){e[i].destroy();}}}m.prototype._destroyTemplateClones=function(t){if(t==null){for(var s in j){n(this._mTemplateClones[s]);}this._initTemplateClonePool();}else{n(this._mTemplateClones[t]);this._mTemplateClones[t]=[];}};m.prototype._closeMenu=function(){var M=this.getAggregation("menu");if(M){M.close();}};m.prototype.setVisible=function(v){this.setProperty("visible",v);C._updateVisibilityIcon(this.getParent(),this.getIndex(),v);return this;};m.prototype._getTable=function(){var p=this.getParent();return f.isA(p,"sap.ui.table.Table")?p:null;};m.ofCell=function(o){return k.get(o)||null;};return m;});
