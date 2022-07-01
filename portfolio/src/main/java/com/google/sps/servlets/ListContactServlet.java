// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

/** Servlet responsible for listing tasks. */
@WebServlet("/list-tasks")
public class ListContactServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("Contact").setOrderBy(OrderBy.desc("timestamp")).build();
    QueryResults<Entity> results = datastore.run(query);

    response.setContentType("text/html");
    response.getWriter().println("<html>");
    response.getWriter().println("<head><title>Message</title></head>");
    response.getWriter().println("<body>");

    while (results.hasNext()) {
      Entity entity = results.next();
      String name = entity.getString("name");
      String email = entity.getString("email");
      String phone = entity.getString("phone");
      String message = entity.getString("message");

      response.getWriter().println("<h3>Form: " + name + "</h3>");
      response.getWriter().println("<p>Email: " + email + "</p>");
      response.getWriter().println("<p>phone: " + phone + "</p>");
      response.getWriter().println("<h3>Message:</h3>");
      response.getWriter().println("<p>: " + message + "</p>");
    }
    
    response.getWriter().println("</body>");
    response.getWriter().println("</html>");
  }
}
