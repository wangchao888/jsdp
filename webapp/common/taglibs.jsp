<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jsdp" uri="/WEB-INF/tag/dicTag.tld" %>

<%
if (request.getProtocol().compareTo("HTTP/1.0") == 0)
response.setHeader("Pragma", "No-cache");
if (request.getProtocol().compareTo("HTTP/1.1") == 0)
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", -1);
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
