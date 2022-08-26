package com.code.seller;

import java.io.File; 
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.db.conn.ConnectionProvider;

/**
 * Servlet implementation class AddProducts 
 */
@WebServlet("/AddProducts")
public class AddProducts extends HttpServlet {
	static Connection con;
	final String UPLOAD_DIRECTORY = "D:/Sem4/Sem4workspace/OnlineAuctionR1/WebContent/products/";
	static int i = 0;
	
	public void init(ServletConfig config) throws ServletException 
	{
		try 
		{
			con=ConnectionProvider.getConnection();
		} 
		catch (Exception e) 
		{
			System.out.println("Exception "+e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) 
		{
			try 
			{
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				System.out.println("ABCD");
				String FileName = "";
				String FileExtention = "";
				long FileSize = 0;

				for (FileItem item1 : multiparts)
				{
					if (!item1.isFormField()) 
					{

						System.out.println("4");
						String name = new File(item1.getName()).getName();
						item1.write(new File(UPLOAD_DIRECTORY + File.separator+name));
						FileName = item1.getName();
						FileExtention = item1.getContentType();
						FileSize = item1.getSize();
					}
				}

				String category	="",p_name="",specification="",base_price="",bid_sdate_time="",bid_ldate_time="";
				int base_price1=0;
				
				for (FileItem item : multiparts)
				{
					if ((item.getFieldName()).equals("category"))
					{
						category = (String) item.getString();
					}
				}


				for (FileItem item : multiparts)
				{
					if ((item.getFieldName()).equals("base_price"))
					{
						base_price = (String) item.getString();
					}
				}

				for (FileItem item : multiparts)
				{
					if ((item.getFieldName()).equals("p_name")) 
					{
						p_name = (String) item.getString();
					}
				}

				for (FileItem item : multiparts)
				{
					if ((item.getFieldName()).equals("specification")) 
					{
						specification = (String) item.getString();
					}
				}

				for (FileItem item : multiparts)
				{
					if ((item.getFieldName()).equals("bid_sdate_time")) 
					{
						bid_sdate_time = (String) item.getString();
					}
				}
				
				for (FileItem item : multiparts)
				{
					if ((item.getFieldName()).equals("bid_ldate_time")) 
					{
						bid_ldate_time = (String) item.getString();
					}
				}
				
				
				System.out.println("FileName " + FileName);
				System.out.println("File Extension " + FileExtention);
				System.out.println("File Size " + FileSize);

				HttpSession session=request.getSession();
				String email_id=session.getAttribute("email").toString();
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date dateobj = new Date();
				String c_date = df.format(dateobj);
				System.out.println("C Date " + c_date);

				base_price1=Integer.parseInt(base_price);
				
				try 
				{

					PreparedStatement ps1 = con.prepareStatement("INSERT INTO `product_details`(`category`, `p_name`, `specification`, `file_name`, `base_price`, `bid_sdate_time`, `bid_ldate_time`,`upload_by`) VALUES ('"+category+"','"+p_name+"','"+specification+"','"+FileName+"','"+base_price1+"','"+bid_sdate_time+"','"+bid_ldate_time+"','"+email_id+"')");
					int rs = ps1.executeUpdate();
					if (rs > 0) 
					{
						System.out.println("Event Add Done ");
						response.sendRedirect("sellerUploadProduct.jsp?add=product");
					} else 
					{
						System.out.println("Event Not Insert Something Wrong ");
						response.sendRedirect("sellerUploadProduct.jsp?fail=upload");
					}
				} 
				catch (Exception e) 
				{
					System.out.println("Exception e" +e);
				}
			}
			catch (Exception ex) {
				System.out.println("Exception e" +ex);
			}
		}
		else 
		{
			System.out.println("Condition False");
			response.sendRedirect("sellerUploadProduct.jsp?fail=uplaod");
		}
	}
}