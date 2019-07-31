package yomo.study.datastructure.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * <p>Title:SingleNode
 * <p>Description:单向链表节点
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/7/31 15:09
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor(staticName="data")
public class SingleNode {
    public Node next;
    public int data;

    public SingleNode(int data) {
        this.data = data;
    }
}