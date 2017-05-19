Curso de Refatoração - Aula2
========================

* A Classe `Trem` tem o método `dis()` no qual não é muito expressivo, temos que analisar a cada
linha o que está sendo realizado. Dificultando o entendimento do método

	public class Trem {
		private List<Vagao> vagoes;
		private int capacidade;
		public boolean disp(int reservas) {
			int r = 0;
			for(Vagao v : vagoes) {
				r += v.reservados();
			}
			r = capacidade - r;
			return r > reservas; 
		}
	}
	
* Podemos alterar isso com boas práticas envolvendo renomeação de variáveis, sepação para métodos, e....

* A variável `r` indica quantos vagões estão reservados, podemos alterar seu nome para um nome mais expressivo `lugaresJaReservados`

	public boolean disp(int reservas) {
		int lugaresJaReservados = 0;
		for(Vagao v : vagoes) {
			lugaresJaReservados += v.reservados();
		}
		lugaresJaReservados = capacidade - lugaresJaReservados;
		return lugaresJaReservados > reservas; 
	}
	
* `lugaresJaReservados` também recebe o cálculo dos lugares já reservados com a capacidade do trem, podemos alterar isto, criando
uma nova variável `lugaresDisponiveis` que indica a quantidade de lugares disponíveis no trem

	public boolean disp(int reservas) {
		int lugaresJaReservados = 0;
		for(Vagao v : vagoes) {
			lugaresJaReservados += v.reservados();
		}
		int lugaresDisponiveis = capacidade - lugaresJaReservados;
		return lugaresDisponiveis > reservas; 
	}
	
* Podemos também separar o for, para um outro método, deixando mais expressivo que estamos buscando os lugares reservados

	public boolean disp(int reservas) {
		int lugaresJaReservados = lugaresJaReservados();
		int lugaresDisponiveis = capacidade - lugaresJaReservados;
		return lugaresDisponiveis > reservas; 
	}
	
	private int lugaresJaReservados(){
		int lugaresJaReservados = 0;
			for(Vagao v : vagoes) {
				lugaresJaReservados += v.reservados();
			}
		return lugaresJareservados;
	}

* Podemos alterar também o nome da váriavel que damos a cada objeto buscado no for `v` para `vagao`

	private int lugaresJaReservados(){
		int lugaresJaReservados = 0;
			for(Vagao vagao : vagoes) {
				lugaresJaReservados += vagao.reservados();
			}
		return lugaresJareservados;
	}