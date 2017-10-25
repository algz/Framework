
<link rel="stylesheet" href="http://localhost:8080/algz/ras/common/css/bootstrap-tag.css" />	

<script src="bootstrap-tag-min.js"></script>



<div class="form-group">
	<label class="col-sm-3 control-label no-padding-right" for="form-field-tags">Tag input</label>
	<div class="col-sm-9">
		<!-- #section:plugins/input.tag-input -->
		<div class="inline">
			<input type="text" name="tags" id="form-field-tags" value="Tag Input Control" placeholder="Enter tags ..." />
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		
		var tag_input = $('#form-field-tags');
		try{
			tag_input.tag(
			  {
				placeholder:tag_input.attr('placeholder'),
				//enable typeahead by specifying the source array
				source: ace.vars['US_STATES'],//defined in ace.js >> ace.enable_search_ahead
				/**
				//or fetch data from database, fetch those that match "query"
				source: function(query, process) {
				  $.ajax({url: 'remote_source.php?q='+encodeURIComponent(query)})
				  .done(function(result_items){
					process(result_items);
				  });
				}
				*/
			  }
			)
	
			//programmatically add a new
			var $tag_obj = $('#form-field-tags').data('tag');
			$tag_obj.add('Programmatically Added');
		}
		catch(e) {
			//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
			tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
			//$('#form-field-tags').autosize({append: "\n"});
		}
		
	})
	</script>
    



<form id='modelParamForm' action="./savemodelparam" method="post">
	
	<input type="text" name="tags" id='tags'>
	
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
