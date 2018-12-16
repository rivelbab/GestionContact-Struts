package org.lip6.struts.servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.lip6.struts.actionForm.SearchValidationForm;
import org.lip6.struts.domain.DAOContact;
import org.lip6.struts.domain.DisplayContact;


public class SearchContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {


		final SearchValidationForm lForm = (SearchValidationForm) pForm;
		
		
		final String word = lForm.getWord();

		final DAOContact daoContact = new DAOContact();
		
		final DisplayContact display = daoContact.searchContact(word);
		

		if (display.getError() == null) {

			pRequest.setAttribute("CONTACT", display.getContacts());
		
			return pMapping.findForward("success");
		} else {
			return pMapping.findForward("error");
		}
	}
}