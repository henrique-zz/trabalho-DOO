package metodos;

import java.time.LocalTime;

public class HorariosMotorista {
    private LocalTime inicio;
    private LocalTime fim;

    public HorariosMotorista(LocalTime inicio, LocalTime fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    public void setFim(LocalTime fim) {
        this.fim = fim;
    }

    public boolean estaNoIntervalo(LocalTime hora) {
        return !hora.isBefore(inicio) && !hora.isAfter(fim); //retorna "true" caso o horário digitado pelo usuário esteja dentro do intervalo de tempo
    }
}
