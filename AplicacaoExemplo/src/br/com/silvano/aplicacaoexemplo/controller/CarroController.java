package br.com.silvano.aplicacaoexemplo.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.silvano.annotations.Button;
import br.com.silvano.annotations.Component;
import br.com.silvano.annotations.ComponentType;
import br.com.silvano.annotations.Model;
import br.com.silvano.aplicacaoexemplo.model.CarroModelo;

@SessionScoped
@ManagedBean(name = "carroController")
public class CarroController {

	@Model({
		@Component(label = "Marca: ", type = ComponentType.INPUT, attribute = "marca"),
		@Component(label = "Modelo: ", type = ComponentType.INPUT, attribute = "modelo"),
		@Component(label = "Ano: ", type = ComponentType.INPUT, attribute = "ano"),
		@Component(label = "Usado: ", type = ComponentType.CHECKBOX, attribute = "usado") 
	})
	private CarroModelo carroModelo;

	@Component(label = "Quantidade: ", type = ComponentType.INPUT)
	private int quantidade;

	public CarroController() {
		carroModelo = new CarroModelo();
		carroModelo.setMarca("Chevrolet");
		carroModelo.setModelo("Opala");
		carroModelo.setAno(1980);
		carroModelo.setUsado(true);
		quantidade = 2;
	}

	@Button(caption = "Excluir")
	public void excluirCarro() {

	}
	
	@Button(caption = "Editar")
	public void editarCarro() {

	}
	
	@Button(caption = "Cadastrar")
	public void cadastrarCarro() {

	}
	
	public CarroModelo getCarroModelo() {
		return carroModelo;
	}

	public void setCarroModelo(CarroModelo carroModelo) {
		this.carroModelo = carroModelo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
