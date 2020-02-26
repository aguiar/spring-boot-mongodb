package com.borba.springbootmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.borba.springbootmongo.domain.Post;
import com.borba.springbootmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity< List<Post>> findAll() {
		List <Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ResponseEntity<Void> insert(@RequestBody PostDTO objDTO) {
//		Post obj = service.fromDTO(objDTO);
//		obj = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
//	
//	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable String id) {
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}
//	
//	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
//	public ResponseEntity<Void> update(@RequestBody PostDTO objDTO, @PathVariable String id) {
//		Post obj = service.fromDTO(objDTO);
//		obj.setId(id);
//		obj = service.update(obj);
//		return ResponseEntity.noContent().build();
//	}

}
