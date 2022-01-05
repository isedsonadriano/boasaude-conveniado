package br.com.boasaude.cadastro.conveniado.application.conveniado.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.boasaude.cadastro.conveniado.application.conveniado.dto.request.v1.ConveniadoRequest;
import br.com.boasaude.cadastro.conveniado.application.conveniado.dto.response.v1.ConveniadoResponse;
import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.infra.repository.orm.ConveniadoJpa;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class ConveniadoMapper {

  private static final MapperFactory factoryConveniadoRequestToConveniado;
  private static final MapperFactory factoryConveniadoToConveniadoJpa;
  private static final MapperFactory factoryConveniadoToConveniadoResponse;

  static {
    factoryConveniadoRequestToConveniado = buildConveniadoRequestToConveniado();
    factoryConveniadoToConveniadoJpa = buildConveniadoToConveniadoJpa();
    factoryConveniadoToConveniadoResponse = buildConveniadoToConveniadoResponse();
  }

  public static Conveniado conveniadoRequestToConveniado(ConveniadoRequest conveniadoRequest) {
    return (Conveniado) map(factoryConveniadoRequestToConveniado.getMapperFacade(), conveniadoRequest, Conveniado.class);
  }

  public static ConveniadoJpa conveniadoToConveniadoJpa(Conveniado conveniado) {
    return (ConveniadoJpa) map(factoryConveniadoToConveniadoJpa.getMapperFacade(), conveniado, ConveniadoJpa.class);
  }

  public static Conveniado conveniadoJpaToConveniado(ConveniadoJpa conveniadoJpa) {
    return (Conveniado) map(factoryConveniadoToConveniadoJpa.getMapperFacade(), conveniadoJpa, Conveniado.class);
  }

  public static ConveniadoResponse conveniadoToConveniadoResponse(Conveniado conveniado) {
    return (ConveniadoResponse) map(factoryConveniadoToConveniadoResponse.getMapperFacade(), conveniado, ConveniadoResponse.class);
  }

  private static MapperFactory buildConveniadoRequestToConveniado() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
		mapperFactory.classMap(ConveniadoRequest.class, Conveniado.class)
				.field("nome", "nome")
				.field("cnpj", "cnpj")
				.field("tipo", "tipo")
				.field("telefone", "telefone.numeroCompleto")
				.field("status", "status")
				.register();
		return mapperFactory;
	}

  private static MapperFactory buildConveniadoToConveniadoJpa() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
		mapperFactory.classMap(Conveniado.class, ConveniadoJpa.class)
        .field("id", "id")
				.field("nome", "nome")
				.field("cnpj", "cnpj")
				.field("tipo", "tipo")
				.field("telefone", "telefone")
				.field("status", "status")
				.register();
		return mapperFactory;
	}

  private static MapperFactory buildConveniadoToConveniadoResponse() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
		mapperFactory.classMap(Conveniado.class, ConveniadoResponse.class)
        .field("id", "id")
				.field("nome", "nome")
				.field("cnpj", "cnpj")
				.field("tipo", "tipo")
				.field("telefone.numeroCompleto", "telefone")
				.field("status", "status")
				.register();
		return mapperFactory;
	}

  @SuppressWarnings({ "rawtypes", "unchecked" })
	private static List map(final MapperFacade mapper, final List source, final Class destType) {
		if (mapper != null && source != null) {
			List dest = new ArrayList();
			dest.addAll(mapper.mapAsList(source, destType));
			return dest;
		}
		return Collections.emptyList();
	}


	@SuppressWarnings({ "rawtypes" })
	private static Object map(MapperFacade mapper, Object obj, Class destType) {
		List list = map(mapper, Arrays.asList(obj), destType);
		if (list != null) {
			return list.get(0);
		}
		return null;
	}
  
}
