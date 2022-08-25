package user_controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.PropertyConst;
import models.User;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUser")
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor s
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            User u = new User();

            String name = request.getParameter("name");
            u.setName(name);

            String code = request.getParameter("code");
            u.setCode(code);

            String password =request.getParameter("password");
            u.setPassword(password);

            //アプリケーションスコープからpepper文字列を取得
            String pepper = (String)request.getServletContext().getAttribute(PropertyConst.PEPPER.getValue());
            String pass=EncryptUtil.getPasswordEncrypt(u.getPassword(), pepper);
            u.setPassword(pass);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            u.setCreated_at(currentTime);




            em.persist(u);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/login");


        }
    }


    }


