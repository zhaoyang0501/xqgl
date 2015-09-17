jQuery.adminCost = {
		costDataTable:null,
		initSearchDataTable : function() {
			if (this.costDataTable == null) {
				this.costDataTable = $('#dt_table_view').dataTable({
					"sDom" : "<'row-fluid'<'span6'l>r>t<'row-fluid'<'span6'i><'span6'p>>",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "每页显示 _MENU_ 条记录",
						"sZeroRecords" : "抱歉， 暂时没有记录",
						"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
						"sSearch" : "",
						"sInfoEmpty" : "没有数据",
						"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : "前一页",
							"sNext" : "后一页",
							"sLast" : "尾页"
						}
					},
					"bAutoWidth" : false,
					"iDisplayLength" : 10,
					"aLengthMenu" : [ 5, 10, 25, 50],
					"bServerSide" : true,
					"sServerMethod" : "POST",
					"bProcessing" : true,
					"bSort" : false,
					"sAjaxSource" : $.ace.getContextPath() + "/admin/cost/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var costName = $("#_costName").val();
						if (!!costName) {
							aoData.push({
								"name" : "costName",
								"value" : costName
							});
						}
						$.ajax({
							"dataType" : 'json',
							"type" : "POST",
							"url" : sSource,
							"data" : aoData,
							"success" : function(data){
								fnCallback(data.resultMap);
							}
						});
					},
					"aoColumns" : [ {
						"mDataProp" : "id"
					}, {
						"mDataProp" : "year"
					}, {
						"mDataProp" : "owner.id"
					}, {
						"mDataProp" : "owner.name"
					}, {
						"mDataProp" : "price"
					}, {
						"mDataProp" : "cash"
					}, {
						"mDataProp" : "state"
					},{
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [6],
							'fnRender' : function(oObj, sVal) {
								if(sVal=='已缴')
								return "<span class='label label-success'>"+sVal+"</span>";
								else
									return "<span class='label label-warning'>"+sVal+"</span>";
							}
						},
						{
							'aTargets' : [7],
							'fnRender' : function(oObj, sVal) {
								return"  <button class=\"btn2 btn-info\" onclick=\"$.adminCost.deleteCost("+oObj.aData.id+")\"><i class=\"icon-trash\"></i> 删除</button>" +
								" <button class=\"btn2 btn-info\" onclick=\"$.adminCost.showEdit("+oObj.aData.id+")\"><i class=\"icon-pencil\"></i>编辑</button>";
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.costDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.costDataTable.fnDraw(oSettings);
			}

		},
		deleteCost :function(id){
			bootbox.confirm( "是否确认删除？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/cost/delete?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminCost.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		showCostAddModal: function(id){
			$("#id").val(id);
			$('#_modal').modal({
			});
			$("#_modal").modal('show');
		},
		showEdit: function (id){
			$("#id").val(id);
			$.ajax({
    			type : "get",
    			url : $.ace.getContextPath() + "/admin/cost/get?id="+id,
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#price").val(json.resultMap.object.price);
    					$("#cash").val(json.resultMap.object.cash);
    					$("#state").val(json.resultMap.object.state);
    					$("#year").val(json.resultMap.object.year);
    					$("#ownerid").val(json.resultMap.object.owner.id);
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
			$("#_modal").modal('show');
		},
		
		saveCost: function(id){
			$.ajax({
    			type : "post",
    			url : $.ace.getContextPath() + "/admin/cost/save",
    			data:{
    				"cost.id":$("#id").val(),
    				"cost.state":$("#state").val(),
    				"cost.price":$("#price").val(),
    				"cost.cash":$("#cash").val(),
    				"cost.year":$("#year").val(),
    				"cost.owner.id":$("#ownerid").val()
    			},
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#_modal").modal('hide');
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
    					$.adminCost.initSearchDataTable();
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		},
		
};