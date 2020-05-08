package wolox.training;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import wolox.training.controllers.UserController;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.BookRepository;
import wolox.training.repositories.UserRepository;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserRepository mockUserRepository;

	@MockBean
	private BookRepository mockBookRepository;

	@Before
	public void setUp() {
	}

//GET
	@Test
	@Order(1)
	public void whenGetUserById_thenJsonArray() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2000-01-01", formatter);
		User juanCarlos = new User("JuanCarlos", "Juan Carlos", ld);

		Mockito.when(mockUserRepository.findById(1L)).thenReturn(Optional.of(juanCarlos));

		mvc.perform(get("/api/users/1")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk()).andExpect(jsonPath("$.username").value("JuanCarlos"));
	}

	@Test
	@Order(2)
	public void whenGetUsers_thenJsonArray() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2000-01-01", formatter);
		User juanCarlos = new User("JuanCarlos", "Juan Carlos", ld);
		ld = LocalDate.parse("2012-12-31", formatter);
		User pepe = new User("pepe", "Pepe Biondi", ld);

		List<User> users = new ArrayList<User>();
		users.add(juanCarlos);
		users.add(pepe);

		Mockito.when(mockUserRepository.findAll()).thenReturn(users);

		mvc.perform(get("/api/users")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Juan Carlos"))
		        .andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	@Order(3)
	public void whenGetUserByUsername_thenJsonArray() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2000-01-01", formatter);
		User juanCarlos = new User("JuanCarlos", "Juan Carlos", ld);

		Mockito.when(mockUserRepository.findFirstByUsernameOrderByIdAsc("JuanCarlos"))
		        .thenReturn(Optional.of(juanCarlos));

		mvc.perform(get("/api/users?username=JuanCarlos")).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Juan Carlos"));
	}

	@Test
	@Order(4)
	public void whenTryGetUserByUsername_thenReturnNotFound() throws Exception {
		Mockito.when(mockUserRepository.findFirstByUsernameOrderByIdAsc("Pepe")).thenReturn(Optional.empty());
		mvc.perform(get("/api/users?username=Pepe")).andExpect(content().string("No hay usuario para el username Pepe"))
		        .andExpect(status().isNotFound());
	}

//POST

	@Test
	@Order(5)
	public void whenPostUser_thenReturnCreated() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2000-01-01", formatter);
		User juanCarlos = new User("JuanCarlos", "Juan Carlos", ld);
		ObjectMapper mp = new ObjectMapper();

		Mockito.when(mockUserRepository.save(any())).thenReturn(juanCarlos);

		mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
		        .content(mp.writeValueAsString(juanCarlos))).andDo(print()).andExpect(status().isCreated())
		        .andExpect(jsonPath("$.username").value("JuanCarlos"));
	}

	@Test
	@Order(6)
	public void whenPostUserAddBook_thenReturnAdded() throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2000-01-01", formatter);
		User juanCarlos = new User("JuanCarlos", "Juan Carlos", ld);

		Book laPsiquiatra = new Book("Thriller", "Wulf Dorn", "laPsiquiatra.JPG", "La Psiquiatra", "--", "??", "1990",
		        400, "1122334455667782");

		ObjectMapper mp = new ObjectMapper();

//		Mockito.when(mockUserRepository.findById(any())).thenReturn(Optional.of(juanCarlos));
//		Mockito.when(mockBookRepository.findById(any())).thenReturn(Optional.of(laPsiquiatra));
//		Mockito.when(mockUserRepository.save(any())).thenReturn(juanCarlos);

		given(mockUserRepository.findById(any())).willReturn(Optional.of(juanCarlos));
		given(mockBookRepository.findById(any())).willReturn(Optional.of(laPsiquiatra));
		User juanCarlosB = new User("JuanCarlos", "Juan Carlos", ld);
		List<Book> books = new ArrayList<Book>();
		books.add(laPsiquiatra);
		juanCarlosB.setBooks(books);

		given(mockUserRepository.save(any())).willReturn(juanCarlosB);

		mvc.perform(post("/api/users/1/books/2").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
		        .content(mp.writeValueAsString(juanCarlos))).andDo(print()).andExpect(status().isOk())
		        .andExpect(jsonPath("$.username").value("JuanCarlos"));
	}

}
