import br.com.silvano.annotations.Button;
import br.com.silvano.annotations.Component;
import br.com.silvano.annotations.ComponentType;
import br.com.silvano.annotations.Model;


public class Teste {

	
@Button(caption="Texto do Bot�o")	
public void metodoDaClasseDeControle() {

}

@Component(label="Label do Campo:", type=ComponentType.INPUT)
String campoDaClasseDeControle1;
@Component(label="Label do Campo:", type=ComponentType.CHECKBOX)
String campoDaClasseDeControle2;


@Model({
	@Component(label="Label do Campo:", type=ComponentType.INPUT, attribute="atributoDaClasseDeModelo"),
	@Component(label="Label do Campo:", type=ComponentType.CHECKBOX, attribute="atributoDaClasseDeModelo")
})
ClasseDeModelo classeDeModelo;
	
	
	
}

class ClasseDeModelo {
	
}
