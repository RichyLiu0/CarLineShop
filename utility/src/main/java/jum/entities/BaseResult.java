package jum.entities;


/**
 * 功能简述:实体类<br>
 * 详细描述:<br>
 *
 * @author 刘伟锐
 * @date 2020/7/20
 */



public class BaseResult implements  java.io.Serializable {

    private static final long serialVersionUID = -802357682595188345L;

    private  int result;
    private String msg ;

    public int getResult() {
        return result;
    }

    public BaseResult setResult(int result) {
        this.result = result;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
