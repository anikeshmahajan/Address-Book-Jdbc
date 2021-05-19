package com.bridgelabz.addressBook;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;

import com.bridgelabz.addressBook.Exception.AddressBookException;
import com.bridgelabz.addressBook.Models.Contacts;

public class AddressBookTest {
	
	AddressBookMain addressBookFunction;
	List<Contacts> record;

	@Before
	public void init() {
		addressBookFunction=new AddressBookMain();
		record=addressBookFunction.readContactData();
	}
	
	@Test
	public void givenAddressBook_WhenRetrieved_ShouldMatchCount() {
		assertEquals(2, record.size());
	}
	
	@Test
	public void givenNewAddressForRecord_WhenUpdated_ShouldSyncWithDatabase() throws AddressBookException {
		addressBookFunction.updateRecord("Anikesh","Jammu");
		assertTrue(addressBookFunction.checkAddressBookInSyncWithDB("Anikesh"));
	}
	
	@Test
	public void givenDateRangeForRecord_WhenRetrieved_ShouldReturnProperData() throws AddressBookException {
		List<Contacts> recordDataInGivenDateRange = addressBookFunction.getRecordAddedInDateRange("2018-01-01","2021-05-20");
		assertEquals(2, recordDataInGivenDateRange.size());
	}
	
	@Test
	public void givenCityOrState_WhenRetrieved_ShouldReturnProperData() throws AddressBookException {
		List<Contacts> recordDataByCityState = addressBookFunction.getRecordsByCityOrState("Akhnoor", "J&K");
		assertEquals(2, recordDataByCityState.size());
	}
	
	@Test
	public void givenNewContact_WhenAdded_ShouldSyncWithDB() throws AddressBookException {
		addressBookFunction.addContactToRecord("Avni", "Prabha", "Mall Road",
				"Gurugram", "Haryana", 12345, "8456211111", "av.prabha@gmail.com");
		assertTrue(addressBookFunction.checkAddressBookInSyncWithDB("Avni"));
	}
}