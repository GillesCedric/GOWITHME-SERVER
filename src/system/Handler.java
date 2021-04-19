package system;

import java.io.Serializable;

import utilities.Keywords;

public class Handler<T> implements Serializable {

	private static final long serialVersionUID = -7159938509163115594L;
	private Keywords action;
    private T data;
    private String name;

    public Handler(Keywords action, T data,String name) {
        this.action = action;
        this.data = data;
        this.name = name;
    }
    
    public Handler(Keywords action, T data) {
        this.action = action;
        this.data = data;
    }

    public Keywords getAction() {
        return action;
    }

    public void setAction(Keywords action) {
        this.action = action;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    

}
