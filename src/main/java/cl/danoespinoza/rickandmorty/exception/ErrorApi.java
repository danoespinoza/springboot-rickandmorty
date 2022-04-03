package cl.danoespinoza.rickandmorty.exception;

public class ErrorApi {
    private String error;

    public ErrorApi() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Error [error=" + error + "]";
    }
}
