package me.tmonteiro.clashroyale.vo;

public class BaseVO<T1, T2> {

    private T1 status;
    private T2 result;

    public T1 getStatus() {
        return status;
    }

    public void setStatus(T1 status) {
        this.status = status;
    }

    public T2 getResult() {
        return result;
    }

    public void setResult(T2 result) {
        this.result = result;
    }
}
