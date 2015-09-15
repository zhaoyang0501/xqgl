jQuery.adminOwner = {
		ownerDataTable:null,
		initSearchDataTable : function() {
			if (this.ownerDataTable == null) {
				this.ownerDataTable = $('#dt_table_view').dataTable({
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
					"sAjaxSource" : $.ace.getContextPath() + "/admin/owner/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var ownerName = $("#_ownerName").val();
						if (!!ownerName) {
							aoData.push({
								"name" : "ownerName",
								"value" : ownerName
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
						"mDataProp" : "name"
					}, {
						"mDataProp" : "addr"
					}, {
						"mDataProp" : "sex"
					}, {
						"mDataProp" : "phone"
					}, {
						"mDataProp" : "job"
					}, {
						"mDataProp" : "area"
					}, {
						"mDataProp" : "count"
					},{
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [8],
							'fnRender' : function(oObj, sVal) {
								return"  <button class=\"btn2 btn-info\" onclick=\"$.adminOwner.deleteOwner("+oObj.aData.id+")\"><i class=\"icon-trash\"></i> 删除</button>" +
								" <button class=\"btn2 btn-info\" onclick=\"$.adminOwner.showEdit("+oObj.aData.id+")\"><i class=\"icon-pencil\"></i>编辑</button>";
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.ownerDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.ownerDataTable.fnDraw(oSettings);
			}

		},
		deleteOwner :function(id){
			bootbox.confirm( "是否确认删除？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/owner/delete?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminOwner.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		showOwnerAddModal: function(id){
			$("#id").val(id);
			$('#_modal').modal({
			});
			$("#_modal").modal('show');
		},
		showEdit: function (id){
			$("#id").val(id);
			$.ajax({
    			type : "get",
    			url : $.ace.getContextPath() + "/admin/owner/get?id="+id,
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#name").val(json.resultMap.object.name);
    					$("#addr").val(json.resultMap.object.addr);
    					$("#sex").val(json.resultMap.object.sex);
    					$("#phone").val(json.resultMap.object.phone);
    					$("#job").val(json.resultMap.object.job);
    					$("#area").val(json.resultMap.object.area);
    					$("#count").val(json.resultMap.object.count);
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
			$("#_modal").modal('show');
		},
		
		saveOwner: function(id){
			$.ajax({
    			type : "post",
    			url : $.ace.getContextPath() + "/admin/owner/save",
    			data:{
    				"owner.id":$("#id").val(),
    				"owner.name":$("#name").val(),
    				"owner.addr":$("#addr").val(),
    				"owner.sex":$("#sex").val(),
    				"owner.phone":$("#phone").val(),
    				"owner.sex":$("#sex").val(),
    				"owner.job":$("#job").val(),
    				"owner.area":$("#area").val(),
    				"owner.count":$("#count").val()
    			},
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#_modal").modal('hide');
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
    					$.adminOwner.initSearchDataTable();
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		},
		
};