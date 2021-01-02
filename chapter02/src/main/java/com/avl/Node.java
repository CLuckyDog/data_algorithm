package com.avl;

public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    public int leftHeight(){
        if (left == null) {return 0;}
        return left.height();
    }

    public int rightHeight(){
        if (right == null) {return 0;}
        return right.height();
    }

    /**
     * 向子树中添加节点
     * @param node
     */
    public void add(Node node) {
        if(node == null){
            return;
        }

        //判断传入的节点的值比当前子树的根节点的值是大还是小
        if (node.value < this.value){
            //如果左节点为空
            if (this.left == null){
                this.left = node;
            }else {
                //如果不为空，则递归添加
                this.left.add(node);
            }
        }else{
            //如果右节点为空
            if (this.right == null){
                this.right = node;
            }else {
                //如果不为空，则递归添加
                this.right.add(node);
            }
        }

        //检查是否平衡
        //进行右旋转
        if(leftHeight()-rightHeight()>=2){
            //双旋转
            if (left!=null&&left.leftHeight()<left.rightHeight()){
                //先对左子树进行左旋转
                left.leftRotate();
                //再对整棵树进行右旋转
                rightRotate();
                //单旋转
            }else{
                rightRotate();
            }
        }
        //进行左旋转
        if (rightHeight()-leftHeight()>=2) {
            //双旋转
            if (right!=null&&right.rightHeight()<right.leftHeight()){
                //先对右子树进行右旋转
                right.rightRotate();
                //再对整棵树进行左旋转
                leftRotate();
                //单旋转
            }else{
                leftRotate();
            }
        }
    }

    private void leftRotate() {
        Node newLeft=new Node(value);
        newLeft.left=left;
        newLeft.right=right.left;
        value=right.value;
        right=right.right;
        left=newLeft;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        //创建一个新的节点，值等于当前节点
        Node newRight=new Node(value);
        //把新节点的右子树设置为当前节点的右子树
        newRight.right=right;
        //把新节点的左子树设置为当前节点的左子树的右子树
        newRight.left=left.right;
        //把当前节点的值替换成左子节点的值
        value=left.value;
        //把当前节点的左子树设置为左子树的左子树
        left=left.left;
        //把当前节点的右子树设置为新节点
        right=newRight;
    }

    public void midShow(Node node) {
        if (node == null){
            return;
        }
        midShow(node.left);
        System.out.println(node.value);
        midShow(node.right);

    }

    public Node search(int value) {
        if (this.value==value){
            return this;
        }else if(value < this.value){
            if (left == null){
                return null;
            }
            return left.search(value);
        }else{
            if (right == null){
                return null;
            }
            return right.search(value);
        }
    }

    public Node searchParent(int value) {
        if ((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }else {
            if (this.value > value && this.left!=null){
                return this.left.searchParent(value);
            }else if(this.value <= value && this.right!=null){
                return this.right.searchParent(value);
            }
            return null;
        }
    }
}
