package murach.survey;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import murach.business.User;
import murach.business.Survey;
import murach.data.UserDB;
import murach.data.SurveyDB;

@WebServlet("/survey")
public class SurveyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = "/index.html";

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        if (action.equals("join")) {
            url = "/index.html";
        } else if (action.equals("add")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String dateOfBirth = request.getParameter("date_of_birth");

            String hearAbout = request.getParameter("hear_about");
            String wantsUpdates = request.getParameter("wants_updates");
            String wantsAnnouncements = request.getParameter("wants_announcements");
            String contact = request.getParameter("contact");

            // Chuẩn hóa hearAbout
            if ("search_engine".equals(hearAbout)) {
                hearAbout = "Search engine";
            } else if ("word".equals(hearAbout)) {
                hearAbout = "Word of mouth";
            } else if ("social".equals(hearAbout)) {
                hearAbout = "Social Media";
            } else if ("other".equals(hearAbout)) {
                hearAbout = "Other";
            }

            // Chuẩn hóa contact (dùng Dropdown Select)
            if ("or".equals(contact)) {
                contact = "Email or postal mail";
            } else if ("email".equals(contact)) {
                contact = "Email only";
            } else if ("postal".equals(contact)) {
                contact = "Postal mail only";
            }

            // Xử lý null cho Checkbox
            String updatesValue = (wantsUpdates != null) ? wantsUpdates : "No";
            String announcementsValue = (wantsAnnouncements != null) ? wantsAnnouncements : "No";

            User user = new User(firstName, lastName, email, dateOfBirth);
            Survey survey = new Survey(hearAbout, updatesValue, announcementsValue, contact);

            try {
                UserDB.insert(user);
                SurveyDB.insert(user.getEmail(), survey);
            } catch (SQLException e) {
                throw new ServletException(e);
            }

            request.setAttribute("user", user);
            request.setAttribute("survey", survey);
            url = "/thanks.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}