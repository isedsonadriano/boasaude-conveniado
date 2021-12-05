package br.com.boasaude.cadastro.conveniado.core.service;

import java.util.List;

import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.core.util.Paginador;

public interface ConveniadoService {

	public List<Conveniado> capturarTodos(Paginador paginador);

	public Conveniado capturarPorId(Long id);

	public void salvar(Conveniado conveniado);

	public void atualizar(Conveniado conveniado);

	public void deletar(Long id);

}
