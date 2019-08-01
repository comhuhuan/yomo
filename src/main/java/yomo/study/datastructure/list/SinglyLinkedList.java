package yomo.study.datastructure.list;


/**
 * <p>Title:SinglyLinkedList
 * <p>Description:单向链表
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/7/31 15:17
 */
public class SinglyLinkedList {


    public static void main(String[] args) {
        addData(1);
        addData(2);
        addData(3);
        addData(4);
        addData(5);
        addData(6);


        printListReversely(header);
        SingleNode singleNode = reverseLinkList(header);
        printListReversely(singleNode);

    }

    static SingleNode header = new SingleNode(0);

    public static void addNode(int value) {
        SingleNode node = new SingleNode(value);

    }


    /**
     * 单向链表反转
     * <p>
     * 面试官：请说一下链表跟数组的区别？
     * <p>
     * 我：数组静态分配内存，链表动态分配内存；数组在内存中连续，链表不连续；数组利用下标定位，时间复杂度为O(1)，链表定位元素时间复杂度O(n)；数组插入或删除元素的时间复杂度O(n)，链表的时间复杂度O(1)。
     * <p>
     * 说人话  数组的优点
     * 随机访问性强（通过下标进行快速定位）
     * 查找速度快
     * 数组的缺点
     * 插入和删除效率低（插入和删除需要移动数据）
     * 可能浪费内存（因为是连续的，所以每次申请数组之前必须规定数组的大小，如果大小不合理，则可能会浪费内存）
     * 内存空间要求高，必须有足够的连续内存空间。
     * 数组大小固定，不能动态拓展
     * 链表的优点
     * 插入删除速度快（因为有next指针指向其下一个节点，通过改变指针的指向可以方便的增加删除元素）
     * 内存利用率高，不会浪费内存（可以使用内存中细小的不连续空间（大于node节点的大小），并且在需要空间的时候才创建空间）
     * 大小没有固定，拓展很灵活。
     * 链表的缺点
     * 不能随机查找，必须从第一个开始遍历，查找效率低
     * <p>
     * <p>
     * 那请说一下单链表和双链表的区别？
     * 单链表只有一个指向下一结点的指针，也就是只能next
     * 双链表除了有一个指向下一结点的指针外，还有一个指向前一结点的指针，可以通过prev()快速找到前一结点，顾名思义，单链表只能单向读取
     * 删除单链表中的某个结点时，一定要得到待删除结点的前驱，得到该前驱有两种方法，第一种方法是在定位待删除结点的同时一路保存当前结点的前驱。第二种方法是在定位到待删除结点之后，重新从单链表表头开始来定位前驱。尽管通常会采用方法一。但其实这两种方法的效率是一样的，指针的总的移动操作都会有2*i次。而如果用双向链表，则不需要定位前驱结点。因此指针总的移动操作为i次。
     * ---------------------
     * <p>
     * <p>
     * 查找时也一样，我们可以借用二分法的思路，从head（首节点）向后查找操作和last（尾节点）向前查找操作同步进行，这样双链表的效率可以提高一倍。
     * <p>
     * https://www.cnblogs.com/wyqx/p/3346293.html
     * https://www.cnblogs.com/Java3y/p/8664874.html
     * https://blog.csdn.net/kangxidagege/article/details/80211225  浅谈单链表与双链表的区别
     * https://blog.csdn.net/yoonerloop/article/details/81489665
     *
     * @param singleNode
     */
    void reverseList(SingleNode singleNode) {

    }


    /**
     * 单向链表新增
     *
     * @param value
     */
    public static void addData(int value) {
        SingleNode newNode = new SingleNode(value);
        SingleNode temp = header;
        //找到尾部节点
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * 遍历列表
     *
     * @param head
     */
    public static void traverse(SingleNode head) {
        SingleNode temp = head.next;
        while (temp.next != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void insertNode(SingleNode head, int index, int value) {
        //首先需要判断指定位置是否合法，
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("插入位置不合法。");
            return;
        }
        //初始化要插入的节点
        SingleNode insertNode = new SingleNode(value);
        SingleNode temp = head;
        int position = 0;
        while (temp.next != null) {


            //上个节点位置
            if (position == (index - 1)) {
                //上个节点的指向交给插入节点
                insertNode.next = temp.next;
                //上个节点的指向指向插入节点
                temp.next = insertNode;
            }
            temp = temp.next;
            position++;
        }


    }

    /**
     * 获取节点长度
     *
     * @param head
     * @return
     */
    private static int linkListLength(SingleNode head) {
        int length = 0;

        //临时节点，从首节点开始
        SingleNode temp = head.next;
        while (temp.next != null) {
            length++;
            temp = temp.next;

        }
        return length;
    }

    /**
     * 删除节点
     * * @param head
     * * @param index
     */
    public static void deleteNode(SingleNode head, int index) {

        //首先需要判断指定位置是否合法，
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("删除位置不合法。");
            return;
        }
        int position = 0;
        SingleNode temp = head;
        while (temp.next != null) {
            if ((index - 1) == position) {

                SingleNode deletnode = temp.next;
                temp.next = deletnode.next;
                return;
            }
            position++;
            temp = temp.next;
        }

    }


    /**
     * 对链表进行排序 冒泡排序
     *
     * @param head
     */
    public static void sortLinkList(SingleNode head) {
        SingleNode current;
        SingleNode next;
        for (current = head.next; current.next != null; current = current.next) {
            for (next = head.next; next.next != null; next = next.next) {
                if (next.data > next.next.data) {
                    int temp = next.data;
                    next.data = next.next.data;
                    next.data = temp;
                }
            }
        }

    }

    /**
     * 删除链表重复数据
     *
     * @param head
     */
    public static void deleteDuplecate(SingleNode head) {
        SingleNode temp = head.next;
        SingleNode nextnode = temp.next;

        while (temp != null) {
            while (nextnode != null) {
                if (temp.data == nextnode.data) {
                    temp.next = nextnode.next.next;

                } else {
                    nextnode = nextnode.next;
                }
            }
            temp = temp.next;
        }

    }

    /**
     * 两个指针，一个先走k个节点，当这个指针为null的时候符合条件
     *
     * @param head
     * @param k
     * @return
     */
    public static SingleNode findKNode(SingleNode head, int k) {
        SingleNode p1 = head;
        SingleNode p2 = head;
        for (int i = 0; i < k - 1; i++) {
            p1 = p1.next;

        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * // 一个走一步，一个走两步，直到为null，走一步的到达的就是中间节点
     *
     * @param head
     * @return
     */
    public static SingleNode searchMid(SingleNode head) {
        SingleNode p1 = head;
        SingleNode p2 = head;

        while (p2 != null && p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;

    }


    /**
     * 通过递归从尾到头输出单链表
     *
     * 思路：
     * （1）先反转单链表，再正序输出。（麻烦，不用）
     * （2）从头到尾遍历链表，每经过一个节点的时候，把该节点放到一个栈中，当遍历完整个链表后，再从栈顶开始输出节点的值。（需要维护一个额外的栈空间，麻烦）
     * （3）递归方法。既然想到了栈来实现这个函数，而递归本质上就是一个栈结构，所以考虑用递归来实现。要实现反过来输出链表，每访问到一个节点的时候，先递归输出它后面的节点，再输出该节点自身，即可实现从尾到头输出单链表。具体实现见代码。
     * --------------------
     * @param head 头节点
     */
    // TODO: 2019/8/1  通过调用
    public static void printListReversely(SingleNode head) {
        if (head != null) {
            printListReversely(head.next);
            System.out.println(head.data);
        }
    }


    /**
     * 实现链表的反转
     *
     * @param node 链表的头节点
     */
    // TODO: 2019/8/1  HH
    public static SingleNode reverseLinkList(SingleNode node) {

        SingleNode prev;
        if (node == null || node.next == null) {
            prev = node;
        } else {
            SingleNode tmp = reverseLinkList(node.next);
            node.next.next = node;
            node.next = null;
            prev = tmp;
        }
        return prev;

    }

}