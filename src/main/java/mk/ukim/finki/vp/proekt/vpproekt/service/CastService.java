package mk.ukim.finki.vp.proekt.vpproekt.service;

import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;
import mk.ukim.finki.vp.proekt.vpproekt.model.dto.CastDto;

import java.util.List;
import java.util.Optional;

public interface CastService {

    Optional<Cast> findById(Long id);

    List<Cast> findAll();

    Optional<Cast> findByName(String name);

    Optional<Cast> save(String name, String surname, Long movie);

    Optional<Cast> save(CastDto castDto);

    Optional<Cast> edit(Long id, String name, String surname, Long movie);

    Optional<Cast> edit(Long id, CastDto castDto);

    void deleteById(Long id);
}
