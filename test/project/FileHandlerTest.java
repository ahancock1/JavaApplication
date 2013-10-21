package project;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.FileHandler;

public class FileHandlerTest {
	
	private FileHandler fileHandle;
	
	// Temporary file
	private File personFile;
	private File orderFile;
	
	@Before
	public void setUp() throws Exception {
		// Create FileHandle instance
		fileHandle = new FileHandler();
		
		// Create a temporary test person data file
		personFile = File.createTempFile("person", ".data", new File(System.getProperty("user.dir") + "/"));
		FileWriter writer = new FileWriter(personFile);
		writer.write("Person ID,First Name,Last Name,Street,City" 	+ System.getProperty("line.separator"));
		writer.write("1,Ola,Hansen,Timoteivn,Sandnes" 				+ System.getProperty("line.separator"));
		writer.write(",Tove,Svendson,Borgvn,Stavanger" 				+ System.getProperty("line.separator"));
		writer.write("4,Tove,Borgvn,Stavanger" 						+ System.getProperty("line.separator"));
		writer.write("3,  Kari, Pettersen, Storgt,Stavanger" 		+ System.getProperty("line.separator"));
		writer.write("5,  Kari, Pettersen, Storgt,Stavanger,etc" 	+ System.getProperty("line.separator"));
		writer.write("5,, Pettersen, Storgt,Stavanger" 				+ System.getProperty("line.separator"));			
		writer.close();

		// Create a temporary test order data file
		orderFile = File.createTempFile("order", ".data", new File(System.getProperty("user.dir") + "/"));
		writer = new FileWriter(orderFile);
		writer.write("Order ID|Order Number|Person ID" 	+ System.getProperty("line.separator"));
		writer.write("|2000" 							+ System.getProperty("line.separator"));
		writer.write("11|2001|2" 						+ System.getProperty("line.separator"));
		writer.write("|2003|10|etx" 					+ System.getProperty("line.separator"));		
		writer.close();
	}
	
	@After
	public void tearDown() throws Exception {
		personFile.delete();
		orderFile.delete();
	}

	@Test
	public void testRead() {
		
		/*
		 * Test Person.data
		 */		
		ArrayList<String[]> result = fileHandle.read(personFile.getName());
				
		assertEquals(3, result.size());
		
		assertEquals("1", result.get(0)[0]);
		assertEquals("OLA", result.get(0)[1]);
		assertEquals("HANSEN", result.get(0)[2]);
		assertEquals("TIMOTEIVN", result.get(0)[3]);
		assertEquals("SANDNES", result.get(0)[4]);
		
		assertEquals("3", result.get(1)[0]);
		assertEquals("KARI", result.get(1)[1]);
		assertEquals("PETTERSEN", result.get(1)[2]);
		assertEquals("STORGT", result.get(1)[3]);
		assertEquals("STAVANGER", result.get(1)[4]);

		assertEquals("5", result.get(2)[0]);
		assertEquals("", result.get(2)[1]);
		assertEquals("PETTERSEN", result.get(2)[2]);
		assertEquals("STORGT", result.get(2)[3]);
		assertEquals("STAVANGER", result.get(2)[4]);
	
		
		assertFalse(result.isEmpty());
		
		/*
		 * Test Order.data
		 */
		result = fileHandle.read(orderFile.getName());
		
		assertEquals(1, result.size());
				
		assertEquals("11", result.get(0)[0]);
		assertEquals("2001", result.get(0)[1]);
		assertEquals("2", result.get(0)[2]);
		
		assertFalse(result.isEmpty());
	}

}
