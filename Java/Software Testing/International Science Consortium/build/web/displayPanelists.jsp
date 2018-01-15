<!--
/*
 * Arelys De La Guardia
 * Deisy Hernandez
 * Michael Smythers
 * Daniel Galano
 * Jairo Pava
 *
 * International Science Consoritum Control System
 *
 * December 1, 2009
 */
-->

<%@page import="Interface.*, java.util.ArrayList, Storage.Repository.*" %>
<%
if(session.getAttribute("User Profile") == null)
{
    response.sendRedirect("index.jsp?message=" + request.getParameter("message"));
    return;
}
%>
<jsp:include page="header.jsp" />

<table width="100%" border="0" cellspacing="10">
    <% 
        EmployeeForms.displayPanelists(request, ((ArrayList<PanelistProfile>)session.getAttribute("Panelists")), session, out);
    %>
</table>
<jsp:include page="footer.jsp" />