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

<%@page import="Interface.*" %>
<%
if(session.getAttribute("User Profile") == null)
{
    response.sendRedirect("index.jsp?message=" + request.getParameter("message"));
    return;
}
%>

<% EmployeeForms.displayCreatePanelPage(request, response); %>

