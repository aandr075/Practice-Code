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
<jsp:include page="header.jsp" />

<table width="20%" border="0" cellspacing="10">
    <form method="POST" action="updatePanelStatus">
        <tr>
            <td>
                Select Status:
            </td>
            <td>
                <select name="panelStatus">
                    <option>In Progress</option>
                    <option>Complete</option>
                    <option>Cancelled</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Comments:
            </td>
            <td>
                <textarea name="statusComments" cols="40" rows="6"></textarea>
                <input type="hidden" name="panelID" value="<% out.print(request.getParameter("panelID")); %>"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Update"/>
            </td>
        </tr>
    </form>
</table>
<jsp:include page="footer.jsp" />