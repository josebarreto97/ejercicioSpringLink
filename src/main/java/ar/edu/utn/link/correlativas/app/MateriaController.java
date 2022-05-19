package ar.edu.utn.link.correlativas.app;


import java.util.Collection;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import ar.edu.utn.link.correlativas.model.Materia;

@RestController
@RequestMapping("/materias")
public class MateriaController {
	
	@Autowired
	private RepoMateriaMem repo;
	
	@GetMapping("")
	public Collection<Materia> list(@RequestParam(value="anio",required = false)  Integer anio,
			Pageable page) {	
		
		if(anio == null) {
			//System.out.println(page);
			return repo.all();
		} else {
			return repo.findByYear(anio);	
		}
		
	}
	
	@GetMapping("/{nombre}")
	public Materia get(@PathVariable("nombre") String nombreMateria) {		
		return repo.findByName(nombreMateria) ;
	}
	
	@PostMapping("")
	public String post(@RequestBody Materia materia) 
			throws MateriaRepetidaException {		
		
		repo.save(materia) ;
		return "ok";
		
	}
	
}
