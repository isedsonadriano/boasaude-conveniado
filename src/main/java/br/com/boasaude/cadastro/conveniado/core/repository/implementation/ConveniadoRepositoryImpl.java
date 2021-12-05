package br.com.boasaude.cadastro.conveniado.core.repository.implementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.core.domain.vo.TipoConveniado;
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
		Conveniado conveniado = new Conveniado();
		conveniado.setNome(conveniadoJpa.getNome());
		conveniado.setCpf(conveniadoJpa.getCpf());
		conveniado.setTipo(conveniadoJpa.getTipo());
		return conveniado;
	}

	@Override
	public List<Conveniado> capturarTodos(Paginador paginador) {
		return capturarConveniadosBD(paginador);
	}

	private List<Conveniado> capturarConveniadosBD(Paginador paginador) {
		PageRequest paginaRetorno =   PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by("id"));
		return repository.findAll(paginaRetorno).get().map(this::buildConveniado).collect(Collectors.toList());
	}

	@Override
	public void atualizar(Conveniado conveniado) {
		Optional<ConveniadoJpa> conveniadoJpa = this.repository.findById(conveniado.getId());
		if (conveniadoJpa.isPresent()) {
			conveniadoJpa.get().setCpf(conveniado.getCpf());
		}
		this.repository.save(conveniadoJpa.get());
	}

	@Override
	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

	private Conveniado buildConveniado(ConveniadoJpa conveniadoJpa) {
		if(Objects.isNull(conveniadoJpa)) {
			return null;
		}
		Conveniado conveniado = new Conveniado();
		conveniado.setId(conveniadoJpa.getId());
		conveniado.setNome(conveniadoJpa.getNome());
		conveniado.setCpf(conveniadoJpa.getCpf());
		conveniado.setTipo(TipoConveniado.getRandomTipoConveniado());
		return conveniado;
	}

	private ConveniadoJpa buildConveniadoJpa(Conveniado conveniado) {
		ConveniadoJpa conveniadoJpa = new ConveniadoJpa();
		conveniadoJpa.setNome(conveniado.getNome());
		conveniadoJpa.setCpf(conveniado.getCpf());
		conveniadoJpa.setTipo(conveniado.getTipo());
		return conveniadoJpa;
	}

	@Override
	public Conveniado findByCpf(String cpf) {
		ConveniadoJpa conveniadoJpa = this.repository.findByCpf(cpf);
		return buildConveniado(conveniadoJpa);
	}
}
