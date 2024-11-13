package ebac.adv.project.controllers;

import ebac.adv.project.entidades.Animal;
import ebac.adv.project.repositories.AnimalRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private List<Animal> findAll() {
        return repository.findAll();
    }

    @PostMapping
    private Animal create(@RequestBody Animal animal) {
        return repository.save(animal);
    }

    @GetMapping("/not-adopted")
    private List<Animal> getNotAdopted() {
        return repository.findNotAdopted();
    }

    @GetMapping("/adopted")
    private List<Animal> getAdopted() {
        return repository.findAllAdopted();
    }

    @GetMapping("/dogs")
    private List<Animal> getAllDogs() {
        return repository.findAllDogs();
    }

    @GetMapping("/cats")
    private List<Animal> getAllCats() {
        return repository.findAllCats();
    }

    @GetMapping("/by-responsavel/{responsavel}")
    private List<Animal> getAnimalByResponsavel(@PathVariable String responsavel) {
        return repository.findByResponsavel(responsavel);
    }

    @GetMapping("/teste")
    private List<Animal> getRecuedByDate(@RequestParam String responsavel,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        if (dataInicio == null) {
            dataInicio = LocalDate.now().minusYears(1);
            dataFim = LocalDate.now();
        }
        if (dataFim == null) {
            dataFim = dataInicio.plusYears(1);
        }

        return repository.findByResponsavelAndDate(responsavel, dataInicio, dataFim);
    }



}
