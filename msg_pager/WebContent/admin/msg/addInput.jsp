<%@page import="siyl.cit.msg.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言添加</title>
<%
 User lu = (User)session.getAttribute("loginUser");
%>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhEditor/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/xhEditor/xheditor-1.1.14-zh-cn.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#content").xheditor({
			tools:'full',
			skin:'o2007silver',
			emots:{pidgin:{name:'头像1',width:22,height:25,line:8,list:{smile:'微笑',cute:'可爱',wink:'眨眼',laugh:'大笑',victory:'胜利',sad:'伤心',cry:'哭泣',angry:'生气',shout:'大骂',curse:'诅咒',devil:'魔鬼',blush:'害羞',tongue:'吐舌头',envy:'羡慕',cool:'耍酷',kiss:'吻',shocked:'惊讶',sweat:'汗',sick:'生病',bye:'再见',tired:'累',sleepy:'睡了',question:'疑问',rose:'玫瑰',gift:'礼物',coffee:'咖啡',music:'音乐',soccer:'足球',good:'赞同',bad:'反对',love:'心',brokenheart:'伤心'}},msn:{name:'MSN',count:40,width:22,height:22,line:8},ipb:{name:'IPB',width:20,height:25,line:8,list:{smile:'微笑',joyful:'开心',laugh:'笑',biglaugh:'大笑',w00t:'欢呼',wub:'欢喜',depres:'沮丧',sad:'悲伤',cry:'哭泣',angry:'生气',devil:'魔鬼',blush:'脸红',kiss:'吻',surprised:'惊讶',wondering:'疑惑',unsure:'不确定',tongue:'吐舌头',cool:'耍酷',blink:'眨眼',whistling:'吹口哨',glare:'轻视',pinch:'捏',sideways:'侧身',sleep:'睡了',sick:'生病',ninja:'忍者',bandit:'强盗',police:'警察',angel:'天使',magician:'魔法师',alien:'外星人',heart:'心动'}}}
		});
	});
</script>
</head>
<body>
<jsp:include page="/msg/inc.jsp">
	<jsp:param value="添加" name="op"/>
</jsp:include>
<form action="add.jsp" method="post">
<input type="hidden" name="userId"  value="<%=lu.getId()%>"/>
<table width="800" align="center" border="1">
	<tr>
	<td width="130">标题</td><td><input type="text" name="title" size="80"/></td>
	</tr>
	<tr>
	<td colspan="2">内容</td>
	</tr>
	<tr>
	<td colspan="2">
	<textarea rows="20" cols="110" name="content" id="content"></textarea>
	</td>
	</tr>
	<tr>
	<td colspan="2" align="center">
		<input type="submit" value="添加留言"/><input type="reset"/>
	</td>
	</tr>
</table>
</form>
</body>
</html>