package br.com.boasaude.cadastro.conveniado.core.repository.implementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.boasaude.cadastro.conveniado.application.conveniado.mapper.ConveniadoMapper;
import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.core.repository.ConveniadoRepository;
import br.com.boasaude.cadastro.conveniado.core.util.Paginador;
import br.com.boasaude.cadastro.conveniado.infra.repository.ConveniadoRepositoryMySql;
import br.com.boasaude.cadastro.conveniado.infra.repository.orm.ConveniadoJpa;

public class ConveniadoRepositoryImpl implements ConveniadoRepository {

	private final ConveniadoRepositoryMySql repository;

	public ConveniadoRepositoryImpl(ConveniadoRepositoryMySql repository) {
		this.repository = repository;
	}

	@Override
	public void salvar(Conveniado conveniado) {
		ConveniadoJpa conveniadoSalvo = repository.saveAndFlush(buildConveniadoJpa(conveniado));
		conveniado.setId(conveniadoSalvo.getId());
	}

	@Override
	public Conveniado capturarPorId(Long id) {
		ConveniadoJpa conveniadoJpa = repository.findById(id).get();
		return ConveniadoMapper.conveniadoJpaToConveniado(conveniadoJpa);
	}

	@Override
	public List<Conveniado> capturarTodos(Paginador paginador) {
		return capturarConveniadosBD(paginador);
	}

	private List<Conveniado> capturarConveniadosBD(Paginador paginador) {
		PageRequest paginaRetorno =   PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by("id").descending());
		return repository.findAll(paginaRetorno).get().map(this::buildConveniado).collect(Collectors.toList());
	}

	@Override
	public void atualizar(Conveniado conveniado) {
		Optional<ConveniadoJpa> conveniadoJpaOpt = this.repository.findById(conveniado.getId());
		if (conveniadoJpaOpt.isPresent()) {
			ConveniadoJpa conveniadoJpa = conveniadoJpaOpt.get();
			conveniadoJpa.setCnpj(conveniado.getCnpj());
			conveniadoJpa.setNome(conveniado.getNome());
			conveniadoJpa.setTipo(conveniado.getTipo());
			conveniadoJpa.setStatus(conveniado.getStatus());
			conveniadoJpa.setTelefone(conveniado.getTelefone());

			this.repository.save(conveniadoJpa);
		}
	}

	@Override
	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

	private Conveniado buildConveniado(ConveniadoJpa conveniadoJpa) {
		if(Objects.isNull(conveniadoJpa)) {
			return null;
		}
		return ConveniadoMapper.conveniadoJpaToConveniado(conveniadoJpa);
	}

	private ConveniadoJpa buildConveniadoJpa(Conveniado conveniado) {
		return ConveniadoMapper.conveniadoToConveniadoJpa(conveniado);
	}

	@Override
	public Conveniado findByCnpj(String cnpj) {
		ConveniadoJpa conveniadoJpa = this.repository.findByCnpj(cnpj);
		return buildConveniado(conveniadoJpa);
	}
}
