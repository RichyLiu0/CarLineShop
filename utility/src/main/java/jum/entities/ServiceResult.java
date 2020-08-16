package jum.entities;

/**
 * 功能简述:实体类<br>
 * 详细描述:<br>
 *
 * @author 刘伟锐
 * @date 2019/10/18 11:43
 */

public class ServiceResult implements  java.io.Serializable {

    private static final long serialVersionUID = 5114951910506303311L;

    public boolean isSucess() {
        return isSucess;
    }

    public ServiceResult setSucess(boolean sucess) {
        isSucess = sucess;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceResult setMessage(String message) {
        this.message = message;
        return this;
    }

    private  boolean isSucess;
    private  String message;

}
