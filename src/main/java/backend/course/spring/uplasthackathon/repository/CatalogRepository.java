package backend.course.spring.uplasthackathon.repository;

import backend.course.spring.uplasthackathon.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    Optional<Catalog> findByCatalogName(String catalogName);
}
