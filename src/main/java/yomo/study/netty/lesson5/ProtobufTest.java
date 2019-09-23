package yomo.study.netty.lesson5;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @program: yomo
 * @description:
 * @author: hh
 * @create: 2019-09-22 22:12
 **/
public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Students build = DataInfo.Students.newBuilder().setAge(12).setAddress("sadf").setName("asdfsf").build();
        byte[] bytes = build.toByteArray();
        DataInfo.Students students = DataInfo.Students.parseFrom(bytes);
        System.out.println(students.getAddress());
        System.out.println(students.getAge());
        System.out.println(students.getName());
    }
}
