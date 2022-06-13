package com.google.sps;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String textName = request.getParameter("name");
    String textEmail = request.getParameter("email");
    String textPhone = request.getParameter("phone");
    String textMessage = request.getParameter("message");

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: " + textName);
    System.out.println("You submitted: " + textEmail);
    System.out.println("You submitted: " + textPhone);
    System.out.println("You submitted: " + textMessage);

    // Write the value to the response so the user can see it.
    response.getWriter().println("You submitted: " + textName);
    response.getWriter().println("You submitted: " + textEmail);
    response.getWriter().println("You submitted: " + textPhone);
    response.getWriter().println("You submitted: " + textMessage);
  }
}