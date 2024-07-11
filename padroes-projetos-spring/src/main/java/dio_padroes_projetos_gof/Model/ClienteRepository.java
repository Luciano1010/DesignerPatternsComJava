package dio_padroes_projetos_gof.Model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface  ClienteRepository extends  CrudRepository<Cliente, Long>{
    
}
