package dio_padroes_projetos_gof.Service;

import dio_padroes_projetos_gof.Model.Cliente;

public interface ClienteService {
    
    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    public void inserir(Cliente cliente);
    public void atualizar (Long id, Cliente cliente);
    public void deletar(Long id);
    

}
