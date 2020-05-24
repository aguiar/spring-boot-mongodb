package com.borba.springbootmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.borba.springbootmongo.domain.Post;
import com.borba.springbootmongo.domain.User;
import com.borba.springbootmongo.dto.AuthorDTO;
import com.borba.springbootmongo.dto.CommentDTO;
import com.borba.springbootmongo.repository.PostRepository;
import com.borba.springbootmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// Criando objeto para data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		// Apagando dados da base(BD)
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		// Setando dados dos Usuarios
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		// Inserindo dados dos Usuarios
		userRepository.insert(Arrays.asList(maria, alex, bob));
		
		// Setando dados dos Posts dos Usuarios
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

		// Setando dados dos Comentarios dos Posts
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite...", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		//Associando Comentarios aos Posts
		post1.getComments().addAll(Arrays.asList(c1, c2));
//		post2.getComments().addAll(Arrays.asList(c3));
		post2.getComments().add(c3);
		
		// Inserindo dados dos Posts
		postRepository.insert(Arrays.asList(post1, post2));
		
		// Associando os Posts para o Usuario maria
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
