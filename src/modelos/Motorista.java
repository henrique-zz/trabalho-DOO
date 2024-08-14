package modelos;

import metodos.HorariosMotorista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Objects;

public class Motorista {
    private String nome;
    private String numeroTelefone;
    private LocalDate dataCadastro;
    private HorariosMotorista horarios;

    public Motorista(String nome, String numeroTelefone, LocalDate dataCadastro, HorariosMotorista horarios) {
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.dataCadastro = dataCadastro;
        this.horarios = horarios;
    }

    public String tempoNoAplicativo(){
        Period periodo = Period.between(dataCadastro,LocalDate.now()); //verifica o tempo entre a data de cadastro inserida e o tempo atual
        return periodo.getYears() + " anos e " + periodo.getMonths() + " meses";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorista motorista = (Motorista) o;
        return Objects.equals(getNome(), motorista.getNome()) && Objects.equals(getNumeroTelefone(), motorista.getNumeroTelefone()) && Objects.equals(getDataCadastro(), motorista.getDataCadastro());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getNumeroTelefone(), getDataCadastro());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public HorariosMotorista getHorarios() {
        return horarios;
    }

    public void setHorarios(HorariosMotorista horarios) {
        this.horarios = horarios;
    }

    public boolean motoristaDisponivel(LocalTime horario){
        return horarios.estaNoIntervalo(horario);
    }
}
