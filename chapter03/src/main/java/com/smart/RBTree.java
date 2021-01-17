package com.smart;

import com.sun.org.apache.regexp.internal.RE;

public class RBTree <K extends Comparable<K>,V>{
    private static final boolean RED=false;
    private static final boolean BLACK=true;

    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        this.root = root;
    }

    /**
     * 围绕p左旋
     *       pf                    pf
     *      /                     /
     *     p                     pr(r)
     *    / \          ==>      / \
     *  pl  pr(r)              p   rr
     *     / \                / \
     *    rl  rr             pl  rl
     * @param p
     */
    private void leftRotate(RBNode p){
        if (p!=null){
            RBNode r = p.right;
            p.right=r.left;
            if (r.left!=null){
                r.left.parent=p;
            }
            r.parent=p.parent;
            if (p.parent==null){
                root=r;
            }else if(p.parent.left==p){
                p.parent.left=r;
            }else{
                p.parent.right=r;
            }
            r.left=p;
            p.parent=r;
        }
    }

    /**
    * 围绕p右旋
     *        pf                    pf
     *         \                     \
     *          p                   (l)pl
     *         / \        =>      /  \
     *    (l)pl  pr              ll   p
     *    / \                        / \
     *   ll   lr                     lr  pr
    */
    private void rightRotate(RBNode p){
        if(p!=null){
            RBNode l = p.left;//取出p的左节点
            p.left=l.right;//把l节点的右节点挂到p的左边
            if (l.right!=null){//如果l节点的右节点不为null
                l.right.parent=p;//则把l节点的右节点的父亲指针指向p
            }
            l.parent=p.parent;//设置l节点的父节点为p的父节点，就是用l替换p的位置
            if (p.parent==null){//如果p的父亲为null,则p为root，此时因为替换要把l节点设置为root
                root=l;
            }else if(p.parent.right == p){//如果p为自己父节点的右节点
                p.parent.right=l;//则把l节点挂载到p的父节点的右边
            }else{
                p.parent.left=l;//否则把l节点挂载到p的父节点的左边
            }
            //处理l节点和p节点的关系
            l.right=p;//把p节点挂载到l的右边
            p.parent=l;//设置p的父节点为l节点

        }
    }

    private RBNode parentOf(RBNode node){
        return node!=null?node.parent:null;
    }

    private RBNode leftOf(RBNode node){
        return node!=null?node.left:null;
    }

    private RBNode rightOf(RBNode node){
        return node!=null?node.right:null;
    }

    private boolean colorOf(RBNode node){
        return node == null ? BLACK:node.color;
    }

    private void setColor(RBNode node,boolean color){
        if (node != null){
            node.color=color;
        }
    }

    public void put(K key,V value){
        RBNode  t=this.root;
        //如果t为null，说明是第一个节点，直接设置为root节点,并结束方法
        if (t==null){
                root = new RBNode<>(key, value == null ? key : value, null);
                return;
        }

        //定义一个父节点指针
        int cmp;
        RBNode parent;
        if (key == null){
            throw  new NullPointerException("传入的key为null...");
        }

        //先从根开始寻找新节点的插入位置，最终的位置一定是叶子节点
        do {
            //和t比较之后，会和t的子节点进行比较，所以，这里变量命名为parent，把t临时寄存给parent，待后面迭代使用
            parent=t;//t一开始赋值了root，所以，这样就可以从root开始寻找插入位置
            cmp=key.compareTo((K) t.key);

            //小于零，从左子树寻找
            if (cmp<0){
                t=t.left;
            }else if(cmp>0){//小于零，从右子树寻找
                t=t.right;
            }else {//如果相等，则替换节点的value值，这个else可有可无，这样就限制了，此红黑树不能有重复节点的存在
                t.setValue(value==null?key:value);
                return;//找到相同节点后，也进行退出操作，结束方法
            }

        }while (t!=null);

        //循环结束后，即找到了新增节点的位置，把新增节点放到该位置即可
        RBNode<K, Object> newNode = new RBNode<>(key, value == null ? key : value, parent);
        //根据前面的比较结果，确定新节点是在左边还是右边
        if (cmp<0){//小于0放左边
            parent.left=newNode;
        }else{//大于0放右边，这里不会有等于0的情况，因为while里面等于0时，就退出方法了
            parent.right=newNode;
        }

        //调整节点位置和节点颜色
        fixAfterPut(newNode);
    }

    /**
     * 1、2-3-4树：新增元素+2节点合并（节点中只有一个元素）=3节点（节点中有两个元素）
     *      红黑树：新增一个红色节点+黑色的父亲节点=上黑下红（2节点）---------无需调整
     *
     * 2、2-3-4树：新增元素+3节点合并（节点中有两个元素）=4节点（节点中有三个元素）
     *            有4种情况（左3，右3，还有2个左中右不需要调整）------左3，右3需要调整，其余2个不需要调整
     *            这里的左3和右3最好理解成，父亲节点为爷爷节点的左节点或右节点，此时又各自对应2种情况
     *            这里的调整，包含调整变色和位置
     *       红黑树：新增红色节点+上黑下红=排序后中间节点是黑色，两边节点都是红色（3节点）
     *
     * 3、2-3-4树：新增一个元素+4节点合并=原来的4节点分裂，中间元素升级为父亲节点，新增元素与剩下的其中一个合并
     *       红黑树：新增红色节点+黑色爷爷节点、红色父亲节点、红色叔叔节点=爷爷节点变红色，父亲节点和叔叔节点变黑色
     *                      如果爷爷节点是根节点，则爷爷节点再变黑色
     *                      这里的调整，只调整了颜色
     *
     * 新增节点的父节点为黑色节点的话，则不需要进行任何调整
     *
     * @param x
     */
    private void fixAfterPut(RBNode x) {
        x.color= RED;//设置新节点颜色为红色
        //x不是根节点，x的父节点为黑色节点时候，是不需要进行调整的，其他情况进入if，进行调整
        while (x!=null&&x!=root&&x.parent.color==RED){
            //1、x的父节点是爷爷的左孩子
            if (parentOf(x)==leftOf(parentOf(parentOf(x)))){
                //取出叔叔节点
                RBNode y = rightOf(parentOf(parentOf(x)));
                //此时，考虑叔叔是否存在，存在则对应第3种情况，则进行颜色调整
                if (colorOf(y)==RED){//这里如果为RED，则叔叔节点是存在的
                    setColor(parentOf(x),BLACK);//设置x的父节点为黑色
                    setColor(y,BLACK);//设置x的叔叔节点为黑色
                    setColor(parentOf(parentOf(x)),RED);//设置x的爷爷节点为红色
                    //此时4节点新增了一个元素，需要裂变出一个元素
                    // 上升到上一层，与上一层节点进行合并，于是用递归进行循环处理
                    x=parentOf(parentOf(x));
                }else {//对应第2种情况的左3，则进行调整节点的颜色和位置
                    //如果此时x挂在父节点的右边，则需要进行左旋
                    //不进行左旋，会导致后面的右旋黑色节点失衡，从而失去红黑树的性质
                    if (x == rightOf(parentOf(x))){
                        //把4节点变成标准左3状态，然后，进行处理
                            x=parentOf(x);//把x指向父亲节点
                            leftRotate(x);//绕着x进行右旋，此时，x又变成了叶子节点，供后面使用
                    }

                    //新增节点再父节点的左边
                    setColor(parentOf(x),BLACK);//设置x的父节点为黑色
                    setColor(parentOf(parentOf(x)),RED);//设置x的爷爷节点为红色

                    rightRotate(parentOf(parentOf(x)));//绕着x的爷爷节点进行右旋转
                }
            }else{//2、x的父节点是爷爷的右孩子
                //取出叔叔节点，并判断叔叔节点是否存在
                RBNode y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED){//如果叔叔节点为红色，说明叔叔节点存在，则进行处理第3种情况，进行变色处理即可
                        setColor(parentOf(x),BLACK);//设置父亲节点为黑色
                        setColor(y,BLACK);//设置叔叔节点为黑色
                        setColor(parentOf(parentOf(x)),RED);//设置爷爷节点为红色
                    //此时4节点新增了一个元素，需要裂变出一个元素
                    // 上升到上一层，与上一层节点进行合并，也就是说上一层中新增了一个节点，于是用递归进行循环处理
                    //也只有裂变的时候，裂变出来的节点为红色，才可能与上一层节点产生连续红色节点的错误
                    //所以递归循环要在这里进行
                    x=parentOf(parentOf(x));
                }else{//叔叔节点不存在，则是第2种情况，右3情况
                        if(x == leftOf(parentOf(x))){//如果x是父亲节点的左孩子
                            //对此时的4节点进行处理，旋转成标准的右3状态
                            x=parentOf(x);//将x指向x的父亲节点
                            rightRotate(x);//对x进行右旋转
                        }

                        //先变色处理
                        setColor(parentOf(x),BLACK);
                        setColor(parentOf(parentOf(x)),RED);
                        leftRotate(parentOf(parentOf(x)));

                }
            }
        }
        //设置根节点颜色为黑色
        root.color=BLACK;
    }

    /**
     *  查找指定节点的前驱节点，即小于node节点的最大值
     * @param node
     * @return
     */
    private RBNode predecessor(RBNode node){
        if (node == null){
            return null;
        }
        else if(node.left!=null){
            RBNode p=node.left;
            while (p.right!=null){
                p=p.right;
            }
            return p;
        }else{
            RBNode p=node.parent;
            RBNode ch = node;
            while (p!=null&&ch == p.left){
                p=p.parent;
                ch=p;
            }
            return p;
        }
    }

    /**
     * 查找指定节点的后继节点
     * @param node
     * @return
     */
    private RBNode successor(RBNode node){
        if (node == null){
            return null;
        }
        else if (node.right !=null){
            //从当前节点的右子树中，沿着左边找到对应的后继节点
            RBNode p = node.left;
            while (p.left!=null){
                p=p.left;
            }
            return p;
        }else {
            //这一块是进不来的，但是，逻辑写一下
            //当前节点没有右子树，所以，要向节点的上面寻找后继节点
            //在第一次父子方向关系发生变化的时候，即是后继节点
            RBNode p=node.parent;
            RBNode ch=node;
            while (p!=null&&ch == p.right){
                p=p.parent;
                ch=p;
            }
            return p;
        }
    }

    public V remove(K key){
        //根据key获取对应的节点
        RBNode node =getNode(key);
        if (node == null){
            return null;
        }
        V oldValue = (V) node.value;
        deleteNode(node);
        return  oldValue;
    }

    /**
     *  删除操作：
     *  1、删除叶子节点，直接删除
     *  2、删除的节点有一个子节点，那么用子节点来代替
     *  3、如果删除的节点有2个子节点，此时需要找到前驱节点或者后继节点来代替
     * @param node
     */
    private void deleteNode(RBNode node) {
        //3、node节点有2个孩子节点
        if(node.left!=null && node.right!=null){
            //找到要是删除节点的后继节点
            RBNode successor = successor(node);
            //把后继节点的key，value都赋值给node
            node.key=successor.key;
            node.value=successor.value;
            //并把node引用指向后继节点
            node=successor;
        }

        //此时node只有一个孩子，或者没有孩子
        RBNode replacement=node.left !=null?node.left:node.right;

        //2、replacement 替代节点不为null，则是有一个孩子的情况
        if (replacement != null){
            //把replacement替代节点挂载到node的父节点下
            replacement.parent=node.parent;
            //让node的父节点的儿子指针指向replacement替代节点
            if (node.parent == null){//要删除的节点是根节点的情况，直接用替代节点替换根节点
                root = replacement;
            }else if (node == node.parent.left){
                node.parent.left=replacement;
            }else {
                node.parent.right=replacement;
            }

            //node脱离树
            node.left=node.right=node.parent=null;

            //替换结束后，进行节点位置和颜色的调整
            if (node.color == BLACK){
                //以替代节点为基础，进行调整
                //这个时候，要删除的node是黑色，它的替代节点肯定是红色
                // 所以，此时的调整就是给替代节点变色，这样，就黑色平衡了
                fixAfterRemove(replacement);
            }
        }else if(node.parent == null){//删除的节点就是根节点，但是，替代节点又不存在的情况
            root=null;
        }else{//1、删除的节点不是root节点，而且 replacement 替代节点为null的情况（即是叶子节点）
            //因为replacement节点不存在，所以要先调整后删除，并以node节点进行调整
            if (node.color == BLACK){
                //删除的是黑色叶子节点，不可以直接删除，所以要先调整下树结构，调整到可以删除的时候，才进行删除操作
                //另外，234树的二节点叶子节点，肯定是黑色
                //黑色叶子节点是不能直接删除的，要问兄弟节点借等一系列调整之后，才能进行删除操作
                //这就是，为什么这里，要先调整后删除的原因
                fixAfterRemove(node);
            }

            //删除操作
            if (node == node.parent.left){//删除左叶子
                node.parent.left=null;
            }else if(node == node.parent.right){//删除右叶子
                node.parent.right=null;
            }

            node.parent=null;
        }
    }

    /**
     *  调整红黑树
     * @param x 这里传入的x节点有两种可能
     *                              1、有替代节点的情况，传入的一定是红色的替代节点，对应删除的第二种情况
     *                              2、没有替代节点时，传入的一定是待删除的黑色叶子节点，对应删除的第一种情况
     */
    private void fixAfterRemove(RBNode x) {
        //传入的是待删除的黑色叶子节点，且不为root节点,需要问兄弟节点借
        while (x != root && x.color == BLACK){
            if (x == x.parent.left){//处理x是左孩子的情况
                //获取x的兄弟节点，因为此时x是左孩子，所以，兄弟节点一定是右孩子
                RBNode rnode = rightOf(parentOf(x));
                //如果此时兄弟节点是红色节点，则要进行旋转调整，找到红黑树中真正可用的兄弟节点
                if (colorOf(rnode)==RED){
                    //把兄弟节点染黑
                    setColor(rnode,BLACK);
                    //把父节点染红
                    setColor(parentOf(x),RED);
                    //进行左旋转
                    leftRotate(parentOf(x));
                    //给兄弟节点重新赋值,找到真正可用的兄弟节点
                    rnode=rightOf(parentOf(x));
                }
                //兄弟节点没有的借，即兄弟节点没有孩子节点
                //因为上面找到了真正可用的黑色兄弟节点，所以，这个兄弟节点的还在一定是红色节点，可用这种方式判空
                if (colorOf(leftOf(rnode))==BLACK&&colorOf(rightOf(rnode))==BLACK){
                    //兄弟没有的借，则直接把兄弟节点变红色，减少一个黑色节点，这样，保持黑色平衡
                    setColor(rnode,RED);
                    //把x往上层进行递归
                    x=parentOf(x);
                }
                //兄弟节点有的借
                else{
                    //有的借，分2中小情况，兄弟节点是3节点或者兄弟节点是4节点
                    //当右孩子为空的时候，左孩子一定不为空，要额外做一次右旋转，把右孩子变成不为空
                    if(colorOf(rightOf(rnode))==BLACK){
                        setColor(leftOf(rnode),BLACK);
                        setColor(rnode,RED);
                        rightRotate(rnode);
                        rnode = rightOf(parentOf(x));
                    }
                    //右孩子不为空的时候，直接一次左旋转即可，不用考虑左孩子是否存在
                    //这个时候，有两种情况，左孩子不为空，则是4节点，左孩子为空则是3节点
                    //但是，两种情况都用同一种方式左旋处理即可
                    setColor(rnode,colorOf(parentOf(x)));
                    setColor(parentOf(x),BLACK);
                    setColor(rightOf(rnode),BLACK);
                    leftRotate(parentOf(x));
                    x=root;//用于终止循环
                }
            }else{//处理x是右孩子的情况
                //获取兄弟节点，因为此时x是右孩子，所以，兄弟节点一定是左孩子
                RBNode lnode = leftOf(parentOf(x));
                //如果兄弟节点是红色的，此时要进行旋转调整，找到真正可用的兄弟节点
                if (colorOf(lnode)==RED){
                    setColor(lnode,BLACK);
                    setColor(parentOf(x),RED);
                    rightRotate(parentOf(x));
                    lnode=leftOf(parentOf(x));
                }

                //兄弟节点没有的借
                if (colorOf(leftOf(lnode))==BLACK&&colorOf(rightOf(lnode))==BLACK){
                    setColor(lnode,RED);
                    x=parentOf(x);
                }
                //兄弟节点有的借
                else{
                    //分为2种小情况，兄弟节点是234树中的3节点或者4节点
                    //如果兄弟节点的左孩子为空，则右孩子一定不为空，则额外进行一次左旋转，把左孩子变成不为空
                    if (colorOf(leftOf(lnode))==BLACK){
                        setColor(lnode,RED);
                        setColor(rightOf(lnode),BLACK);
                        leftRotate(lnode);
                        lnode=leftOf(parentOf(x));
                    }
                    setColor(lnode,colorOf(parentOf(x)));
                    setColor(parentOf(x),BLACK);
                    setColor(leftOf(lnode),BLACK);
                    rightRotate(parentOf(x));
                    x=root;
                }
            }
        }
        //传入的是红色的替代节点，则直接染黑
        // 如果是待删除的黑色叶子节点，则会向上递归x节点来处理兄弟没的借的情况
        setColor(x,BLACK);
    }

    private RBNode getNode(K key) {
        //从root根节点开始查找
        RBNode node = this.root;
        int cmp=0;
        while (node != null){
            //这个泛型的值比较，要注意下
            cmp=key.compareTo((K) node.key);
            if (cmp<0){
                node = node.left;
            }else if (cmp>0){
                node = node.right;
            }else{
                return node;
            }
        }
        return null;
    }

    static class RBNode<K extends Comparable<K>,V>{
        private RBNode parent;
        private RBNode left;
        private RBNode right;

        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode( K key, V value,RBNode parent) {
            this.color=BLACK;
            this.parent = parent;
            this.key = key;
            this.value = value;
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


}
