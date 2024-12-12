package com.jack.sevlet;

import com.jack.conn.HibernateUtil;
import com.jack.dao.StudentDao;
import com.jack.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("Name");
        String rollno=req.getParameter("rollno");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String department=req.getParameter("department");
        Student st= new Student(rollno,name,email,password,department);
        System.out.println(st);

        // saving into
        StudentDao studentDao= new StudentDao(HibernateUtil.getSessionfactory());
       boolean b= studentDao.saveStudent(st);
        HttpSession httpSession= req.getSession();

       if(b){
           httpSession.setAttribute("msg","registered");
           System.out.println("successfully registered");

       }
       else{
           httpSession.setAttribute("msg","not registered");
           System.out.println("something wrong on server!");
       }
       resp.sendRedirect("index.jsp");


    }
}
