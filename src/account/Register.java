package account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.louishong.database.ProfileWrapper;

/**
 * Servlet implementation class Register
 */
@WebServlet("/account/Register")
public class Register extends HttpServlet {

	private static ProfileWrapper profileWrapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			profileWrapper = new ProfileWrapper();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");

		String defaultJob = "用户";
		int defaultPoints = 0;
		String defaultPhonenumber = "";

		class InvalidPasswordException extends Exception {
			private static final long serialVersionUID = 9189950135555433669L;
		}
		class InvalidUsernameException extends Exception {
			private static final long serialVersionUID = -3095670783787744112L;
		}
		class InvalidNicknameException extends Exception {
			private static final long serialVersionUID = 1482669809013298735L;
		}

		try {
			if (!checkUsernameValidity(username)) {
				throw new InvalidUsernameException();
			}
			if (!checkPasswordValidity(password)) {
				throw new InvalidPasswordException();
			}
			if (!checkNickNameValidity(nickname)) {
				throw new InvalidNicknameException();
			}

			profileWrapper.addUser(username, password, nickname, defaultJob, email, defaultPoints, defaultPhonenumber);
			request.setAttribute("msg", username + " 注册成功, 请登录!");
			request.setAttribute("redirect", "/gravifile/server/account/login.jsp");
			request.getRequestDispatcher("../announcement.jsp").forward(request, response);
			return;
		} catch (InvalidUsernameException e) {
			System.out.println("用户名信息不符合标准, 或者已经被用");
			request.setAttribute("msg", "用户名信息不符合标准, 或者已经被用");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} catch (InvalidPasswordException e) {
			System.out.println("密码信息不符合标准");
			request.setAttribute("msg", "密码信息不符合标准");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} catch (InvalidNicknameException e) {
			System.out.println("昵称信息不符合标准");
			request.setAttribute("msg", "昵称信息不符合标准");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Fuck, Server uncaught exception");
			e.printStackTrace();
			request.setAttribute("msg", "服务器内部错误");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
	private boolean checkPasswordValidity(String password) {
		final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{6,63}$";
		Pattern pattern;
		pattern = Pattern.compile(USERNAME_PATTERN);

		return pattern.matcher(password).matches();
	}

	private boolean checkUsernameValidity(String username) throws SQLException {
		final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
		Pattern pattern;
		pattern = Pattern.compile(USERNAME_PATTERN);

		return pattern.matcher(username).matches() && !profileWrapper.hasUser(username);
	}

	private boolean checkNickNameValidity(String nickname) throws SQLException {
		return (nickname.length() > 0) && (nickname.length() <= 16);
	}
}
