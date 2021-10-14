package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

public class EncerradorDeLeilaoTest {

    private Usuario joao;
    private Usuario maria;
    private Usuario silva;

    @Before
    public void preparaAmbiente() {
        this.joao = new Usuario("João");
        this.maria = new Usuario("Maria");
        this.silva = new Usuario("Silva");
    }

    @Test
    public void deveEncerrarLeilaoQueComecaramUmaSemanaAntes() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("Ps5").naData(antiga)
                .lance(maria, 5500.00)
                .lance(maria, 3500.00)
                .lance(silva, 3000.00)
                .constroi();

        Leilao leilao2 = new CriadorDeLeilao().para("Ps5").naData(antiga)
                .lance(maria, 5500.00)
                .lance(maria, 3500.00)
                .lance(joao, 500.00)
                .constroi();

        EncerradorDeLeilao encerradorDeLeilao = new EncerradorDeLeilao();
        encerradorDeLeilao.encerra();

        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }

}
