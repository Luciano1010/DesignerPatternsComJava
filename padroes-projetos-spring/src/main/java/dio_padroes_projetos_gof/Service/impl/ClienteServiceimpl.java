package dio_padroes_projetos_gof.Service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio_padroes_projetos_gof.Model.Cliente;
import dio_padroes_projetos_gof.Model.ClienteRepository;
import dio_padroes_projetos_gof.Model.Endereco;
import dio_padroes_projetos_gof.Model.EnderecoRepository;
import dio_padroes_projetos_gof.Service.ClienteService;
import dio_padroes_projetos_gof.Service.ViaCepService;
import jakarta.transaction.Transactional;

@Service
public class ClienteServiceimpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Transactional
    @Override
    public void inserir(Cliente cliente) {
        
        salvarClienteComCep(cliente);
        
    }
    
    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
            if(clienteBd.isPresent()){
                salvarClienteComCep(cliente);
            }

        }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    // código refatorado para inserir cliente.
    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
    
}
