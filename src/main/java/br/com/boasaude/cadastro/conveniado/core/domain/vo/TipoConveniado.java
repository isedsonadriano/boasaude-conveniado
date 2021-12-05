package br.com.boasaude.cadastro.conveniado.core.domain.vo;

import java.util.Random;

public enum TipoConveniado {

	HOSPITAL("H"),
    CLINICA("C"),
    NAO_INFORMADO("NI");

    private String value;

     TipoConveniado(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static TipoConveniado fromValue(String text){
         for(TipoConveniado tipo: values()){
             if(String.valueOf(tipo.value).equalsIgnoreCase(text)){
                 return tipo;
             }
         }
         return NAO_INFORMADO;
    }
    
    public static TipoConveniado getRandomTipoConveniado() {
    	return values()[new Random().nextInt(2)];
    }
}

