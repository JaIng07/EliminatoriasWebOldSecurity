package com.example.eliminatorias.services;

import com.example.eliminatorias.entities.Resultado;
import com.example.eliminatorias.exceptions.ResultadoNotFoundException;
import com.example.eliminatorias.repositories.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoServiceImpl implements ResultadoService {
    private final ResultadoRepository resultadoRepository;

    public ResultadoServiceImpl(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    @Override
    public List<Resultado> findAllResultados() {
        return resultadoRepository.findAll();
    }

    @Override
    public Optional<Resultado> findResultadoById(Long id) {
        return resultadoRepository.findById(id);
    }

    @Override
    public Resultado createResultado(Resultado resultado) {
        Resultado resultadoCopy = new Resultado(null, resultado.getGolLocal(), resultado.getGolVisitante(), resultado.getNroTarjetasAmarillas(), resultado.getNroTarjetasRojas(), resultado.getPartido());

        resultadoCopy.setGolLocal(resultado.getGolLocal());
        resultadoCopy.setGolVisitante(resultado.getGolVisitante());
        resultadoCopy.setNroTarjetasAmarillas(resultado.getNroTarjetasAmarillas());
        resultadoCopy.setNroTarjetasRojas(resultado.getNroTarjetasRojas());

        return resultadoRepository.save(resultadoCopy);
    }

    @Override
    public Optional<Resultado> updateResultado(Long id, Resultado newResultado) {
        Optional<Resultado> resultadoInDB = resultadoRepository.findById(id);

        Optional<Resultado> resultadoUpdated = resultadoInDB.map(oldResultadoInDb -> {
            Resultado updated = oldResultadoInDb.updateWith(newResultado);
            return resultadoRepository.save(updated);
        });
        return resultadoUpdated;
    }

    @Override
    public void deleteResultado(Long id) {
        resultadoRepository.deleteById(id);
    }
    
    @Override
    public List<Resultado> findByGolLocalOrGolVisitante(int golLocal, int golVisitante) {
    	List<Resultado> resultados = resultadoRepository.findByGolLocalOrGolVisitante(golLocal, golVisitante);
    	
    	if(resultados.isEmpty()) {
    		throw new ResultadoNotFoundException("No se encontraron resultados con dichos parametros");
    	}
    	return resultados;
    }

    @Override
    public List<Resultado> findByNroTarjetasAmarillasOrNroTarjetasRojas(int nroTarjetasAmarillas, int nroTarjetasRojas) {
    	List<Resultado> resultados = resultadoRepository.findByNroTarjetasAmarillasOrNroTarjetasRojas(nroTarjetasAmarillas, nroTarjetasRojas);
    	
    	if(resultados.isEmpty()) {
    		throw new ResultadoNotFoundException("No se encontraron resultados con dichos parametros");
    	}
    	return resultados;
    }
}
