package cl.mailservices.domain;

import java.io.Serializable;

/**
 * Created by cgonroja on 09-08-19.
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 2L;

    private String mensaje;
    private int codigoHttp;

    public Response(String mensaje, int codigoHttp) {
        this.mensaje = mensaje;
        this.codigoHttp = codigoHttp;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigoHttp() {
        return codigoHttp;
    }

    public void setCodigoHttp(int codigoHttp) {
        this.codigoHttp = codigoHttp;
    }
}
