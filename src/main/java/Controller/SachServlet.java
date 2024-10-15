package Controller;

import Entity.Sach;
import Service.SachRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ehhehehe",value = {"/sach/hien-thi","/sach/detail","/sach/remove","/sach/search"})
public class SachServlet extends HttpServlet {
    private List<Sach> list = new ArrayList<>();
    private SachRepository repo = new SachRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String ma = req.getParameter("ma");
        if (uri.contains("hien-thi")){
            list = repo.getAll();
            req.setAttribute("list",list);
            req.getRequestDispatcher("/view/hien-thi.jsp").forward(req,resp);
        }else if (uri.contains("/detail")){
            Sach s = repo.getOne(ma);
            req.setAttribute("s",s);
            list = repo.getAll();
            req.setAttribute("list",list);
            req.getRequestDispatcher("/view/hien-thi.jsp").forward(req,resp);
        }else if (uri.contains("/remove")){
            repo.delete(ma);
            list = repo.getAll();
            resp.sendRedirect("/sach/hien-thi");
        }
        else if (uri.contains("/search")){
            String maSach = req.getParameter("searchQuery");
            List<Sach> timSach = repo.timkiem(maSach);
            list = timSach;
            req.setAttribute("list",list);
            req.getRequestDispatcher("/view/hien-thi.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("update")){
            String ma = req.getParameter("ma");
            String ten = req.getParameter("ten");
            int soTrang = Integer.valueOf(req.getParameter("soTrang"));
            int donGia = Integer.valueOf(req.getParameter("donGia"));
            Sach s = repo.update(ma,new Sach(ma,ten,soTrang,donGia));
            if (s!=null){
                s.setMa(ma);
                s.setTen(ten);
                s.setSoTrang(soTrang);
                s.setDonGia(donGia);
            }
            list.clear();
            list = repo.getAll();
            req.setAttribute("list",list);
            req.getRequestDispatcher("/view/hien-thi.jsp").forward(req,resp);
            req.setAttribute("s",s);
        }
    }
}
