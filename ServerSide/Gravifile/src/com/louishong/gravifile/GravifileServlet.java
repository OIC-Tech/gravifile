package com.louishong.gravifile;

import java.io.*;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

import com.louishong.database.ProfileWrapper;


@WebServlet("/")
public class GravifileServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5265128452538012292L;
	PrintWriter out;

	protected String printDocument(String firstName) throws IOException
	{
		if (firstName == null) {
			firstName = "";
		}
		//Initialize variables
		firstName = new String(firstName.getBytes("ISO-8859-1"),"UTF-8");
		String userJob = ProfileWrapper.getUserJob(firstName);
		String userPoint = ProfileWrapper.getUserPoint(firstName);
		

		//Check if userJob, userPoint first name is not empty
		if ((userJob == "") || (userJob == null))
		{
			userJob = "未找到用户姓名信息";
		}
		if ((userPoint == "") || (userPoint == null)) {
			userPoint = "未找到用户分数息";
		}
		if ((firstName == "") || (firstName == null) || !(ProfileWrapper.hasUser(firstName)))
		{
			firstName = "未找到用户";
		}
		
		//get HTML from localhost
		BufferedReader bufferedReader;
		String line = "";
		String outputString = "";

		//Read from the file
		bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://localhost/htmldoc/gravifile/gravifile.html").openStream(), "UTF-8"));
 
		while(!((line = bufferedReader.readLine()) == null))
		{
			outputString = outputString + line + "\n";
		}
		
		//Search for last name and user points

		//Output the html
		return String.format(outputString, firstName, userJob, userPoint);
		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
	{
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
		try
		{
			out.print(printDocument(request.getParameter("name")));
		} catch (IOException e)
		{
			out.print("Document Not Found!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		doGet(request, response);
	}

}
