package telas;

import metodos.HorariosMotorista;
import modelos.Motorista;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TelaMotoristas extends JFrame implements TelasInterface, ActionListener {
    private Usuario usuario;
    private String nome, cpf, email,telefone;
    private JButton botaoVisualizarTodos, botaoFiltrar, botaoVoltar;
    private List<Motorista> motoristas = new ArrayList<>();

    public TelaMotoristas(Usuario usuario){
        this.usuario = usuario;
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();

        adicionaMotorista("Motorista 1", "(49) 11111-1111", LocalDate.of(2019,2,12),
                new HorariosMotorista(LocalTime.of(6, 0), LocalTime.of(12, 0)));

        adicionaMotorista("Motorista 2", "(49) 22222-2222", LocalDate.of(2020,5,19),
                new HorariosMotorista(LocalTime.of(14, 0), LocalTime.of(19, 0)));

        adicionaMotorista("Motorista 3", "(49) 33333-3333", LocalDate.of(2021,10,21),
                new HorariosMotorista(LocalTime.of(6, 0), LocalTime.of(16, 0)));

        adicionaMotorista("Motorista 4", "(49) 44444-4444", LocalDate.of(2018,1,5),
                new HorariosMotorista(LocalTime.of(17, 0), LocalTime.of(23, 0)));

        adicionaMotorista("Motorista 5", "(49) 55555-5555", LocalDate.of(2023,9,25),
                new HorariosMotorista(LocalTime.of(0, 0), LocalTime.of(4, 0)));

        //no momento que chammos a TelaMotoristas, nós já criamos 5 motoristas no aplicativo para nosso exemplo de aplicação, visto que é apenas a visão do usuário então não
        //temos a visão de se cadastrar como motorista
    }

    @Override
    public void tela(){
        this.setTitle("Motoristas Cadastrados");
        this.setSize(250, 330);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel label = new JLabel("Selecione a opção desejada");
        label.setFont(new Font("Arial", Font.BOLD, 15));

        botaoVisualizarTodos = criaBotao("Visualizar Todos os Motoristas");
        botaoFiltrar = criaBotao("Filtrar Motoristas por Horário");
        botaoVoltar = criaBotao("Voltar");

        gbc.gridy = 0;
        this.add(label,gbc);

        gbc.gridy = 1;
        this.add(botaoVisualizarTodos,gbc);

        gbc.gridy = 2;
        this.add(botaoFiltrar,gbc);

        gbc.gridy = 3;
        this.add(botaoVoltar,gbc);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JButton criaBotao(String tituloBotao){
        JButton botao = new JButton(tituloBotao);
        botao.setSize(100,100);
        botao.addActionListener(this);
        return botao;
    }

    private void adicionaMotorista(String nome, String numeroTelefone, LocalDate dataCadastro, HorariosMotorista horarios){
        motoristas.add(new Motorista(nome, numeroTelefone, dataCadastro, horarios));
    }

    private void telaVisualizarTodos(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoVisualizarTodos){
            this.dispose();
            new TelaTodosMotoristas(usuario,motoristas).tela();
        }

        if(e.getSource() == botaoFiltrar){
            this.dispose();
            new TelaFiltrarMotoristas(usuario,motoristas).tela();
        }

        if(e.getSource() == botaoVoltar){
            this.dispose();
            new TelaInicialPrograma(usuario).tela();
        }
    }
}
