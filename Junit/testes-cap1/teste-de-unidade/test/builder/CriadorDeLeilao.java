package builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CriadorDeLeilao {

    private Leilao leilao;
    private String descricao;
    private Calendar data;
    private List<Lance> lances;
    private boolean encerrado;

    public CriadorDeLeilao() {
        this.data = Calendar.getInstance();
        lances = new ArrayList<Lance>();
    }

    public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao naData(Calendar data) {
        this.data = data;
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double v) {
        leilao.propoe(new Lance(usuario, v));
        return this;
    }

    public Leilao constroi() {
        if(encerrado) leilao.encerra();
        return leilao;
    }
}
