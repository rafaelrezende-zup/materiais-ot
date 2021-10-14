import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new Leilao("Ps5");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("CJ"), 2500.0));
        assertEquals(1, leilao.getLances().size());
        assertEquals(2500, leilao.getLances().get(0).getValor(), 0.0001);
    }

    @Test
    public void deveReceberVariosLances() {
        Leilao leilao = new Leilao("Ps5");

        leilao.propoe(new Lance(new Usuario("CJ"), 2500.0));
        leilao.propoe(new Lance(new Usuario("CF"), 3500.0));
        assertEquals(2, leilao.getLances().size());
        assertEquals(2500, leilao.getLances().get(0).getValor(), 0.0001);
        assertEquals(3500, leilao.getLances().get(1).getValor(), 0.0001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("Ps5");
        Usuario joao = new Usuario("Joao");

        leilao.propoe(new Lance(joao, 2500.0));
        leilao.propoe(new Lance(joao, 3500.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2500, leilao.getLances().get(0).getValor(), 0.0001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDoMesmoUsuario() {
        Leilao leilao = new Leilao("Ps5");
        Usuario joao = new Usuario("Joao");
        Usuario silva = new Usuario("Silva");

        leilao.propoe(new Lance(joao, 2500.0));
        leilao.propoe(new Lance(silva, 2500.0));
        leilao.propoe(new Lance(joao, 2500.0));
        leilao.propoe(new Lance(silva, 2500.0));
        leilao.propoe(new Lance(joao, 2500.0));
        leilao.propoe(new Lance(silva, 2500.0));
        leilao.propoe(new Lance(joao, 3500.0));
        leilao.propoe(new Lance(silva, 3600.0));
        leilao.propoe(new Lance(joao, 3700.0));
        leilao.propoe(new Lance(silva, 3800.0));

        leilao.propoe(new Lance(joao, 3900.0));

        assertEquals(10, leilao.getLances().size());
        assertEquals(3800.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.0001);
    }

    @Test
    public void deveDobrarOValorDoUltimoLanceDoUsuario() {
        Leilao leilao = new Leilao("Ps5");
        Usuario joao = new Usuario("Joao");
        Usuario silva = new Usuario("Silva");

        leilao.propoe(new Lance(joao, 2000.0));
        leilao.propoe(new Lance(silva, 2500.0));

        leilao.dobraLance(joao);

        assertEquals(3, leilao.getLances().size());
        assertEquals(4000.0, leilao.getLances().get(2).getValor(), 0.00001);
    }

}
