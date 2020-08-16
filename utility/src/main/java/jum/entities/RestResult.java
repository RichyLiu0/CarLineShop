package jum.entities;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 功能简述:实体类<br>
 * 详细描述:<br>
 *
 * @author 刘伟锐
 * @date 2019/10/21 9:29
 */


@XmlRootElement(name = "RestResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResult<T>  implements  java.io.Serializable {

    private static final long serialVersionUID = -802357682595188365L;

    private  int result;
    private String msg ;
    private  T data;


    public  RestResult(){}

    public RestResult(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public RestResult setResult(int result) {
        this.result = result;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RestResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RestResult setData(T data) {
        this.data = data;
        return this;
    }
}
