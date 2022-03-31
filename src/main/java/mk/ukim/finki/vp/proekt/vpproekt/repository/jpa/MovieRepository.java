package mk.ukim.finki.vp.proekt.vpproekt.repository.jpa;

import mk.ukim.finki.vp.proekt.vpproekt.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
}
