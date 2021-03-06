package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dao.LeilaoDao;

import java.util.Calendar;
import java.util.List;

public class EncerradorDeLeilao {

    private int total = 0;
    private final LeilaoDao leilaoDao;

    public EncerradorDeLeilao(LeilaoDao leilaoDao) {
        this.leilaoDao = leilaoDao;
    }

    public void encerra() {
        List<Leilao> todosLeiloesCorrentes = leilaoDao.correntes();

        for (Leilao leilao : todosLeiloesCorrentes) {
            if (comecouSemanaPassada(leilao)) {
                leilao.encerra();
                total++;
                leilaoDao.atualiza(leilao);
            }
        }
    }

    private boolean comecouSemanaPassada(Leilao leilao) {
        return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
    }

    private int diasEntre(Calendar inicio, Calendar fim) {
        Calendar data = (Calendar) inicio.clone();
        int diasNoIntervalo = 0;
        while (data.before(fim)) {
            data.add(Calendar.DAY_OF_MONTH, 1);
            diasNoIntervalo++;
        }

        return diasNoIntervalo;
    }

    public int getTotalEncerrados() {
        return total;
    }
}
