if(typeof IN_ADMINCP == 'undefined') {
	if(typeof showngethreadcard != 'undefined' && showngethreadcard == 1) {
		_attachEvent(window, 'load', ngethreadInit, document);
	}
}

function ngethreadInit() {
	var cardShow = function (obj) {
		if(BROWSER.ie && BROWSER.ie < 7 && obj.href.indexOf('tid') != -1) {
			return;
		}
		pos = obj.getAttribute('nge_c') == '1' ? '43' : obj.getAttribute('nge_c');
		USERCARDST = setTimeout(function() {ngethreadajaxmenu(obj, 500, 1, 2, pos, null, 'p_pop card');}, 250);
	};
	var a = document.body.getElementsByTagName('a');
	for(var i = 0;i < a.length;i++){
		if(a[i].getAttribute('nge_c')) {
			a[i].setAttribute('mid', 'study_nge_thread' + a[i].getAttribute('nge_t'));
			a[i].onmouseover = function() {cardShow(this)};
			a[i].onmouseout = function() {clearTimeout(USERCARDST);};
		}
	}
}

function ngethreadajaxmenu(ctrlObj, timeout, cache, duration, pos, recall, idclass, contentclass) {
	if(!ctrlObj.getAttribute('mid')) {
		var ctrlid = ctrlObj.id;
		if(!ctrlid) {
			ctrlObj.id = 'ajaxid_' + Math.random();
		}
	} else {
		var ctrlid = ctrlObj.getAttribute('mid');
		if(!ctrlObj.id) {
			ctrlObj.id = 'ajaxid_' + Math.random();
		}
	}
	var menuid = ctrlid + '_menu';
	var menu = $(menuid);
	if(isUndefined(timeout)) timeout = 3000;
	if(isUndefined(cache)) cache = 1;
	if(isUndefined(pos)) pos = '43';
	if(isUndefined(duration)) duration = timeout > 0 ? 0 : 3;
	if(isUndefined(idclass)) idclass = 'p_pop';
	if(isUndefined(contentclass)) contentclass = 'p_opt';
	var func = function() {
		showMenu({'ctrlid':ctrlObj.id,'menuid':menuid,'duration':duration,'timeout':timeout,'pos':pos,'cache':cache,'layer':2});
		if(typeof recall == 'function') {
			recall();
		} else {
			eval(recall);
		}
	};

	if(menu) {
		if(menu.style.display == '') {
			hideMenu(menuid);
		} else {
			func();
		}
	} else {
		menu = document.createElement('div');
		menu.id = menuid;
		menu.style.display = 'none';
		menu.className = idclass;
		menu.innerHTML = '<div class="' + contentclass + '" id="' + menuid + '_content"></div>';
		$('append_parent').appendChild(menu);
		var url = 'plugin.php?id=study_nge:ngethread&tid=' + ctrlObj.getAttribute('nge_t');
		url += (url.indexOf('?') != -1 ? '&' :'?') + 'ajaxmenu=1';
		ajaxget(url, menuid + '_content', 'ajaxwaitid', '', '', func);
	}
	doane();
}

function handlePrompt(id,type) {
	var msgObj = $('ngethread_message'+id);
	if(type) {
		if(msgObj.value == msgstr) {
			msgObj.value = '';
			msgObj.className = 'msgfocus xg2';
		}
	} else {
		if(msgObj.value == ''){
			msgObj.value = msgstr;
			msgObj.className = 'xg1';
		}
	}
}

function ngethread_onsubmit(id,tmid,fail){
	
	if($('ngethread_message'+tmid).value != msgstr) {
		$('ngethread_message'+tmid).value = parseurl($('ngethread_message'+tmid).value);
		ajaxpost(id, 'return_reply', 'return_reply', 'onerror','','setTimeout("window.location.reload()", 1000)');
		hideWindow(this);
	} else {
		showPrompt(null, null, '<span>' + fail + '</span>', 1500);
		return false;
	}
	
}


function nge_toggle_collapse(objname, noimg, complex, lang){
	toggle_collapse(objname, noimg, complex, lang);
	if(getcookie('toggle_' + objname)){
		setcookie('toggle_' + objname, '', 86400 * 365);
	}else{
		setcookie('toggle_' + objname, '1314', 86400 * 365);
	}
}

function nge_extstyle(styleid) {
	$('study_nge_div').className = styleid ? 'study_nge_' + styleid : 'study_nge_' + getcookie('study_nge_extstyle_default');
	setcookie('study_nge_extstyle', styleid, 86400 * 365);
}


function study_nge_hoverLi(m,n,counter){
    for(var i=0;i<counter;i++){
        $('study_nge_t'+m+i).className='nge_inactive';
        $('study_nge_m'+m+i).className='nge_undis';
    }
    $('study_nge_t'+m+n).className='nge_active';
    $('study_nge_m'+m+n).className='nge_dis';
}

var sPop = null;
var pltsoffsetX = 10;
var pltsoffsetY = 15;

function showPopupText(event) {
	try {
		o = event.srcElement ? event.srcElement : event.target;
		if (o.name=='xxx'){ 
		if (o.alt != null && o.alt != '') { o.pop = o.alt; o.alt = ''; }
		if (o.title != null && o.title != '') { o.pop = o.title; o.title = ''; }
		if (o.pop != sPop) {
			sPop = o.pop;
			if (sPop == null || sPop == '') {
				$('popLayer').style.visibility = 'hidden';
				$('popLayer').innerHTML = '';
			} else {
				$('popLayer').style.visibility = 'visible';
				$('popLayer').innerHTML = sPop.replace(/<(.*)>/g, "&lt;$1&gt;").replace(/\n/g, '<br />');
				moveToMouseLoc(event);
			}
		}} else {
			if (o.alt != null && o.alt != '') { o.pop = o.alt;}
		if (o.title != null && o.title != '') { o.pop = o.title;}
		if (o.pop != sPop) {
			sPop = o.pop;
			if (sPop == null || sPop == '' ||1) {
				$('popLayer').style.visibility = 'hidden';
				$('popLayer').innerHTML = '';
			} else {
				$('popLayer').style.visibility = 'visible';
				$('popLayer').innerHTML = sPop.replace(/<(.*)>/g, "&lt;$1&gt;").replace(/\n/g, '<br />');
				moveToMouseLoc(event);
			}
		}
		}
	} catch (e) {
		return true;
	}
}

function moveToMouseLoc(event){
	try {
		if ($('popLayer').innerHTML == '') return true;
		var MouseX = event.clientX;
		var MouseY = event.clientY;
		var popHeight = $('popLayer').offsetHeight;
		var popWidth = $('popLayer').offsetWidth;
		if (MouseY + pltsoffsetY + popHeight > document.documentElement.clientHeight - 10) {
			popTopAdjust =- popHeight - pltsoffsetY * 1.5; 
		} else {
			popTopAdjust = 0;
		}
		if (MouseX + pltsoffsetX + popWidth > document.documentElement.clientWidth - 10) {
			popLeftAdjust =- popWidth - pltsoffsetX * 2; 
		} else {
			popLeftAdjust = 0;
		}
		var pleft = MouseX + pltsoffsetX + document.documentElement.scrollLeft + popLeftAdjust;
		var ptop = MouseY + pltsoffsetY + document.documentElement.scrollTop + popTopAdjust;
		$('popLayer').style.left = (pleft > 5 ? pleft : 5) + 'px';
		$('popLayer').style.top = (ptop > 5 ? ptop : 5) + 'px';
	  	return true;
	} catch (e) {
		return true;
	}
}

if (!document.onmouseover) {
	document.onmouseover = function(e) {
		!e ? showPopupText(window.event) : showPopupText(e);
	};
}
//from 1314study.com