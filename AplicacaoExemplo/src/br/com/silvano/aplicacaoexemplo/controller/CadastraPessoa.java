package br.com.silvano.aplicacaoexemplo.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.silvano.annotations.Button;
import br.com.silvano.annotations.Component;
import br.com.silvano.annotations.ComponentType;
import br.com.silvano.annotations.Model;
import br.com.silvano.aplicacaoexemplo.model.Pessoa;

@SessionScoped
@ManagedBean(name = "cadastraPessoa")
public class CadastraPessoa {

	@Model({ 
		@Component(label = "Nome: ", type = ComponentType.INPUT, attribute = "nome"), 
		@Component(label = "Idade: ", type = ComponentType.INPUT, attribute = "idade"), 
		@Component(label = "Maior Idade: ", type = ComponentType.CHECKBOX, attribute = "maiorIdade") 
	})
	private Pessoa pessoa;

	public CadastraPessoa() {
		pessoa = new Pessoa();
		pessoa.setNome("Silvano");
		pessoa.setIdade(28);
		pessoa.setMaiorIdade(true);
	}
	@Button(caption = "Excluir")
	public void excluirPessoa() {

	}
	@Button(caption = "Editar")
	public void editarPessoa() {

	}
	@Button(caption = "Criar")
	public void criarPessoa() {

	}	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
}
