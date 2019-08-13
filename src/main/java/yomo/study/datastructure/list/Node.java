package yomo.study.datastructure.list;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>Title:Node
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/7/31 14:59
 */
@Data
@AllArgsConstructor
public class Node {
    public Node Pre;
    public Node next;
    public int data;

    public Node(int data) {
        this.data = data;
    }
}