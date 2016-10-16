
$( document ).ready( function() {
	
	ko.bindingHandlers.sort = {
		init: function(element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
			var asc = false;
			element.style.cursor = 'pointer';
			
			element.onclick = function(){
				var value = valueAccessor();
				var prop = value.prop;
				var data = value.arr;
				
				asc = !asc;
				
				data.sort(function(left, right){
					var rec1 = left;
					var rec2 = right;
					
					if(!asc) {
						rec1 = right;
						rec2 = left;
					}
					
					var props = prop.split('.');
					for(var i in props){
						var propName = props[i];
						var parenIndex = propName.indexOf('()');
						if(parenIndex > 0){
							propName = propName.substring(0, parenIndex);
							rec1 = rec1[propName]();
							rec2 = rec2[propName]();
						} else {
							rec1 = rec1[propName];
							rec2 = rec2[propName];
						}
					}
					
					return rec1 == rec2 ? 0 : rec1 < rec2 ? -1 : 1;
				});
	        };
	    }
	};
	
	ko.bindingHandlers.datepicker = {
        init: function (element, valueAccessor, allBindingsAccessor) {
            //initialize datepicker with some optional options
            var options = allBindingsAccessor().datepickerOptions || {changeMonth: true, changeYear: true, dateFormat: 'yy-mm-dd', yearRange: "-100:+0",};
            $(element).datepicker(options);

            //handle the field changing
            ko.utils.registerEventHandler(element, "change", function () {
                var observable = valueAccessor();
                //observable($(element).datepicker("getDate"));
            });

            //handle disposal (if KO removes by the template binding)
            ko.utils.domNodeDisposal.addDisposeCallback(element, function () {
                $(element).datepicker("destroy");
            });

        },
        update: function (element, valueAccessor) {
            var value = ko.utils.unwrapObservable(valueAccessor()),
                current = $(element).datepicker("getDate");

            if (value.toString()[0] == "/") {
                value = new Date(parseInt(value.toString().substr(6)));
            }

            if (value - current !== 0) {
                $(element).datepicker("setDate", value);
            }
        }
    };
	
	function User(data) {
	    this.id = ko.observable(data.id);
	    this.firstName = ko.observable(data.firstName);
	    this.lastName = ko.observable(data.lastName);
	    this.email = ko.observable(data.email);
	    this.birthDate = ko.observable(data.birthDate);
	}
	
	function UserListViewModel() {
	    // Data
	    var self = this;
	    self.users = ko.observableArray([]);
	    
	    // Load initial data
	    $.getJSON("api/", function(allData) {
	        var mappedUsers = $.map(allData, function(item) { return new User(item) });
	        self.users(mappedUsers);
	    });
	    
	    self.currentUser = ko.observable(null);
	    
	    self.showModal = function(user){
	    	 if(user.id) {	
	    		 debugger;
	    		 self.currentUser(user);
	    	 }
	    	 else {
	    		 debugger;
	    		 self.currentUser(
	    				 new User({id: 0, firstName: "", lastName : "", email : "",birthDate : ""}));
	    	 }
	         $('#saveDialog').modal('show');
	    };
	    
	    self.removeUser = function(user) {
	    	$("#removeDialog").dialog({
	    	      buttons : {
	    	        "Confirm" : function() {
	    	        	$.ajax({
		    	    	    url: 'api/remove/' + user.id(),
		    	    	    type: 'DELETE',
		    	    	    success: function(result) {
		    	    	    	self.users.remove(user);
		    	    	    	toastr.success('User has been removed successfully!');
		    	    	    },
		    	    		error: function(result) {
		    	    			toastr.error('Unable to remove!');
		    	    		}
		    	    	});
	    	        	$(this).dialog("close");
	    	        },
	    	        "Cancel" : function() {
	    	          $(this).dialog("close");
	    	        }
	    	      }
    	    });   	
	    };
	    
	    self.save = function() {
	        $.ajax({
	            data: ko.toJSON(self.currentUser()),
	            url: "api/save/", 
	            type: "POST", 
	            contentType: "application/json",
	            success: function(result) { 
	            	toastr.success('User has been saved successfully!');
	            	$.getJSON("api/", function(allData) {
		    	        var mappedUsers = $.map(allData, function(item) { return new User(item) });
		    	        self.users(mappedUsers);
		    	    });
	            },
		        error: function(result) { 
		        	toastr.error('Unable to save!', 
		        			'Please check your data! Email must be unique, proper format and all the fields must be set'); 
		        	$.getJSON("api/", function(allData) {
		    	        var mappedUsers = $.map(allData, function(item) { return new User(item) });
		    	        self.users(mappedUsers);
		    	    });
		        }
	        });
	        
	    }; 
	}
	
	ko.applyBindings(new UserListViewModel());
	
} );

