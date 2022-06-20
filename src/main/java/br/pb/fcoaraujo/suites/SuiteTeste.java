package br.pb.fcoaraujo.suites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.pb.fcoaraujo.test.TesteCadastro;
import br.pb.fcoaraujo.test.TesteCampoTreinamento;
import br.pb.fcoaraujo.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class,
	TesteCampoTreinamento.class
})
public class SuiteTeste {

}
