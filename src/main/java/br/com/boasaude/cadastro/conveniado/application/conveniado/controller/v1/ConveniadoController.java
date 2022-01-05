package br.com.boasaude.cadastro.conveniado.application.conveniado.controller.v1;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.boasaude.cadastro.conveniado.application.conveniado.dto.request.v1.ConveniadoRequest;
import br.com.boasaude.cadastro.conveniado.application.conveniado.dto.response.v1.ConveniadoResponse;
import br.com.boasaude.cadastro.conveniado.application.conveniado.mapper.ConveniadoMapper;
import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.core.service.ConveniadoService;
import br.com.boasaude.cadastro.conveniado.core.util.Paginador;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("v1/conveniados")
@Api(tags = "/v1/conveniados", value = "API's para manipular conveniados", authorizations = {@Authorization(value="jwtToken")})
@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Retorno para a inclusão de conveniado"),
		@ApiResponse(code = 401, message = "Erro de autenticação"),
		@ApiResponse(code = 403, message = "Erro de autorização"),
		@ApiResponse(code = 404, message = "Recurso não encontrado")}
)
@CrossOrigin(origins = "*", maxAge = 3600L)
public class ConveniadoController  {

	@Autowired
	private ConveniadoService service;
	
	@Autowired
	protected ModelMapper modelMapper;

	
	@ApiOperation(value = "API para salvar um conveniado", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ConveniadoResponse> salvar(@RequestBody @Valid ConveniadoRequest conveniadoRequest, UriComponentsBuilder uriBuilder) {
		Conveniado conveniado = ConveniadoMapper.conveniadoRequestToConveniado(conveniadoRequest);
		this.service.salvar(conveniado);
		URI uri = uriBuilder.path("{id}").buildAndExpand(conveniado.getId()).toUri();
		return ResponseEntity.created(uri).body(buildConveniadoResponse(conveniado));
	}
	
	@ApiOperation(value = "API para listar conveniados", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	@ResponseBody
	public List<ConveniadoResponse> listar(@PageableDefault(sort="id", direction = Direction.DESC) Pageable pageable) {
		 List<Conveniado> conveniados = service.capturarTodos(new Paginador(pageable.getOffset(), pageable.getPageNumber(), pageable.getPageSize(), false, false));
		return conveniados.stream().map(this::buildConveniadoResponse).collect(Collectors.toList());
	}

	@ApiOperation(value = "API para capturar conveniado por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ConveniadoResponse> catpurar(@PathVariable Long id) {
		Conveniado conveniado = service.capturarPorId(id);
		if (Objects.nonNull(conveniado)) {
			return ResponseEntity.ok(ConveniadoMapper.conveniadoToConveniadoResponse(conveniado));
		}
		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "API para atualizar o conveniado", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ConveniadoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ConveniadoRequest conveniadoRequest, UriComponentsBuilder uriBuilder) {
		Conveniado conveniado = ConveniadoMapper.conveniadoRequestToConveniado(conveniadoRequest);
		conveniado.setId(id);
		this.service.atualizar(conveniado);
		return ResponseEntity.ok(buildConveniadoResponse(conveniado));
	}

	@ApiOperation(value = "API para deletar um conveniado", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ConveniadoResponse> deletar(@PathVariable Long id) {
		this.service.deletar(id);
		return ResponseEntity.ok().build();
	}

	private ConveniadoResponse buildConveniadoResponse(Conveniado conveniado) {
		return modelMapper.map(conveniado, ConveniadoResponse.class);
	}

}
