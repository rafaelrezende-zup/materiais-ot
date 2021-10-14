import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import builder.CriadorDeLeilao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario maria;
    private Usuario silva;

    @Before
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.maria = new Usuario("Maria");
        this.silva = new Usuario("Silva");
    }

    @Test
    public void testAvaliadorLanceRand() {
        Leilao leilao = getLeilaoRand();

        leiloeiro.avalia(leilao);

        double maiorLance = 5500.00;
        double menorLance = 500.00;
        double valorMedio = 2666.6666;
        List<Lance> maioresLances = leiloeiro.getMaiores();

        assertEquals(maiorLance, leiloeiro.getMaiorDeTodos(), 0.00001);
        assertEquals(menorLance, leiloeiro.getMenorDeTodos(), 0.00001);
        assertEquals(valorMedio, leiloeiro.getMediaLances(), 0.0001);
        assertEquals(3, maioresLances.size());
        assertEquals(5500.00, maioresLances.get(0).getValor(), 0.0001);
        assertEquals(3500.00, maioresLances.get(1).getValor(), 0.0001);
        assertEquals(3000.00, maioresLances.get(2).getValor(), 0.0001);
    }

    @Test
    public void testAvaliadorLanceCresc() {
        Leilao leilao = getLeilaoCresc();

        leiloeiro.avalia(leilao);

        double maiorLance = 5500.00;
        double menorLance = 1500.00;
        double valorMedio = 3333.33333;
        List<Lance> maioresLances = leiloeiro.getMaiores();

        assertEquals(maiorLance, leiloeiro.getMaiorDeTodos(), 0.00001);
        assertEquals(menorLance, leiloeiro.getMenorDeTodos(), 0.00001);
        assertEquals(valorMedio, leiloeiro.getMediaLances(), 0.0001);
        assertEquals(3, maioresLances.size());
        assertEquals(5500.00, maioresLances.get(0).getValor(), 0.0001);
        assertEquals(3000.00, maioresLances.get(1).getValor(), 0.0001);
        assertEquals(1500.00, maioresLances.get(2).getValor(), 0.0001);
    }

    @Test
    public void testAvaliadorLanceDecres() {
        Leilao leilao = getLeilaoDecres();

        leiloeiro.avalia(leilao);

        double maiorLance = 3500.00;
        double menorLance = 500.00;
        double valorMedio = 2000.0;
        List<Lance> maioresLances = leiloeiro.getMaiores();

        assertEquals(maiorLance, leiloeiro.getMaiorDeTodos(), 0.00001);
        assertEquals(menorLance, leiloeiro.getMenorDeTodos(), 0.00001);
        assertEquals(valorMedio, leiloeiro.getMediaLances(), 0.0001);
        assertEquals(3, maioresLances.size());
        assertEquals(3500.00, maioresLances.get(0).getValor(), 0.0001);
        assertEquals(2000.00, maioresLances.get(1).getValor(), 0.0001);
        assertEquals(500.00, maioresLances.get(2).getValor(), 0.0001);
    }

    @Test
    public void testAvaliadorUmLance() {
        Leilao leilao = getLeilaoUmLance();

        leiloeiro.avalia(leilao);

        double valorLance = 1500.00;
        List<Lance> maioresLances = leilao.getLances();

        assertEquals(valorLance, leiloeiro.getMaiorDeTodos(), 0.00001);
        assertEquals(valorLance, leiloeiro.getMenorDeTodos(), 0.00001);
        assertEquals(valorLance, leiloeiro.getMediaLances(), 0.0001);
        assertEquals(1, maioresLances.size());
        assertEquals(valorLance, maioresLances.get(0).getValor(), 0.0001);
    }

    @Test(expected = RuntimeException.class)
    public void testAvaliadorSemLance() {
        Leilao leilao = getLeilaoSemLance();

        leiloeiro.avalia(leilao);

        List<Lance> maioresLances = leilao.getLances();

        assertEquals(0, maioresLances.size());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLance() {
        Leilao leilao = getLeilaoSemLance();
        leiloeiro.avalia(leilao);
    }

    private Leilao getLeilaoRand() {
        return new CriadorDeLeilao().para("Ps5")
                .lance(joao, 1500.00)
                .lance(maria, 3500.00)
                .lance(silva, 3000.00)
                .lance(joao, 500.00)
                .lance(maria, 5500.00)
                .lance(silva, 2000.00)
                .constroi();
    }

    private Leilao getLeilaoCresc() {
        return new CriadorDeLeilao().para("Ps5")
                .lance(maria, 5500.00)
                .lance(maria, 3500.00)
                .lance(silva, 3000.00)
                .lance(silva, 2000.00)
                .lance(joao, 1500.00)
                .lance(joao, 500.00)
                .constroi();
    }

    private Leilao getLeilaoDecres() {
        return new CriadorDeLeilao().para("Ps5")
                .lance(joao, 500.00)
                .lance(joao, 1500.00)
                .lance(silva, 2000.00)
                .lance(silva, 3000.00)
                .lance(maria, 3500.00)
                .lance(maria, 5500.00)
                .constroi();
    }

    private Leilao getLeilaoUmLance() {
        return new CriadorDeLeilao().para("Ps5")
                .lance(joao, 1500.00)
                .constroi();
    }

    private Leilao getLeilaoSemLance() {
        return new Leilao("Ps5");
    }

}
