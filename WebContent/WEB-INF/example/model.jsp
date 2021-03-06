模态框 modal.js

模态框经过了优化，更加灵活，以弹出对话框的形式出现，具有最小和最实用的功能集。

不支持同时打开多个模态框
千万不要在一个模态框上重叠另一个模态框。要想同时支持多个模态框，需要自己写额外的代码来实现。
模态框的 HTML 代码放置的位置
务必将模态框的 HTML 代码放在文档的最高层级内（也就是说，尽量作为 body 标签的直接子元素），以避免其他组件影响模态框的展现和/或功能。
对于移动设备的附加说明
这里提供了在移动设备上使用模态框有一些附加说明。请参考浏览器支持章节。
Due to how HTML5 defines its semantics, the autofocus HTML attribute has no effect in Bootstrap modals. To achieve the same effect, use some custom JavaScript:

Copy
$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').focus()
})
实例
静态实例
以下模态框包含了模态框的头、体和一组放置于底部的按钮。

×
Modal title
One fine body…

Close  Save changes
Copy
<div class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body&hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
动态实例
点击下面的按钮即可通过 JavaScript 启动一个模态框。此模态框将从上到下、逐渐浮现到页面前。

Launch demo modal
Copy
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
增强模态框的可访问性
务必为 .modal 添加 role="dialog" 和 aria-labelledby="..." 属性，用于指向模态框的标题栏；为 .modal-dialog 添加 aria-hidden="true" 属性。

另外，你还应该通过 aria-describedby 属性为模态框 .modal 添加描述性信息。
嵌入 YouTube 视频（天朝无用）
在模态框中嵌入 YouTube 视频需要增加一些额外的 JavaScript 代码，用于自动停止重放等功能，这些代码并没有在 Bootstrap 中提供。请参考这份发布在 Stack Overflow 上的文章。
可选尺寸
模态框提供了两个可选尺寸，通过为 .modal-dialog 增加一个样式调整类实现。

大模态框  小模态框
Copy
<!-- Large modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Large modal</button>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      ...
    </div>
  </div>
</div>

<!-- Small modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Small modal</button>

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      ...
    </div>
  </div>
</div>
禁止动画效果
如果你不需要模态框弹出时的动画效果（淡入淡出效果），删掉 .fade 类即可。

Copy
<div class="modal" tabindex="-1" role="dialog" aria-labelledby="...">
  ...
</div>
Using the grid system
To take advantage of the Bootstrap grid system within a modal, just nest .rows within the .modal-body and then use the normal grid system classes.

Launch demo modal
Copy
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-4">.col-md-4</div>
          <div class="col-md-4 col-md-offset-4">.col-md-4 .col-md-offset-4</div>
        </div>
        <div class="row">
          <div class="col-md-3 col-md-offset-3">.col-md-3 .col-md-offset-3</div>
          <div class="col-md-2 col-md-offset-4">.col-md-2 .col-md-offset-4</div>
        </div>
        <div class="row">
          <div class="col-md-6 col-md-offset-3">.col-md-6 .col-md-offset-3</div>
        </div>
        <div class="row">
          <div class="col-sm-9">
            Level 1: .col-sm-9
            <div class="row">
              <div class="col-xs-8 col-sm-6">
                Level 2: .col-xs-8 .col-sm-6
              </div>
              <div class="col-xs-4 col-sm-6">
                Level 2: .col-xs-4 .col-sm-6
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
Varying modal content based on trigger button
Have a bunch of buttons that all trigger the same modal, just with slightly different contents? Use event.relatedTarget and HTML data-* attributes (possibly via jQuery) to vary the contents of the modal depending on which button was clicked. See the Modal Events docs for details on relatedTarget,

Open modal for @mdo  Open modal for @fat  Open modal for @getbootstrap ...more buttons...
Copy
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Open modal for @mdo</button>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@fat">Open modal for @fat</button>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">Open modal for @getbootstrap</button>
...more buttons...

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">New message</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">Recipient:</label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Message:</label>
            <textarea class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Send message</button>
      </div>
    </div>
  </div>
</div>
Copy
$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var recipient = button.data('whatever') // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
  modal.find('.modal-title').text('New message to ' + recipient)
  modal.find('.modal-body input').val(recipient)
})
用法
通过 data 属性或 JavaScript 调用模态框插件，可以根据需要动态展示隐藏的内容。模态框弹出时还会为 <body> 元素添加 .modal-open 类，从而覆盖页面默认的滚动行为，并且还会自动生成一个 .modal-backdrop 元素用于提供一个可点击的区域，点击此区域就即可关闭模态框。

通过 data 属性
不需写 JavaScript 代码也可激活模态框。通过在一个起控制器作用的元素（例如：按钮）上添加 data-toggle="modal" 属性，或者 data-target="#foo" 属性，再或者 href="#foo" 属性，用于指向被控制的模态框。

Copy
<button type="button" data-toggle="modal" data-target="#myModal">Launch modal</button>
通过 JavaScript 调用
只需一行 JavaScript 代码，即可通过元素的 id myModal 调用模态框：

Copy
$('#myModal').modal(options)
参数
可以将选项通过 data 属性或 JavaScript 代码传递。对于 data 属性，需要将参数名称放到 data- 之后，例如 data-backdrop=""。

名称	类型	默认值	描述
backdrop	boolean 或 字符串 'static'	true	Includes a modal-backdrop element. Alternatively, specify static for a backdrop which doesn't close the modal on click.
keyboard	boolean	true	键盘上的 esc 键被按下时关闭模态框。
show	boolean	true	模态框初始化之后就立即显示出来。
remote	path	false	
This option is deprecated since v3.3.0 and has been removed in v4. We recommend instead using client-side templating or a data binding framework, or calling jQuery.load yourself.

如果提供的是 URL，将利用 jQuery 的 load 方法从此 URL 地址加载要展示的内容（只加载一次）并插入 .modal-content 内。如果使用的是 data 属性 API，还可以利用 href 属性指定内容来源地址。下面是一个实例：

Copy
<a data-toggle="modal" href="remote.html" data-target="#modal">Click me</a>
方法
.modal(options)

将页面中的某块内容作为模态框激活。接受可选参数 object。

Copy
$('#myModal').modal({
  keyboard: false
})
.modal('toggle')

手动打开或关闭模态框。在模态框显示或隐藏之前返回到主调函数中（也就是，在触发 shown.bs.modal 或 hidden.bs.modal 事件之前）。

Copy
$('#myModal').modal('toggle')
.modal('show')

手动打开模态框。在模态框显示之前返回到主调函数中 （也就是，在触发 shown.bs.modal 事件之前）。

Copy
$('#myModal').modal('show')
.modal('hide')

手动隐藏模态框。在模态框隐藏之前返回到主调函数中 （也就是，在触发 hidden.bs.modal 事件之前）。

Copy
$('#myModal').modal('hide')
.modal('handleUpdate')

Readjusts the modal's positioning to counter a scrollbar in case one should appear, which would make the modal jump to the left.

Only needed when the height of the modal changes while it is open.

Copy
$('#myModal').modal('handleUpdate')
事件
Bootstrap 的模态框类提供了一些事件用于监听并执行你自己的代码。

All modal events are fired at the modal itself (i.e. at the <div class="modal">).

事件类型	描述
show.bs.modal	show 方法调用之后立即触发该事件。如果是通过点击某个作为触发器的元素，则此元素可以通过事件的 relatedTarget 属性进行访问。
shown.bs.modal	此事件在模态框已经显示出来（并且同时在 CSS 过渡效果完成）之后被触发。如果是通过点击某个作为触发器的元素，则此元素可以通过事件的 relatedTarget 属性进行访问。
hide.bs.modal	hide 方法调用之后立即触发该事件。
hidden.bs.modal	此事件在模态框被隐藏（并且同时在 CSS 过渡效果完成）之后被触发。
loaded.bs.modal	从远端的数据源加载完数据之后触发该事件。
Copy
$('#myModal').on('hidden.bs.modal', function (e) {
  // do something...
})