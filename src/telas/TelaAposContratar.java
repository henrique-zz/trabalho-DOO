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
    private JButton botaoContratar, botaoVoltar, botaoVisualizarPerfil;


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
        this.setSize(400,400);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
