package jum.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述:实体类<br>
 * 详细描述:<br>
 *
 * @author 刘伟锐
 * @date 2019/10/18 11:43
 */

public class ApiRequest<T extends java.io.Serializable > implements  java.io.Serializable {

    private static final long serialVersionUID = 5114951910626303311L;

    private  String type;
    private List<T> data;

    public ApiRequest() {
        this.type = "";
        this.data = null;
    }

    public ApiRequest(String type, List<T> data) {
        this.type = type;
        this.data = new ArrayList<T>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
