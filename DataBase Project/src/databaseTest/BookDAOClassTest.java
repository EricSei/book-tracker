package databaseTest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookDAOClassTest {

	BookDAOClass test;
	@BeforeAll
	static void initial() throws Exception {
		BookDAOClass test = new BookDAOClass();
	}
	
	@AfterAll
	static void end() throws Exception {
		BookDAOClass test = null;
	}
	
	@BeforeEach
	void setUp() throws Exception {
		 test = new BookDAOClass();
	}

	@AfterEach
	void tearDown() throws Exception {
		test = null;
	}


	@Test
	void testGetAllBooks() {
		List<Book> bookList = new ArrayList<Book>(50);
		for(int i = 1; i < 51; i++) {
			bookList.add(test.getBookbyId(i));
		}
		assertTrue((test.getAllBooks().equals(bookList)));
	}

	@Test
	void testGetBookbyId() {
		Book testBook = new Book(3, "The Traitor Baru Cormorant", "Seth Dickinson", "TOR", 399, "Fantasy", 1, 1, true, null, "https://images-na.ssl-images-amazon.com/images/I/71BpO2pC-KL.jpg", "The Empire of Masks is coming, armed with coin and ink, doctrine and compass, soap and lies. They\'ll conquer Baru’s island, rewrite her culture, criminalize her customs, and dispose of one of her fathers. But Baru is patient. She\'ll swallow her hate, prove her talent, and join the Masquerade. She will learn the secrets of empire. She’ll be exactly what they need. And she\'ll claw her way high enough up the rungs of power to set her people free.");
	
		
		assertTrue(test.getBookbyId(3).equals(testBook) );
	}
	
	@Test
	void testGetDBSize() {
		List<Book> bookList = new ArrayList<Book>(50);
		for(int i = 1; i < 51; i++) {
			bookList.add(test.getBookbyId(i));
		}
		System.out.println(test.getDBSize() + " "+ bookList.get(bookList.size()-1).getBookId());
		assertTrue(test.getDBSize() == bookList.get(bookList.size()-1).getBookId());
	}

	@Test
	void testGetBookbyTitle() {
		Book testBook = new Book(3, "The Traitor Baru Cormorant", "Seth Dickinson", "TOR", 399, "Fantasy", 1, 1, true, null, "https://images-na.ssl-images-amazon.com/images/I/71BpO2pC-KL.jpg", "The Empire of Masks is coming, armed with coin and ink, doctrine and compass, soap and lies. They\'ll conquer Baru’s island, rewrite her culture, criminalize her customs, and dispose of one of her fathers. But Baru is patient. She\'ll swallow her hate, prove her talent, and join the Masquerade. She will learn the secrets of empire. She’ll be exactly what they need. And she\'ll claw her way high enough up the rungs of power to set her people free.");

		assertTrue(test.getBookbyTitle("The Traitor Baru Cormorant").equals(testBook));
	}

	@Test
	void testGetBookbyAuthor() {
		List<Book> bookList = new ArrayList<Book>(50);
		for(int i = 1; i < 51; i++) {
			Book temp = test.getBookbyId(i);
			if(temp.getAuthor().equals("Brandon Sanderson")) {
				bookList.add(temp);
			}
		}
		assertTrue(bookList.equals(test.getBookbyAuthor("Brandon Sanderson")));
	}

	@Test
	void testAddBook() {
		Book temp = new Book(0, "Elantris", "Brandon Sanderson", "TOR", 622, "Fantasy", null, 1, true, 1, "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1475740953i/68427.jpg", "Elantris was the capital of Arelon: gigantic, beautiful, literally radiant, filled with benevolent beings who used their powerful magical abilities for the benefit of all. Yet each of these demigods was once an ordinary person until touched by the mysterious transforming power of the Shaod. Ten years ago, without warning, the magic failed. Elantrians became wizened, leper-like, powerless creatures, and Elantris itself dark, filthy, and crumbling.");
		test.addBook(temp);
		temp.setBookId(test.getBookbyTitle("Elantris").getBookId());
		assertTrue(test.getBookbyId(test.getDBSize()).equals(temp));
		test.deleteBookbyTitle("Elantris");
	}

	@Test
	void testDeleteBookbyTitle() {
		
		Book temp = test.getBookbyId(50);
		test.deleteBookbyTitle(temp.getTitle());

		assertTrue(test.getBookbyId(temp.getBookId()) == null);
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA" + test.addBook(temp));
		
	
	}
}
