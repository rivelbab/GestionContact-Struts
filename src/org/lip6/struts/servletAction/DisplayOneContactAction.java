package org.lip6.struts.servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.lip6.struts.domain.DAOContact;
import org.lip6.struts.domain.DisplayContact;

public class DisplayOneContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping, ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {

		final DAOContact daoContact = new DAOContact();
		
		final String id = pRequest.getParameter("id");
		
		final DisplayContact display = daoContact.displayOneContact(Integer.valueOf(id));

		if (display.getError() == null) {
			
			pRequest.setAttribute("CONTACT", display.getContacts());
			
			if (!display.getContacts().get(0).getAddress().isEmpty()) {
				
				pRequest.setAttribute("ADDRESS", display.getContacts().get(0).getAddress());
			}
			
			return pMapping.findForward("success");
		} else {
			return pMapping.findForward("error");
		}
	}
}
