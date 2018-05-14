/*
var sideBar = {} 会造成全局污染，sideBar赋给Windows，作为windows的一个属性*/
/*目前常用模块模式的方法，避免全局污染*/

/*立即执行函数*/
(function () {
    var MenuBar = function () {
        this.el = document.querySelector('#sidebar ul');
        this.state = 'allClosed';
        /*禁止向上传播，不然点击sidebar中的ul也会触发和点击关闭按钮一样的事件*/
        this.el.addEventListener('click',function (e) {
            e.stopPropagation();
        });
        var self = this;
        this.menulist = document.querySelectorAll('#sidebar ul > li');
        this.currentOpenMenuContent = null;
        this.targetId = null;
        var curIndex= "prof";
        
        for(var i = 0;i<this.menulist.length;i++){
            this.menulist[i].addEventListener('click',function (e) {
                var menuContentEl = document.getElementById(e.currentTarget.id +'-content');
                var targetId = e.currentTarget.id;
                if(targetId=="prof" && !loginflag){
                	window.location.href= appUrl + "/login.jsp?operatype=login";
                	return;
                }
                if(targetId=="webchat" && typeUser == "2001"){
                	window.location.href= appUrl + "/jsp_v2/websocket/websocketchat.jsp";
                	return;
                }
                if(targetId=="asset" ){
                	if(!loginflag){
                		window.location.href= appUrl + "/login.jsp?operatype=login";
                		return;
                	}
                }
                if(targetId=="suggest"){
                	window.location.href= appUrl + "/jsp_v2/suggestion/suggestion.jsp";
                	return;
                }
                if(self.state === 'allClosed'){ 
                    console.log('open'+menuContentEl.id);
                    
                    menuContentEl.style.top = '60';
                    menuContentEl.style.left = '-85px';
                    menuContentEl.className = 'nav-content';
                    menuContentEl.classList.add('menuContent-move-right');
                    self.state='hasOpened';
                    self.currentOpenMenuContent = menuContentEl;
                    self.targetId = targetId;
                    curIndex = targetId;
                }else{
                    console.log('closed'+self.currentOpenMenuContent.id);
                    console.log('open'+menuContentEl.id);
                    self.currentOpenMenuContent.className = 'nav-content';
                    self.currentOpenMenuContent.style.top = '60';
                    self.currentOpenMenuContent.style.left = '-10px';
                    self.currentOpenMenuContent.classList.add('menuContent-move-left');
                    self.state = 'allClosed';
                	curIndex = targetId;
                    if(curIndex != self.targetId){
	                    menuContentEl.className='nav-content';
	                    menuContentEl.style.top = '310px';
	                    menuContentEl.style.left = '35px';
	                    menuContentEl.classList.add('menuContent-move-up');
	                    self.state='hasOpened';
	                    self.currentOpenMenuContent = menuContentEl;
	                    self.targetId = targetId;
                    }
                    
                }
            });
        }
        this.menuContentList = document.querySelectorAll('.nav-content > div.nav-con-close');
        for(var i=0;i<this.menuContentList.length;i++){
            this.menuContentList[i].addEventListener('click',function (e) {
                var menuContent = e.currentTarget.parentNode;
                menuContent.className = 'nav-content';
                menuContent.style.top = '60';
                menuContent.style.left ='35px';
                menuContent.classList.add('menuContent-move-left');
                this.state='allClosed';
            });
        }
    };
    /*Sidebar第一个字母大写，构造函数的基本规范 */
    var Sidebar = function (eId,closeBarId) {
        this.state = 'opened';
        this.el = document.getElementById(eId || 'sidebar');
        this.closeBarEl = document.getElementById(closeBarId || 'closeBar');
        /*默认向上冒泡,第三个参数可以不写*/
        var self = this;
        this.menubar = new MenuBar();
        this.el.addEventListener('click',function (event) {
            if(event.target !== self.el){
                //console.log(this);
                self.triggerSwich();
            }
        });
        
    }
    Sidebar.prototype.close = function () {
        console.log('关闭sidebar');
        this.el.className = 'sidebar-move-left';
        this.closeBarEl.className = 'closebar-move-right';
        this.state = 'closed';
    };
    Sidebar.prototype.open = function () {
        console.log('打开sidebar');
        this.el.style.left = '-120px';
        this.el.className = 'sidebar-move-right';
        this.closeBarEl.style.left = '160px';
        this.closeBarEl.className = 'closebar-move-left';
        this.state = 'opened';
    };
    Sidebar.prototype.triggerSwich = function () {
        if(this.state === 'opened'){
            this.close();
        }else{
            this.open();
        }
    };
    var sidebar = new Sidebar();


})();