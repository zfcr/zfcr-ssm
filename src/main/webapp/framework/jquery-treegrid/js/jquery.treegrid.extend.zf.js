var methos_zf = {
	// 初始化列表头信息	
	initTableHeader : function(settings) {
		var columns = settings.columns;
		var treeColName = settings.treeColName;
		var operates = settings.operates;
		var headerAlign = settings.headerAlign;
		
		var htmlHeaderData = "<thead><tr>";
		
		var htmlHeadertreeColTh = ""; // 以树形结果显示的列必须放在第一列
		var htmlHeaderColTh = "";
		for (var k = 0; k < columns.length; k++) {
			var colName = columns[k]["name"];
			var style = "";
			if(columns[k].width){
				var width=columns[k].width,widthUnit="px";
				if($.type(width) === "string" && width.indexOf("%") != -1){
					widthUnit = "%";
				}
				width = width.replace('%', '').replace('px', '');
				width = width + widthUnit;
				style += 'width: '+width+';';
			}
			if(colName == treeColName){
				htmlHeadertreeColTh = '<th align="' + headerAlign + 
					'" colName="' + colName + 
					'" ';
				if(style != ""){
					htmlHeadertreeColTh += ' style="'+style+'"';
				}
				htmlHeadertreeColTh += '><div style="text-align:' + headerAlign + 
					';">' + columns[k]["text"] + 
					'</div></th>';
				continue;
			}
			var isHidden = columns[k]["isHidden"];
			if (!isHidden) isHidden = false;
			htmlHeaderColTh += '<th align="' + headerAlign +
				'" colName="' + colName + '"';
			if (isHidden) {
				style += 'display:none;';
			}
			if(style != ""){
				htmlHeaderColTh += ' style="'+style+'"';
			}
			htmlHeaderColTh += '><div style="text-align:' + headerAlign + ';';
			htmlHeaderColTh += '">' + columns[k]["text"] + '</div></th>';
		}
		if (operates && operates.length > 0) {
			htmlHeaderColTh += '<th><div style="text-align:' + headerAlign
					+ ';">操作</div></th>';
		}
		htmlHeaderData +=  htmlHeadertreeColTh + htmlHeaderColTh;
		htmlHeaderData += "</tr></thead>";
		return htmlHeaderData;
	},
	initTableBody:function(settings,treegridObj){
		var url = settings.url;
		var formId = settings.formId;
		var fromData = {};
		if(formId){
			fromData = $('#'+formId).serialize();
		}
		var idColName = settings.idColName;
		var columns = settings.columns;
		var treeColName = settings.treeColName;
		var textAlign = settings.textAlign;
		
		var ajaxReturnData = {};
		$.ajax({
			async:false,
			type:'POST',
			url:url,
			data:fromData,
			dataType:'json',
			error:function(){
				return '';
			},
			success:function(data){
				var returnData = data;
				if($.type(data) === "string"){
					returnData = eval("("+data+")");
				}
				ajaxReturnData = returnData;
			}
		});
		
		if(ajaxReturnData.data && ajaxReturnData.data != '' && ajaxReturnData.data.length > 0){
			var treeData = ajaxReturnData.data;
			var htmlTextData = "<tbody>";
			for(var i = 0; i < treeData.length; i++){
				var id = treeData[i][idColName];
				var parentId = treeData[i]["parentId"];
				htmlTextData += '<tr class="treegrid-'+id;
				if(parentId && parentId != '00000000000000000000000000000000'){
					htmlTextData += ' treegrid-parent-'+parentId;
				}
				if(treeData[i].isLeaf != undefined && treeData[i].isLeaf == false){
					htmlTextData += ' treegrid-collapsed';
				}
				htmlTextData += '">'
				var treeColTd = "";
				var colTd = "";
				for(var j = 0; j < columns.length; j++){
					var colName = columns[j]["name"];
					if(colName == treeColName){
						treeColTd = '<td style="text-align:left;" colName="' + colName + '">' + treeData[i][colName] + '</td>';
						continue;
					}
					
					var isHidden = columns[j]["isHidden"];
					if(!isHidden) isHidden = false;
					colTd += '<td colName="'+columns[j]["name"]+'"';
					if(isHidden){
						colTd += ' style="display:none;"';
					}
					if(columns[j].formatter && typeof columns[j].formatter == 'function'){
						var showValue = columns[j].formatter(treeData[i][colName], treeData[i]);
						showValue = showValue == undefined ? "" : showValue;
						colTd += '><div style="text-align:'+textAlign+';">'+showValue+'</div></td>';
					}else{
						colTd += '><div style="text-align:'+textAlign+';">'+treeData[i][colName]+'</div></td>';
					}
				}
				if (settings.operates && settings.operates.length > 0) {
					colTd += this.initOperates(settings,treeData[i]);
				}
				htmlTextData += treeColTd + colTd;
				htmlTextData += "</tr>";
			}
			htmlTextData += "</tbody>";
			treegridObj.data(treeData);
			return htmlTextData;
		}
	},
	initOperates:function(settings,data){
		var operates = settings.operates;
		var idColName = settings.idColName;
		var operatesTd = "<td>";
		for(var i = 0; i < operates.length; i++){
			var params = operates[i]["params"];
			var arguments = "";
			if(params && params.length > 0){
				var paramArray = params.split(",");
				for(var j = 0; j < paramArray.length; j++){
					var paramName = paramArray[j];
					var paramValue = data[paramName];
					if($.type(paramValue) === "string"){
						paramValue = "'" + paramValue + "'";
					}
					arguments += paramValue + ",";
				}
				arguments = arguments.substring(0,arguments.length-1);
			}else{
				arguments = "'" + data[idColName] + "'"; 
			}
			var method = operates[i]["method"];
			var icon = operates[i]["icon"];
			if(!icon) icon = "";
			var text = operates[i]["text"];
			operatesTd += '<a href="####" onclick="'+method+'('+arguments+')"><i class="'+icon+'"></i> '+text+'</a> ';
		}
		operatesTd += "</td>";
		return operatesTd;
	}
};
    
$.fn.zfTreeGrid = function(config){
	var settings = $.extend({}, this.zfTreeGrid.defaults, config);
	if(!settings.columns || settings.columns.length <= 0){
		return;
	}

	var htmlHeaderData = methos_zf.initTableHeader(settings);
	
	if(settings.url && settings.url != ""){
		var htmlTextData = methos_zf.initTableBody(settings,$(this));
	}
	$(this).append(htmlHeaderData+htmlTextData);
	$(this).addClass("table table-bordered table-hover tree");
	$(this).css("margin","0 auto");
	$(this).treegrid(settings);
	if(settings.width){
		$(this).width(settings.width);
	}else{
		settings.width = $(this).width();
	}
	
	// 选中行变色
	$(this).find("tbody tr").click(function(){
		var settings = $(this).treegrid('getTreeContainer').data('settings');
		settings.selectedNodeId = $(this).treegrid('getNodeId');
		$(this).treegrid('getTreeContainer').data('settings', settings);
		$(this).addClass("active").siblings().removeClass("active");
	});
}

$.fn.zfTreeGrid.defaults = {
	treeColName : "name",	// 需要以树形结构展示的列
	idColName : "id",	// 主键列
	headerAlign : "center", // 列表头对齐规则
	textAlign : "left" // 列表内容对齐规则，以树形结构展示的列不使用此规则（只能左对齐）
}

// 参数配置详解
/**
 * url 后台获取数据的url，必须
 * columns 列表需要显示的列，json数组，必须
 * 	例：[{"name":"id","text":"id","isHidden":true},
 *      {"name":"name","text":"名称","width":"50%"},
 *	    {"name":"age","text":"年龄","width":"30%"}]
 *	name：后台返回数据的字段名称，text：显示在表格中的数据，isHidden：是否隐藏
 * operates 在列表数据内容中显示的操作列，json数组，必须
 * 	例：[{"text":"增加","icon":"fa fa-plus","method":"goadd"},
 *		{"text":"修改","icon":"fa fa-plus","method":"goedit","params":"id"},
 *		{"text":"删除","icon":"fa fa-plus","method":"godelete","params":"id"}]
 * 	text：按钮名称，icon：显示图标，method：按钮调用的方法，params：方法需要的参数，多个参数以逗号分隔
 * formId form表单的id值，如果需要带条件查询，将条件放在form表单中，传入该表单，可选
 */