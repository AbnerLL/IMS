<%
            if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
                response.setHeader("Pragma", "no-cache");
            } else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
                response.setHeader("Cache-Control", "no-cache");
            }
            response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>