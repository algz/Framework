

/**
 * 
 * html:
 * <div id="tree"></div>
 * 
 * 			<!-- bootstrap-treeview.js -->
			<script src="<%=basePath%>ras/common/js/treeview/bootstrap-treeview.js"></script>
 * 
 * @return {}
 */

function getTree() {
var tree = [
  {
    text: "Parent 1",
    nodes: [
      {
        text: "Child 1",
        nodes: [
          {
            text: "Grandchild 1"
          },
          {
            text: "Grandchild 2"
          }
        ]
      },
      {
        text: "Child 2"
      }
    ]
  },
  {
    text: "Parent 2"
  }
];
  return tree;
}

$('#tree').treeview({data: getTree()});

//1.选择结点
//getSelected()
//Returns an array of selected nodes e.g. state.selected = true.
var nodes=$('#tree').treeview('getSelected');

//2.删除结点
//removeNode()
//Removes given nodes from the tree.
$('#tree').treeview('removeNode', [ nodes, { silent: true } ]);

//3.修改结点
//var node={
//	productID:data.productID,
//	isLeaf:'0',
//	text: productName
//} //创建一个结点对象.
var node=$.extend({},nodes[0]); //复制对象.另:$.extend(true,{},nodes[0]):深度复制,从顶层开始.
node.$el=null;//必须删除$el属性,否则函数仍认为同一节点,无法修改.
node.text=productName;
$("#tree").treeview("updateNode", [nodes,node ]);

//4.获得所有结点
//this._orderedNodes
var nodes=$('#tree').treeview('getNodes');

//5.获取所有checked选中的值
var nodes=$('#tree').treeview('getChecked');