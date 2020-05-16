package p3.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController
{

	@RequestMapping(value = { "/", "/home" })
	public String home()
	{
		return "home";
	}

	@RequestMapping(value = "/403")
	public String Error403()
	{
		return "403";
	}

	@RequestMapping(value = { "/login" })
	public String login()
	{
		return "login";
	}

	@RequestMapping(value = "/user")
	public String user()
	{
		return "user";
	}

	@RequestMapping(value = "/admin")
	public String admin()
	{
		return "admin";
	}

	@RequestMapping(value = "/dogs")
	public String dogs()
	{
		return "dogs";
	}

	@RequestMapping(value = "/cats")
	public String cats()
	{
		return "cats";
	}

	@RequestMapping(value = "/anonymous")
	public String anonymous()
	{
		return "anonymous";
	}

	@RequestMapping(value = "/public")
	public String publicPage()
	{
		return "public";
	}

}
