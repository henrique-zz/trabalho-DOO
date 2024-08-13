package telas;

import exceptions.CampoNuloException;
import modelos.Motorista;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaTodosMotoristas extends JFrame implements TelasInterface, ActionListener {
    private Usuario usuario;
    private String nome, cpf, email, telefone;
    private List<Motorista> motoristas;
    private Motorista motoristaSelecionado;
    private JFrame framePerfil;
    private JButton botaoContratar, botaoVoltar, botaoVisualizarPerfil;
    private JComboBox<String> comboBoxMotoristas;

    public TelaTodosMotoristas(Usuario usuario, List<Motorista> motoristas){
        this.usuario = usuario;
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();

        this.motoristas = motoristas;
    }

    @Override
    public void tela(){
        comboBoxMotoristas = new JComboBox<>();

        for(Motorista m : motoristas){
            comboBoxMotoristas.addItem(m.getNome());
        }

        this.setTitle("Motoristas Disponíveis");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;

        JLabel label = new JLabel("Selecione um motorista:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(label, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboBoxMotoristas, gbc);

        botaoVisualizarPerfil = new JButton("Visualizar Perfil");
        botaoVisualizarPerfil.addActionListener(this);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;

        this.add(botaoVisualizarPerfil, gbc);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoVisualizarPerfil){
            this.dispose();
            int indiceSelecionado = comboBoxMotoristas.getSelectedIndex();
            motoristaSelecionado = motoristas.get(indiceSelecionado);
            mostrarPerfilMotorista(motoristaSelecionado);
        }

        if (e.getSource() == botaoVoltar) {
            framePerfil.dispose();
            new TelaMotoristas(usuario).tela();
        }

        if (e.getSource() == botaoContratar){

        }

    }

    private void mostrarPerfilMotorista(Motorista motorista) {
        framePerfil = new JFrame("Perfil do Motorista");
        framePerfil.setSize(400, 300);
        framePerfil.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePerfil.setLayout(new BorderLayout());

        JPanel panelDetalhes = new JPanel();
        panelDetalhes.setLayout(new BoxLayout(panelDetalhes, BoxLayout.Y_AXIS));

        String detalhesMotorista = "Nome: " + motorista.getNome() +
                "\nTelefone: " + motorista.getNumeroTelefone() +
                "\nData de Cadastro: " + motorista.getDataCadastro() +
                "\nHorários: " + motorista.getHorarios().getInicio() + " - " + motorista.getHorarios().getFim() +
                "\nTempo no aplicativo: " + motorista.tempoNoAplicativo();

        JTextArea areaDetalhes = new JTextArea(detalhesMotorista);
        areaDetalhes.setEditable(false);
        areaDetalhes.setLineWrap(true);
        areaDetalhes.setWrapStyleWord(true);

        panelDetalhes.add(new JScrollPane(areaDetalhes));

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        botaoContratar = new JButton("Contratar Motorista");
        botaoContratar.addActionListener(this);
        panelBotoes.add(botaoContratar);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(this);
        panelBotoes.add(botaoVoltar);

        framePerfil.add(panelDetalhes, BorderLayout.CENTER);
        framePerfil.add(panelBotoes, BorderLayout.SOUTH);

        framePerfil.setLocationRelativeTo(null);
        framePerfil.setVisible(true);
    }
}
