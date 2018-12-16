package org.lip6.struts.servletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.lip6.struts.actionForm.AddContactValidationForm;
import org.lip6.struts.domain.DAOContact;
import org.lip6.struts.domain.DisplayContact;

public class AddContactAction extends Action {

	public ActionForward execute(final ActionMapping pMapping,
			ActionForm pForm, final HttpServletRequest pRequest,
			final HttpServletResponse pResponse) {
		
		final AddContactValidationForm lForm = (AddContactValidationForm) pForm;
		
		final long id = lForm.getId();
		final String firstName = lForm.getFirstName();
		final String lastName = lForm.getLastName();
		final String email = lForm.getEmail();
		
		final String street = lForm.getStreet();
		final String city = lForm.getCity();
		final String zip = lForm.getZip();
		final String country = lForm.getCountry();
		
		// on cree un nouveau contact
		final DAOContact lDAOContact = new DAOContact();
		// puis on ajoute dans la base 
		final String lError = lDAOContact.addContact(id, firstName, lastName, email, id, street, city, zip, country);
		
		final DisplayContact display = lDAOContact.displayContact();
		if(lError == null) {
			// if no exception is raised, forward "success"
			pRequest.setAttribute("LISTECONTACTS", display.getContacts());
			return pMapping.findForward("success");
		}
		else {
			// If any exception, return the "error" forward
			return pMapping.findForward("error");
		}
	}

}
