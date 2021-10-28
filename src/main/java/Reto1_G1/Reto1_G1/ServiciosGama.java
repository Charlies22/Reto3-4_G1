/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reto1_G1.Reto1_G1;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Janus
 */
@Service
public class ServiciosGama {
    @Autowired
    private RepositorioGama metodosCrud;

    public List<Gama> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Gama> getGama(int GamaId) {
        return metodosCrud.getGama(GamaId);
    }

    public Gama save(Gama gama) {
        if (gama.getIdGama()== null) {
            return metodosCrud.save(gama);
        } else {
            Optional<Gama> gama1 = metodosCrud.getGama(gama.getIdGama());
            if (!gama1.isPresent()) {
                return metodosCrud.save(gama);
            } else {
                return gama;
            }
        }
    }

    public Gama update(Gama gama){
        if(gama.getIdGama()!=null){
            Optional<Gama>g=metodosCrud.getGama(gama.getIdGama());
            if(g.isPresent()){
                if(gama.getDescription()!=null){
                    g.get().setDescription(gama.getDescription());
                }
                if(gama.getName()!=null){
                    g.get().setName(gama.getName());
                }
                return metodosCrud.save(g.get());
            }
        }
        return gama;
    }
    public boolean deletegama(int gamaId){
        Boolean d=getGama(gamaId).map(gama -> {
            metodosCrud.delete(gama);
            return true;
        }).orElse(false);
        return d;
    }
    
}
