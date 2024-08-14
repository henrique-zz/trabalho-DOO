package telas;

import exceptions.CampoNuloException;
import modelos.Motorista;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaAposContratar extends JFrame implements TelasInterface, ActionListener {
    private Usuario usuario;
    private String nome, cpf, email, telefone;
    private List<Motorista> motoristas;
    private JButton botaoContratarMaisMotoristas, botaoVoltarInicio, botaoVisualizarMotoristasContratados;


    public TelaAposContratar(Usuario usuario){
        this.usuario = usuario;
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.motoristas = usuario.getListaMotoristas();
    }

    @Override
    public void tela() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Motorista Contratado");
        this.setSize(290,330);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;

        JLabel label = new JLabel("Selecione a Opção Desejada");
        gbc.gridy = 0;
        this.add(label, gbc);

        botaoContratarMaisMotoristas = new JButton("Contratar Outros Motoristas");
        botaoContratarMaisMotoristas.addActionListener(this);
        gbc.gridy = 1;
        this.add(botaoContratarMaisMotoristas, gbc);

        botaoVisualizarMotoristasContratados = new JButton("Visualizar Motoristas Contratados");
        botaoVisualizarMotoristasContratados.addActionListener(this);
        gbc.gridy = 2;
        this.add(botaoVisualizarMotoristasContratados, gbc);

        botaoVoltarInicio = new JButton("Voltar à Tela Inicial");
        botaoVoltarInicio.addActionListener(this);
        gbc.gridy = 3;
        this.add(botaoVoltarInicio, gbc);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoContratarMaisMotoristas){
            this.dispose();
            new TelaMotoristas(usuario).tela();
        }

        if(e.getSource() == botaoVisualizarMotoristasContratados){

        }

        if(e.getSource() == botaoVoltarInicio){
            this.dispose();
            new TelaInicialPrograma(usuario).tela();
        }
    }
}
