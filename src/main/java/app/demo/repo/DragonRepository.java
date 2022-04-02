package app.demo.repo;

import app.demo.model.Dragon;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface DragonRepository extends PagingAndSortingRepository<Dragon, Long>, JpaSpecificationExecutor<Dragon> {
    @Query("SELECT d.creationDate FROM dragon d where d.id = ?1 ")
    ZonedDateTime findCreationDateByDragonId(long id);

    @Query("select d from dragon d")
    List<Dragon> findAllDragons ();
}