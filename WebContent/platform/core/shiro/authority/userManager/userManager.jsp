<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  
<head>
    <title>
      数据表格_大气漂亮的Bootstrap后台管理系统模板Se7en - JS代码网
    </title>
    <!--<link href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700" media="all" rel="stylesheet" type="text/css" />-->
	<link href="stylesheets/bootstrap.min.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/font-awesome.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/se7en-font.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/isotope.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/jquery.fancybox.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/fullcalendar.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/wizard.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/select2.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/morris.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/datatables.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/datepicker.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/timepicker.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/colorpicker.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/bootstrap-switch.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/daterange-picker.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/typeahead.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/summernote.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/pygments.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/style.css" media="all" rel="stylesheet" type="text/css" /><link href="stylesheets/color/green.css" media="all" rel="alternate stylesheet" title="green-theme" type="text/css" /><link href="stylesheets/color/orange.css" media="all" rel="alternate stylesheet" title="orange-theme" type="text/css" /><link href="stylesheets/color/magenta.css" media="all" rel="alternate stylesheet" title="magenta-theme" type="text/css" /><link href="stylesheets/color/gray.css" media="all" rel="alternate stylesheet" title="gray-theme" type="text/css" />
	<script src="javascripts/jquery.min.js" type="text/javascript"></script>
	<script src="javascripts/jquery-ui.js" type="text/javascript"></script>
	<script src="javascripts/bootstrap.min.js" type="text/javascript"></script><script src="javascripts/raphael.min.js" type="text/javascript"></script><script src="javascripts/selectivizr-min.js" type="text/javascript"></script><script src="javascripts/jquery.mousewheel.js" type="text/javascript"></script><script src="javascripts/jquery.vmap.min.js" type="text/javascript"></script><script src="javascripts/jquery.vmap.sampledata.js" type="text/javascript"></script><script src="javascripts/jquery.vmap.world.js" type="text/javascript"></script><script src="javascripts/jquery.bootstrap.wizard.js" type="text/javascript"></script><script src="javascripts/fullcalendar.min.js" type="text/javascript"></script><script src="javascripts/gcal.js" type="text/javascript"></script><script src="javascripts/jquery.dataTables.min.js" type="text/javascript"></script><script src="javascripts/datatable-editable.js" type="text/javascript"></script><script src="javascripts/jquery.easy-pie-chart.js" type="text/javascript"></script><script src="javascripts/excanvas.min.js" type="text/javascript"></script><script src="javascripts/jquery.isotope.min.js" type="text/javascript"></script><script src="javascripts/isotope_extras.js" type="text/javascript"></script><script src="javascripts/modernizr.custom.js" type="text/javascript"></script><script src="javascripts/jquery.fancybox.pack.js" type="text/javascript"></script><script src="javascripts/select2.js" type="text/javascript"></script><script src="javascripts/styleswitcher.js" type="text/javascript"></script><script src="javascripts/wysiwyg.js" type="text/javascript"></script><script src="javascripts/summernote.min.js" type="text/javascript"></script><script src="javascripts/jquery.inputmask.min.js" type="text/javascript"></script><script src="javascripts/jquery.validate.js" type="text/javascript"></script><script src="javascripts/bootstrap-fileupload.js" type="text/javascript"></script><script src="javascripts/bootstrap-datepicker.js" type="text/javascript"></script><script src="javascripts/bootstrap-timepicker.js" type="text/javascript"></script><script src="javascripts/bootstrap-colorpicker.js" type="text/javascript"></script><script src="javascripts/bootstrap-switch.min.js" type="text/javascript"></script><script src="javascripts/typeahead.js" type="text/javascript"></script><script src="javascripts/daterange-picker.js" type="text/javascript"></script><script src="javascripts/date.js" type="text/javascript"></script><script src="javascripts/morris.min.js" type="text/javascript"></script><script src="javascripts/skycons.js" type="text/javascript"></script><script src="javascripts/fitvids.js" type="text/javascript"></script><script src="javascripts/jquery.sparkline.min.js" type="text/javascript"></script><script src="javascripts/main.js" type="text/javascript"></script><script src="javascripts/respond.js" type="text/javascript"></script>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
  </head>
  <body>
    <div class="modal-shiftfix">

      <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            用户管理
          </h1>
        </div>
        <!-- DataTables Example -->
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-table"></i>表格标题
              </div>
              <div class="widget-content padded clearfix">
              
              <button class="btn btn-xs btn-primary-outline">增加</button>
              <button class="btn btn-xs btn-primary-outline">修改</button>
              <button id='user-del' class="btn btn-xs btn-primary-outline">删除</button>
                <table class="table table-bordered table-striped" id="dataTable1">
                  <thead>
                    <th class="check-header hidden-xs">
                      <label><input id="checkAll" name="checkAll" type="checkbox"><span></span></label>
                    </th>
                    <th>
                      First Name
                    </th>
                    <th>
                      Last Name
                    </th>
                    <th class="hidden-xs">
                      Email
                    </th>
                    <th class="hidden-xs">
                      Date Added
                    </th>
                    <th class="hidden-xs">
                      Status
                    </th>
                    <th></th>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Robert
                      </td>
                      <td>
                        Kelso
                      </td>
                      <td class="hidden-xs">
                        robert@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">Approved</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        John
                      </td>
                      <td>
                        Dorian
                      </td>
                      <td class="hidden-xs">
                        john@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-warning">Pending</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Olivia
                      </td>
                      <td>
                        Benson
                      </td>
                      <td class="hidden-xs">
                        olivia@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">Approved</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Elliot
                      </td>
                      <td>
                        Stabler
                      </td>
                      <td class="hidden-xs">
                        elliot@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">Approved</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Maggie
                      </td>
                      <td>
                        Smith
                      </td>
                      <td class="hidden-xs">
                        maggie@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-warning">Pending</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Sara
                      </td>
                      <td>
                        Johnson
                      </td>
                      <td class="hidden-xs">
                        sara@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-danger">Rejected</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Michael
                      </td>
                      <td>
                        Gold
                      </td>
                      <td class="hidden-xs">
                        michael@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">Approved</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Rita
                      </td>
                      <td>
                        Johnson
                      </td>
                      <td class="hidden-xs">
                        rita@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">Approved</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Dexter
                      </td>
                      <td>
                        Morgan
                      </td>
                      <td class="hidden-xs">
                        dexter@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">Approved</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Ben
                      </td>
                      <td>
                        Miller
                      </td>
                      <td class="hidden-xs">
                        ben@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-warning">Pending</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Barbara
                      </td>
                      <td>
                        Fisk
                      </td>
                      <td class="hidden-xs">
                        barbara@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-warning">Pending</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Jack
                      </td>
                      <td>
                        McCoy
                      </td>
                      <td class="hidden-xs">
                        jack@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-danger">Rejected</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Karen
                      </td>
                      <td>
                        Fuller
                      </td>
                      <td class="hidden-xs">
                        karen@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">Approved</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        Denise
                      </td>
                      <td>
                        Compton
                      </td>
                      <td class="hidden-xs">
                        denise@gmail.com
                      </td>
                      <td class="hidden-xs">
                        8-15-2013
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">Approved</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#"><i class="icon-eye-open"></i></a><a class="table-actions" href="#"><i class="icon-pencil"></i></a><a class="table-actions" href="#"><i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- end DataTables Example -->
      </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $("#user-del").click(function() { 
            $.ajax( { 
                type : "POST", 
                url : "add", 
                data : "username=zhangsan&age=20", 
                dataType: "text", 
                success : function(msg) { 
                    alert(msg); 
                } 
            }); 
        });
    })
    </script>
  </body>

</html>