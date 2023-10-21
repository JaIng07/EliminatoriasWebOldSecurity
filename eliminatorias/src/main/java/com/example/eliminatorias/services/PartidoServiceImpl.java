package com.example.eliminatorias.services;

import com.example.eliminatorias.entities.Partido;
import com.example.eliminatorias.exceptions.PartidoNotFoundException;
import com.example.eliminatorias.repositories.PartidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PartidoServiceImpl implements PartidoService {
    private final PartidoRepository partidoRepository;

    public PartidoServiceImpl(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }

    @Override
    public List<Partido> findAllPartidos() {
        return partidoRepository.findAll();
    }

    @Override
    public Optional<Partido> findPartidoById(Long id) {
        return partidoRepository.findById(id);
    }

    @Override
    public Partido createPartido(Partido partido) {
        Partido partidoCopy = new Partido(null, partido.getFecha(), partido.getEstadio(), partido.getLocal(), partido.getVisitante(), partido.getResultado(), partido.getArbitroPrincipal());

        partidoCopy.setFecha(partido.getFecha());
        partidoCopy.setEstadio(partido.getEstadio());
        partidoCopy.setLocal(partido.getLocal());
        partidoCopy.setVisitante(partido.getVisitante());
        partidoCopy.setArbitroPrincipal(partido.getArbitroPrincipal());
        partidoCopy.setResultado(partido.getResultado());

        return partidoRepository.save(partidoCopy);
    }

    @Override
    public Optional<Partido> updatePartido(Long id, Partido newPartido) {
        Optional<Partido> partidoInDB = partidoRepository.findById(id);

        Optional<Partido> partidoUpdated = partidoInDB.map(oldPartidoInDb -> {
            Partido updated = oldPartidoInDb.updateWith(newPartido);
            return partidoRepository.save(updated);
        });
        return partidoUpdated;
    }

    @Override
    public void deletePartido(Long id) {
        partidoRepository.deleteById(id);
    }
    
    @Override
    public List<Partido> findByFecha(LocalDate fecha) {
    	List<Partido> partidos = partidoRepository.findByFecha(fecha);
    	
    	if(partidos.isEmpty()) {
    		throw new PartidoNotFoundException("No se encontraron partidos en dicha fecha");
    	}
    	return partidos;
    }

    @Override
    public List<Partido> findByEstadio(String estadio) {
    	List<Partido> partidos = partidoRepository.findByEstadio(estadio);
    	
    	if(partidos.isEmpty()) {
    		throw new PartidoNotFoundException("No se encontraron partidos en dicho estadio");
    	}
    	return partidos;
    }

    @Override
    public List<Partido> findByEquipo(Long idEquipo) {
    	List<Partido> partidos = partidoRepository.findByEquipo(idEquipo);
    	
    	if(partidos.isEmpty()) {
    		throw new PartidoNotFoundException("No se encontraron partidos de dicho equipo");
    	}
    	return partidos;
    }

    @Override
    public List<Partido> findByArbitroPrincipal(String arbitroPrincipal) {
    	List<Partido> partidos = partidoRepository.findByArbitroPrincipal(arbitroPrincipal);
    	
    	if(partidos.isEmpty()) {
    		throw new PartidoNotFoundException("No se encontraron partidos pitados por dicho arbitro");
    	}
    	return partidos;
    }
}

