package br.com.boasaude.cadastro.conveniado.core.repository;

import java.util.List;

import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.core.util.Paginador;

public interface ConveniadoRepository {

	void salvar(Conveniado conveniado);

	Conveniado capturarPorId(Long id);

	List<Conveniado> capturarTodos(Paginador paginador);

	void atualizar(Conveniado conveniado);

	void deletar(Long id);

	Conveniado findByCpf(String cpf);

}
