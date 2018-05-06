package br.com.jkavdev.alura.tdd.test.leilao.util.matchers;

import br.com.jkavdev.alura.tdd.leilao.dominio.Lance;
import br.com.jkavdev.alura.tdd.leilao.dominio.Leilao;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {

    private final Lance lance;

    public LeilaoMatcher(Lance lance) {
        this.lance = lance;
    }

    @Override
    protected boolean matchesSafely(Leilao item) {
        return item.getLances().contains(lance);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("leilao com lance " + lance.getValor());
    }

    public static Matcher<Leilao> temUmLance(Lance lance) {
        return new LeilaoMatcher(lance);
    }
}
