package org.lip6.struts.servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.lip6.struts.domain.DAOContact;
import org.lip6.struts.domain.DisplayContact;

public class DeleteContactAction extends DispatchAction {
	
	public ActionForward delete(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {

		final String id = pRequest.getParameter("id");

		final DAOContact daoContact = new DAOContact();

		final String lError = daoContact.deleteContact(Integer.valueOf(id));
		final DisplayContact display = daoContact.displayContact();

		if (display.getError() == null && lError == null) {

			pRequest.setAttribute("LISTECONTACTS", display.getContacts());
			return pMapping.findForward("success");
		} else {			
			return pMapping.findForward("error");
		}
	}
}
