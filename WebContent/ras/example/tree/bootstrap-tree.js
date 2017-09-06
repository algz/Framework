

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
