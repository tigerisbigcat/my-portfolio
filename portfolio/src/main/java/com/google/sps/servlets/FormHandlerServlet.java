package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

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


    // Sanitize user input to remove HTML tags and JavaScript.
    String nameValue = Jsoup.clean(request.getParameter("name"), Safelist.basic());
    String emailValue = Jsoup.clean(request.getParameter("email"), Safelist.basic());
    String phoneValue = Jsoup.clean(request.getParameter("phone"), Safelist.basic());
    String messageValue = Jsoup.clean(request.getParameter("message"), Safelist.basic());
    long timestamp = System.currentTimeMillis();

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Contact");
    FullEntity contactEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", nameValue)
            .set("email", emailValue)
            .set("phone", phoneValue)
            .set("message", messageValue)
            .set("timestamp", timestamp)
            .build();
    datastore.put(contactEntity);

    // response.sendRedirect("/index.html");
  }

}