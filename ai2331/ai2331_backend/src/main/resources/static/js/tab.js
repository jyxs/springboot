function addTab(options) {
     //option:
     //tabMainName:tab标签页所在的容器
     //tabId:当前tab的唯一标识
     //tabTitle:当前tab的标题
     //tabUrl:当前tab所指向的URL地址
     var exists = checkTabIsExists(options.tabMainName, options.tabId);
     if(exists){
         $("#tab_a_"+options.tabId).click();
     } else {
         $("#"+options.tabMainName).append('<li id="tab_li_'+options.tabId+'" class="tab_li" data-toggle="context" data-target="#context-menu"><a href="#tab_content_'+options.tabId+'" data-toggle="tab" id="tab_a_'+options.tabId+'"><button class="close closeTab" type="button" onclick="closeTab(this);" style="opacity:1;color:white;padding:1px;">×</button>'+options.tabTitle+'</a></li>');
          
         //固定TAB中IFRAME高度
         mainHeight = $(document.body).height();
          
         var content = '';
         if(options.content){
             content = options.content;
         } else {
             content = '<iframe src="' + options.tabUrl + '" width="100%" height="'+mainHeight+'px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>';
         }
         $("#"+options.tabContentMainName).append('<div id="tab_content_'+options.tabId+'" class="tab-pane">'+content+'</div>');
         $("#tab_a_"+options.tabId).click();
     }
 }

 function closeTab (button) {
     //通过该button找到对应li标签的id
     var li=$(button).parent().parent();
     var li_id = li.attr('id');
     var id = li_id.replace("tab_li_","");
      
     //如果关闭的是当前激活的TAB，激活他的前一个TAB
     if (li.hasClass('active')) {
         li.prev().find("a").click();
     }
     //关闭TAB
     $("#" + li_id).remove();
     $("#tab_content_" + id).remove();
 }

 function checkTabIsExists(tabMainName, tabId){
     var tab = $("#"+tabMainName+" > #tab_li_"+tabId);
     //console.log(tab.length)
     return tab.length > 0;
 }

 function closeAll(){
    $(".tab_li").remove();
    $(".tab-pane").remove();
 }

 function closeLeft(){
    var _this=$(contextmenu.target);
    var id=_this.attr('id');
    var has_active=false;
    $(".tab_li").each(function(){
        if(id==$(this).attr("id")){
            return false;
        }
        if($(this).hasClass('active')){
            has_active=true;
        }
        $(this).remove();
        var _id=$(this).attr("id");
        _id =_id.substring(_id.lastIndexOf('_')+1);
        $("#tab_content_" + _id).remove();
        
    });
    if(has_active){
        if(!_this.hasClass('active')){
            _this.find("a").click();
        }
    }
 }

 function closeRight(){
    var _this=$(contextmenu.target);
    var id=_this.attr('id');
    var _del=false;
    var has_active=false;
    $(".tab_li").each(function(){
        if(id==$(this).attr("id")){
            _del=true
            return true;
        }
        if(_del==true){
            if($(this).hasClass('active')){
                has_active=true;
            }
            $(this).remove();
            var _id=$(this).attr("id");
            _id =_id.substring(_id.lastIndexOf('_')+1);
            $("#tab_content_" + _id).remove();
        }
    });
    if(has_active){
        if(!_this.hasClass('active')){
            _this.find("a").click();
        }
    }
 }

 function closeOther(){
    var _this=$(contextmenu.target);
    var id=_this.attr('id');
    $(".tab_li").each(function(){
        if(id==$(this).attr("id")){
            return true;
        }
        $(this).remove();
        var _id=$(this).attr("id");
        _id =_id.substring(_id.lastIndexOf('_')+1);
        $("#tab_content_" + _id).remove();
        $(this).prev().find("a").click();
    });
    if(!_this.hasClass('active')){
        _this.find("a").click();
    }
 }

 function addHomeTab(homeUrl){
    var option={};
    option.tabMainName="nav_tab";
    option.tabContentMainName="nav_tab_content";
    option.tabTitle='Home';
    option.tabId='000';
    option.tabUrl=$cp+"portal";
    console.info(option.tabUrl);
    addTab(option);
 }