(function ($) {
    $.fn.contracts = function (initialData) {
        var el = this.first();
        var data = el.data("contracts");
    	
        if (!data) {
            data = $.extend(true, {
                el: el,
                contractApiUrl: "",
            }, initialData);
            el.data("contracts", data);
            
            data.renderContracts = function() {
            	var tableEl = data.el.find("#contract-table tbody");
            	
            	var content = "";            	
            	for(var i = 0; i < data.contracts.length; i++) {
            		content += "<tr data-id='" + data.contracts[i].id + "'>";
            		content += "<td>" + data.contracts[i].name + "</td>";
            		content += "<td>" + (data.contracts[i].document 
            				? "<a href='" + data.contractApiUrl + data.contracts[i].id + "/document'>" + data.contracts[i].document.name  + "</a>" 
            				: "") + "</td>";
            		content += "<td><button type='button' class='btn btn-info'>Загрузить документ</button></td>";
            		content += "</tr>";
            	}            	
            	
            	tableEl.html(content);
            };
            
            data.loadContracts = function() {
        		$.ajax({
        			url: data.contractApiUrl,
        			success: function (result) {
        				data.contracts = result;
        				data.renderContracts();
        			},
        			error: function () {
        				alert("Ошибка загрузки договоров");
        			}
        		});            	
            };
            
            data.createContract = function() {
        		$.ajax({
        	        type: "POST",
        			url: data.contractApiUrl,
        	        success: function (result) {
        	        	data.contracts.push(result);
        	        	data.renderContracts();
        	        },
        	        error: function () {
        	            alert("Ошибка создания договора");
        	        }
        	    });
            };

            data.uploadDocument = function(contractId, file) {            	
        	    var fd = new FormData();
        	    fd.set("document", file);
        		$.ajax({
        	        type: "POST",
        			url: data.contractApiUrl + contractId + "/document",
        	        data: fd,
        	        processData: false,
        	        contentType: false,
        	        success: function () {        	           
        	           data.loadContracts();
        	        },
        	        error: function () {
        	            alert("Ошибка загрузки документа");
        	        }
        	    });
            };
            
            data.el.find("#create-contract").click(data.createContract);
            
            data.el.find("#new-document").change(function () {
				if(this.files.length !== 1) {
					return;
				}					
				data.uploadDocument($(this).attr("data-id"), this.files[0]);
				$(this).val("");
			});		            
            
            data.el.find("#contract-table").on("click", "button", function() {
            	var contractId = $(this).closest("tr").attr("data-id");
            	data.el.find("#new-document").attr("data-id", contractId).click();
            });
            
            data.loadContracts();
        }
        
        return data;
    }
})(jQuery);
