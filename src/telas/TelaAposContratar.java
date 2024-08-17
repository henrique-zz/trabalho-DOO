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
    private JButton botaoContratarMaisMotoristas, botaoVoltarInicio, botaoVisualizarMotoristasContratados, botaoVoltar;
    private JFrame framePerfilMotoristasCadastrados;

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
        label.setFont(new Font("Arial", Font.BOLD, 15));
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

    private void frameMotoristasContratados(){
        framePerfilMotoristasCadastrados= new JFrame("Motoristas Contratados");
        framePerfilMotoristasCadastrados.setSize(300,300);
        framePerfilMotoristasCadastrados.setDefaultCloseOperation(EXIT_ON_CLOSE);
        framePerfilMotoristasCadastrados.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;

        JLabel label = new JLabel("Motoristas contratados pelo usuário: " + usuario.getNome());
        label.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 0;
        framePerfilMotoristasCadastrados.add(label,gbc);

        int i = 1;
        for(Motorista m : usuario.getListaMotoristas()){
            JLabel labelMotorista = new JLabel(m.getNome());
            label.setFont(new Font("Arial", Font.BOLD, 11));
            gbc.gridy = i;
            framePerfilMotoristasCadastrados.add(labelMotorista,gbc);

            i++;
        }

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(this);
        gbc.gridy = i;
        framePerfilMotoristasCadastrados.add(botaoVoltar, gbc);

        framePerfilMotoristasCadastrados.setLocationRelativeTo(null);
        framePerfilMotoristasCadastrados.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoContratarMaisMotoristas){
            this.dispose();
            new TelaMotoristas(usuario).tela();
        }

        if(e.getSource() == botaoVisualizarMotoristasContratados){
            this.dispose();
            frameMotoristasContratados();
        }

        if(e.getSource() == botaoVoltarInicio){
            this.dispose();
            new TelaInicialPrograma(usuario).tela();
        }

        if(e.getSource() == botaoVoltar){
            framePerfilMotoristasCadastrados.dispose();
            new TelaAposContratar(usuario).tela();
        }
    }
}
