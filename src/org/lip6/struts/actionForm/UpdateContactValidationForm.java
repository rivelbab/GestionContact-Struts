package org.lip6.struts.actionForm;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.lip6.struts.domain.DAOContact;
import org.lip6.struts.domain.DisplayContact;

public class UpdateContactValidationForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// Contact
	private long id = 0;
	private String lastName = null;
	private String firstName = null;
	private String email = null;

	// Address
	private String street = null;
	private String city = null;
	private String zip = null;
	private String country = null;

	/**
	 * @return ID Returns ID
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return Street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return City
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return Zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @return Country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param l
	 *            Sets the ID
	 */
	public void setId(long l) {
		id = l;
	}

	/**
	 * @param string
	 *            sets the Last Name
	 */
	public void setLastName(String string) {
		lastName = string;
	}

	/**
	 * @param string
	 *            Sets the First Name
	 */
	public void setFirstName(String string) {
		firstName = string;
	}

	/**
	 * @param string
	 *            Sets the Email
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
	 *            sets the Street
	 */
	public void setStreet(String string) {
		street = string;
	}

	/**
	 * @param string
	 *            sets the City
	 */
	public void setCity(String string) {
		city = string;
	}

	/**
	 * @param string
	 *            sets the Zip code
	 */
	public void setZip(String string) {
		zip = string;
	}

	/**
	 * @param string
	 *            sets the Country
	 */
	public void setCountry(String string) {
		country = string;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		DAOContact daoContact = new DAOContact();

		final DisplayContact display = daoContact.displayOneContact(Integer.valueOf(request.getParameter("id")));

		/* On pre-remplie le formulaire d'update */

		// Contact
		this.id = display.getContacts().get(0).getId();
		this.lastName = display.getContacts().get(0).getLastName();
		this.firstName = display.getContacts().get(0).getFirstName();
		this.email = display.getContacts().get(0).getEmail();


		// Address
		if (display.getContacts().get(0).getAddress().isEmpty()) {
			this.street = null;
			this.city = null;
			this.zip = null;
			this.country = null;
		} else {
			this.street = display.getContacts().get(0).getAddress().get(0).getStreet();
			this.city = display.getContacts().get(0).getAddress().get(0).getCity();
			this.zip = display.getContacts().get(0).getAddress().get(0).getZip();
			this.country = display.getContacts().get(0).getAddress().get(0).getCountry();
		}
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		System.out.println("Entre dans validation form update contact");

		// Contact
		if (getId() < 1) {
			errors.add("id", new ActionMessage("creation.id.error.required"));
		}

		// matches permet de voir si notre string contient des nombres
		if (getFirstName().trim() == null || getFirstName().trim().length() < 1 || getFirstName().matches(".*\\d.*")) {
			errors.add("first name", new ActionMessage("creation.firstname.error.required"));
		}
		if (getLastName().trim() == null || getLastName().trim().length() < 1 || getLastName().matches(".*\\d.*")) {
			errors.add("last name", new ActionMessage("creation.lastname.error.required"));
		}

		// Pour le matches on regarde que l'email est bien de la forme
		// string@string.string et on invalide toutes les autres formes
		if (getEmail().trim() == null || getEmail().trim().length() < 1 || !getEmail().matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			errors.add("email", new ActionMessage("creation.email.error.required"));
		}

		// Address
		if (!getStreet().trim().isEmpty() || !getCity().trim().isEmpty() || !getZip().trim().isEmpty()
				|| !getCountry().trim().isEmpty()) {

			if (getStreet().trim() == null || getStreet().trim().length() < 1) {
				errors.add("street", new ActionMessage("creation.street.error.required"));
			}
			if (getCity().trim() == null || getCity().trim().length() < 1) {
				errors.add("city", new ActionMessage("creation.city.error.required"));
			}
			if (getZip().trim() == null || getZip().trim().length() < 1) {
				errors.add("zip", new ActionMessage("creation.zip.error.required"));
			}
			if (getCountry().trim() == null || getCountry().trim().length() < 1) {
				errors.add("country", new ActionMessage("creation.country.error.required"));
			}
		}

		return errors;
	}
}
