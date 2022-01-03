package br.com.boasaude.cadastro.conveniado.core.domain.vo;

import java.util.Random;

public enum TipoConveniado {

	HOSPITAL,
    CLINICA,
    NAO_INFORMADO;
    
    public static TipoConveniado getRandomTipoConveniado() {
    	return values()[new Random().nextInt(2)];
    }
}

