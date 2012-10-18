
@Button(caption="Texto do Botão")	
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
