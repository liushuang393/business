<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>三圓会社管理システム</title>
<link
	href="${pageContext.request.contextPath}/resources/css/authority/main_css.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/authority/zTreeStyle.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/zTree/jquery.ztree.core-3.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/authority/commonAll.js"></script>
<script type="text/javascript">
	/**ログアウト**/
	function logout() {
		if (confirm("ログアウトしますか？")) {
			window.location.href = "${pageContext.request.contextPath}/user/logout.do";
		}
	}

	function getDate01() {
		var time = new Date();
		var myYear = time.getFullYear();
		var myMonth = time.getMonth() + 1;
		var myDay = time.getDate();
		if (myMonth < 10) {
			myMonth = "0" + myMonth;
		}
		//document.getElementById("yue_fen").innerHTML = myYear + "." + myMonth;
		document.getElementById("day_day").innerHTML = myYear + "." + myMonth
				+ "." + myDay;
	}
	/* zTree  */
	var zTree;

	var setting = {
		view : {
			dblClickExpand : false,
			showLine : false,
			expandSpeed : ($.browser.msie && parseInt($.browser.version) <= 6) ? ""
					: "fast"
		},
		data : {
			key : {
				name : "resourceName"
			},
			simpleData : {
				enable : true,
				idKey : "resourceID",
				pIdKey : "parentID",
				rootPId : ""
			}
		},
		callback : {
			beforeExpand : beforeExpand,
			onExpand : onExpand,
			onClick : zTreeOnClick
		}
	};

	var curExpandNode = null;
	function beforeExpand(treeId, treeNode) {
		var pNode = curExpandNode ? curExpandNode.getParentNode() : null;
		var treeNodeP = treeNode.parentTId ? treeNode.getParentNode() : null;
		for (var i = 0, l = !treeNodeP ? 0 : treeNodeP.children.length; i < l; i++) {
			if (treeNode !== treeNodeP.children[i]) {
				zTree.expandNode(treeNodeP.children[i], false);
			}
		}
		while (pNode) {
			if (pNode === treeNode) {
				break;
			}
			pNode = pNode.getParentNode();
		}
		if (!pNode) {
			singlePath(treeNode);
		}

	}
	function singlePath(newNode) {
		if (newNode === curExpandNode)
			return;
		if (curExpandNode && curExpandNode.open == true) {
			if (newNode.parentTId === curExpandNode.parentTId) {
				zTree.expandNode(curExpandNode, false);
			} else {
				var newParents = [];
				while (newNode) {
					newNode = newNode.getParentNode();
					if (newNode === curExpandNode) {
						newParents = null;
						break;
					} else if (newNode) {
						newParents.push(newNode);
					}
				}
				if (newParents != null) {
					var oldNode = curExpandNode;
					var oldParents = [];
					while (oldNode) {
						oldNode = oldNode.getParentNode();
						if (oldNode) {
							oldParents.push(oldNode);
						}
					}
					if (newParents.length > 0) {
						for (var i = Math.min(newParents.length,
								oldParents.length) - 1; i >= 0; i--) {
							if (newParents[i] !== oldParents[i]) {
								zTree.expandNode(oldParents[i], false);
								break;
							}
						}
					} else {
						zTree.expandNode(oldParents[oldParents.length - 1],
								false);
					}
				}
			}
		}
		curExpandNode = newNode;
	}

	function onExpand(event, treeId, treeNode) {
		curExpandNode = treeNode;
	}

	function zTreeOnClick(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("dleft_tab1");
		zTree.expandNode(treeNode, null, null, null, true);
		if (treeNode.isParent) {
			$('#here_area').html(
					'現在位置：' + treeNode.getParentNode().resourceName
							+ '&nbsp;>&nbsp;<span style="color:#1A5CC6">'
							+ treeNode.resourceName + '</span>');
		} else {
			$('#here_area').html(
					'現在位置：システム&nbsp;>&nbsp;<span style="color:#1A5CC6">'
							+ treeNode.resourceName + '</span>');
		}
		if (treeNode.isParent) {
			return false;
		}
		if (treeNode.accessPath == "" || treeNode.accessPath == "#") {
			return false;
		}

		rightMain(treeNode.accessPath);

	};

	/* 上のメニュー*/
	function switchTab(tabpage, tabid) {
		var oItem = document.getElementById(tabpage).getElementsByTagName("li");
		for (var i = 0; i < oItem.length; i++) {
			var x = oItem[i];
			x.className = "";
		}
		if ('left_tab1' == tabid) {
			$(document).ajaxStart(onStart).ajaxSuccess(onStop);
			loadMenu('YEWUMOKUAI', 'dleft_tab1');
		} else if ('left_tab2' == tabid) {
			$(document).ajaxStart(onStart).ajaxSuccess(onStop);
			loadMenu('XITONGMOKUAI', 'dleft_tab1');
		} else if ('left_tab3' == tabid) {
			$(document).ajaxStart(onStart).ajaxSuccess(onStop);
			loadMenu('QITAMOKUAI', 'dleft_tab1');
		}

		if (zTree) {
			zTree.expandAll(false);
		}
	}

	$(document).ready(function() {
		$(document).ajaxStart(onStart).ajaxSuccess(onStop);
		loadMenu('YEWUMOKUAI', "dleft_tab1");
		if (zTree) {
			zTree.expandAll(false);
		}
	});

	function loadMenu(resourceType, treeObj) {
		/*$.ajax({
		    type:"POST",
		    url:"${dynamicURL}/authority/modelPart.action?resourceType=" + resourceType,
		    dataType : "json",
		    success:function(data){
		        // 戻り値はブランクではない時、業務モジュールをローウドする。
		        if(data != null){
		            //
		            $.fn.zTree.init($("#"+treeObj), setting, data);
		             alert(treeObj);
		            zTree = $.fn.zTree.getZTreeObj(treeObj);
		            if( zTree ){
		                zTree.expandAll(false);
		            }
		        }
		    }
		});*/

		/*メニュー画面*/
        data1 =
            [
            {"accessPath":"","checked":true,  "delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":1,"resourceID":2,"resourceName":"社員管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/business/b01user/b01user01_info.html",            "checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":200,"resourceName":"社員情報","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/business/b02attendance/b02attendance01_info.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":201,"resourceName":"勤怠管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/business/b03resume/b03resume01_info.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":202,"resourceName":"履歴書管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/business/b04skill/b04skill01_info.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":203,"resourceName":"スキル管理","resourceOrder":0,"resourceType":""},

            {"accessPath":"","checked":false,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":1,"resourceID":3,"resourceName":"承認管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/business/b99approval_list.html","checked":false,"delFlag":0,"parentID":3,"resourceCode":"","resourceDesc":"","resourceGrade":3,"resourceID":300,"resourceName":"承認管理","resourceOrder":0,"resourceType":""},

            ];

        data2 =
            [
            {"accessPath":"","checked":true,"delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":1,"resourceID":2,"resourceName":"社内管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"${pageContext.request.contextPath}/department/departmentListInit.do","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":200,"resourceName":"部門管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/system/s02post/s02post01_list.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":201,"resourceName":"ポスト管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/system/s03title/s03title01_list.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":202,"resourceName":"タイトル管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/system/s04user/s04user01_list.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":203,"resourceName":"社員情報管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/system/s05attendance/s05attendance01_list.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":204,"resourceName":"勤怠管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/system/s06resume/s06resume01_list.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":204,"resourceName":"履歴書管理","resourceOrder":0,"resourceType":""},
            ];

        data3 =
            [
            {"accessPath":"","checked":true, "delFlag":0,"parentID":1,"resourceCode":"","resourceDesc":"","resourceGrade":1,"resourceID":2,"resourceName":"営業管理","resourceOrder":0,"resourceType":""},
            {"accessPath":"./jsp/sales/sa01contract/SA01contract01_list.html","checked":false,"delFlag":0,"parentID":2,"resourceCode":"","resourceDesc":"","resourceGrade":2,"resourceID":200,"resourceName":"営業管理","resourceOrder":0,"resourceType":""},

            ];

		// 如果返回数据不为空，加载"业务模块"目录
		if ('YEWUMOKUAI' == resourceType) {
			if (data1 != null) {
				$.fn.zTree.init($("#" + treeObj), setting, data1);
			}
		} else if ('XITONGMOKUAI' == resourceType) {
			if (data2 != null) {
				$.fn.zTree.init($("#" + treeObj), setting, data2);
			}
		} else if ('QITAMOKUAI' == resourceType) {
			if (data3 != null) {
				$.fn.zTree.init($("#" + treeObj), setting, data3);
			}
		}

		//              alert(treeObj);
		zTree = $.fn.zTree.getZTreeObj(treeObj);
		if (zTree) {
			// 默认关闭所有节点
			zTree.expandAll(false);
		}
	}

	//ajax start function
	function onStart() {
		$("#ajaxDialog").show();
	}

	//ajax stop function
	function onStop() {
		//         $("#ajaxDialog").dialog("close");
		$("#ajaxDialog").hide();
	}
</script>
</head>
<body onload="getDate01()">
	<div id="top">
		<div id="top_logo">
			<img alt="logo"
				src="${pageContext.request.contextPath}/resources/images/common/logo.jpg"
				width="274" height="49" style="vertical-align: middle;">
		</div>
		<form:form id="submitForm" method="post" commandName="logInUser">
			<div id="top_links">
				<div id="top_op">
					<ul>
						<li><a target="_blank"
							onclick="rightMain('${pageContext.request.contextPath}/user/introduce.do');"><b>トップ</b>
						</a>&nbsp;&nbsp;&nbsp; </li>
						<li><img alt="現在のユーザー"
							src="${pageContext.request.contextPath}/resources/images/common/user.jpg">：
							<span>${logInUser.userName} </span></li>
						<li><img alt="今日"
							src="${pageContext.request.contextPath}/resources/images/common/date.jpg">：
							<span id="day_day"></span></li>
					</ul>
				</div>
				<div id="top_close">
					<a href="javascript:void(0);" onclick="logout();" target="_parent">
						<img alt="ログアウト" title="ログアウト"
						src="${pageContext.request.contextPath}/resources/images/common/close.jpg"
						style="position: relative; top: 10px; left: 25px;">
					</a>
				</div>
			</div>
		</form:form>
	</div>
	<!-- side menu start -->
	<div id="side">
		<div id="left_menu">
			<ul id="TabPage2" style="height: 200px; margin-top: 50px;">
				<li id="left_tab1" class="selected"
					onClick="javascript:switchTab('TabPage2','left_tab1');"
					title="サービスモジュール"><img alt="サービスモジュール" title="サービスモジュール"
					src="${pageContext.request.contextPath}/resources/images/common/1_hover.jpg"
					width="33" height="31"></li>
				<li id="left_tab2"
					onClick="javascript:switchTab('TabPage2','left_tab2');"
					title="システム管理"><img alt="システム管理" title="システム管理"
					src="${pageContext.request.contextPath}/resources/images/common/2.jpg"
					width="33" height="31"></li>
				<li id="left_tab3"
					onClick="javascript:switchTab('TabPage2','left_tab3');" title="その他">
					<img alt="その他" title="その他"
					src="${pageContext.request.contextPath}/resources/images/common/3.jpg"
					width="33" height="31">
				</li>
			</ul>


			<div id="nav_show"
				style="position: absolute; bottom: 0px; padding: 10px;">
				<a href="javascript:;" id="show_hide_btn"> <img alt="表示/非表示"
					title="表示/非表示"
					src="${pageContext.request.contextPath}/resources/images/common/nav_hide.png"
					width="35" height="35">
				</a>
			</div>
		</div>
		<div id="left_menu_cnt">
			<div id="nav_module">
				<img
					src="${pageContext.request.contextPath}/resources/images/common/module_1.png"
					width="210" height="58" />
			</div>
			<div id="nav_resource">
				<ul id="dleft_tab1" class="ztree"></ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#TabPage2 li').click(
					function() {
						var index = $(this).index();
						$(this).find('img').attr(
								'src',
								'${pageContext.request.contextPath}/resources/images/common/'
										+ (index + 1) + '_hover.jpg');
						$(this).css({
							background : '#fff'
						});
						$('#nav_module').find('img').attr(
								'src',
								'${pageContext.request.contextPath}/resources/images/common/module_'
										+ (index + 1) + '.png');
						$('#TabPage2 li').each(
								function(i, ele) {
									if (i != index) {
										$(ele).find('img').attr(
												'src',
												'${pageContext.request.contextPath}/resources/images/common/'
														+ (i + 1) + '.jpg');
										$(ele).css({
											background : '#044599'
										});
									}
								});
						// 显示侧边栏
						switchSysBar(true);
					});

			// 显示隐藏侧边栏
			$("#show_hide_btn").click(function() {
				switchSysBar();
			});
		});

		/**隐藏或者显示侧边栏**/
		function switchSysBar(flag) {
			var side = $('#side');
			var left_menu_cnt = $('#left_menu_cnt');
			if (flag == true) { // flag==true
				left_menu_cnt.show(500, 'linear');
				side.css({
					width : '280px'
				});
				$('#top_nav').css({
					width : '77%',
					left : '304px'
				});
				$('#main').css({
					left : '280px'
				});
			} else {
				if (left_menu_cnt.is(":visible")) {
					left_menu_cnt.hide(10, 'linear');
					side.css({
						width : '60px'
					});
					$('#top_nav').css({
						width : '100%',
						left : '60px',
						'padding-left' : '28px'
					});
					$('#main').css({
						left : '60px'
					});
					$("#show_hide_btn")
							.find('img')
							.attr('src',
									'${pageContext.request.contextPath}/resources/images/common/nav_show.png');
				} else {
					left_menu_cnt.show(500, 'linear');
					side.css({
						width : '280px'
					});
					$('#top_nav').css({
						width : '77%',
						left : '304px',
						'padding-left' : '0px'
					});
					$('#main').css({
						left : '280px'
					});
					$("#show_hide_btn")
							.find('img')
							.attr('src',
									'${pageContext.request.contextPath}/resources/images/common/nav_hide.png');
				}
			}
		}
	</script>
	<!-- side menu start -->
	<div id="top_nav">
		<span id="here_area">現在位置：トップ&nbsp;>&nbsp;</span>
	</div>
	<div id="main">
		<iframe name="right" id="rightMain"
			src="${pageContext.request.contextPath}/user/introduce.do"
			frameborder="no" scrolling="auto" width="100%" height="100%"
			allowtransparency="true" />
	</div>
</body>
</html>

