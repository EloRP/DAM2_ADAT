package UD2.A4UD2REPASOVinculación_RodalPerezEloy.EJ1_A4UD2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
public class CapturaMinima {
    private String unidade;
    private int valor;

    @XmlAttribute
    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @XmlValue
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
