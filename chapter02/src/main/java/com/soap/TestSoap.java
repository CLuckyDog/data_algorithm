package com.soap;

import java.net.URL;

import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class TestSoap {
    public static void main(String[] args) throws Exception {
        //一般如果没有注解targetNamespace的话，默认生成的就是接口这个文件的路径名
        String nameSpaceURI = "http://service.cm.com";
//        String publishUrl = "http://localhost:8087/services/ServiceMonitor?wsdl";
        String publishUrl = "http://localhost:8087/services/TestAxisService?wsdl";
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(publishUrl));
        //指定接口路径，要调用的方法名
//        call.setOperationName(new QName(nameSpaceURI, "monitor"));
        call.setOperationName(new QName(nameSpaceURI, "testAxis"));
        //如果没用@WebParam(name="name")来表明参数名，则方法的入参是啥，这边就必须传一样的参数名才行。不然报错。
        call.addParameter("name", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("age", XMLType.XSD_INT, ParameterMode.IN);
        call.setReturnType(XMLType.XSD_STRING);
        String name = "zhanglifeng";
        int age = 18;
        Object[] obj = new Object[] { name,age};
        String result = (String) call.invoke(obj);

        System.out.println(result);
    }
}
