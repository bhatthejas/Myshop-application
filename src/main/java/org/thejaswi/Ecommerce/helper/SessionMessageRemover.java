package org.thejaswi.Ecommerce.helper;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionMessageRemover {
	public void removeMessage() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();
		session.removeAttribute("successMessage");
		session.removeAttribute("failMessage");
	}
}
