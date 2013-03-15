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
	String httpMethod = "GET";
	PrintWriter out;

	protected String printDocument(String firstName) throws IOException
	{
		//Initialize variables
		String lastName = null;
		String userPoint = null;
		
		//Check if firstName parameter is empty or not
		if ((firstName == "") || (firstName == null) || !(ProfileWrapper.hasUser(firstName)))
		{
			
			firstName = "未找到用户";
		}
		
		//get HTML from localhost
		BufferedReader bufferedReader;
		String line = null;
		String outputString = "";

		//Read from the file
		bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://localhost/htmldoc/gravifile/gravifile.html").openStream()));
 
		while(!((line = bufferedReader.readLine()) == null))
		{
			outputString = outputString + line + "\n";
		}
		
		//Search for last name and user points
		lastName = ProfileWrapper.getLastName(firstName);
		userPoint = ProfileWrapper.getUserPoint(firstName);
		
		//Check if lastName and userPoint is not empty
		if ((lastName == "") || (lastName == null))
		{
			lastName = "未找到用户姓名信息";
		}
		if ((userPoint == "") || (userPoint == null)) {
			userPoint = "未找到用户分数息";
		}

		//Output the html
		return String.format(outputString, firstName, lastName, userPoint);
		
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
		
		PrintWriter out = null;
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			out.print(printDocument(request.getParameter("firstName")));
		} catch (IOException e)
		{
			out.print("Document Not Found!");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
	{
		httpMethod = "POST";
		doGet(request, response);
		httpMethod = "GET";
	}

}
