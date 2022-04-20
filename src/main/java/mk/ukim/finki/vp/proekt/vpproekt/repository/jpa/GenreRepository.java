package mk.ukim.finki.vp.proekt.vpproekt.repository.jpa;

import mk.ukim.finki.vp.proekt.vpproekt.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    List<Genre> findAllByNameLike(String text);

    void deleteByName(String name);

     Optional<Genre> findByName(String name);
}
