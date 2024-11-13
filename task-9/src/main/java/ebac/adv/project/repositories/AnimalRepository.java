package ebac.adv.project.repositories;

import ebac.adv.project.entidades.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    @Query("SELECT a FROM Animal a WHERE a.dataSaida IS NULL")
    List<Animal> findNotAdopted();

    @Query("SELECT a FROM Animal a WHERE a.dataSaida IS NOT NULL")
    List<Animal> findAllAdopted();

    @Query("SELECT a FROM Animal a WHERE a.especie = 'CACHORRO'")
    List<Animal> findAllDogs();

    @Query("SELECT a FROM Animal a WHERE a.especie = 'GATO'")
    List<Animal> findAllCats();

    @Query("SELECT a FROM Animal a WHERE a.responsavel = :responsavel")
    List<Animal> findByResponsavel(String responsavel);

  @Query("SELECT a FROM Animal a WHERE a.responsavel = :responsavel AND a.dataEntrada >= :dataInicio AND a.dataEntrada <= :dataFim")
    List<Animal> findByResponsavelAndDate(
            @Param("responsavel") String responsavel,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim)
  ;


}
