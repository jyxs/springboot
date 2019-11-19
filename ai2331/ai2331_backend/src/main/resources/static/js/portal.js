$(document).ready(function(){
    /* --------------------------------------------------------
	Components
    -----------------------------------------------------------*/
    (function(){
        /* Textarea */
    	if($('.auto-size')[0]) {
    	    $('.auto-size').autosize();
    	}

            //Select
    	if($('.select')[0]) {
    	    $('.select').selectpicker();
    	}
            
        //Sortable
        if($('.sortable')[0]) {
    	    $('.sortable').sortable();
    	}
    	
        //Tag Select
    	if($('.tag-select')[0]) {
    	    $('.tag-select').chosen();
    	}
            
        /* Tab */
    	if($('.tab')[0]) {
    	    $('.tab a').click(function(e) {
    		e.preventDefault();
    		$(this).tab('show');
    	    });
    	}
            
        /* Collapse */
    	if($('.collapse')[0]) {
    	    $('.collapse').collapse();
    	}
        
        /* Accordion */
        $('.panel-collapse').on('shown.bs.collapse', function () {
            $(this).prev().find('.panel-title a').removeClass('active');
        });

        $('.panel-collapse').on('hidden.bs.collapse', function () {
            $(this).prev().find('.panel-title a').addClass('active');
        });

        //Popover
    	if($('.pover')[0]) {
    	    $('.pover').popover();
    	} 
    })();

    /* --------------------------------------------------------
	Sidebar + Menu
    -----------------------------------------------------------*/
    (function(){
        /* Menu Toggle */
        $('body').on('click touchstart', '#menu-toggle', function(e){
            e.preventDefault();
            $('html').toggleClass('menu-active');
            $('#sidebar').toggleClass('toggled');
            //$('#content').toggleClass('m-0');
        });
         
        /* Active Menu */
        $('#sidebar .menu-item').hover(function(){
            $(this).closest('.dropdown').addClass('hovered');
        }, function(){
            $(this).closest('.dropdown').removeClass('hovered');
        });

        /* Prevent */
        $('.side-menu .dropdown > a').click(function(e){
            e.preventDefault();
        });
    })();

    /* --------------------------------------------------------
	Custom Scrollbar
    -----------------------------------------------------------*/
    (function() {
	if($('.overflow')[0]) {
	    var overflowRegular, overflowInvisible = false;
	    overflowRegular = $('.overflow').niceScroll();
	}
    })();

    /* --------------------------------------------------------
	Calendar
    -----------------------------------------------------------*/
    (function(){
	
        //Sidebar
        if ($('#sidebar-calendar')[0]) {
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
            $('#sidebar-calendar').fullCalendar({
                editable: false,
                events: [],
                header: {
                    left: 'title'
                },
                monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
                dayNames:["周一","周二","周三","周四","周五","周六","周日"],
                dayNamesShort:["周一","周二","周三","周四","周五","周六","周日"],
            });
        }
    })();
    

    /* ---------------------------
     Vertical tab
     --------------------------- */
    (function(){
        $('.tab-vertical').each(function(){
            var tabHeight = $(this).outerHeight();
            var tabContentHeight = $(this).closest('.tab-container').find('.tab-content').outerHeight();

            if ((tabContentHeight) > (tabHeight)) {
                $(this).height(tabContentHeight);
            }
        })

        $('body').on('click touchstart', '.tab-vertical li', function(){
            var tabVertical = $(this).closest('.tab-vertical');
            tabVertical.height('auto');

            var tabHeight = tabVertical.outerHeight();
            var tabContentHeight = $(this).closest('.tab-container').find('.tab-content').outerHeight();

            if ((tabContentHeight) > (tabHeight)) {
                tabVertical.height(tabContentHeight);
            }
        });


    })();
    /* --------------------------------------------------------
        MAC Hack 
    -----------------------------------------------------------*/
    (function(){
	   //Mac only
        if(navigator.userAgent.indexOf('Mac') > 0) {
            $('body').addClass('mac-os');
        }
    })();
    
});

$(window).load(function(){
    /* --------------------------------------------------------
     Tooltips
     -----------------------------------------------------------*/
    (function(){
        if($('.tooltips')[0]) {
            $('.tooltips').tooltip();
        }
    })();
    
});

/* --------------------------------------------------------
Date Time Widget
-----------------------------------------------------------*/
(function(){
    setInterval( function() {
        // Create a newDate() object and extract the seconds of the current time on the visitor's
        var seconds = new Date().getSeconds();
        // Add a leading zero to seconds value
        $("#sec").html(( seconds < 10 ? "0":"" ) + seconds);
    },500);
    setInterval( function() {
        // Createa newDate() object and extract the minutes of the current time on the visitor's
        var minutes = new Date().getMinutes();
        // Add a leading zero to the minutes value
        $("#min").html(( minutes < 10 ? "0":"" ) + minutes);
    },500);
    setInterval( function() {
        // Create a newDate() object and extract the hours of the current time on the visitor's
        var hours = new Date().getHours();
        // Add a leading zero to the hours value
        $("#hours").html(( hours < 10 ? "0" : "" ) + hours);
    }, 500);
})();

/* --------------------------------------------------------
menu nav
-----------------------------------------------------------*/
(function(){
    $("ul.side-menu li").click(function(e){
        e.preventDefault();
        var option={};
        option.tabMainName="nav_tab";
        option.tabContentMainName="nav_tab_content";
        var _target=$(e.target);
        if(_target.context.localName=='span'){
            _target=_target.parent();
            var tab_url=_target.attr('tab_url');
            if(tab_url){
                option.tabTitle=_target.attr('tab_title');
                option.tabId=_target.attr('tab_index');
                option.tabUrl=tab_url
                addTab(option);
            }
        }else if(_target.context.localName=='a'){
            var tab_url=_target.attr('tab_url');
            if(tab_url){
                option.tabTitle=_target.attr('tab_title');
                option.tabId=_target.attr('tab_index');
                option.tabUrl=tab_url
                addTab(option);
            }
        }
    });
})();