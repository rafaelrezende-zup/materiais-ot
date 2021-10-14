import br.com.caelum.leilao.dao.LeilaoDao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.EncerradorDeLeilao;
import builder.CriadorDeLeilao;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        antiga.set(2020, Calendar.FEBRUARY, 20);

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

        LeilaoDao leilaoDao = mock(LeilaoDao.class);

        EncerradorDeLeilao encerradorDeLeilao = new EncerradorDeLeilao(leilaoDao);

        when(leilaoDao.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));

        encerradorDeLeilao.encerra();

        // assertEquals(2, encerradorDeLeilao.getTotalEncerrados());
        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }

    @Test
    public void naoDeveEncerrarLeiloesQueComecaramMenosDeUmaSemanaAtras() {

        Calendar ontem = Calendar.getInstance();
        ontem.add(Calendar.DAY_OF_MONTH, -1);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
                .naData(ontem).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
                .naData(ontem).constroi();

        LeilaoDao daoFalso = mock(LeilaoDao.class);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));

        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
        encerrador.encerra();

        assertEquals(0, encerrador.getTotalEncerrados());
        assertFalse(leilao1.isEncerrado());
        assertFalse(leilao2.isEncerrado());
    }

    @Test
    public void naoDeveEncerrarLeiloesCasoNaoHajaNenhum() {

        LeilaoDao daoFalso = mock(LeilaoDao.class);
        when(daoFalso.correntes()).thenReturn(new ArrayList<Leilao>());

        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
        encerrador.encerra();

        assertEquals(0, encerrador.getTotalEncerrados());
    }

}
