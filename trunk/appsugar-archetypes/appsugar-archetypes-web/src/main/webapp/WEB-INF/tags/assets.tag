<%@ tag body-content="empty" %>
<%@ attribute name="group" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<c:if test="${empty group}"><c:set var="group" value="main"/></c:if>
<c:if test="${not empty param.debug}">
    <c:set var="debugAssets" value="${param.debug}" scope="session"/>
</c:if>

<c:choose>
    <c:when test="${sessionScope.debugAssets}">
        <link rel="stylesheet" type="text/css" href="${base}/webjars/bootswatch/3.3.6/spacelab/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${base}/static/css/style.css"/>
        <script type="text/javascript" src="${base}/webjars/jquery/2.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="${base}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${base}/static/js/formutil.js"></script>
    </c:when>
    <c:otherwise>
        <link rel="stylesheet" type="text/css" href="${base}/assets/${group}.css"/>
        <script type="text/javascript" src="${base}/assets/${group}.js"></script>
    </c:otherwise>
</c:choose>

<%request.setAttribute("local_string",request.getLocale().toString()); %>
<!-- jquery validator error message -->
<!-- Default message  -->
<script type="text/javascript" src="${base}/static/js/messages/messages_en.js"></script>
<!-- Dynamic message , May not found but we don't need pay attention -->
<script type="text/javascript" src="${base}/static/js/messages/messages_${local_string}.js"></script>

<!-- JQuery validation  extension -->
<script type="text/javascript" src="${base}/static/js/validation.ext.js"></script>
