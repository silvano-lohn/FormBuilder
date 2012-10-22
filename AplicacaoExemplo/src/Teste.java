
@Component(label="Label do Campo:", type=ComponentType.INPUT)
private String campoDaClasseDeControle1;
@Component(label="Label do Campo:", type=ComponentType.CHECKBOX)
private String campoDaClasseDeControle2;


@Model({
   @Component(label="Label do Campo:", type=ComponentType.INPUT, attribute="atributoDaClasseDeModelo"),
   @Component(label="Label do Campo:", type=ComponentType.CHECKBOX, attribute="atributoDaClasseDeModelo")
})
ClasseDeModelo classeDeModelo;







@Button(caption="Texto do Botão")	
public void metodoDaClasseDeControle() {

}



@Model({
	@Component(label="Label do Campo:", type=ComponentType.INPUT, attribute="atributoDaClasseDeModelo"),
	@Component(label="Label do Campo:", type=ComponentType.CHECKBOX, attribute="atributoDaClasseDeModelo")
})
ClasseDeModelo classeDeModelo;
	
	
	
}

class ClasseDeModelo {
	
}
