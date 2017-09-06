<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>飞机论证参照模块</title>

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="http://localhost:8080/algz/ras/common/css/bootstrap.css" />
<link rel="stylesheet" href="http://localhost:8080/algz/ras/common/css/font-awesome.css" />
		<link rel="stylesheet" href="http://localhost:8080/algz/ras/common/css/bootstrap-tag.css" />	

<script src='http://localhost:8080/algz/ras/common/js/jquery.js'></script>
		<script src="//cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap.js"></script>

		<script src="bootstrap-tag-min.js"></script>

	</head>

	<body >

<!-- 标签属性 -->


<div class="main-content">
	<div class="main-content-inner">
		<div class="page-content">

<!-- 标签属性 -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		
			
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-sm-12">
					<!-- #section:elements.tab -->
					<div class="tabbable">
	
						<div class="tab-content">
						
							<div id="param" class="tab-pane fade in active">
								

    



<form id='modelParamForm' action="./savemodelparam" method="post">
	<input name="overviewID" type="hidden" value="null"/>
	<input name="basicID" type="hidden" value="null"/>
	
	<input type="text" name="tags" id='tags'>
	

	<div class="row">
		<div class="col-sm-offset-6">
			<div class="btn-group">
				<input type="submit" id="addModelparam" class="btn btn-primary" value="保存"/>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	$(function(){
		$('#tags').tag(
				  {
					//placeholder:tag_input.attr('placeholder'),
					//enable typeahead by specifying the source array
					source: ['US_STATES','us_a','us_aa','us_aaa']//defined in ace.js >> ace.enable_search_ahead
				  });
		
	})
	</script>
</form>

							</div>
						</div>
					</div>
	
					<!-- /section:elements.tab -->
				</div><!-- /.col -->	
			</div>
			<p>
			<!-- PAGE CONTENT ENDS -->
		
			
		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->

		</div><!-- /.page-content -->
		</div>
</div><!-- /.main-content -->
		
	
			

	</body>
</html>
