package mk.ukim.finki.vp.proekt.vpproekt.service.impl;

import mk.ukim.finki.vp.proekt.vpproekt.model.Cast;
import mk.ukim.finki.vp.proekt.vpproekt.repository.jpa.CastRepository;
import mk.ukim.finki.vp.proekt.vpproekt.service.CastService;

import java.util.List;

public class CastServiceImpl implements CastService {

    private final CastRepository castRepository;

    public CastServiceImpl(CastRepository castRepository) {
        this.castRepository = castRepository;
    }

    @Override
    public List<Cast> findAll() {
        return castRepository.findAll();
    }
}
