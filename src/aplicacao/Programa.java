package aplicacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Funcionario;

public class Programa {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
	
		System.out.print("Informe o diretorio completo do arquivo: ");
		String diretorio = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(diretorio))){
			
			List<Funcionario> listaFuncionario = new ArrayList<>();
			
			String linha = br.readLine();
			while (linha != null) {
				String[] campos = linha.split(",");
				String nome = campos[0];
				String email = campos[1];
				Double salario = Double.parseDouble(campos[2]);
				
				listaFuncionario.add(new Funcionario(nome, email, salario));
				
				linha = br.readLine();
			}
			
			System.out.print("Informe o salario: ");
			double salarioAux = sc.nextDouble();
			
			List<String> filtroSalarioEmail = listaFuncionario.stream()
					.filter(f -> f.getSalario() > salarioAux)
					.map(f -> f.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email dos funcionários cujo salário é superior do que " + String.format("%.2f", salarioAux));
			filtroSalarioEmail.forEach(System.out::println);
			
			double somaSalario = listaFuncionario.stream()
					.filter(f -> f.getNome().charAt(0) == 'M')
					.map(f -> f.getSalario())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("Soma do salario das pessoas com nome iniciado por 'M': " + String.format("%.2f", somaSalario));
		} 
		catch (IOException e) {
			System.out.println("Erro ao ler o arquivo informado: " + e.getMessage());
		}
		sc.close();
	}

}
