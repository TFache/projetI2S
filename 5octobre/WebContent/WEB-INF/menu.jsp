<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<ul>
<li><a href = "/5octobre/maths?nom=Fache&prenom=Thomas">Maths</a></li>
<li><a href = "/5octobre/informatique?nom=Fache&prenom=Thomas">Info</a></li>
<li><a href = "/5octobre/reseaux?nom=Fache&prenom=Thomas">Reseau</a></li>
</ul>

Cours de
<% String n = (String)request.getAttribute("nom"); String p = (String)request.getAttribute("prenom");
out.println(n+" "+p);%>

