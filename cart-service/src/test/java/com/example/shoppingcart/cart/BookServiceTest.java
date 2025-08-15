package com.example.shoppingcart.cart;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.shoppingcart.cart.model.entity.Book;
import com.example.shoppingcart.cart.repository.BookRepository;
import com.example.shoppingcart.cart.service.impl.BookServiceImpl;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

// Replace with your package and class names

class BookServiceTest {

    @Mock
    private BookRepository bookRepository; // Mock dependency (replace with your actual repository)

    @InjectMocks
    private BookServiceImpl bookService; // The service under test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetBookById() {
        // Arrange
        Book mockBook =   new Book(1L, "New Book", "New Author",12.0,"Book Test");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));

        // Act
        Book book = bookService.getBookById(1L);

        // Assert
        assertNotNull(book);
        assertEquals("Test Book", book.getBookTitle());
        assertEquals("Test Author", book.getBookAuthor());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        List<Book> mockBooks = Arrays.asList(
            new Book(1L, "New Book", "New Author",10.0,"Book Test"),
            new Book(2L, "New Book", "New Author",12.0,"Book Test"),
            new Book(3L, "New Book", "New Author",14.0,"Book Test")
        );
        when(bookRepository.findAll()).thenReturn(mockBooks);

        // Act
        List<Book> books = bookService.getAllBook();

        // Assert
        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testAddBook() {
        // Arrange
        Book newBook = new Book(null, "New Book", "New Author",10.0,"Book Test");
        Book savedBook = new Book(1L, "New Book", "New Author",10.0,"Book Test");
        when(bookRepository.save(savedBook)).thenReturn(newBook);

        // Act
        Book book = bookService.createBook(savedBook);

        // Assert
        assertNotNull(book);
        assertEquals(1L, book.getId());
        assertEquals("New Book", book.getBookTitle());
        verify(bookRepository, times(1)).save(newBook);
    }

    @Test
    void testDeleteBook() {
        // Arrange
        Long bookId = 1L;
        doNothing().when(bookRepository).deleteById(bookId);

        // Act
        bookService.deleteBook(bookId);

        // Assert
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
