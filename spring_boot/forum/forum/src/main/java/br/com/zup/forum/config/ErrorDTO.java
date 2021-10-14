package br.com.zup.forum.config;

public class ErrorDTO {

    private final String campo;
    private final String erro;

    public ErrorDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
