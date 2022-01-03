package br.com.boasaude.cadastro.conveniado.core.service.implementation;

import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.core.exception.DomainException;
import br.com.boasaude.cadastro.conveniado.core.repository.ConveniadoRepository;
import br.com.boasaude.cadastro.conveniado.core.service.ConveniadoService;
import br.com.boasaude.cadastro.conveniado.core.util.Paginador;

import java.util.List;
import java.util.Objects;

public class ConveniadoServiceImpl implements ConveniadoService {

	final private ConveniadoRepository clientRepository;

	public ConveniadoServiceImpl(ConveniadoRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<Conveniado> capturarTodos(Paginador paginador) {
		return clientRepository.capturarTodos(paginador);
	}

	@Override
	public Conveniado capturarPorId(Long id) {
		return clientRepository.capturarPorId(id);
	}

	@Override
	public void salvar(Conveniado conveniado) {
		DomainException.throwIf(Objects.nonNull(this.clientRepository.findByCnpj(conveniado.getCnpj())), "Conveniado já cadastrado");
		this.clientRepository.salvar(conveniado);
	}

	@Override
	public void atualizar(Conveniado conveniado) {
		this.clientRepository.atualizar(conveniado);
	}

	@Override
	public void deletar(Long id) {
		this.clientRepository.deletar(id);
	}
}
