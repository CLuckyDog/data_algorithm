package com.avl;

public class BinarySortTree {
    Node root;

    /**
     * 向二叉排序树中新增节点
     * @param node
     */
    public void add(Node node){
        //如果是一颗空树
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    //中序遍历，结果是从小到大的顺序
    public void midShow(){
        if(root != null){
            root.midShow(root);
        }
    }

    public Node search(int value) {
        if (root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    /**
     * 删除节点
     * @param value
     */
    public void delete(int value){
        if (root == null){
            return;
        }else {
            //找到这个节点
            Node target = root.search(value);
            //如果节点为空
            if (target == null){
                return;
            }
            //查找父节点
            Node parent = searchParent(value);
            //要删除的节点是叶子节点
            if (target.left==null&&target.right==null){
                //要删除的节点是父节点的左子节点
                if (parent.left.value == value){
                    parent.left=null;
                }else{
                    //要删除的节点是父节点的右子节点
                    parent.right=null;
                }
                //删除的节点有两个子节点
            }else if (target.left!=null&&target.right!=null){
                //删除右子树中值最小的节点，获取到该节点的值
                int min = deleteMin(target.right);
                //替换目标节点中的值
                target.value=min;
                //删除的节点有一个子节点
            }else{
                //有左子节点
                if (target.left!=null){
                    //要删除的节点是父节点的左子节点
                    if (parent.left.value == value){
                        parent.left=target.left;
                    }else{
                        //要删除的节点是父节点的右子节点
                        parent.right=target.left;
                    }
                    //有右子节点
                }else{
                    //要删除的节点是父节点的左子节点
                    if (parent.left.value == value){
                        parent.left=target.right;
                    }else{
                        //要删除的节点是父节点的右子节点
                        parent.right=target.right;
                    }
                }
            }
        }
    }

    /**
     * 删除一棵树中最小的节点
     * @param node
     * @return
     */
    private int deleteMin(Node node) {
        Node target=node;
        //递归向左找最小的节点
        while (target.left!=null){
            target=target.left;
        }
        //删除最小的节点
        delete(target.value);
        return target.value;
    }

    /**
     * 查找父节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return  root.searchParent(value);
        }
    }
}
