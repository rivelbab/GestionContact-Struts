package org.lip6.struts.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAOContact {

	private final static String RESOURCE_JDBC = "java:comp/env/jdbc/ContactBD";

	//============= creation ================
	public String addContact(final long id, final String firstName, final String lastName, final String email,
			final long idAddress, final String street, final String city, final String zip, final String country) {

		try {

			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			final Connection lConnection = lDataSource.getConnection();

			//============ verification doublon ==========

			// On regarde si la combinaison email/nom existe
			final PreparedStatement lPreparedStatementContact =
					lConnection.prepareStatement("SELECT idContact FROM Contact WHERE lastName = ? AND email = ?");

			lPreparedStatementContact.setString(1, lastName);
			lPreparedStatementContact.setString(2, email);

			ResultSet rsContact = lPreparedStatementContact.executeQuery();
			if (rsContact.next()) {
				return "La combinaison email/nom existe deja !";
			}

			// On regarde si le email existe
			final PreparedStatement lPreparedStatementEmail = 
					lConnection.prepareStatement("SELECT idContact FROM Contact WHERE email = ?");

			lPreparedStatementEmail.setString(1, email);
			ResultSet rsEmail = lPreparedStatementEmail.executeQuery();
			if (rsEmail.next()) {
				return "L'email existe deja !";
			}

			// adding a new contact
			final PreparedStatement lPreparedStatementCreation = 
					lConnection.prepareStatement("INSERT INTO Contact(idContact, firstName, lastName, email) VALUES(?, ?, ?, ?)");

			lPreparedStatementCreation.setLong(1, id);
			lPreparedStatementCreation.setString(2, firstName);
			lPreparedStatementCreation.setString(3, lastName);
			lPreparedStatementCreation.setString(4, email);
			lPreparedStatementCreation.executeUpdate();

			//======= address ==========
			if (!street.isEmpty()) {
				PreparedStatement lPreparedStatementAddressCreation =
						lConnection.prepareStatement(
								"INSERT INTO Address(idAddress, street, city, zip, country) VALUES(?, ?, ?, ?, ?)");

				lPreparedStatementAddressCreation.setLong(1, idAddress);
				lPreparedStatementAddressCreation.setString(2, street);
				lPreparedStatementAddressCreation.setString(3, city);
				lPreparedStatementAddressCreation.setString(4, zip);
				lPreparedStatementAddressCreation.setString(5, country);
				lPreparedStatementAddressCreation.executeUpdate();
			}			

			return null;
		} catch (NamingException e) {

			return "NamingException : " + e.getMessage();

		} catch (SQLException e) {

			return "SQLException : " + e.getMessage();
		}
	}

	//================ affichage  de tous contacts ==================
	public DisplayContact displayContact() {

		final DisplayContact display = new DisplayContact();

		try {

			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			final Connection lConnection = lDataSource.getConnection();

			final List<Contact> contacts = new LinkedList<Contact>();

			final PreparedStatement lPreparedStatementContact = 
					lConnection.prepareStatement("SELECT idContact, firstName, lastName, email FROM Contact");

			ResultSet rsContact = lPreparedStatementContact.executeQuery();

			while (rsContact.next()) {
				final Long id = rsContact.getLong("idContact");
				final String lastName = rsContact.getString("lastName");
				final String firstName = rsContact.getString("firstName");
				final String email = rsContact.getString("email");

				contacts.add(new Contact(id, lastName, firstName, email, null, null));
			}

			display.setContacts(contacts);

		} catch (NamingException e) {

			System.out.println(e.getMessage());
			display.setError("NamingException : " + e.getMessage());

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			display.setError("SQLException : " + e.getMessage());

		}

		return display;
	}

	//=========== affichage d'un seul contact ==============
	public DisplayContact displayOneContact(int idContact) {

		final DisplayContact display = new DisplayContact();

		try {
			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			Connection lConnection = lDataSource.getConnection();

			final List<Contact> contacts = new LinkedList<Contact>();

			final PreparedStatement lPreparedStatementContact =
					lConnection.prepareStatement("SELECT idContact, lastName, firstName, email FROM Contact WHERE idContact=?");

			lPreparedStatementContact.setLong(1, idContact);
			ResultSet rsContact = lPreparedStatementContact.executeQuery();

			while (rsContact.next()) {
				final int id = rsContact.getInt("idContact");
				final String lastName = rsContact.getString("lastName");
				final String firstName = rsContact.getString("firstName");
				final String email = rsContact.getString("email");	

				//========== Adress ===========
				final List<Address> address = new LinkedList<Address>();

				final PreparedStatement lPreparedStatementAddress = 
						lConnection.prepareStatement("SELECT street, city, zip, country FROM Address WHERE idAddress=?");

				lPreparedStatementAddress.setLong(1, idContact);
				ResultSet rsAddress = lPreparedStatementAddress.executeQuery();

				while (rsAddress.next()) {

					final String street = rsAddress.getString("street");
					final String city = rsAddress.getString("city");
					final String zip = rsAddress.getString("zip");
					final String country = rsAddress.getString("country");

					address.add(new Address(id, street, city, zip, country));
				}
				
				
				/*final List<PhoneNumber> phones = new LinkedList<PhoneNumber>();

				final PreparedStatement lPreparedStatementPhone = lConnection.prepareStatement(
						"SELECT idPhone, phoneKind, phoneNumber FROM PhoneNumber WHERE idContact = ?");

				lPreparedStatementPhone.setInt(1, id);

				ResultSet rsPhone = lPreparedStatementPhone.executeQuery();

				while (rsPhone.next()) {
					final int idPhone = rsPhone.getInt("idPhone");
					final String phoneKind = rsPhone.getString("phoneKind");
					final String phoneNumber = rsPhone.getString("phoneNumber");

					phones.add(new PhoneNumber(idPhone, id, phoneKind, phoneNumber, null));
				}*/

				contacts.add(new Contact(id, firstName, lastName, email, address, null));
			}

			display.setContacts(contacts);

		} catch (NamingException e) {

			System.out.println(e.getMessage());
			display.setError("NamingException : " + e.getMessage());

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			display.setError("SQLException : " + e.getMessage());

		} 
		return display;
	}


	//============ suppression ===============
	public String deleteContact(long id) {

		try {
			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			final Connection lConnection = lDataSource.getConnection();

			PreparedStatement lPreparedStatementDeletion =
					lConnection.prepareStatement("DELETE FROM Contact WHERE idContact=?");

			lPreparedStatementDeletion.setLong(1, id);
			lPreparedStatementDeletion.executeUpdate();

			PreparedStatement lPreparedStatementDeletionAddress = lConnection
					.prepareStatement("DELETE FROM Address WHERE idAddress=?");

			lPreparedStatementDeletionAddress.setLong(1, id);
			lPreparedStatementDeletionAddress.executeUpdate();

			/*PreparedStatement lPreparedStatementDeletionPhone = lConnection
					.prepareStatement("DELETE FROM PhoneNumber WHERE idContact=?");

			lPreparedStatementDeletionPhone.setLong(1, id);
			lPreparedStatementDeletionPhone.executeUpdate();*/

			return null;

		} catch (NamingException e) {

			return "NamingException : " + e.getMessage();

		} catch (SQLException e) {

			return "SQLException : " + e.getMessage();

		} 
	}

	//============ update ==============
	public String updateContact(final long id, final String lastName, final String firstName, final String email,
			final String street, final String city, final String zip, final String country) {

		Connection lConnection = null;

		try {
			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			lConnection = lDataSource.getConnection();

			// On regarde si la combinaison email/nom existe
			final PreparedStatement lPreparedStatementContact =

					lConnection.prepareStatement("SELECT idContact FROM Contact WHERE lastName = ? AND email = ?");

			lPreparedStatementContact.setString(1, lastName);
			lPreparedStatementContact.setString(2, email);

			ResultSet rsContact = lPreparedStatementContact.executeQuery();

			if (rsContact.next()) {
				//On regarde si le contact a mettre a jour est bon (pour ne pas avoir d'erreur du type email/nom existe deja)
				final int idContact = rsContact.getInt("idContact");
				if(idContact != id) {
					return "La combinaison email/nom existe deja !";
				}
			}

			// On regarde si le email existe
			final PreparedStatement lPreparedStatementEmail = 
					lConnection.prepareStatement("SELECT idContact FROM Contact WHERE email = ?");

			lPreparedStatementEmail.setString(1, email);
			ResultSet rsEmail = lPreparedStatementEmail.executeQuery();
			if (rsEmail.next()) {

				final int idContact = rsEmail.getInt("idContact");
				if(idContact != id) {
					return "L'email existe deja !";
				}
			}

			// si tout est ok, on lance la mise a jour 
			PreparedStatement lPreparedStatementUpdate =
					lConnection.prepareStatement("UPDATE Contact SET lastName=?, firstName=?, email=? WHERE idContact=?");

			lPreparedStatementUpdate.setString(1, lastName);
			lPreparedStatementUpdate.setString(2, firstName);
			lPreparedStatementUpdate.setString(3, email);
			lPreparedStatementUpdate.setLong(4, id);
			lPreparedStatementUpdate.executeUpdate();

			PreparedStatement lPreparedStatementAddressExist =
					lConnection.prepareStatement("SELECT * FROM Address WHERE idAddress=?");

			lPreparedStatementAddressExist.setLong(1, id);
			ResultSet rsAddress = lPreparedStatementAddressExist.executeQuery();

			if (!street.isEmpty() && rsAddress.next()) {
				PreparedStatement lPreparedStatementUpdateAddress =lConnection.prepareStatement(
								"UPDATE Address SET idAddress=?, street=?, city=?, zip=?, country=? WHERE idAddress=?");

				lPreparedStatementUpdateAddress.setLong(1, id);
				lPreparedStatementUpdateAddress.setString(2, street);
				lPreparedStatementUpdateAddress.setString(3, city);
				lPreparedStatementUpdateAddress.setString(4, zip);
				lPreparedStatementUpdateAddress.setString(5, country);
				lPreparedStatementUpdateAddress.setLong(6, id);
				lPreparedStatementUpdateAddress.executeUpdate();
				
			} else if (!street.isEmpty() && !rsAddress.next()) {
				
				PreparedStatement lPreparedStatementUpdateAddress =	lConnection.prepareStatement(
								"INSERT INTO Address(idAddress, street, city, zip, country) VALUES(?, ?, ?, ?, ?)");

				lPreparedStatementUpdateAddress.setLong(1, id);
				lPreparedStatementUpdateAddress.setString(2, street);
				lPreparedStatementUpdateAddress.setString(3, city);
				lPreparedStatementUpdateAddress.setString(4, zip);
				lPreparedStatementUpdateAddress.setString(5, country);
				lPreparedStatementUpdateAddress.executeUpdate();
				
			} else {
				
				PreparedStatement lPreparedStatementDeletionAddress = lConnection
						.prepareStatement("DELETE FROM Address WHERE idAddress=?");

				lPreparedStatementDeletionAddress.setLong(1, id);
				lPreparedStatementDeletionAddress.executeUpdate();
			}

			return null;

		} catch (NamingException e) {

			return "NamingException : " + e.getMessage();

		} catch (SQLException e) {

			return "SQLException : " + e.getMessage();

		} 
	}


	//==================== recherche de contact par nom, prenom, email ou id =============

	public DisplayContact searchContact(String word) {


		final DisplayContact display = new DisplayContact();

		try {
			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			Connection lConnection = lDataSource.getConnection();

			final List<Contact> contacts = new LinkedList<Contact>();

			//=== on prends quelques mesure de securite ======
			word = word.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");

			final PreparedStatement lPreparedStatementContact = lConnection.prepareStatement(
					"SELECT idContact, lastName, firstName, email FROM Contact WHERE idContact LIKE ? OR lastName LIKE ? OR firstName LIKE ? OR email LIKE ?");

			lPreparedStatementContact.setString(1, "%" + word + "%");
			lPreparedStatementContact.setString(2, "%" + word + "%");
			lPreparedStatementContact.setString(3, "%" + word + "%");
			lPreparedStatementContact.setString(4, "%" + word + "%");
			ResultSet rsContact = lPreparedStatementContact.executeQuery();

			while (rsContact.next()) {

				final Long id = rsContact.getLong("idContact");
				final String lastName = rsContact.getString("lastName");
				final String firstName = rsContact.getString("firstName");
				final String email = rsContact.getString("email");

				contacts.add(new Contact(id, lastName, firstName, email, null, null));
			}

			display.setContacts(contacts);

		} catch (NamingException e) {

			System.out.println(e.getMessage());
			display.setError("NamingException : " + e.getMessage());

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			display.setError("SQLException : " + e.getMessage());

		} 

		return display;
	}

}
