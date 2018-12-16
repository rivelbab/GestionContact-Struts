package org.lip6.struts.actionForm;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginValidationForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String login = null;
	private String password = null;

	/**
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.login = null;
		this.password= null;
	}

	public ActionErrors validate( ActionMapping mapping, HttpServletRequest request ) {

		ActionErrors errors = new ActionErrors();
		
		if( getLogin()== null || getPassword() == null ) {
			errors.add("login",new
					ActionMessage("login.fn.error.required"));
		}
		return errors;
	}
}