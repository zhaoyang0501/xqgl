jQuery.adminWorker = {
		workerDataTable:null,
		initSearchDataTable : function() {
			if (this.workerDataTable == null) {
				this.workerDataTable = $('#dt_table_view').dataTable({
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
					"sAjaxSource" : $.ace.getContextPath() + "/admin/worker/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var workerName = $("#_workerName").val();
						if (!!workerName) {
							aoData.push({
								"name" : "workerName",
								"value" : workerName
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
						"mDataProp" : "username"
					}, {
						"mDataProp" : "password"
					}, {
						"mDataProp" : "headimg"
					}, {
						"mDataProp" : "name"
					}, {
						"mDataProp" : "joinDate"
					}, {
						"mDataProp" : "sex"
					}, {
						"mDataProp" : "tel"
					}, {
						"mDataProp" : "type"
					}, {
						"mDataProp" : "remark"
					},{
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [10],
							'fnRender' : function(oObj, sVal) {
								return"  <button class=\"btn2 btn-info\" onclick=\"$.adminWorker.deleteWorker("+oObj.aData.id+")\"><i class=\"icon-trash\"></i> 删除</button>" +
								" <button class=\"btn2 btn-info\" onclick=\"$.adminWorker.showEdit("+oObj.aData.id+")\"><i class=\"icon-pencil\"></i>编辑</button>";
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.workerDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.workerDataTable.fnDraw(oSettings);
			}

		},
		deleteWorker :function(id){
			bootbox.confirm( "是否确认删除？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/worker/delete?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminWorker.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		showWorkerAddModal: function(id){
			$("#id").val(id);
			$('#_modal').modal({
			});
			$("#_modal").modal('show');
		},
		showEdit: function (id){
			$("#id").val(id);
			$.ajax({
    			type : "get",
    			url : $.ace.getContextPath() + "/admin/worker/get?id="+id,
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("input[name='worker.id']").val(json.resultMap.object.id);
    					$("input[name='worker.username']").val(json.resultMap.object.username);
    					$("input[name='worker.password']").val(json.resultMap.object.password);
    					$("input[name='worker.name']").val(json.resultMap.object.name);
    					$("input[name='worker.joinDate']").val(json.resultMap.object.joinDate);
    					$("select[name='worker.sex']").val(json.resultMap.object.sex);
    					$("input[name='worker.tel']").val(json.resultMap.object.tel);
    					$("input[name='worker.type']").val(json.resultMap.object.type);
    					$("input[name='worker.remark']").val(json.resultMap.object.remark);
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
			$("#_modal").modal('show');
		},
		
		saveWorker: function(id){
			$.ajax({
    			type : "post",
    			url : $.ace.getContextPath() + "/admin/worker/save",
    			data:{
    				"worker.id":$("#id").val(),
    				"worker.name":$("#name").val(),
    				"worker.headimg":$("#name").val(),
    				"worker.carno":$("#carno").val(),
    				"worker.comefrom":$("#comefrom").val(),
    				"worker.phone":$("#phone").val(),
    				
    			},
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#_modal").modal('hide');
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
    					$.adminWorker.initSearchDataTable();
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		},
		
};