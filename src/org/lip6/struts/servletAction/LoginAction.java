package org.lip6.struts.servletAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.lip6.struts.actionForm.LoginValidationForm;

public class LoginAction extends Action {

	public ActionForward execute(final ActionMapping pMapping,
			ActionForm pForm, final HttpServletRequest pRequest, final HttpServletResponse pResponse) {
		
		final LoginValidationForm lForm=(LoginValidationForm)pForm;
		final String login = lForm.getLogin();	
		final String password = lForm.getPassword();

		//======= si le login est egal au mot de passe =======
		if(login.equals(password)) {
			return pMapping.findForward("success");
		} else {
			return pMapping.findForward("error");
		}

	}
}