jQuery.adminGuest = {
		guestDataTable:null,
		initSearchDataTable : function() {
			if (this.guestDataTable == null) {
				this.guestDataTable = $('#dt_table_view').dataTable({
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
					"sAjaxSource" : $.ace.getContextPath() + "/admin/guest/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var guestName = $("#_guestName").val();
						if (!!guestName) {
							aoData.push({
								"name" : "guestName",
								"value" : guestName
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
						"mDataProp" : "phone"
					}, {
						"mDataProp" : "comefrom"
					}, {
						"mDataProp" : "carno"
					}, {
						"mDataProp" : "owner.name"
					}, {
						"mDataProp" : "createDate"
					},{
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [7],
							'fnRender' : function(oObj, sVal) {
								return"  <button class=\"btn2 btn-info\" onclick=\"$.adminGuest.deleteGuest("+oObj.aData.id+")\"><i class=\"icon-trash\"></i> 删除</button>" +
								" <button class=\"btn2 btn-info\" onclick=\"$.adminGuest.showEdit("+oObj.aData.id+")\"><i class=\"icon-pencil\"></i>编辑</button>";
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.guestDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.guestDataTable.fnDraw(oSettings);
			}

		},
		deleteGuest :function(id){
			bootbox.confirm( "是否确认删除？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "get",
	        			url : $.ace.getContextPath() + "/admin/guest/delete?id="+id,
	        			dataType : "json",
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminGuest.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		showGuestAddModal: function(id){
			$("#id").val(id);
			$('#_modal').modal({
			});
			$("#_modal").modal('show');
		},
		showEdit: function (id){
			$("#id").val(id);
			$.ajax({
    			type : "get",
    			url : $.ace.getContextPath() + "/admin/guest/get?id="+id,
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#name").val(json.resultMap.object.name);
    					$("#carno").val(json.resultMap.object.carno);
    					$("#comefrom").val(json.resultMap.object.comefrom);
    					$("#phone").val(json.resultMap.object.phone);
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
			$("#_modal").modal('show');
		},
		
		saveGuest: function(id){
			$.ajax({
    			type : "post",
    			url : $.ace.getContextPath() + "/admin/guest/update",
    			data:{
    				"guest.id":$("#id").val(),
    				"guest.name":$("#name").val(),
    				"guest.carno":$("#carno").val(),
    				"guest.comefrom":$("#comefrom").val(),
    				"guest.phone":$("#phone").val(),
    				
    			},
    			dataType : "json",
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#_modal").modal('hide');
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
    					$.adminGuest.initSearchDataTable();
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		},
		
};